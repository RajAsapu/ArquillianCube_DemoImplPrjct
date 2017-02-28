package tests;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import setup.OpenBrowser;
import java.io.IOException;
import java.net.URL;

@RunWith(Arquillian.class)
@RunAsClient
public class CheckListPage {

//    @Deployment(testable = false)
//    public static GenericArchive runPriceUi()
//    {
//        return ShrinkWrap.create(GenericArchive.class,"app.jar")
//                .add(new ClassLoaderAsset("index.js"),"./Repos/price-configuration-ui.git/index.js")
//                .add(new ClassLoaderAsset("package.json"),"./Repos/price-configuration-ui.git/package.json");
//    }

    @Test
    public void checkListPage(@ArquillianResource URL base)throws IOException,InterruptedException
    {
        WebDriver driver=driver= OpenBrowser.getDriver(OpenBrowser.Open.CHROME);
        System.out.println(base);
        driver.get("http://localhost:4200/");
        Thread.sleep(3000);
    }
}
