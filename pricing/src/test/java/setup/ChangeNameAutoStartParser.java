package setup;

import org.arquillian.cube.HostPort;
import org.arquillian.cube.docker.impl.client.CubeDockerConfiguration;
import org.arquillian.cube.docker.impl.client.config.CubeContainer;
import org.arquillian.cube.docker.impl.client.config.DockerCompositions;
import org.arquillian.cube.spi.AutoStartParser;
import org.arquillian.cube.spi.Node;
import org.jboss.arquillian.core.api.Instance;
import org.jboss.arquillian.core.api.annotation.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.arquillian.cube.spi.Node;

public class ChangeNameAutoStartParser  {

//    @Inject
//    public Instance<CubeDockerConfiguration> cubeDockerConfigurationInstance;
//
//    @HostPort(containerName = "ngContainer", value = 4200)
//    private static int ngPort;
//
//    @Override
//    protected int getPort() {
//        return ngPort;
//    }
//
//    @Override
//    public Map<String, Node> parse() {
//        final DockerCompositions dockerContainersContent = cubeDockerConfigurationInstance.get().getDockerContainersContent();
//
//        final Map<String, Node> nodes = new HashMap<>();
//        final Set<String> containersNames = new TreeSet<>(dockerContainersContent.getContainers().keySet());
//
//        for (String name : containersNames) {
//            nodes.put(new StringBuilder(name).reverse().toString(), Node.from(name));
//        }
//
//        return nodes;
//    }
}