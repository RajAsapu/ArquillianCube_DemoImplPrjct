package setup;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DockerClientBuilder;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;

public class ContainerConfiguration {

    private static boolean dockerEnvBroken = false;
    private static DockerClient dockerClient;
    private static Logger log = LoggerFactory.getLogger(ContainerConfiguration.class);
    /*
     * Method to verify if the containers are running
     */
    public void checkIfContainersAreRunning() {
        dockerClient = DockerClientBuilder.getInstance().build();
        List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();
        String activeContainers[] = {"/pricing_ui", "/pricing_datamock", "/pricing_engine", "/pricing_database", "/pricing_service"};
        for (Container temp : containers) {
            log.info("#--------------Running--------------#");
            log.info("Name:" + temp.getNames()[0] + ",Status:" + temp.getStatus());
            if (Arrays.asList(activeContainers).contains(temp.getNames()[0]) ) {
                if (!temp.getStatus().contains("Up")) {
                    dockerEnvBroken=true;
                    Assert.fail("Failed to start container:" + temp.getNames()[0]);
                }
            }
        }
    }

    public static boolean getDockerEnvStatus()
    {
        return dockerEnvBroken;
    }

    public static void removePriceNetwork()
    {
        try{
            dockerClient = DockerClientBuilder.getInstance().build();
            dockerClient.removeNetworkCmd("price_network").exec();
            log.info("Price Network is removed");
        }
        catch (NullPointerException exp)
        {
            log.info("Price Network doesn't exist !");
        }
    }
}
