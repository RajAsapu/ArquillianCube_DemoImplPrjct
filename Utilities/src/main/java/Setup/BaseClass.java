package Setup;

import Constants.Urls;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public WebDriver driver;
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

    public WebDriver initBrowser(String url)
    {
        driver=OpenBrowser.getDriver(OpenBrowser.Open.CHROME);

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        return driver;
    }

    /*
      Closes the browser
     */
    public void closeBrowser()
    {
        driver.quit();
    }
}
