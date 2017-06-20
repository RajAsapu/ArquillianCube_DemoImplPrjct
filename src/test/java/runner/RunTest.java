package runner;

import com.google.common.base.Verify;
import courgette.api.CourgetteOptions;
import courgette.api.CourgetteRunLevel;
import courgette.api.junit.Courgette;
import cucumber.api.CucumberOptions;
import cucumber.runtime.arquillian.CukeSpace;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import org.arquillian.cube.CubeController;
import org.arquillian.cube.CubeIp;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.*;
import org.junit.runner.JUnitCore;
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


/*
  One Scenario
  Running on
        phantom js - 1m 12 s
        chromeheadless - 37 s
        grid/chrome stable - 1m 24s

 */
@RunWith(CukeSpace.class)
@CucumberOptions(
        plugin = {"progress","html:target/cucumber-html-report", "json:target/cucumber-json-report"},
        features = {"src/test/resources/features/"},
        glue = {"classpath:"},
        tags = {"@SmokeTest"}
)
public class RunTest {

    private UpdateProperties props = new UpdateProperties();
    private ContainerConfiguration containerConfiguration = new ContainerConfiguration();
    private Map<String, String> map = new HashMap<String, String>();
    private static Logger log = LoggerFactory.getLogger(RunTest.class);
    /*
     *  Service image name
     */
    private final static String SERVICE_IMAGE = "/epe-config";
    /*
     * List of container names
     */
    private final static String DATABASE_CONTAINER_NAME = "database";
    /*
     * Variable to get the ipaddress of the database container
     */
    @CubeIp(containerName = DATABASE_CONTAINER_NAME)
    private String ipDatabase;

    @ArquillianResource
    CubeController cubeController;

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

    @Test
    public void setEnvironment() {
        if (props.getEnv().equals("Docker")) {
            /*
             * Remove service running container
             */
            cubeController.stop("service");
            cubeController.destroy("service");
            /*
             * Start service container
             */
            startServiceContainer();
            /*
             * Writing exposed ports to the properties file
             */
            map.put("pricing.ui"      , "localhost:4200");
            map.put("pricing.datamock", "localhost:8082");
            map.put("pricing.engine"  , "localhost:8081");
            map.put("pricing.service" , "localhost:8080");
            props.setProperty(map);
            props.updateHostConfig();
            /*
             * Display running containers
             */
            containerConfiguration.displayRunningContainers();
            log.debug("Run the tests");
        }
    }
    /*
     * Method to start the service container using database ip address
     */
    public void startServiceContainer()
    {
        String tagName,registryName=null;
        try {
            registryName = System.getenv("DOCKER_REGISTRY");
            tagName = System.getenv("SERVICE_IMAGE_TAG");
        }catch (Exception exp){
            tagName="latest";
        }
        Verify.verify(containerConfiguration.startServiceContainer(ipDatabase, registryName+SERVICE_IMAGE+":"+tagName));
        log.debug("Service Container has started");
    }
}

