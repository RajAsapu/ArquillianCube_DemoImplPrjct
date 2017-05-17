package setup;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverBean {

    public EventFiringWebDriver edriver;

    public void init()
    {
        edriver=null;
    }

    public EventFiringWebDriver getDriver() {
        return edriver;
    }

    public void setDriver(EventFiringWebDriver driver) {
        edriver = driver;
    }

}
