package setup;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CommonFunctions {

    final static Logger logger = Logger.getLogger(CommonFunctions.class.getName());
    private static EventFiringWebDriver edriver= DriverBean.getDriver();
    public enum page{List,Create};
    public enum module{Calculation_Rule,Workbook,Index,Currency_Exchange,Formula}
    public enum type {all,manual,automatic}
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
        WebElement options=edriver.findElement(By.xpath(Constants.indexList_status_xpath));
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
        WebElement type=edriver.findElement(By.xpath(Constants.indexList_type_xpath));
        Select s=new Select(type);

        switch (temp)
        {
            case all:s.selectByIndex(0);
                break;
            case automatic:s.selectByIndex(2);
                    break;
            case manual:   s.selectByIndex(1);
                    break;
            default:logger.error("Please provide valid type value in the index page");
        }
    }
    public void setRateBasis(String rate)
    {
        WebElement options=edriver.findElement(By.xpath(Constants.indexList_rateBasis_xpath));
        Select s=new Select(options);
        s.selectByVisibleText(rate);
    }
    public void selectName(String key)
    {
        /*
          Need to implement code to select the text from auto fill
         */
        edriver.findElement(By.xpath(Constants.indexList_name_xpath)).sendKeys(key.substring(0,key.length()-1));
        List<WebElement> autofillList=edriver.findElements(By.xpath(Constants.indexList_name_autofill_xpath));
        if(autofillList.size()==0)
        {
            autofillList=edriver.findElements(By.xpath(Constants.indexCreate_name_autofill_path));
        }
        autofillList.listIterator().next().click();
    }
    public void selectCurrency(String key)
    {
        List<WebElement> autofillList=edriver.findElements(By.xpath(Constants.indexList_currency_xpath));
        if(autofillList.size()==0)
        {
            autofillList=edriver.findElements(By.xpath(Constants.indexCreate_currency_xpath));
        }
        Select currList=new Select(autofillList.listIterator().next());
        currList.selectByVisibleText(key);
    }
    public void selectUOM(String key)
    {
        List<WebElement> autofillList=edriver.findElements(By.xpath(Constants.indexList_uom_xpath));
        if(autofillList.size()==0)
        {
            autofillList=edriver.findElements(By.xpath(Constants.indexCreate_uom_xpath));
        }
        Select currList=new Select(autofillList.listIterator().next());
        currList.selectByVisibleText(key);
    }
}
