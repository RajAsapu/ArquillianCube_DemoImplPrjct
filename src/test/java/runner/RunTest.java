package runner;

import com.google.common.base.Verify;
import cucumber.api.CucumberOptions;
import cucumber.runtime.arquillian.ArquillianCucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import org.arquillian.cube.CubeIp;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import setup.UpdateProperties;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(ArquillianCucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-html-report","json:target/cucumber-json-report"},
        features = {"src/test/resources/features/pageobjects/"},
        glue = {"classpath:"},
        tags = {"@TestData1"}
)
@RunAsClient

public class RunTest {

    @CubeIp(containerName = "database")
    protected static String ipDatabase;

    @BeforeClass
    public static void initialization() throws Exception {
         UpdateProperties props = new UpdateProperties();
         Map<String, String> map = new HashMap<String, String>();

        if (props.getEnv().equalsIgnoreCase("docker")) {
            Verify.verify(props.startServiceContainer(ipDatabase, "epe-config:latest"));
            map.put("pricing.ui", "localhost:4200");
            map.put("pricing.datamock", "localhost:5555");
            map.put("pricing.engine", "localhost:8081");
            map.put("pricing.service", "localhost:8080");
            props.setProperty(map);
            props.updateHostConfig();
            System.out.println("Arquillian - Containers has started .");
        }
    }

    @AfterClass
    public static void generateReports()
    {
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-json-report");

        String buildNumber = System.getenv("bamboo.buildNumber");
        String projectName = "Pricing-e2e-tests";
        boolean parallelTesting = false;

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setParallelTesting(false);
        configuration.setBuildNumber(buildNumber);

        configuration.addClassifications("Platform", "Linux");
        configuration.addClassifications("Browser", "Phantom JS");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        Reportable result = reportBuilder.generateReports();

    }
 }

