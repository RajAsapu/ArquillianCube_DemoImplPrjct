package runner;

import cucumber.api.CucumberOptions;
import cucumber.runtime.arquillian.CukeSpace;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.arquillian.cube.CubeController;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setup.ConfigureProperties;
import setup.OpenBrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(CukeSpace.class)
@CucumberOptions(
        plugin = {"json:target/cucumber-json-report"},
        features = {"src/test/resources/features/"},
        glue = {"classpath:"},
        tags = {"@TestCalc"}
)
public class RunTest {

    @ArquillianResource
    static CubeController cubeController;
    private static Logger log = LoggerFactory.getLogger(RunTest.class);
    private ConfigureProperties configureProperties = new ConfigureProperties();

    @AfterClass
    public static void generateReports() {
        ConfigureProperties props = new ConfigureProperties();
        /*
         * Stop the service container
         */
        cubeController.stop(ConfigureProperties.getGradleProperty("serviceContainerName"));
        cubeController.destroy(ConfigureProperties.getGradleProperty("serviceContainerName"));
        /*
         * Generate report
         */
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
        configuration.addClassifications("Build Number", System.getenv("bamboo.buildNumber"));

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    @Test
    public void setEnvironment() {
        if (System.getenv("ENV").equals("Docker")) {
            /*
             * Start the service container
             */
            cubeController.create(ConfigureProperties.getGradleProperty("serviceContainerName"));
            cubeController.start(ConfigureProperties.getGradleProperty("serviceContainerName"));
            log.info("Service Container has started");
            /*
             * Update pricing properties
             */
            configureProperties.updatePricingProperties();
            /*
             * Update hostConfig file in the ui container
             */
            configureProperties.updateHostConfig();
            /*
             * Display running containers
             */
            configureProperties.checkIfContainersAreRunning();
            log.info("Running tests");
        }
    }
}
