package setup;

import constants.Urls;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class BaseClass {

    public WebDriver driver;
    private EventFiringWebDriver edriver;
    private IEventListener listener;

    public enum url{Pricing};

    /*
      Next Step :Implimenet hooks
     */

    /*
       Intialization of the Browser
       * Opens the browser
       * Maximizes the browser
       * Set implicit wait time as 10 seconds
       * Set Page load time as 10 seconds
     */

    public EventFiringWebDriver initBrowser(String url)
    {
        driver= OpenBrowser.getDriver(OpenBrowser.Open.CHROME);

        listener=new IEventListener();
        edriver=new EventFiringWebDriver(driver);
        edriver.register(listener);

        edriver.navigate().to(url);
        edriver.manage().window().maximize();
        edriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        edriver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);

        return edriver;
    }

    /*
      Closes the browser
     */
    public void closeBrowser()
    {
        edriver.unregister(listener);
        edriver.quit();
    }
}
