import cucumber.api.CucumberOptions;
import cucumber.runtime.arquillian.CukeSpace;
import org.arquillian.cube.CubeIp;
import org.junit.Test;
import org.junit.runner.RunWith;
import setup.UpdateProperties;

@RunWith(CukeSpace.class)
@CucumberOptions(
        features = "src/test/features/pageobjects/",
        glue = {"classpath:stepdef", "classpath:setup"},
        format = {"pretty", "html:target/integration-cucumber", "json:target/integration-cucumber.json"},
        tags = {"~@Intg"})
public class RunTest {

    private UpdateProperties props;

    @CubeIp(containerName = "aqpriceui")
    private String priceip;

    @Test
    public void priceprops() {
        props=new UpdateProperties();
        props.setProperty("priceui.ip","localhost","priceui.port","4200");
        System.out.println("properties are updated");
    }
}