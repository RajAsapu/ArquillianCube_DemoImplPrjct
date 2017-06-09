package runner;

import com.google.common.base.Verify;
import cucumber.api.CucumberOptions;
import cucumber.runtime.arquillian.CukeSpace;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import org.arquillian.cube.CubeIp;
import org.arquillian.cube.HostPort;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import setup.OpenBrowser;
import setup.UpdateProperties;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(CukeSpace.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-html-report", "json:target/cucumber-json-report"},
        features = {"src/test/resources/features/pageobjects/"},
        glue = {"classpath:"},
        tags = {"@TestData,@WorkBookList"}
)
@RunAsClient
public class RunTest {

    @HostPort(containerName = "ui", value = 80)
    private static int uiPort;
    @HostPort(containerName = "datamock", value = 5555)
    private static int datamockPort;
    @HostPort(containerName = "engine", value = 6666)
    private static int enginePort;
    @CubeIp(containerName = "database")
    protected String ipDatabase;

    @AfterClass
    public static void generateReports() {
        UpdateProperties props = new UpdateProperties();
        OpenBrowser browser = new OpenBrowser();
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-json-report");

        String buildNumber = System.getenv("bamboo.buildNumber");
        String projectName = "Pricing-e2e-tests";
        boolean parallelTesting = false;

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setParallelTesting(false);
        configuration.setBuildNumber(buildNumber);

        configuration.addClassifications("Environment", props.getEnv());
        configuration.addClassifications("Browser", browser.getSelectedDriver());

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        Reportable result = reportBuilder.generateReports();

    }

    @Before
    public void initialization() throws Exception {

        Map<String, String> map = new HashMap<String, String>();
        UpdateProperties props = new UpdateProperties();
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

