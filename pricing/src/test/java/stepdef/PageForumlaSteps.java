package stepdef;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import functions.CreateFormulaMethods;
import functions.ListFormulaMethods;
import functions.PageCommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageForumlaSteps {
    private static EventFiringWebDriver edriver;
    private PageCommonMethods pageCommonMethods;
    private String temp;
    private ListFormulaMethods listFormulaMethods;
    private CreateFormulaMethods createFormulaMethods;

    public PageForumlaSteps() {
        pageCommonMethods = new PageCommonMethods();
        edriver = DriverBean.getDriver();
        listFormulaMethods = new ListFormulaMethods();
    }

    @When("^the user filters the list using \"([^\"]*)\"$")
    public void the_user_filters_the_list_using(String filter) throws Throwable {
        Thread.sleep(3000);
        temp = listFormulaMethods.applyFilter(filter.toLowerCase());
    }

    @Then("^the list should display the results applying the filters$")
    public void the_list_should_display_the_results_applying_the_filters(DataTable table) throws Throwable {
        List<List<String>> row = table.raw();
        String filter = row.get(0).get(0);
        Thread.sleep(3000);
        listFormulaMethods.verifyIfFilterIsApplied(filter);
    }
    /*
     * Create Formula with name
     */
    @When("^the user creates a formula with \"([^\"]*)\"$")
    public void the_user_creates_a_formula_with(String name) throws Throwable {
       createFormulaMethods.setName(name);
    }

    @When("^description as \"([^\"]*)\"$")
    public void description_as(String description) throws Throwable {
        createFormulaMethods.setDescription(description);
    }

    @When("^set type as \"([^\"]*)\"$")
    public void set_type_as(String type) throws Throwable {
        createFormulaMethods.setDescription(type);
    }

    @When("^expression as \"([^\"]*)\"$")
    public void expression_as(String expression) throws Throwable {
        createFormulaMethods.setExpression(expression);
    }

    @When("^set the start date for formula as \"([^\"]*)\"$")
    public void set_the_start_date_for_formula_as(String startDate) throws Throwable {
        createFormulaMethods.setStartDate(startDate);
    }

    @When("^(set|update) the end date for formula as \"([^\"]*)\"$")
    public void set_the_end_date_for_formula_as(String action, String endDate) throws Throwable {
        createFormulaMethods.setStartDate(endDate);
    }

    @When("^set the rounding mode as \"([^\"]*)\"$")
    public void set_the_rounding_mode_as(String mode) throws Throwable {
        createFormulaMethods.setRoundingMode(mode);
    }

    @When("^set the rounding precision to \"([^\"]*)\"$")
    public void set_the_rounding_precision_to(String precisionTo) throws Throwable {
        createFormulaMethods.setRoundingPrecision(precisionTo);
    }

    @When("^enter the details for the paramters$")
    public void enter_the_details_for_the_paramters(DataTable arg1) throws Throwable {
        List<List<String>> params = arg1.raw();
       createFormulaMethods.addParameters(params);
    }

    @When("^validate the expression and click on Create$")
    public void validate_the_expression_and_click_on_Create() throws Throwable {
        createFormulaMethods.createFormula();
    }

    @Then("^the formula (should|should not) be (created|updated)$")
    public void the_formula_should_be_createdUpdated(String perform, String action) throws Throwable {
        createFormulaMethods.verifyIfFormulaCreatedOrNot(perform,action);
    }

    @When("^the user clicks on \"([^\"]*)\" button$")
    public void the_user_clicks_on_button(String button) throws Throwable {
        Thread.sleep(3000);
        listFormulaMethods.clickOnButton(button);
    }

    @Then("^the inactive formulas shouldn't have option to edit$")
    public void the_inactive_formulas_shouldn_t_have_option_to_edit() throws Throwable {
        listFormulaMethods.verifyIfInactiveElementHasEdit();
    }

    @When("^the user searches the (inactive|active) record$")
    public void the_user_searches_the_activeOrinactive_record(String act) throws Throwable {
        // Modify when DFCT - 275 is fixed
        if (act.equals("inactive")) {
            the_user_filters_the_list_using("status");
        } else if (act.equals("active")) {
            the_user_filters_the_list_using("status");
            the_user_filters_the_list_using("status");
        }
    }

    @And("^the user inactivates the formula$")
    public void the_user_inactivates_the_formula() { // Used for readability
    }
    @Then("^the formula should be displayed as inactive$")
    public void the_formula_should_be_displayed_as_inactive() throws Exception {
        listFormulaMethods.verifyIfFormulaIsInactivated();
    }

    @And("^the user shall be able to view all the formula details$")
    public void the_user_shall_be_able_to_view_all_the_formuladetails()throws Exception {
        createFormulaMethods.verifyIfUserIsAbleToViewAllDetails();
    }
    /*
     * Move to the List Formula Methods Class
     */
    @Then("^the formula rules shall be displayed in (ascending order|descending order)$")
    public void the_calculation_rules_shall_be_displayed_in_sorted_order(String order, DataTable table) throws Throwable {
        Thread.sleep(3000);
        List<List<String>> rows = table.raw();
        String header = rows.get(0).get(0);
        List<WebElement> columnList = null;
        List<String> colList = new ArrayList<>();
        List<String> compareList = new ArrayList<>();

        if (header.equalsIgnoreCase("Status")) {
            columnList = edriver.findElements(By.xpath(Constants.formulaList_statusColumn_xpath));
        } else if (header.equalsIgnoreCase("Name")) {
            columnList = edriver.findElements(By.xpath(Constants.formulaList_nameColumn_xpath));
        } else if (header.equalsIgnoreCase("Type")) {
            columnList = edriver.findElements(By.xpath(Constants.formulaList_typeColumn_xpath));
        } else if (header.equalsIgnoreCase("Description")) {
            columnList = edriver.findElements(By.xpath(Constants.formulaList_descrpColumn_xpath));
        } else if (header.equalsIgnoreCase("expression")) {
            columnList = edriver.findElements(By.xpath(Constants.formulaList_expressionColumn_xpath));
        } else if (header.equalsIgnoreCase("startdate")) {
            columnList = edriver.findElements(By.xpath(Constants.formulaList_startDateColumn_xpath));
        } else if (header.equalsIgnoreCase("enddate")) {
            columnList = edriver.findElements(By.xpath(Constants.formulaList_endDateColumn_xpath));
        } else if (header.equalsIgnoreCase("rounding precision")) {
            columnList = edriver.findElements(By.xpath(Constants.formulaList_roundingPrecisionColumn_xpath));
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
}
