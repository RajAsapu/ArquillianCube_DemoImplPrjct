package setup;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DockerClientBuilder;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContainerConfiguration {
    private static boolean dockerEnvBroken = false;
    private DockerClient dockerClient;
    private static Logger log = LoggerFactory.getLogger(ContainerConfiguration.class);

    public boolean startServiceContainer(String dbIpAddress, String image) {
        try {
            dockerClient = DockerClientBuilder.getInstance().build();
            CreateContainerResponse container = dockerClient.createContainerCmd(image)
                    .withExposedPorts(new ExposedPort(8080, InternetProtocol.TCP))
                    .withPortBindings(PortBinding.parse("8080:8080"))
                    .withPublishAllPorts(true)
                    .withAttachStderr(true)
                    .withAttachStdin(true)
                    .withEnv("cassandra.contactpoints=" + dbIpAddress)
                    .withName("service")
                    .withCmd("sleep", "5000")
                    .withOomKillDisable(false)
                    .withDnsSearch("localhost")
                    .withRestartPolicy(RestartPolicy.unlessStoppedRestart())
                    .exec();

            dockerClient.startContainerCmd(container.getId()).exec();
            log.debug("Started Service Container");
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

    /*
     * Method to display running containers
     */
    public void displayRunningContainers() {
        List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();
        String activeContainers[] = {"/ui", "/datamock", "/engine", "/database", "/service"};
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
}
