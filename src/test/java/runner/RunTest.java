package runner;

import com.google.common.base.Verify;
import cucumber.api.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import org.arquillian.cube.CubeIp;
import org.arquillian.cube.HostPort;
import org.jboss.arquillian.junit.Arquillian;
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

@RunWith(Arquillian.class)
@CucumberOptions(
        plugin = {"progress","html:target/cucumber-html-report", "json:target/cucumber-json-report"},
        features = {"src/test/resources/features/"},
        glue = {"classpath:"},
        tags = {"@TestIndex"}
)
public class RunTest {

    private UpdateProperties props = new UpdateProperties();
    private ContainerConfiguration containerConfiguration = new ContainerConfiguration();
    private Map<String, String> map = new HashMap<String, String>();
    private static Logger log = LoggerFactory.getLogger(RunTest.class);
    /*
     *  Service image name
     */
    private final static String SERVICE_IMAGE = "epe-config";
    /*
     * List of container names
     */
    private final static String UI_CONTAINER_NAME = "ui";
    private final static String ENGINE_CONTAINER_NAME = "engine";
    private final static String DATAMOCK_CONTAINER_NAME = "datamock";
    private final static String DATABASE_CONTAINER_NAME = "database";
    /*
     * Extract exposed ports into variables
     */
    @HostPort(containerName = UI_CONTAINER_NAME, value = 80)
    private int uiPort;
    @HostPort(containerName = ENGINE_CONTAINER_NAME, value = 8081)
    private int enginePort;
    @HostPort(containerName = DATAMOCK_CONTAINER_NAME, value = 8082)
    private int datamockPort;
    /*
     * Variable to get the ipaddress of the database container
     */
    @CubeIp(containerName = DATABASE_CONTAINER_NAME)
    private String ipDatabase;


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
            startServiceContainer();
            /*
             * Writing exposed ports to the properties file
             */
            map.put("pricing.ui", "localhost:" + String.valueOf(uiPort));
            map.put("pricing.datamock", "localhost:" + String.valueOf(datamockPort));
            map.put("pricing.engine", "localhost:" + String.valueOf(enginePort));
            map.put("pricing.service", "localhost:8080");
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
        String tagName;
        try {
            tagName = System.getenv("SERVICE_IMAGE_TAG");
        }catch (Exception exp){
            tagName="latest";
        }
        Verify.verify(containerConfiguration.startServiceContainer(ipDatabase, SERVICE_IMAGE+":"+tagName));
        log.debug("Service Container has started");
    }
}

