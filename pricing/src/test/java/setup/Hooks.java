package setup;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import dockerhandler.HandleDocker;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class Hooks {
    HandleDocker handle = new HandleDocker();

    @Before
    public void init() {

    }

    @After
    public void tearDown() {
        DriverBean.getDriver().quit();
        DriverBean.edriver = null;
    }
}

