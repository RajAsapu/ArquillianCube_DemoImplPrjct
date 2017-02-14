package Setup;

import StepDef.Page_Common_steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommonFn extends Page_Common_steps{

    enum page{List,Create};
    enum module{Calculation_Rule,Workbook,Index,Currency_Exchange,Formula}

    public void moveTo(module m,page p)
    {
        driver.findElement(By.linkText(m.toString().replace("_",""))).click();

        List<WebElement> list=driver.findElements(By.linkText(p.toString()));

        for(WebElement temp:list)
        {
            if(temp.isDisplayed())
            {
                temp.click();
                break;
            }
        }
    }
}
