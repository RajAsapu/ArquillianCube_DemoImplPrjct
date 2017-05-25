package setup;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;

public class Hooks {
    @After
    public void tearDown() {


        DriverBean.getDriver().quit();
        System.out.println("Browser is closed");
    }
}

