package functions;

import com.google.common.base.Verify;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import setup.Constants;
import setup.DriverBean;

public class PageCommonMethods {

    protected EventFiringWebDriver edriver;
    protected static Logger log = null;
    protected WebDriverWait webDriverWait;
    /*
     * Constructor to intialize the dependent classes
     */
    public PageCommonMethods()
    {
        edriver= DriverBean.getDriver();
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
        act.moveToElement(edriver.findElement(By.xpath("//*/a[normalize-space()='"+m.toString().replace("_", " ")+"']"))).clickAndHold().perform();
        waitFor(1);
        act.click(edriver.findElement(By.xpath("//*/a[normalize-space()='"+p.toString()+"']"))).perform();
        System.out.println("Navigated to " + p.toString() + " page under " + m.toString());
    }

    /*
     * Method to login to the application
     */
    public void login() {
        log.info("Url"+edriver.getCurrentUrl()+"\n");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(edriver,30,5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.login_username_xpath))).sendKeys(Constants.username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.login_password_xpath))).sendKeys(Constants.password);
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
