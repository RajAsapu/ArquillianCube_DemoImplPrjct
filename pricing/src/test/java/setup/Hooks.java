package setup;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import dockerhandler.HandleDocker;

public class Hooks {
    HandleDocker handle = new HandleDocker();

    @Before
    public void init() throws Exception {

    }

    @After
    public void tearDown() {
        DriverBean.getDriver().quit();
        DriverBean.edriver = null;
    }
}

