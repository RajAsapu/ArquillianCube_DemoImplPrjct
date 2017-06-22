package functions;

import com.google.common.base.Verify;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setup.Constants;
import setup.DriverBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PageCommonMethods {

    protected static Logger log = null;
    protected EventFiringWebDriver edriver;
    protected WebDriverWait webDriverWait;

    /*
     * Constructor to intialize the dependent classes
     */
    public PageCommonMethods() {
        edriver = DriverBean.getDriver();
        log = LoggerFactory.getLogger(PageCommonMethods.class);
    }

    public void waitFor(long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method to navigate to a page under a module (eg : List under Index)
     *  Calculation Rule
     *  Workbook
     *  Index
     *  Currency Exchange
     *  Formula
     */
    public void moveTo(page p, module m) throws Exception {
        Actions act = new Actions(edriver);
        act.moveToElement(edriver.findElement(By.xpath("//*/a[normalize-space()='" + m.toString().replace("_", " ") + "']"))).clickAndHold().perform();
        waitFor(1);
        List<WebElement> elementList = edriver.findElements(By.xpath("//*/*[normalize-space()='" + p.toString() + "']"));
        for (WebElement temp : elementList) {
            if (temp.isDisplayed()) {
                act.click(temp).perform();
            }
        }
        System.out.println("Navigated to " + p.toString() + " page under " + m.toString());
    }

    /*
     * Method to login to the application
     */
    public void login() {
        try{
            edriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            edriver.findElement(By.partialLinkText("Back to Application")).click();
            log.info("KeyCloak displayed error 'Invalid parameter: redirect_uri'");
        }catch (Exception exp){}
        edriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement element = (new WebDriverWait(edriver, 60)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(By.xpath(Constants.login_username_xpath));
            }
        });
        element.sendKeys(Constants.username);
        edriver.findElement(By.xpath(Constants.login_password_xpath)).sendKeys(Constants.password);
        edriver.findElement(By.xpath(Constants.login_submit_xpath)).click();
    }

    /*
     * Method to verify if the error message is displayed or not
     * isDisplayed : true - Message should be displayed
     *               false - Message should not be displayed
     */
    public void verifyIfErrorMessageIsDisplayed(String errorOrSuccess, String message, boolean isDisplayed) {
        try {
            WebDriverWait wait = new WebDriverWait(edriver, 10);
            if (isDisplayed) {
                wait.until(ExpectedConditions.visibilityOf(edriver.findElement(By.xpath("//*[normalize-space()=\"" + message + "\"]"))));
            } else {
                Verify.verify(!edriver.findElement(By.xpath("//*[normalize-space()=\"" + message + "\"]")).isDisplayed(), errorOrSuccess + " Message is displayed!!");
            }
        } catch (NullPointerException | NoSuchElementException exp) {
            Assert.fail(errorOrSuccess + " message is not displayed, Expected " + errorOrSuccess + " message:" + message);
        }
    }

    public enum module {
        Calculation_Rule, Workbook, Index, Currency_Exchange, Formula
    }

    public enum page {
        List, Create
    }
}
