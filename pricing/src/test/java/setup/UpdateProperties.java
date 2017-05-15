package setup;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.InternetProtocol;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.RestartPolicy;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.WaitContainerResultCallback;
import org.junit.Assert;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UpdateProperties {

    public Properties props = new Properties();
    File resourceFile;
    String resourceFilePath;
    PrintWriter out;
    FileReader read;
    File hostConfigFile;
    InputStream inputStream;
    private DockerClient dockerClient;

    public UpdateProperties() {

    }

    public void setProperty(Map<String, String> map) {
        try {
//            resourceFile = new File("pricing.properties");
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

    public void updateHostConfig() throws Exception {
        JsonNode root;
        String resultUpdate;
        FileWriter fwrite;
        ObjectMapper mapper = new ObjectMapper();
        /*
         * Updating the hostConfig file
         */
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
        /*
         * Copying the hostConfig.json file to the Container
         */
            dockerClient = DockerClientBuilder.getInstance().build();
            dockerClient.copyArchiveToContainerCmd(getContainerIdUsingName("ui")).withRemotePath("/usr/share/nginx/html/config").withHostResource(hostConfigFile.getAbsolutePath()).withNoOverwriteDirNonDir(false).exec();
            System.out.println("hostConfig file is updated in the ui container");
            System.out.println(resultUpdate);
        }
    }

    public String getContainerIdUsingName(String containerName) {
        InspectContainerResponse containerInfo = dockerClient.inspectContainerCmd(containerName).exec();
        return containerInfo.getId();
    }

    public boolean startServiceContainer(String dbIpAddress,String image)
    {
        try
        {
            dockerClient = DockerClientBuilder.getInstance().build();
            CreateContainerResponse container = dockerClient.createContainerCmd(image)
                    .withExposedPorts(new ExposedPort(8080, InternetProtocol.TCP))
                    .withPortBindings(PortBinding.parse("8080:8080"))
                    .withPublishAllPorts(true)
                    .withAttachStderr(true)
                    .withAttachStdin(true)
                    .withEnv("cassandra.contactpoints="+dbIpAddress)
                    .withName("service")
                    .withRestartPolicy(RestartPolicy.onFailureRestart(2))
                    .exec();

            dockerClient.startContainerCmd(container.getId()).exec();

            return true;
        }
        catch (Exception exp)
        {
            if(exp.getMessage().contains("already in use by container"))
            {
                return true;
            }
            else
            {
                Assert.fail("Service Container is not started"+exp.getMessage());
                return false;
            }
        }

    }
}
