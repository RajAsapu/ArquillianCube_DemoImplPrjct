package stepdef;


import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.CommonFunctions;
import setup.Constants;
import setup.DriverBean;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageCalculationRuleSteps extends CommonFunctions {

    final static Logger logger = Logger.getLogger(PageIndexSteps.class.getName());
    private static EventFiringWebDriver edriver;
    public PageCommonSteps steps;

    public PageCalculationRuleSteps()
    {
        edriver = DriverBean.getDriver();
        steps = new PageCommonSteps();
    }

    @When("^the user enters name  as \"([^\"]*)\"$")
    public void the_user_enters_name_as(String name) throws Throwable {
        enterName(name);
    }

    @When("^set the start date as \"([^\"]*)\"$")
    public void set_the_start_date_as(String date) throws Throwable {
        WebElement datepicker = edriver.findElement(By.xpath(Constants.calculationRuleCreate_startDatePicker_xpath));

        Actions act = new Actions(edriver);
        act.click(datepicker).sendKeys(date).sendKeys(Keys.TAB).perform();
    }

    @When("^set the end date as \"([^\"]*)\"$")
    public void set_the_end_date_as(String date) throws Throwable {
        WebElement datepicker = edriver.findElement(By.xpath(Constants.calculationRuleCreate_endDatePicker_xpath));

        Actions act = new Actions(edriver);
        act.click(datepicker).sendKeys(date).sendKeys(Keys.TAB).perform();
    }

    @When("^select type as \"([^\"]*)\"$")
    public void select_type_as(String type) throws Throwable {
        selectType(type);
    }

    @When("^enter the description as \"([^\"]*)\"$")
    public void enter_the_description_as(String desp) throws Throwable {
        enterDescription(desp);
    }

    @When("^select the day rule type as \"([^\"]*)\"$")
    public void select_the_day_rule_type_as(String rule) throws Throwable {
       selectDayRuleType(rule);
    }

    @When("^enter days before event as \"([^\"]*)\"$")
    public void enter_days_before_event_as(String days) throws Throwable {
        enterDaysBeforeEvent(days);
    }

    @When("^enter days after event as \"([^\"]*)\"$")
    public void enter_days_after_event_as(String days) throws Throwable {
        enterDaysAfterEvent(days);
    }

    @When("^(include|do not include) event day$")
    public void include_event_day(String cond) throws Throwable {
        if(cond.equals("include"))
        {
            includeEvent(true);
        }
        else if(cond.equals("do not include"))
        {
            includeEvent(false);
        }
    }

    @Then("^the calculation rule should be created$")
    public void the_calculation_rule_should_be_created(DataTable table)throws Exception
    {
        List<List<String>> rule=table.raw();
        /*
         * implemenet validation of the row
         */
        Thread.sleep(5000);
        if(!edriver.getCurrentUrl().contains("/calc-rule/list"))
        {
            throw new Exception("Calculation Rule is not created");
        }
    }

    @When("^start day of the week for the effective period as \"([^\"]*)\"$")
    public void start_day_of_the_week_for_the_effective_period_as(String day) throws Throwable {
        selectEffectiveStartDay(day);
    }

    @When("^set number of weeks for the effective period as \"([^\"]*)\"$")
    public void set_number_of_weeks_for_the_effective_period_as(String weeks) throws Throwable {
        enterEffTotalNoOfWeeks(weeks);
    }

    @When("^start day of the week for the calculation period as \"([^\"]*)\"$")
    public void start_day_of_the_week_for_the_calculation_period_as(String day) throws Throwable {
        selectCalculationStartDay(day);
    }

    @When("^set number of weeks for the calculation period as \"([^\"]*)\"$")
    public void set_number_of_weeks_for_the_calculation_period_as(String weeks) throws Throwable {
        enterCalTotalNoOfWeeks(weeks);
    }

    @When("^set offset as \"(\\d+)$")
    public void set_offset_as(int offset) throws Throwable {
        enterCalOffset(String.valueOf(offset));
    }

    @When("^overlap (is|is not) allowed$")
    public void overlap_is_allowed(String cond) throws Throwable {
        if(cond.equals("is"))
        {
            overLapAllowed(true);
        }
        else if(cond.equals("is not"))
        {
            overLapAllowed(false);
        }
    }

    @And("^set month rule type as \"([^\"]*)\"$")
    public void set_month_rule_type_as(String rule) throws Throwable {
        selectMonthRuleType(rule);
    }

    @And("^number of months for effective period as \"([^\"]*)\"$")
    public void number_of_months_for_effective_period_as(String months) throws Throwable {
       setNoOfMonthsEffPeriod(months);
    }

    @And("^effective start day of month as \"([^\"]*)\"$")
    public void effective_start_day_of_month_as(String day) throws Throwable {
        setEffStartDayOfMonth(day);
    }

    @And("^number of months for calculation period as \"([^\"]*)\"$")
    public void number_of_months_for_calculation_period_as(String months) throws Throwable {
        setNoOfMonthsCalPeriod(months);
    }

    @And("^set calculation start day of month as \"([^\"]*)\"$")
    public void set_calculation_start_day_of_month_as(String day) throws Throwable {
       setCalStartDayOfMonth(day);
    }

    @When("^the user enters \"([^\"]*)\" as \"([^\"]*)\"$")
    public void the_user_enters_as(String filter, String key) throws Throwable {
        WebElement filterType = null;
        if(filter.equalsIgnoreCase("Name"))
        {
            filterType=edriver.findElement(By.xpath(Constants.calculationRuleList_searchByName_xpath));
        }
        else if(filter.equalsIgnoreCase("Type"))
        {
            filterType=edriver.findElement(By.xpath(Constants.calculationRuleList_searchByType_xpath));
        }
        else if(filter.equalsIgnoreCase("Description"))
        {
            filterType=edriver.findElement(By.xpath(Constants.calculationRuleList_searchByDescrp_xpath));
        }
        filterType.sendKeys(key);
        Thread.sleep(3000);
    }

    @Then("^the list should display the records matching the filter criteria$")
    public void the_list_should_display_the_records_matching_the_filter_criteria(DataTable table) throws Throwable {

        List<List<String>> rows = table.raw();
        String type = rows.get(0).get(0);
        String key = rows.get(0).get(1);
        List<WebElement> rowList = null;

        if(type.equalsIgnoreCase("Name"))
        {
            rowList=edriver.findElements(By.xpath(Constants.calculationRuleList_nameColumn_xpath));
        }
        else if(type.equalsIgnoreCase("Type"))
        {
            rowList=edriver.findElements(By.xpath(Constants.calculationRuleList_typeColumn_xpath));
        }
        else if(type.equalsIgnoreCase("Description"))
        {
            rowList=edriver.findElements(By.xpath(Constants.calculationRuleList_descpColumn_xpath));
        }

        for(WebElement temp:rowList)
        {
            if(!temp.getText().equals(key))
            {
                throw new Exception("Filter:"+type+" doesnt match results, Expected:"+key+" Actual:"+temp.getText());
            }
        }

    }

    @And("^the user clicks on the add new rule button$")
    public void the_user_clicks_on_the_add_new_rule_button()
    {
        WebElement newRule = edriver.findElement(By.linkText(Constants.calculationRuleList_addNewRule_linkText));
        newRule.click();
    }

    @And("^the user clicks on \"([^\"]*)\"$")
    public void the_user_clicks_on(String hdr) throws Throwable
    {
        WebElement header = null;
        if(hdr.equals("Status"))
        {
            header = edriver.findElement(By.xpath(Constants.calculationRuleList_hdrStatusColumn_xpath));
        }
        else if(hdr.equals("Name"))
        {
            header = edriver.findElement(By.xpath(Constants.calculationRuleList_hdrNameColumn_xpath));
        }
        else if(hdr.equals("Type"))
        {
            header = edriver.findElement(By.xpath(Constants.calculationRuleList_hdrTypeColumn_xpath));
        }
        else if(hdr.equals("Description"))
        {
            header = edriver.findElement(By.xpath(Constants.calculationRuleList_hdrDescrpColumn_xpath));
        }
        header.click();
        Thread.sleep(3000);
    }

    @Then("^the calculation rules shall be displayed in sorted order$")
    public void the_calculation_rules_shall_be_displayed_in_sorted_order(DataTable table) throws Throwable {
        List<List<String>> rows = table.raw();
        String header = rows.get(0).get(0);
        List<WebElement> columnList = null;
        List<String> colList =new ArrayList<>();
        List<String> compareList=new ArrayList<>();
        if(header.equals("Status"))
        {
            columnList = edriver.findElements(By.xpath(Constants.calculationRuleList_statusColumn_xpath));
        }
        else if(header.equals("Name"))
        {
            columnList = edriver.findElements(By.xpath(Constants.calculationRuleList_nameColumn_xpath));
        }
        else if(header.equals("Type"))
        {
            columnList = edriver.findElements(By.xpath(Constants.calculationRuleList_typeColumn_xpath));
        }
        else if(header.equals("Description"))
        {
            columnList = edriver.findElements(By.xpath(Constants.calculationRuleList_descpColumn_xpath));
        }
        for(WebElement temp:columnList)
        {
            colList.add(temp.getText());
            compareList.add(temp.getText());
        }
        Collections.sort(colList);
        if(!colList.equals(compareList))
        {
            Collections.reverse(colList);
            if(!colList.equals(compareList))
            {
                throw new Exception("Column:"+header+" is not sorted");
            }
        }
    }

}
