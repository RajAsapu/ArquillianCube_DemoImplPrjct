package runner;

import cucumber.api.CucumberOptions;
import cucumber.runtime.arquillian.CukeSpace;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.arquillian.cube.CubeController;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setup.ContainerConfiguration;
import setup.OpenBrowser;
import setup.UpdateProperties;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(CukeSpace.class)
@CucumberOptions(
        plugin = {"json:target/cucumber-json-report"},
        features = {"src/test/resources/features/"},
        glue = {"classpath:"},
        tags = {"@AppTestData"}
)
public class RunTest {

    private UpdateProperties props = new UpdateProperties();
    private ContainerConfiguration containerConfiguration = new ContainerConfiguration();
    private Map<String, String> map = new HashMap<String, String>();
    private static Logger log = LoggerFactory.getLogger(RunTest.class);

    private static String pricing_Service_ContainerName = "pricing_service";
    private static String pricing_Database_ContainerName = "pricing_database";
    private static String pricing_Ui_ContainerName = "pricing_ui";
    private static String pricing_Datamock_ContainerName = "pricing_datamock";
    private static String pricing_Engine_ContainerName = "pricing_engine";

    @ArquillianResource
    static CubeController cubeController;

    @Test
    public void setEnvironment() {
        if (System.getenv("ENV").equals("Docker"))
        {
            cubeController.create(pricing_Service_ContainerName);
            cubeController.start(pricing_Service_ContainerName);
            log.debug("Service Container has started");
            /*
             * Writing exposed ports to the properties file
             */
            map.put("pricing.ui"      , System.getenv("UI_URL"));
            map.put("pricing.datamock", System.getenv("DATAMOCK_URL"));
            map.put("pricing.engine"  , System.getenv("ENGINE_URL"));
            map.put("pricing.service" , System.getenv("SERVICE_URL"));
            props.setProperty(map);
            props.updateHostConfig();
            /*
             * Display running containers
             */
            containerConfiguration.checkIfContainersAreRunning();
            log.debug("Running tests");
        }
    }

    @AfterClass
    public static void generateReports() {
        UpdateProperties props = new UpdateProperties();
        OpenBrowser browser = new OpenBrowser();
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-json-report");

        String projectName = "Pricing-e2e-tests";
        boolean parallelTesting = false;

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setParallelTesting(false);

        configuration.addClassifications("Environment", System.getenv("ENV"));
        configuration.addClassifications("Browser", browser.getSelectedDriver());
        configuration.addClassifications("Build Number",System.getenv("bamboo.buildNumber"));

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
