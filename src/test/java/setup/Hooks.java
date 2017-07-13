package setup;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private static Logger log = LoggerFactory.getLogger(Hooks.class);

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverBean.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        DriverBean.getDriver().quit();
        log.info("Scenario executed:" + scenario.getName());
        log.info("Browser is closed");

    }

    @Before
    public void startUp(Scenario scenario) {
        log.info("Scenario Running:" + scenario.getName());
        if (System.getenv("ENV").equalsIgnoreCase("Docker") && ConfigureProperties.getDockerEnvStatus()) {
            Assume.assumeTrue(!ConfigureProperties.getDockerEnvStatus());
        }
    }
}

