package setup;

import cucumber.api.java.After;

public class Hooks {
    @After
    public void tearDown() {
        DriverBean.getDriver().quit();
        DriverBean.edriver = null;
    }
}

