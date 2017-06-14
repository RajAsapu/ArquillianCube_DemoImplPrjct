package setup;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class Hooks {
    private static Logger log = LoggerFactory.getLogger(Hooks.class);
    @After
    public void tearDown(Scenario scenario) {
       log.info("Scenario executed:"+scenario.getName());
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
       log.info("Scenario Running:"+scenario.getName());
        Collection<String> tags=scenario.getSourceTagNames();
        for(String temp:tags)
        {
            System.out.println(temp);
        }
    }
}

