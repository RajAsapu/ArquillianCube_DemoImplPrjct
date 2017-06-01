package setup;

import cucumber.api.Scenario;
import functions.PageCommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IEventListener extends AbstractWebDriverEventListener {

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        new DateOperations().getScreenShot();
    }
}