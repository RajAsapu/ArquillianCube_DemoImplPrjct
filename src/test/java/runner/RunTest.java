package runner;

import com.google.common.base.Verify;
import cucumber.api.CucumberOptions;
import cucumber.runtime.arquillian.ArquillianCucumber;
import cucumber.runtime.arquillian.CukeSpace;
import org.arquillian.cube.CubeIp;
import org.arquillian.cube.HostPort;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import setup.UpdateProperties;
import java.util.HashMap;
import java.util.Map;

@RunWith(CukeSpace.class)
@CucumberOptions(
        strict = true,
        features = {"src/test/resources/features/pageobjects/TestData.feature"},
        glue = {"classpath:"},
        tags = {"@TestData"}
)
@RunAsClient
@RunListener.ThreadSafe
public class RunTest {

    @HostPort(containerName = "ui", value = 80)
    private static int uiPort;
    @HostPort(containerName = "datamock", value = 5555)
    private static int datamockPort;
    @HostPort(containerName = "engine", value = 6666)
    private static int enginePort;
    @CubeIp(containerName = "database")
    protected String ipDatabase;
    private UpdateProperties props = new UpdateProperties();
    private Map<String, String> map = new HashMap<String, String>();


    @cucumber.api.java.Before
    public void initialization() throws Exception {
        if (props.getEnv().equalsIgnoreCase("docker")) {
            Verify.verify(props.startServiceContainer(ipDatabase, "epe-config:latest"));
            map.put("pricing.ui", "localhost:" + String.valueOf(uiPort));
            map.put("pricing.datamock", "localhost:" + String.valueOf(datamockPort));
            map.put("pricing.engine", "localhost:" + String.valueOf(enginePort));
            map.put("pricing.service", "localhost:8080");
            props.setProperty(map);
            props.updateHostConfig();
            System.out.println("Arquillian - Containers has started .");
        }
    }
}