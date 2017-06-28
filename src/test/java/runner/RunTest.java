package runner;

import com.google.common.base.Verify;
import cucumber.api.CucumberOptions;
import cucumber.runtime.arquillian.CukeSpace;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.arquillian.cube.CubeController;
import org.arquillian.cube.CubeIp;
import org.jboss.arquillian.junit.Arquillian;
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
        tags = {"@TestCalc"}
)
public class RunTest {

    private UpdateProperties props = new UpdateProperties();
    private ContainerConfiguration containerConfiguration = new ContainerConfiguration();
    private Map<String, String> map = new HashMap<String, String>();
    private static Logger log = LoggerFactory.getLogger(RunTest.class);
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
    static CubeController cubeController;

    @AfterClass
    public static void generateReports() {
        removeServiceContainer();
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


//
//    public void startService()
//    {
//        cubeController.start("pricing_service");
//        log.debug("Running tests");
//    }

    public void setEnvironment() {
        if (System.getenv("ENV").equals("Docker")) {
//            removeServiceContainer();
//            log.debug("Stopped service container");
            /*
             * Start service container
             */
//            startServiceContainer();
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
            containerConfiguration.displayRunningContainers();
            log.debug("Running tests");
        }
    }
    /*
     * Method to start the service container using database ip address
     */
    public void startServiceContainer()
    {
        Verify.verify(containerConfiguration.startServiceContainer(ipDatabase, System.getenv("DOCKER_REGISTRY")+"/"+System.getenv("SERVICE_IMAGE")));
        log.debug("Service Container has started");
    }
    /*
     * Remove running service container
     */
    public static void removeServiceContainer()
    {
        cubeController.stop("service");
        cubeController.destroy("service");
    }

}
