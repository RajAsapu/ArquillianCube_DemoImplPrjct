package Setup;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverBean {

    public static EventFiringWebDriver getDriver() {
        return edriver;
    }

    public static void setDriver(EventFiringWebDriver edriver) {
        DriverBean.edriver = edriver;
    }

    public static EventFiringWebDriver edriver;

}
