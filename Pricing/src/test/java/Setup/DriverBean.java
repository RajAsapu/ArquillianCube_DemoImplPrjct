package Setup;

import org.openqa.selenium.WebDriver;

public class DriverBean {

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        DriverBean.driver = driver;
    }

    public static WebDriver driver;

}
