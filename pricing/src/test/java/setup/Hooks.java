package setup;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import dockerhandler.HandleDocker;
import org.arquillian.cube.HostPort;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

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

