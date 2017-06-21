package setup;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.core.DockerClientBuilder;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

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
            out.println("#Pricing Application - Container AppProperties#");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                out.println(entry.getKey() + "=http://" + entry.getValue() + "/");
            }
            out.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
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
            if (AppProperties.getEnv().equals("Docker")) {
                hostConfigFile = new File("./hostConfig.json");
                if (!hostConfigFile.exists()) {
                    hostConfigFile.createNewFile();
                    fwrite = new FileWriter(hostConfigFile);
                    fwrite.write("{ }");
                    fwrite.close();
                }
                root = mapper.readTree(hostConfigFile);
                ((ObjectNode) root).put("priceEngineServiceUrl", AppProperties.getPricingProperty("pricing.engine"));
                ((ObjectNode) root).put("masterDataServiceUrl", AppProperties.getPricingProperty("pricing.datamock"));
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
