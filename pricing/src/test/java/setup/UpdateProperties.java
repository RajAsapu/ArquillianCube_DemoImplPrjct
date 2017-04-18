package setup;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.core.DockerClientBuilder;
import java.io.*;
import java.util.Map;
import java.util.Properties;

public class UpdateProperties {

    public Properties props = new Properties();
    File resourceFile;
    String resourceFilePath;
    PrintWriter out;
    private DockerClient dockerClient;
    FileReader read;
    File hostConfigPath;

    public UpdateProperties() {
        resourceFile = new File(".//");
        resourceFilePath = resourceFile.getAbsolutePath().replace(".", "src/test/resources/pricing.properties");
        resourceFile = new File(resourceFilePath);
    }

    public void setProperty(Map<String,String> map) {
        try{
            out = new PrintWriter(resourceFile);
            out.println("#Pricing Application - Container Properties#");
        for(Map.Entry<String,String> entry:map.entrySet())
        {
            out.println(entry.getKey()+"=http://"+entry.getValue()+"/");
        }
            out.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public String getProperty(String propertyName) {
         /*
         * Loading the properties file
         */
        try{
            read=new FileReader(resourceFile);
            props.load(read);
            return props.getProperty(propertyName);
        }
        catch (Exception exp){
            exp.printStackTrace();
        }
        return null;
    }

    public void updateHostConfig()throws Exception
    {
        JsonNode root;
        String resultUpdate;
        FileWriter fwrite;
        ObjectMapper mapper = new ObjectMapper();
        /*
         * Updating the hostConfig file
         */
        hostConfigPath = new File("src/test/resources/hostConfig.json");
        root = mapper.readTree(hostConfigPath);
        ((ObjectNode)root).put("priceEngineServiceUrl",getProperty("pricing.engine"));
        ((ObjectNode)root).put("masterDataServiceUrl",getProperty("pricing.datamock"));
        resultUpdate = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        fwrite=new FileWriter(hostConfigPath);
        fwrite.write(resultUpdate);
        fwrite.close();
        /*
         * Copying the hostConfig.json file to the Container
         */
        dockerClient = DockerClientBuilder.getInstance().build();
        dockerClient.copyArchiveToContainerCmd(getContainerIdUsingName("ui")).withRemotePath("/usr/share/nginx/html/config").withHostResource(hostConfigPath.getAbsolutePath()).withNoOverwriteDirNonDir(false).exec();
        System.out.println("Copying");
    }

    public String getContainerIdUsingName(String containerName)
    {
        InspectContainerResponse containerInfo = dockerClient.inspectContainerCmd(containerName).exec();
        return containerInfo.getId();
    }
}
