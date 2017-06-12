package setup;

import org.jboss.arquillian.phantom.resolver.ResolvingPhantomJSDriverService;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OpenBrowser {

    private static String choosenBrowser;
    public WebDriver driver;
    private EventFiringWebDriver edriver;
    private IEventListener listener;

    public EventFiringWebDriver initBrowser(String url) {
        driver = getDriver(Open.PhantomJS);
        listener = new IEventListener();
        edriver = new EventFiringWebDriver(driver);
        edriver.register(listener);
        DriverBean.setDriver(edriver);
        edriver.navigate().to(url);
        edriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        edriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        edriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        return edriver;
    }

    public void closeBrowser() {
        edriver.unregister(listener);
        edriver.quit();
    }

    public WebDriver getDriver(Open browser) {
        /*
         * Chrome Options
         */
        choosenBrowser = browser.toString();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-plugins");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("window-size=1280x1024");
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        switch (browser) {
            case Chrome:
                driver = new ChromeDriver(options);
                break;
            /*
             * Chrome version greater than 58 required
             */
            case Chrome_Headless:
                options.setBinary("/usr/bin/google-chrome-unstable");
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
                break;
            case PhantomJS:
                try {
                    String[] cli_args = new String[]{"--ignore-ssl-errors=true", "--debug=true"};
                    DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
                    capabilities.setJavascriptEnabled(true);
                    capabilities.acceptInsecureCerts();
                    capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cli_args);
                    driver = new PhantomJSDriver(
                            ResolvingPhantomJSDriverService.createDefaultService(),
                            capabilities);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ((JavascriptExecutor) driver).executeAsyncScript(
                        "window.setTimeout(arguments[arguments.length - 1], 30000);");
                driver.manage().window().setSize(new Dimension(1280, 1024));
        }
        return driver;
    }

    public String getSelectedDriver() {
        return choosenBrowser;
    }

    public static enum Open {
        Chrome, PhantomJS, Chrome_Headless
    }
}
