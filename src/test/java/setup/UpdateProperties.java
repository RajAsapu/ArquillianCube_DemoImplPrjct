package setup;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.WaitContainerResultCallback;
import com.google.common.base.Verify;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UpdateProperties {

    protected static Logger log = null;
    public Properties props;
    File resourceFile;
    String resourceFilePath;
    PrintWriter out;
    FileReader read;
    File hostConfigFile;
    InputStream inputStream;
    private DockerClient dockerClient;

    public UpdateProperties() {
        props = new Properties();
        log = LoggerFactory.getLogger(UpdateProperties.class);
    }

    public void setProperty(Map<String, String> map) {
        try {
            URL pricingPath = UpdateProperties.class.getClassLoader().getResource("pricing.properties");
            out = new PrintWriter(pricingPath.getPath());
            out.println("#Pricing Application - Container Properties#");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                out.println(entry.getKey() + "=http://" + entry.getValue() + "/");
            }
            out.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public String getPricingProperty(String propertyName) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pricing");
        return resourceBundle.getString(propertyName);
    }

    public String getApplicationProperty(String propertyName) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        return resourceBundle.getString(propertyName);
    }

    public String getEnv() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        return resourceBundle.getString("env");
    }

    public void updateHostConfig() {
        JsonNode root;
        String resultUpdate = null;
        FileWriter fwrite;
        ObjectMapper mapper = new ObjectMapper();
        /*
         * Updating the hostConfig file
         */
        try {
            if (getEnv().equals("Docker")) {
                hostConfigFile = new File("./hostConfig.json");
                if (!hostConfigFile.exists()) {
                    hostConfigFile.createNewFile();
                    fwrite = new FileWriter(hostConfigFile);
                    fwrite.write("{ }");
                    fwrite.close();
                }
                root = mapper.readTree(hostConfigFile);
                ((ObjectNode) root).put("priceEngineServiceUrl", getPricingProperty("pricing.engine"));
                ((ObjectNode) root).put("masterDataServiceUrl", getPricingProperty("pricing.datamock"));
                ((ObjectNode) root).put("priceConfigServiceUrl", "http://localhost:8080/");
                resultUpdate = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
                fwrite = new FileWriter(hostConfigFile);
                fwrite.write(resultUpdate);
                fwrite.close();
            }
        } catch (IOException exp) {
            log.info("Error creating the hostConfig File" + exp.getMessage());
        }
        /*
         * Copying the hostConfig.json file to the Container
         */
        try {
            dockerClient = DockerClientBuilder.getInstance().build();
            dockerClient.copyArchiveToContainerCmd(getContainerIdUsingName("ui")).withRemotePath("/usr/share/nginx/html/config").withHostResource(hostConfigFile.getAbsolutePath()).withNoOverwriteDirNonDir(false).exec();
            log.info("hostConfig file is updated in the ui container");
            log.info(resultUpdate);
        } catch (Exception exp) {
            Assert.fail("Check if environment is set to docker in application.properties!!" + exp.getMessage());
        }
    }

    public String getContainerIdUsingName(String containerName) {
        InspectContainerResponse containerInfo = dockerClient.inspectContainerCmd(containerName).exec();
        return containerInfo.getId();
    }

    public boolean startServiceContainer(String dbIpAddress, String image) {
        try {
            Thread.sleep(5000);
            dockerClient = DockerClientBuilder.getInstance().build();
            CreateContainerResponse container = dockerClient.createContainerCmd(image)
                    .withExposedPorts(new ExposedPort(8080, InternetProtocol.TCP))
                    .withPortBindings(PortBinding.parse("8080:8080"))
                    .withPublishAllPorts(true)
                    .withAttachStderr(true)
                    .withAttachStdin(true)
                    .withEnv("cassandra.contactpoints=" + dbIpAddress)
                    .withName("service")
                    .withRestartPolicy(RestartPolicy.onFailureRestart(2))
                    .exec();

            dockerClient.startContainerCmd(container.getId()).exec();
            log.info("Started Service Container");
            /*
             * Display list of running conatiners
             */
            List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();
            assertThat(containers, notNullValue());
            log.info("Container List: {}", containers);

            return true;
        } catch (Exception exp) {
            if (exp.getMessage().contains("already in use by container")) {
                return true;
            } else {
                Assert.fail("Service Container is not started" + exp.getMessage());
                return false;
            }
        }

    }
}
