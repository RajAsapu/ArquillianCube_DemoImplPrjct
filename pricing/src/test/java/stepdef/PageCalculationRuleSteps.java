package stepdef;

import com.google.common.base.Verify;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import functions.CreateCalculationRuleMethods;
import functions.ListCalculationRuleMethods;
import functions.PageCommonMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageCalculationRuleSteps  {

    final static Logger logger = Logger.getLogger(PageIndexSteps.class.getName());
    private static EventFiringWebDriver edriver;
    public PageCommonMethods pageCommonMethods;
    public String ruleName;
    private CreateCalculationRuleMethods createCalculationRuleMethods;
    private ListCalculationRuleMethods listCalculationRuleMethods;

    public PageCalculationRuleSteps() {
        edriver = DriverBean.getDriver();
        pageCommonMethods = new PageCommonMethods();
        createCalculationRuleMethods = new CreateCalculationRuleMethods();
        listCalculationRuleMethods = new ListCalculationRuleMethods();
    }

    @When("^the user enters name  as \"([^\"]*)\"$")
    public void the_user_enters_name_as(String name) throws Throwable {
        createCalculationRuleMethods.setName(name);
    }

    @When("^set the start date as \"([^\"]*)\"$")
    public void set_the_start_date_as(String startDate) throws Throwable {
        createCalculationRuleMethods.setStartDate(startDate);
    }

    @When("^set the end date as \"([^\"]*)\"$")
    public void set_the_end_date_as(String endDate) throws Throwable {
        createCalculationRuleMethods.setEndDate(endDate);
    }

    @When("^select type as \"([^\"]*)\"$")
    public void select_type_as(String type) throws Throwable {
        createCalculationRuleMethods.setType(type);
    }

    @When("^enter the description as \"([^\"]*)\"$")
    public void enter_the_description_as(String description) throws Throwable {
        createCalculationRuleMethods.setDescription(description);
    }

    @When("^select the day rule type as \"([^\"]*)\"$")
    public void select_the_day_rule_type_as(String rule) throws Throwable {
        createCalculationRuleMethods.setDayRuleType(rule);
    }

    @When("^enter days before event as \"([^\"]*)\"$")
    public void enter_days_before_event_as(String daysBeforeEvent) throws Throwable {
       createCalculationRuleMethods.setDaysBeforeEvent(daysBeforeEvent);
    }

    @When("^enter days after event as \"([^\"]*)\"$")
    public void enter_days_after_event_as(String daysAfterEvent) throws Throwable {
       createCalculationRuleMethods.setDaysAfterEvent(daysAfterEvent);
    }

    @When("^(include|do not include) event day$")
    public void include_event_day(String cond) throws Throwable {
        if (cond.equals("include")) {
            createCalculationRuleMethods.includeEventDay(true);
        } else if (cond.equals("do not include")) {
            createCalculationRuleMethods.includeEventDay(false);
        }
    }

    @Then("^the calculation rule should be (created|updated)$")
    public void the_calculation_rule_should_be_createdOrupdated(String status, DataTable table) throws Exception {
        List<List<String>> rule = table.raw();
        createCalculationRuleMethods.verifyIfPageIsCalculationRuleList();
    }

    @When("^start day of the week for the effective period as \"([^\"]*)\"$")
    public void start_day_of_the_week_for_the_effective_period_as(String day) throws Throwable {
        createCalculationRuleMethods.setEffectiveStartDay(day);
    }

    @When("^set number of weeks for the effective period as \"([^\"]*)\"$")
    public void set_number_of_weeks_for_the_effective_period_as(String weeks) throws Throwable {
        createCalculationRuleMethods.setEffTotalNoOfWeeks(weeks);
    }

    @When("^start day of the week for the calculation period as \"([^\"]*)\"$")
    public void start_day_of_the_week_for_the_calculation_period_as(String day) throws Throwable {
        createCalculationRuleMethods.setCalculationStartDay(day);
    }

    @When("^set number of weeks for the calculation period as \"([^\"]*)\"$")
    public void set_number_of_weeks_for_the_calculation_period_as(String weeks) throws Throwable {
        createCalculationRuleMethods.setCalTotalNoOfWeeks(weeks);
    }

    @When("^set offset as \"(\\d+)$")
    public void set_offset_as(int offset) throws Throwable {
        createCalculationRuleMethods.setCalOffset(String.valueOf(offset));
    }

    @When("^overlap (is|is not) allowed$")
    public void overlap_is_allowed(String cond) throws Throwable {
        if (cond.equals("is")) {
            createCalculationRuleMethods.overlapAllowed(true);
        } else if (cond.equals("is not")) {
            createCalculationRuleMethods.overlapAllowed(true);
        }
    }

    @And("^set month rule type as \"([^\"]*)\"$")
    public void set_month_rule_type_as(String rule) throws Throwable {
        createCalculationRuleMethods.setMonthRuleType(rule);
    }

    @And("^number of months for effective period as \"([^\"]*)\"$")
    public void number_of_months_for_effective_period_as(String months) throws Throwable {
        createCalculationRuleMethods.setNoOfMonthsEffPeriof(months);
    }

    @And("^effective start day of month as \"([^\"]*)\"$")
    public void effective_start_day_of_month_as(String day) throws Throwable {
        createCalculationRuleMethods.setEffStartDayOfMonth(day);
    }

    @And("^number of months for calculation period as \"([^\"]*)\"$")
    public void number_of_months_for_calculation_period_as(String months) throws Throwable {

        createCalculationRuleMethods.setNoOfMonthsCalPeriod(months);
    }

    @And("^set calculation start day of month as \"([^\"]*)\"$")
    public void set_calculation_start_day_of_month_as(String day) throws Throwable {
        createCalculationRuleMethods.setCalStartDayOfMonth(day);
    }

    @When("^the user enters \"([^\"]*)\" as \"([^\"]*)\"$")
    public void the_user_enters_as(String filter, String key) throws Throwable {
        if (filter.equalsIgnoreCase("Name"))
        {
           listCalculationRuleMethods.setFilterName(key);
        }
        else if (filter.equalsIgnoreCase("Type"))
        {
            listCalculationRuleMethods.setTypeName(key);
        }
        else if (filter.equalsIgnoreCase("Description"))
        {
            listCalculationRuleMethods.setDescriptionName(key);
        }
    }

    @Then("^the list should display the records matching the filter criteria$")
    public void the_list_should_display_the_records_matching_the_filter_criteria(List<List<String>> table) throws Throwable {
        String filter = table.get(0).get(0);
        String value = table.get(0).get(1);

        listCalculationRuleMethods.verifyDataInRowsMatchTheSearhFilter(filter,value);
    }

    @And("^the user clicks on the add new rule button$")
    public void the_user_clicks_on_the_add_new_rule_button() {
       listCalculationRuleMethods.clickOnAddNewRule();
    }

    @And("^the user clicks on \"([^\"]*)\"$")
    public void the_user_clicks_on(String button) throws Throwable {
      listCalculationRuleMethods.clickOnAction(button);
    }
    /*
     * Move to the class implimentation file
     */
    @Then("^the calculation rules shall be displayed in (ascending order|descending order)$")
    public void the_calculation_rules_shall_be_displayed_in_sorted_order(String order, DataTable table) throws Throwable {
        List<List<String>> rows = table.raw();
        String header = rows.get(0).get(0);
        List<WebElement> columnList = null;
        List<String> colList = new ArrayList<>();
        List<String> compareList = new ArrayList<>();
        if (header.equals("Status")) {
            columnList = edriver.findElements(By.xpath(Constants.calculationRuleList_statusColumn_xpath));
        } else if (header.equals("Name")) {
            columnList = edriver.findElements(By.xpath(Constants.calculationRuleList_nameColumn_xpath));
        } else if (header.equals("Type")) {
            columnList = edriver.findElements(By.xpath(Constants.calculationRuleList_typeColumn_xpath));
        } else if (header.equals("Description")) {
            columnList = edriver.findElements(By.xpath(Constants.calculationRuleList_descpColumn_xpath));
        }
        for (WebElement temp : columnList) {
            if (temp.getText() != null && !temp.getText().equals("")) {
                colList.add(temp.getText().toLowerCase());
                compareList.add(temp.getText().toLowerCase());
            }
        }
        if (order.contains("asc")) {
            Collections.sort(colList);
            if (!colList.equals(compareList)) {
                throw new Exception("Column:" + header + " is not sorted in ascending order");
            }
        } else {
            Collections.sort(colList);
            Collections.reverse(colList);
            if (!colList.equals(compareList)) {
                throw new Exception("Column:" + header + " is not sorted in descending order");
            }
        }
    }

    @When("^the user deactivates the existing plan$")
    public void the_user_deactivates_the_existing_plan() throws Throwable {
        listCalculationRuleMethods.clickOnAction("statushdr");
        ruleName = edriver.findElement(By.xpath(Constants.calculationRuleList_nameColumn_xpath)).getText();
        listCalculationRuleMethods.clickOnAction("inactive");
    }

    @Then("^the calculation rules should display the status as inactive$")
    public void the_calculation_rules_should_display_the_status_as_inactive() throws Throwable {
        the_user_enters_as("Name", ruleName);
        List<List<String>> list = new ArrayList<>();
        List<String> sublist = new ArrayList<>();
        sublist.add("Name");
        sublist.add(ruleName);
        list.add(sublist);
        the_list_should_display_the_records_matching_the_filter_criteria(list);
    }

    @When("^the user clicks on the (view|edit) button of a plan$")
    public void the_user_clicks_on_the_view_button_of_a_plan(String act) throws Throwable {
        if (act.equals("view"))
        {
            listCalculationRuleMethods.clickOnAction("view");
        } else if (act.equals("edit"))
        {
            listCalculationRuleMethods.clickOnAction("edit");
        }
    }

    @Then("^the user shall be able to (view|edit) the calculation rule details$")
    public void the_user_shall_be_able_to_view_the_calculation_rule_details(String act, DataTable table) throws Throwable {
            createCalculationRuleMethods.verifyIfUserIsAbleToViewOrEditDetails(act,table);
    }

    @Then("^the application displays an error message as \"(.*)\"$")
    public void the_application_displays_an_error_message_as(String errMsg) throws Throwable {
        pageCommonMethods.verifyIfErrorMessageIsDisplayed(errMsg,true);
    }

    @Then("^the application accepts the input$")
    public void the_application_accepts_the_input()
    {
        pageCommonMethods.verifyIfErrorMessageIsDisplayed("End date cannot be before start date",false);
    }
}
