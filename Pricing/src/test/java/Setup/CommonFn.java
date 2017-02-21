package Setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CommonFn {

    private static EventFiringWebDriver edriver= DriverBean.getDriver();
    public enum page{List,Create};
    public enum module{Calculation_Rule,Workbook,Index,Currency_Exchange,Formula}

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
    }

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

    public void login()
    {
        edriver.findElement(By.id(Constants.login_username_id)).sendKeys(Constants.username);
        edriver.findElement(By.id(Constants.login_password_id)).sendKeys(Constants.password);
        edriver.findElement(By.id(Constants.login_submit_id)).click();
    }
}
