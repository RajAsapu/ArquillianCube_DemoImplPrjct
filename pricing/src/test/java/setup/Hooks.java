package setup;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import dockerhandler.HandleDocker;

public class Hooks {
	HandleDocker handle = new HandleDocker();
	@Before
	public void restartDocker() {
		/*
		 * Configure arquillian to restart the docker deamon before running the
		 * tests
		 */
		 handle.stopDocker();
	}
	@After
	public void tearDown() {
		DriverBean.getDriver().quit();
	}
}
