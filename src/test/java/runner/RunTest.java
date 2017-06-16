package runner;

import cucumber.api.CucumberOptions;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class RunTest {
    @Test
    public void getImage()
    {
        System.out.println("Ran arquillian test");
    }
}

