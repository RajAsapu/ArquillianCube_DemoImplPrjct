package functions;

import com.google.common.base.Verify;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setup.Constants;
import setup.DriverBean;

public class PageCommonMethods {

    public static EventFiringWebDriver edriver;
    private static Logger log = null;
    private WebDriverWait webDriverWait;

    /*
  * Constructor to intialize the dependent classes
  */
    public PageCommonMethods() {
        log = LoggerFactory.getLogger(PageCommonMethods.class);
        edriver = DriverBean.getDriver();
    }

    public void waitFor(long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds*1000);
        } catch (InterruptedException|NullPointerException e) {
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
        act.moveToElement(edriver.findElement(By.linkText(m.toString().replace("_", " ")))).clickAndHold().perform();
        Thread.sleep(1000);
        act.click(edriver.findElement(By.linkText(p.toString()))).perform();
        log.info("Navigated to " + p.toString() + " page under " + m.toString());
    }

    /*
     * Method to login to the application
     */
    public void login() {
        edriver.findElement(By.id(Constants.login_username_id)).sendKeys(Constants.username);
        edriver.findElement(By.id(Constants.login_password_id)).sendKeys(Constants.password);
        edriver.findElement(By.id(Constants.login_submit_id)).click();
    }

    /*
     * Method to verify if the error message is displayed or not
     * isDisplayed : true - Message should be displayed
     *               false - Message should not be displayed
     */
    public void verifyIfErrorMessageIsDisplayed(String message, boolean isDisplayed) {
        try {
            if (isDisplayed) {
                Verify.verify(edriver.findElement(By.xpath("//*[normalize-space()='" + message + "']")).isDisplayed(), "Error Message is not displayed!!");
            } else {
                Verify.verify(!edriver.findElement(By.xpath("//*[normalize-space()='" + message + "']")).isDisplayed(), "Error Message is displayed!!");
            }
        } catch (NullPointerException | NoSuchElementException exp) {
            Assert.fail("Error message is not displayed, Expected error message:" + message);
        }
    }

    public enum module {
        Calculation_Rule, Workbook, Index, Currency_Exchange, Formula
    }

    public enum page {
        List, Create
    }
}