package setup;

import cucumber.api.Scenario;
import cucumber.api.SummaryPrinter;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.runtime.DefaultSummaryPrinter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Scenario executed:"+scenario.getName());
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverBean.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        DriverBean.getDriver().close();
        System.out.println("Browser is closed");

    }
}

