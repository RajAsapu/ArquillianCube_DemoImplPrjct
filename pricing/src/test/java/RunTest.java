import cucumber.api.CucumberOptions;
import cucumber.runtime.arquillian.CukeSpace;
import org.arquillian.cube.CubeIp;
import org.arquillian.cube.HostIp;
import org.arquillian.cube.HostPort;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import setup.UpdateProperties;

import java.util.Map;

@RunWith(CukeSpace.class)
@CucumberOptions(
        features = "src/test/resources/features/pageobjects/Page_Index_CreatePage.feature",
        glue = {"classpath:stepdef", "classpath:setup"},
        format = {"pretty", "html:target/integration-cucumber", "json:target/integration-cucumber.json"},
        tags = {"~@Intg"})
@RunAsClient
public class RunTest {

    private UpdateProperties props;
    private Map<String,String> map;

    @CubeIp(containerName = "pricing_datamock")
    private String ipPriceDataMock;

    @CubeIp(containerName = "pricing_engine")
    private String ipPriceEngine;

    @CubeIp(containerName = "pricing_ui")
    private String ipPriceUi;

    @HostPort(containerName = "pricing_datamock")
    private String portPriceDataMock;

    @HostPort(containerName = "pricing_engine")
    private String portPriceEngine;

    @HostPort(containerName = "pricing_ui")
    private String portPriceUi;

    @Test
    public void priceprops() {

        map.put("Ui_ip",ipPriceUi);
        map.put("Ui_port",portPriceUi);

        map.put("Engine_ip",ipPriceEngine);
        map.put("Engine_port",portPriceEngine);

        map.put("Datamock_ip",ipPriceDataMock);
        map.put("Datamock_port",portPriceDataMock);

        props.setProperty(map);
    }
}