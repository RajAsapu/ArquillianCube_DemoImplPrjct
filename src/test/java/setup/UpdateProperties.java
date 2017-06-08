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

    private static Logger log = LoggerFactory.getLogger(UpdateProperties.class);
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
}
