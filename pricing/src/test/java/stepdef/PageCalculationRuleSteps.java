package stepdef;

import com.google.common.base.Verify;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageCalculationRuleSteps extends CommonFunctions {

    final static Logger logger = Logger.getLogger(PageIndexSteps.class.getName());
    private static EventFiringWebDriver edriver;
    public PageCommonSteps steps;
    public String ruleName;

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
        edriver.findElement(By.xpath(Constants.calculationRuleCreate_endDate_xpath)).clear();
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

    @Then("^the calculation rule should be (created|updated)$")
    public void the_calculation_rule_should_be_createdOrupdated(String status,DataTable table)throws Exception
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
    public void the_list_should_display_the_records_matching_the_filter_criteria(List<List<String>> table) throws Throwable {
        String type = table.get(0).get(0);
        String key = table.get(0).get(1);
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

    @Then("^the calculation rules shall be displayed in (ascending order|descending order)$")
    public void the_calculation_rules_shall_be_displayed_in_sorted_order(String order,DataTable table) throws Throwable {
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
            if(temp.getText()!=null && !temp.getText().equals(""))
            {
                colList.add(temp.getText().toLowerCase());
                compareList.add(temp.getText().toLowerCase());
            }
        }
        if(order.contains("asc"))
        {
            Collections.sort(colList);
            if(!colList.equals(compareList))
            {
                throw new Exception("Column:"+header+" is not sorted in ascending order");
            }
        }
        else
        {
            Collections.sort(colList);
            Collections.reverse(colList);
            if(!colList.equals(compareList))
            {
                throw new Exception("Column:"+header+" is not sorted in descending order");
            }
        }
    }

    @When("^the user deactivates the existing plan$")
    public void the_user_deactivates_the_existing_plan() throws Throwable {
        WebElement header = edriver.findElement(By.xpath(Constants.calculationRuleList_hdrStatusColumn_xpath));
        // Ascending order
        header.click();

        ruleName = edriver.findElement(By.xpath(Constants.calculationRuleList_nameColumn_xpath)).getText();

        WebElement inactive = edriver.findElement(By.xpath(Constants.calculationRuleList_actionInactive_xpath));
        inactive.click();

    }

    @Then("^the calculation rules should display the status as inactive$")
    public void the_calculation_rules_should_display_the_status_as_inactive() throws Throwable {
        the_user_enters_as("Name", ruleName);
        List<List<String>> list=new ArrayList<>();
        List<String> sublist=new ArrayList<>();
        sublist.add("Name");
        sublist.add(ruleName);
        list.add(sublist);
        the_list_should_display_the_records_matching_the_filter_criteria(list);
    }

    @When("^the user clicks on the (view|edit) button of a plan$")
    public void the_user_clicks_on_the_view_button_of_a_plan(String act) throws Throwable {
        WebElement temp = null;
        if(act.equals("view"))
        {
            temp = edriver.findElement(By.xpath(Constants.calculationRuleList_actionView_xpath));
        }else if(act.equals("edit"))
        {
            temp = edriver.findElement(By.xpath(Constants.calculationRuleList_actionEdit_xpath));
        }
        temp.click();
    }

    @Then("^the user shall be able to (view|edit) the calculation rule details$")
    public void the_user_shall_be_able_to_view_the_calculation_rule_details(String act,DataTable table) throws Throwable {

        assert !edriver.findElement(By.id(Constants.calculationRuleCreate_name_id)).isEnabled();
        assert !edriver.findElement(By.xpath(Constants.calculationRuleCreate_type_xpath)).isEnabled();
        assert !edriver.findElement(By.name(Constants.calculationRuleCreate_description_name)).isEnabled();

        if(act.equals("view")) {
            assert !edriver.findElement(By.id(Constants.calculationRuleCreate_daysBeforeEvent_id)).isEnabled();
            assert !edriver.findElement(By.id(Constants.calculationRuleCreate_daysAfterEvent_id)).isEnabled();
            assert !edriver.findElement(By.id(Constants.calculationRuleCreate_includeEventDay_id)).isEnabled();}
        else if(act.equals("edit"))
        {
            List<List<String >> row=table.raw();
            set_the_end_date_as(row.get(0).get(0));
        }
    }

    @Then("^the application displays an error message as \"(.*)\"$")
    public void the_application_displays_an_error_message_as(String errMsg) throws Throwable {
       Verify.verify(edriver.findElement(By.xpath("//*[normalize-space()='"+errMsg+"']")).isDisplayed(),"Error Message is not displayed!!");
    }

}
