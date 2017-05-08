package runner;

import com.google.common.base.Verify;
import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.runtime.arquillian.ArquillianCucumber;
import cucumber.runtime.arquillian.CukeSpace;
import org.arquillian.cube.CubeIp;
import org.arquillian.cube.HostPort;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import setup.UpdateProperties;
import stepdef.AbstractApiDefinitions;
import java.util.HashMap;
import java.util.Map;

@RunWith(ArquillianCucumber.class)
@RunAsClient
@CucumberOptions(
        features = {"src/test/resources/features/pageobjects/"},
        glue = {"classpath:"},
        tags = {"@PageObjects"}
)
public class RunTest {

    private UpdateProperties props = new UpdateProperties();
    private Map<String, String> map = new HashMap<String, String>();

    @HostPort(containerName = "ui", value = 80)
    private static int uiPort;
    @HostPort(containerName = "datamock", value = 5555)
    private static int datamockPort;
    @HostPort(containerName = "engine", value = 6666)
    private static int enginePort;
    @HostPort(containerName = "service", value = 8080)
    private static int servicePort;
    @CubeIp(containerName = "database")
    protected String ipDatabase;

    @Test
    public void updatePricingProperties() throws Exception {
        Verify.verify(props.startServiceContainer(ipDatabase, "epe-config:latest"));
        map.put("pricing.ui", "localhost:" + String.valueOf(uiPort));
        map.put("pricing.datamock", "localhost:" + String.valueOf(datamockPort));
        map.put("pricing.engine", "localhost:" + String.valueOf(enginePort));
        map.put("pricing.service", "localhost:" + String.valueOf(servicePort));
        props.setProperty(map);
        props.updateHostConfig();
        System.out.println("Arquillian - Containers has started .");
    }
}