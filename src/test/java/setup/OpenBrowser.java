package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OpenBrowser {

    public WebDriver driver;
    private EventFiringWebDriver edriver;
    private IEventListener listener;
    public static enum Open {
        CHROME, IE, FIREFOX, HtmlUnitDriver
    }
    public enum url {
        Pricing
    }

    public EventFiringWebDriver initBrowser(String url) {
        driver = getDriver(Open.CHROME);
        listener = new IEventListener();
        edriver = new EventFiringWebDriver(driver);
        edriver.register(listener);
        edriver.navigate().to(url);
        edriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        edriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
      return edriver;
    }

    public void closeBrowser() {
        edriver.unregister(listener);
        edriver.quit();
    }

    public WebDriver getDriver(Open browser) {
        switch (browser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver",
                        "chromedriver");
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-plugins");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-extensions");
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver",
                        "geckodriver");
                driver = new FirefoxDriver();
                break;
            case IE:
                driver = new InternetExplorerDriver();
                break;
            case HtmlUnitDriver:
                driver = new HtmlUnitDriver();
        }
        return driver;
    }
}
