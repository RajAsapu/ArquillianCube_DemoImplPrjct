package setup;

import functions.PageCommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IEventListener extends AbstractWebDriverEventListener {

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        Logger log = LoggerFactory.getLogger(PageCommonMethods.class);
        log.debug(driver.getCurrentUrl());
        log.debug(driver.getPageSource());
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        throwable.printStackTrace();
    }

}
