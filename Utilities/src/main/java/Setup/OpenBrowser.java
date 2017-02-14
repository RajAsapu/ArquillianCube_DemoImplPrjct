package Setup;

import junit.framework.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;


class OpenBrowser {

    static WebDriver driver;
    static enum Open{CHROME,IE,FIREFOX}

    public static WebDriver getDriver(Open browser)
    {
        ClassLoader loader= Test.class.getClassLoader();
        System.out.print(loader.getResource("."));
        switch (browser)
        {
            case CHROME:
                System.setProperty("webdriver.chrome.driver",".//resources/chromedriver.exe");
                driver=new ChromeDriver();
                break;
            case FIREFOX:
                driver=new FirefoxDriver();
                break;
            case IE:
                driver=new InternetExplorerDriver();
                break;
        }
        return driver;
    }
}
