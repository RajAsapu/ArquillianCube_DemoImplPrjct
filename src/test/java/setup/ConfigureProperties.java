package setup;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DockerClientBuilder;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConfigureProperties {

    public static Properties props;
    private static Logger log = LoggerFactory.getLogger(ConfigureProperties.class);
    private static boolean dockerEnvBroken = false;
    PrintWriter out;
    File hostConfigFile;
    private DockerClient dockerClient;

    public ConfigureProperties() {
        props = new Properties();
    }

    /*
     * Method to retrieve pricing properties
     */
    public static String getPricingProperty(String propertyName) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pricing");
        return resourceBundle.getString(propertyName);
    }

    /*
     *  Method to retrieve gradle properties
     */
    public static String getGradleProperty(String propertyName) {
        try {
            InputStream inputStream = new FileInputStream("gradle.properties");
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props.getProperty(propertyName);
    }

    public static boolean getDockerEnvStatus() {
        return dockerEnvBroken;
    }

    /*
     * Container urls are saved in the pricing properties file
     */
    public void updatePricingProperties() {
        try {
            OutputStream outputStream = new FileOutputStream("pricing.properties");

            props.setProperty("pricing.ui", getGradleProperty("uiDnsWithPort"));
            props.setProperty("pricing.datamock", getGradleProperty("datamockDnsWithPort"));
            props.setProperty("pricing.engine", getGradleProperty("engineDnsWithPort"));
            props.setProperty("pricing.service", getGradleProperty("serviceDnsWithPort"));

            props.store(outputStream, "#Pricing Application - Container properties#");
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    /*
     * Method to update the hostConfig file in the ui container
     */
    public void updateHostConfig() {
        JsonNode root;
        String resultUpdate = null;
        FileWriter fwrite;
        ObjectMapper mapper = new ObjectMapper();
        /*
         * Updating the hostConfig file
         */
        try {
            if (System.getenv("ENV").equals("Docker")) {
                hostConfigFile = new File("./hostConfig.json");
                if (!hostConfigFile.exists()) {
                    hostConfigFile.createNewFile();
                    fwrite = new FileWriter(hostConfigFile);
                    fwrite.write("{ }");
                    fwrite.close();
                }
                root = mapper.readTree(hostConfigFile);
                ((ObjectNode) root).put("priceEngineServiceUrl", getGradleProperty("engineDnsWithPort"));
                ((ObjectNode) root).put("masterDataServiceUrl", getGradleProperty("datamockDnsWithPort"));
                ((ObjectNode) root).put("priceConfigServiceUrl", getGradleProperty("serviceDnsWithPort"));
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
            dockerClient.copyArchiveToContainerCmd(getContainerIdUsingName(getGradleProperty("uiContainerName")))
                    .withRemotePath("/usr/share/nginx/html/config")
                    .withHostResource(hostConfigFile.getAbsolutePath())
                    .withNoOverwriteDirNonDir(false).exec();

            log.info("hostConfig file is updated in the " + getGradleProperty("uiContainerName") + " container");
            log.info(resultUpdate);
        } catch (Exception exp) {
            Assert.fail("Check if environment is set to docker in application.properties!!" + exp.getMessage());
        }
    }

    public String getContainerIdUsingName(String containerName) {
        InspectContainerResponse containerInfo = dockerClient.inspectContainerCmd(containerName).exec();
        return containerInfo.getId();
    }

    /*
     * Method to verify if the containers are running
     */
    public void checkIfContainersAreRunning() {
        dockerClient = DockerClientBuilder.getInstance().build();
        List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();
        String activeContainers[] = {"/" + getGradleProperty("uiContainerName"),
                "/" + getGradleProperty("datamockContainerName"),
                "/" + getGradleProperty("engineContainerName"),
                "/" + getGradleProperty("databaseContainerName"),
                "/" + getGradleProperty("serviceContainerName")};
        for (Container temp : containers) {
            log.info("#--------------Running--------------#");
            log.info("Name:" + temp.getNames()[0] + ",Status:" + temp.getStatus());
            if (Arrays.asList(activeContainers).contains(temp.getNames()[0])) {
                if (!temp.getStatus().contains("Up")) {
                    dockerEnvBroken = true;
                    Assert.fail("Failed to start container:" + temp.getNames()[0]);
                }
            }
        }
    }
}
