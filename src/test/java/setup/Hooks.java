package setup;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import cucumber.runtime.CucumberException;
import org.junit.Assume;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.notification.RunNotifier;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runner.RunTest;

import java.util.Collection;

public class Hooks {
    private static Logger log = LoggerFactory.getLogger(Hooks.class);
    @After
    public void tearDown(Scenario scenario) {
       log.debug("Scenario executed:"+scenario.getName());
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverBean.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        DriverBean.getDriver().close();
        log.info("Browser is closed");

    }
    @Before
    public void startUp(Scenario scenario) {
       log.debug("Scenario Running:"+scenario.getName());
        if(AppProperties.getEnv().equalsIgnoreCase("Docker")){
           Assume.assumeTrue(!ContainerConfiguration.getDockerEnvStatus());
        }
    }
}

