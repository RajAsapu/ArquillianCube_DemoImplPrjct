package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import cucumber.runtime.arquillian.ArquillianCucumber;
import cucumber.runtime.arquillian.CukeSpace;
import org.arquillian.cube.HostPort;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.junit.runner.RunWith;
import setup.UpdateProperties;
import java.util.HashMap;
import java.util.Map;

@RunWith(ArquillianCucumber.class)
@RunAsClient
@CucumberOptions(
        features={"src/test/resources/features/pageobjects/"},
        glue = {"classpath:"} ,
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

        @Before
        public void updatePricingProperties() throws Exception {
                map.put("pricing.ui", "localhost:" + String.valueOf(uiPort));
                map.put("pricing.datamock", "localhost:" + String.valueOf(datamockPort));
                map.put("pricing.engine", "localhost:" + String.valueOf(enginePort));
                props.setProperty(map);
                props.updateHostConfig();
                System.out.println("Arquillian - Containers has started .");
        }

}