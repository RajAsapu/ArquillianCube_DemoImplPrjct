package setup;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Hooks {
    @After
    public void tearDown() {
        File screenshot = ((TakesScreenshot) DriverBean.getDriver()).getScreenshotAs(OutputType.FILE);
        Scenario name;
        try {
            FileUtils.copyFile(screenshot,new File("screenshot/testRun.jpeg"));
            Thread.sleep(5000);
        } catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }

        DriverBean.getDriver().quit();
        System.out.println("Browser is closed");
    }
}

