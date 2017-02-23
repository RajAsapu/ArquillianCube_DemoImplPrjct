package stepdef;

import com.google.common.base.Verify;
import cucumber.api.java.en.And;
import org.codehaus.groovy.runtime.dgmimpl.arrays.IntegerArrayGetAtMetaMethod;
import setup.commonFunctions;
import setup.constants;
import setup.driverBean;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class pageIndexSteps extends commonFunctions {
    private static EventFiringWebDriver edriver= driverBean.getDriver();
    final static Logger logger = Logger.getLogger(pageIndexSteps.class.getName());
    public pageCommonSteps steps=new pageCommonSteps();

    @When("^the user enters the start date as \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_enters_the_start_date_as_and_status_as(String date, String status)throws Exception{

        WebElement datepicker=edriver.findElement(By.xpath(constants.indexList_startdatepicker_xpath));

        Actions act=new Actions(edriver);
        act.click(datepicker).sendKeys(date).perform();
        act.sendKeys(Keys.TAB).perform();

        setStatusIndex(status);
        setType(type.manual);
    }


    @Then("^the user shall be able to view the list of indexes with start date from \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_with_start_date_from_and_status_as(String arg1, String arg2) throws Exception {

        List<WebElement> statusList=edriver.findElements(By.xpath(constants.indexList_StatusColumn_xpath));

        for(WebElement temp:statusList)
        {
            if(!temp.equals(arg2))
            {
                assert false;
            }
        }

        List<WebElement> startdateList=edriver.findElements(By.xpath(constants.indexList_StartDateColumn_xpath));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date dateSelected=formatter.parse(arg1);
        for(WebElement temp:startdateList)
        {
            Date tempDate=formatter.parse(temp.getText());
            if(tempDate.compareTo(dateSelected)!=1)
                assert false;
        }

        edriver.quit();
    }

    @When("^the user enters the end date as \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_enters_the_end_date_as_and_status_as(String date, String status) throws Throwable {
        WebElement datepicker=edriver.findElement(By.xpath(constants.indexList_enddatepicker_xapth));

        Actions act=new Actions(edriver);
        act.click(datepicker).sendKeys(date).perform();
        act.sendKeys(Keys.TAB).perform();

        setStatusIndex(status);
        setType(type.manual);
    }

    @Then("^the user shall be able to view the list of indexes with end date from \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_with_end_date_from_and_status_as(String arg1, String arg2) throws Throwable {
        List<WebElement> statusList=edriver.findElements(By.xpath(constants.indexList_StatusColumn_xpath));

        for(WebElement temp:statusList)
        {
            if(!temp.getText().equals(arg2))
            {
                assert false;
            }
        }

        List<WebElement> startdateList=edriver.findElements(By.xpath(constants.indexList_EndDateColumn_xpath));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date dateSelected=formatter.parse(arg1);
        for(WebElement temp:startdateList)
        {
            Date tempDate=null;
            try {
                tempDate = formatter.parse(temp.getText());
            }
            catch (java.text.ParseException exp)
            {
                if(exp.getMessage().contains("Unparseable date: \"\""))
                {
                    throw new Exception("End date is empty !!");
                }
                else
                {
                    exp.printStackTrace();
                }
            }
            if(tempDate.compareTo(dateSelected)!=1)
                assert false;
        }

        edriver.quit();
    }
    @When("^the user enters the type as \"([^\"]*)\"$")
    public void the_user_enters_the_type_as(String type) throws Throwable {
        if(type.equals("MANUAL"))
            setType(commonFunctions.type.manual);
        else if(type.equals("AUTOMATIC"))
            setType(commonFunctions.type.automatic);
        else
            logger.error("Invalid type");
    }

    @Then("^the user shall be able to view the list of indexes with type as \"([^\"]*)\"$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_with_type_as(String type) throws Throwable {

        List<WebElement> typeList=edriver.findElements(By.xpath(constants.indexList_typeColumn_xpath));

        for(WebElement temp:typeList)
        {
            if(!temp.getText().equals(type))
            {
                throw new Exception("Type doesn't match (Expected:+"+type+",Actual:"+temp.getText()+") !!");
            }
        }

    }

    @When("^the user enters rate basis as \"([^\"]*)\"$")
    public void the_user_enters_rate_basis_as(String rateBase) throws Throwable {
        setRateBasis(rateBase);
    }

    @When("^name as \"([^\"]*)\"$")
    public void name_as(String name) throws Throwable {
        selectName(name);
    }

    @And("^comment as \"([^\"]*)\"$")
    public void comment_as(String comment) throws Throwable {
        edriver.findElement(By.xpath(constants.indexCreate_comment_xpath)).sendKeys(comment);
    }

    @When("^currency as \"([^\"]*)\"$")
    public void currency_as(String curr) throws Throwable {
        selectCurrency(curr);
    }

    @When("^unit of measurement as \"([^\"]*)\"$")
    public void unit_of_measurement_as(String uom) throws Throwable {
        selectUOM(uom);
    }

    @Then("^the user shall be able to view the list of indexes matching the above search criteria$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_matching_the_above_search_criteria() throws Throwable {
        checkRateBasis_IndexTabe("FLAT");
        checkName_IndexTabe("NY RBOB Prem Brg");
        checkCurrency_IndexTabe("USD");
        checkUom_IndexTabe("USG");
    }
     /*
      Check if the index list has expected Rate basis type
      */
     public void checkRateBasis_IndexTabe(String rate)throws Exception
     {
         List<WebElement> rateList= edriver.findElements(By.xpath(constants.indexList_ratebasisColumn_xpath));

         for(WebElement temp:rateList)
         {
             if(!temp.getText().equals(rate))
             {
                 throw new Exception("Rate Basis doesn't match, Expected :"+rate+" Actual:"+temp.getText());
             }
         }
     }
    /*
    Check if the index list has expected name
    */
    public void checkName_IndexTabe(String name)throws Exception
    {
        List<WebElement> nameList= edriver.findElements(By.xpath(constants.indexList_nameColumn_xpath));

        for(WebElement temp:nameList)
        {
            if(!temp.getText().equals(name.toUpperCase()))
            {
                throw new Exception("Name doesn't match, Expected :"+name+" Actual:"+temp.getText());
            }
        }
    }
    /*
    Check if the index list has expected currency
    */
    public void checkCurrency_IndexTabe(String currency)throws Exception
    {
        List<WebElement> currList= edriver.findElements(By.xpath(constants.indexList_currencyColumn_xpath));

        for(WebElement temp:currList)
        {
            if(!temp.getText().equals(currency))
            {
                throw new Exception("Currency doesn't match, Expected :"+currency+" Actual:"+temp.getText());
            }
        }
    }
    /*
   Check if the index list has expected currency
   */
    public void checkUom_IndexTabe(String uom)throws Exception
    {
        List<WebElement> uomList= edriver.findElements(By.xpath(constants.indexList_uomColumn_xpath));

        for(WebElement temp:uomList)
        {
            if(!temp.getText().equals(uom))
            {
                throw new Exception("UOM doesn't match, Expected :"+uom+" Actual:"+temp.getText());
            }
        }
    }
    /*
     Method to insert high,medium,low and close prices
     */
    @When("^([^\"]*),([^\"]*),([^\"]*) and closePrice are entered$")
    public void and_are_entered(String low, String mid, String high) throws Throwable {
        edriver.findElement(By.xpath(constants.indexCreate_lowprice_xpath)).sendKeys(low.trim());
        edriver.findElement(By.xpath(constants.indexCreate_midprice_xpath)).sendKeys(mid.trim());
        edriver.findElement(By.xpath(constants.indexCreate_highprice_xpath)).sendKeys(high.trim());
        Random r=new Random();
        edriver.findElement(By.xpath(constants.indexCreate_closeprice_xpath)).sendKeys(String.valueOf(new DecimalFormat("$#.00000").format(r.nextDouble()* Double.parseDouble(high))));
    }

    @And("^start date as \"([^\"]*)\" and end date as \"([^\"]*)\"")
    public void enterStartDateAndEndDate(String startDate, String endDate) throws Throwable {
        WebElement datepicker=edriver.findElement(By.xpath(constants.indexCreate_startDatePicker_xpath));

        Actions act=new Actions(edriver);
        act.click(datepicker).sendKeys(startDate).perform();
        act.sendKeys(Keys.TAB).perform();

        datepicker=edriver.findElement(By.xpath(constants.indexCreate_endDatePicker_xpath));
        act.click(datepicker).sendKeys(endDate).perform();
        act.sendKeys(Keys.TAB).perform();
    }
    @Then("^the user shall be able to view the created index in the list on filtering with \"([^\"]*)\"$")
    public void the_user_shall_be_able_to_view_the_created_index_in_the_list_on_filtering_with(String rate) throws Throwable {
        Thread.sleep(5000);
        Verify.verify(edriver.getCurrentUrl().contains("/index/list"));
        setRateBasis(rate);
        setStatusIndex("Active");
        steps.clicks_on_the_search_button();
    }
}
