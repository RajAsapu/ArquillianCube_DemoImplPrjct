package setup;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.jboss.arquillian.phantom.resolver.ResolvingPhantomJSDriverService;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OpenBrowser {

    public WebDriver driver;
    private EventFiringWebDriver edriver;
    private IEventListener listener;
    public static enum Open {
        CHROME, IE, FIREFOX, HtmlUnitDriver , PhantomJS
    }
    public enum url {
        Pricing
    }

    public EventFiringWebDriver initBrowser(String url) {
        driver = getDriver(Open.PhantomJS);
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
                driver = new HtmlUnitDriver(true);
            case PhantomJS:
//                PhantomJsDriverManager.getInstance().setup();
//                DesiredCapabilities capabilities = new DesiredCapabilities();
//                capabilities.setJavascriptEnabled(true);
////                capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"phantomjs");
//                capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX,true);
//                capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_PATH_PROPERTY,"2.0.0");
//                capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,"--webdriver=3838 --webdriver-selenium-grid-hub" +
//                        "=http ://localhost:3839");
//                capabilities.setCapability("--ignore-ssl-errors",true);
//                driver = new PhantomJSDriver(capabilities);
//                long start = System.currentTimeMillis();
//                ((JavascriptExecutor) driver).executeAsyncScript(
//                        "window.setTimeout(arguments[arguments.length - 1], 5000);");
                try {
                    driver = new PhantomJSDriver(
                            ResolvingPhantomJSDriverService.createDefaultService(),
                            DesiredCapabilities.phantomjs());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                driver.manage().window().setSize(new Dimension(1280,1024));
        }
        return driver;
    }
}
