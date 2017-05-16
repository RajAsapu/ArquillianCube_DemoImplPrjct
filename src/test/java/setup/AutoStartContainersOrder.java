package setup;


import org.arquillian.cube.docker.impl.client.CubeDockerConfiguration;
import org.arquillian.cube.docker.impl.client.config.DockerCompositions;
import org.arquillian.cube.spi.AutoStartParser;
import org.arquillian.cube.spi.Node;
import org.jboss.arquillian.core.api.Instance;
import org.jboss.arquillian.core.api.annotation.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AutoStartContainersOrder implements AutoStartParser {

    /*
     * Implement code to handle multiple environments - Test, Dev or Docker
     */

    @Inject
    public Instance<CubeDockerConfiguration> cubeDockerConfigurationInstance;
    public UpdateProperties updateProperties = new UpdateProperties();

    @Override
    public Map<String, Node> parse() {
        final DockerCompositions dockerContainersContent =
                cubeDockerConfigurationInstance.get().getDockerContainersContent();
        final Map<String, Node> nodes = new HashMap<>();
        final Set<String> containersNames = new TreeSet<>(dockerContainersContent.getContainers().keySet());
        String environment = updateProperties.getEnv();
        if (environment.equals("Docker")) {
            for (String name : containersNames) {
                if (name.contains("service")) {
                    continue;
                }
                nodes.put(new StringBuilder(name).reverse().toString(), Node.from(name));
            }
        }
        return nodes;
    }
}
