package setup;

import io.github.bonigarcia.wdm.ChromeDriverManager;
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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OpenBrowser {

    public WebDriver driver;
    private EventFiringWebDriver edriver;
    private IEventListener listener;
    public static enum Open {
        CHROME, PhantomJS
    }
    public enum url {
        Pricing
    }

    public EventFiringWebDriver initBrowser(String url) {
        driver = getDriver(Open.PhantomJS);
        listener = new IEventListener();
        edriver = new EventFiringWebDriver(driver);
        edriver.register(listener);
        DriverBean.setDriver(edriver);
        edriver.navigate().to(url);
        edriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        edriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        edriver.manage().timeouts().setScriptTimeout(30,TimeUnit.SECONDS);
      return edriver;
    }

    public void closeBrowser() {
        edriver.unregister(listener);
        edriver.quit();
    }

    public WebDriver getDriver(Open browser) {
        switch (browser) {
            case CHROME:
//                ChromeDriverManager.getInstance().setup();
                System.setProperty("webdriver.chrome.driver",
                        "chromedriver");
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--no-sandbox");
////                options.setBinary("/usr/bin/google-chrome-stable");
//                options.addArguments("window-size=1280x1024");
//                options.addArguments("--headless");
                options.addArguments("--disable-plugins");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-extensions");
                driver=new ChromeDriver(options);
            case PhantomJS:
                try {
                    String[] cli_args = new String[]{ "--ignore-ssl-errors=true" ,"--debug=true"};
                    DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
                    capabilities.setJavascriptEnabled(true);
                    capabilities.acceptInsecureCerts();
                    capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,cli_args);
                    driver = new PhantomJSDriver(
                            ResolvingPhantomJSDriverService.createDefaultService(),
                            capabilities);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                                ((JavascriptExecutor) driver).executeAsyncScript(
                        "window.setTimeout(arguments[arguments.length - 1], 30000);");
                driver.manage().window().setSize(new Dimension(1280,1024));
        }
        return driver;
    }
}
