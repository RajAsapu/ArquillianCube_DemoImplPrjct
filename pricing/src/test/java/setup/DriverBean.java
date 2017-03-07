package setup;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverBean {

	public static EventFiringWebDriver edriver;

	public static EventFiringWebDriver getDriver() {
		return edriver;
	}

	public static void setDriver(EventFiringWebDriver edriver) {
		DriverBean.edriver = edriver;
	}

}
