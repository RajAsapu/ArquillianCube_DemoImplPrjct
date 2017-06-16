package setup;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DockerClientBuilder;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContainerConfiguration {
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
                    .withCmd("sleep","5000")
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
    public void displayRunningContainers()
    {
//        List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();
//        assertThat(containers, notNullValue());
//        for(Container temp:containers)
//        {
//            log.debug("#--------------Running--------------#");
//            log.debug("Name:"+temp.getNames()[0].toUpperCase()+",Status:"+temp.getStatus());
//            if(!temp.getStatus().contains("Up")){
//                Assert.fail("Failed to start container:"+temp.getNames()[0]);
//            }
//        }
    }
}
