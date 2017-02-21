package StepDef;

import Setup.CommonFn;
import Setup.Constants;
import Setup.DriverBean;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Page_Index_Steps extends CommonFn{
    private static WebDriver driver= DriverBean.getDriver();

    @When("^the user enters the start date as \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_enters_the_start_date_as_and_status_as(String date, String status)throws Exception{

        WebElement datepicker=driver.findElement(By.xpath(Constants.index_startdatepicker_xpath));

        Actions act=new Actions(driver);
        act.click(datepicker).sendKeys(date).perform();
        act.sendKeys(Keys.TAB).perform();

        setStatusIndex(status);
    }


    @Then("^the user shall be able to view the list of indexes with start date from \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_with_start_date_from_and_status_as(String arg1, String arg2) throws Exception {

        List<WebElement> statusList=driver.findElements(By.xpath(Constants.index_StatusColumn_xpath));

        for(WebElement temp:statusList)
        {
            if(!temp.equals(arg2))
            {
                assert false;
            }
        }

        List<WebElement> startdateList=driver.findElements(By.xpath(Constants.index_StartDateColumn_xpath));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date dateSelected=formatter.parse(arg1);
        for(WebElement temp:startdateList)
        {
            Date tempDate=formatter.parse(temp.getText());
            if(tempDate.compareTo(dateSelected)!=1)
                assert false;
        }

        driver.quit();
    }

    @When("^the user enters the end date as \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_enters_the_end_date_as_and_status_as(String date, String status) throws Throwable {
        WebElement datepicker=driver.findElement(By.xpath(Constants.index_enddatepicker_xapth));

        Actions act=new Actions(driver);
        act.click(datepicker).sendKeys(date).perform();
        act.sendKeys(Keys.TAB).perform();

        setStatusIndex(status);
    }

    @Then("^the user shall be able to view the list of indexes with end date from \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_with_end_date_from_and_status_as(String arg1, String arg2) throws Throwable {
        List<WebElement> statusList=driver.findElements(By.xpath(Constants.index_StatusColumn_xpath));

        for(WebElement temp:statusList)
        {
            if(!temp.equals(arg2))
            {
                assert false;
            }
        }

        List<WebElement> startdateList=driver.findElements(By.xpath(Constants.index_EndDateColumn_xpath));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date dateSelected=formatter.parse(arg1);
        for(WebElement temp:startdateList)
        {
            Date tempDate=formatter.parse(temp.getText());
            if(tempDate.compareTo(dateSelected)!=1)
                assert false;
        }

        driver.quit();
    }
}
