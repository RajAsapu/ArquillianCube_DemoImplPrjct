package setup;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public class driverBean {

    public static EventFiringWebDriver getDriver() {
        return edriver;
    }

    public static void setDriver(EventFiringWebDriver edriver) {
        driverBean.edriver = edriver;
    }

    public static EventFiringWebDriver edriver;

}
