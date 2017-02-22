package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


class openBrowser {

    static WebDriver driver;
    static enum Open{CHROME,IE,FIREFOX}

    public static WebDriver getDriver(Open browser)
    {

        switch (browser)
        {
            case CHROME:
                System.setProperty("webdriver.chrome.driver",".//resources/chromedriver".replaceFirst(".","utilities/src/main"));
                driver=new ChromeDriver();
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver",".//resources/geckodriver".replaceFirst(".","utilities/src/main"));
                driver=new FirefoxDriver();
                break;
            case IE:
                driver=new InternetExplorerDriver();
                break;
        }
        return driver;
    }
}
