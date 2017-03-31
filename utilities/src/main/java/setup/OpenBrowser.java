package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class OpenBrowser {

    static WebDriver driver;

    public static WebDriver getDriver(Open browser) {

        switch (browser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver",
                        ".//resources/chromedriver".replaceFirst(".", "utilities/src/main"));
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--test-type");
                options.addArguments("--disable-plugins");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-extensions");
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver",
                        ".//resources/geckodriver".replaceFirst(".", "utilities/src/main"));
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

    public static enum Open {
        CHROME, IE, FIREFOX, HtmlUnitDriver
    }
}
