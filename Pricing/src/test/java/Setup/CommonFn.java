package Setup;

import org.apache.log4j.Logger;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static Setup.CommonFn.rateBasis.*;

public class CommonFn {

    final static Logger logger = Logger.getLogger(CommonFn.class.getName());
    private static EventFiringWebDriver edriver= DriverBean.getDriver();
    public enum page{List,Create};
    public enum module{Calculation_Rule,Workbook,Index,Currency_Exchange,Formula}
    public enum type {manual,automatic}
    public enum rateBasis{All,Flat,Price_Point_Scale,Point_Break_Scale}

    /*
     Method to login to the application
     */
    public void login()
    {
        edriver.findElement(By.id(Constants.login_username_id)).sendKeys(Constants.username);
        edriver.findElement(By.id(Constants.login_password_id)).sendKeys(Constants.password);
        edriver.findElement(By.id(Constants.login_submit_id)).click();
    }
    /*
     Method to navigate to a page under a module (eg : List under Index)
     */
    public void moveTo(module m,page p)
    {
        edriver.findElement(By.linkText(m.toString().replace("_",""))).click();

        List<WebElement> list=edriver.findElements(By.linkText(p.toString()));

        for(WebElement temp:list)
        {
            if(temp.isDisplayed())
            {
                temp.click();
                break;
            }
        }
        logger.info("User has navigated to "+p+" page under "+m+" module");
    }

    /*
     Index page common methods
     */
    public void setStatusIndex(String select)
    {
        WebElement options=edriver.findElement(By.xpath(Constants.index_status_xpath));
        Select s=new Select(options);
        switch (select)
        {
            case "All": s.selectByIndex(0);
                        break;
            case "Active":s.selectByIndex(1);
                        break;
            case "Inactive":s.selectByIndex(2);
                        break;
            default:System.out.println("Please provide valid status value in the index page");
        }
    }

    public void setType(type temp)
    {
        WebElement type=edriver.findElement(By.xpath(Constants.index_type_xpath));
        switch (temp)
        {
            case automatic:type.sendKeys("AUTOMATIC");
                    break;
            case manual:   type.sendKeys("MANUAL");
                    break;
            default:logger.error("Please provide valid type value in the index page");
        }
    }
    public void setRateBasis(String rate)
    {
        WebElement options=edriver.findElement(By.xpath(Constants.index_rateBasis_xpath));
        Select s=new Select(options);
        switch (rate)
        {
            case "All": s.selectByIndex(0);
                break;
            case "Flat":s.selectByIndex(1);
                break;
            case "Price_Point_Scale":s.selectByIndex(2);
                break;
            case "Point_Break_Scale":s.selectByIndex(3);
                break;
            default:logger.error("Please provide valid rate Basis value in the index page");
        }
    }
    public void selectName(String key)
    {
        /*
          Need to implement code to select the text from auto fill
         */
        edriver.findElement(By.xpath(Constants.index_name_xpath)).sendKeys(key.substring(0,key.length()-1));
        edriver.findElement(By.xpath(Constants.index_name_autofill_xpath)).click();
    }
    public void selectCurrency(String key)
    {
        WebElement options=edriver.findElement(By.xpath(Constants.index_currency_xpath));
        Select currList=new Select(options);
        currList.selectByVisibleText(key);
    }
    public void selectUOM(String key)
    {
        WebElement options=edriver.findElement(By.xpath(Constants.index_uom_xpath));
        Select uomList=new Select(options);
        uomList.selectByVisibleText(key);
    }
}
