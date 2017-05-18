package setup;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverBean {

    public static EventFiringWebDriver edriver=null;

    public static EventFiringWebDriver getDriver() {
        return edriver;
    }

    public static void setDriver(EventFiringWebDriver driver) {
        edriver = driver;
    }

}
