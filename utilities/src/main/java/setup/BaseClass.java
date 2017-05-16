package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class BaseClass {

    public WebDriver driver;
    private EventFiringWebDriver edriver;
    private IEventListener listener;

    public EventFiringWebDriver initBrowser(String url,OpenBrowser.Open browser){
        driver = OpenBrowser.getDriver(browser);
        listener = new IEventListener();
        edriver = new EventFiringWebDriver(driver);
        edriver.register(listener);
        edriver.navigate().to(url);
        edriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        edriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        return edriver;
    }

    /*
     * Closes the browser
     */
    public void closeBrowser() {
        edriver.unregister(listener);
        edriver.quit();
    }

    public enum url {
        Pricing
    }
}
