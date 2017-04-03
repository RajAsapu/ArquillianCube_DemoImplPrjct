import cucumber.api.CucumberOptions;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@CucumberOptions(
        strict = true,
        features = {"src/test/features/integrationtests/uitests"},
        glue = {"classpath:stepdef", "classpath:artifacts", "classpath:setup", "classpath:dockerhandler"},
        format = {"pretty", "html:target/integration-cucumber", "json:target/integration-cucumber.json"},
        tags = {
                "@Intg"
        }
)
@RunWith(Arquillian.class)
@RunAsClient
public class RunTest {
    @Test
    public void check() {
        System.out.println("Run test!!");
    }
}