package stepdef;

import com.google.common.base.Verify;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.CommonFunctions;
import setup.Constants;
import setup.DriverBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageForumlaSteps {
    private static EventFiringWebDriver edriver;
    private CommonFunctions fn;
    private String temp;

    public PageForumlaSteps() {
        fn = new CommonFunctions();
        edriver = DriverBean.getDriver();
    }

    @When("^the user filters the list using \"([^\"]*)\"$")
    public void the_user_filters_the_list_using(String filter) throws Throwable {
        Thread.sleep(3000);
        switch (filter.toLowerCase()) {
            case "name":
                temp = fn.getFirstElementFromList(Constants.formulaList_nameColumn_xpath);
                fn.setValue(Constants.formulaList_nameFilter_xpath, temp);
                break;
            case "description":
                temp = fn.getFirstElementFromList(Constants.formulaList_descrpColumn_xpath);
                fn.setValue(Constants.formulaList_descrpFilter_xpath, temp);
                break;
            case "type":
                temp = fn.getFirstElementFromList(Constants.formulaList_typeColumn_xpath);
                fn.setValue(Constants.formulaList_typeFilter_xpath, temp);
                break;
            case "expression":
                temp = fn.getFirstElementFromList(Constants.formulaList_expressionColumn_xpath);
                fn.setValue(Constants.formulaList_exprsFilter_xpath, temp);
                break;
            case "startdate":
                temp = fn.getFirstElementFromList(Constants.formulaList_startDateColumn_xpath);
                fn.setValue(Constants.formulaList_startDateFilter_xpath, temp);
                break;
            case "enddate":
                temp = fn.getFirstElementFromList(Constants.formulaList_endDateColumn_xpath);
                fn.setValue(Constants.formulaList_endDateFilter_xpath, temp);
                break;
            case "rounding precision":
                temp = fn.getFirstElementFromList(Constants.formulaList_roundingPrecisionColumn_xpath);
                fn.setValue(Constants.formulaList_roundPrecsFilter_xpath, temp);
                break;
            case "status":
                fn.clickButton(Constants.formulaList_status_xpath);
                break;

            default:
                System.out.println("Filter Option is not from the list");
                break;
        }

    }

    @Then("^the list should display the results applying the filters$")
    public void the_list_should_display_the_results_applying_the_filters(DataTable table) throws Throwable {
        List<List<String>> row = table.raw();
        String filter = row.get(0).get(0);
        Thread.sleep(3000);
        switch (filter.toLowerCase()) {
            case "name":
                fn.checkIfTheRowsMatchFilters(Constants.formulaList_nameColumn_xpath, fn.getValue(Constants.formulaList_nameFilter_xpath));
                break;
            case "description":
                fn.checkIfTheRowsMatchFilters(Constants.formulaList_descrpColumn_xpath, fn.getValue(Constants.formulaList_descrpFilter_xpath));
                break;
            case "type":
                fn.checkIfTheRowsMatchFilters(Constants.formulaList_typeColumn_xpath, fn.getValue(Constants.formulaList_typeFilter_xpath));
                break;
            case "expression":
                fn.checkIfTheRowsMatchFilters(Constants.formulaList_expressionColumn_xpath, fn.getValue(Constants.formulaList_exprsFilter_xpath));
                break;
            case "startdate":
                fn.checkIfTheRowsMatchFilters(Constants.formulaList_startDateColumn_xpath, fn.getValue(Constants.formulaList_startDateFilter_xpath));
                break;
            case "enddate":
                fn.checkIfTheRowsMatchFilters(Constants.formulaList_endDateColumn_xpath, fn.getValue(Constants.formulaList_endDateFilter_xpath));
                break;
            case "rounding precision":
                fn.checkIfTheRowsMatchFilters(Constants.formulaList_roundingPrecisionColumn_xpath, fn.getValue(Constants.formulaList_roundPrecsFilter_xpath));
                break;
            default:
                System.out.println("Filter Option is not from the list");
                break;
        }
    }

    @And("^click on filter$")
    public void click_on_filters() {
        edriver.findElement(By.xpath(Constants.formulaList_filter_xpath)).click();
    }

    /*
     * Create Formula with name
     */
    @When("^the user creates a formula with \"([^\"]*)\"$")
    public void the_user_creates_a_formula_with(String formula) throws Throwable {
        fn.enterText(Constants.formulaCreate_name_xpath, formula);
    }

    @When("^description as \"([^\"]*)\"$")
    public void description_as(String descrp) throws Throwable {
        fn.enterText(Constants.formulaCreate_description_xpath, descrp);
    }

    @When("^set type as \"([^\"]*)\"$")
    public void set_type_as(String type) throws Throwable {
        fn.selectFromDropDown(Constants.formulaCreate_typeSelect_xpath, type);
    }

    @When("^expression as \"([^\"]*)\"$")
    public void expression_as(String expression) throws Throwable {
        fn.enterText(Constants.formulaCreate_expression_xpath, expression);
    }

    @When("^set the start date for formula as \"([^\"]*)\"$")
    public void set_the_start_date_for_formula_as(String startDate) throws Throwable {

        LocalDate da = LocalDate.now();
        if (startDate.equalsIgnoreCase("today")) {
            startDate = LocalDate.now().toString();
        } else if (startDate.equalsIgnoreCase("tomorrow")) {
            startDate = LocalDate.now().plusDays(1).toString();
        } else if (startDate.equalsIgnoreCase("yesterday")) {
            startDate = LocalDate.now().minusDays(1).toString();
        } else if (startDate.matches("[a-z+0-9]*")) {
            int days = Integer.parseInt(startDate.split("\\+")[1]);
            startDate = LocalDate.now().plusDays(days).toString();
        }
        WebElement datepicker = edriver.findElement(By.xpath(Constants.formulaCreate_startDatePicker_xpath));

        Actions act = new Actions(edriver);
        act.click(datepicker).sendKeys(startDate).perform();
        act.sendKeys(Keys.TAB).perform();
    }

    @When("^(set|update) the end date for formula as \"([^\"]*)\"$")
    public void set_the_end_date_for_formula_as(String action, String endDate) throws Throwable {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (action.equals("update")) {
            endDate = fn.getValue(Constants.formulaCreate_endDate_xpath);
            today = LocalDate.parse(endDate, formatter);
            endDate = today.plusDays(1).toString();
        }

        if (endDate.equalsIgnoreCase("today")) {
            endDate = today.toString();
        } else if (endDate.equalsIgnoreCase("tomorrow")) {
            endDate = today.plusDays(1).toString();
        } else if (endDate.equalsIgnoreCase("yesterday")) {
            endDate = today.minusDays(1).toString();
        } else if (endDate.matches("^[a-z+0-9]*$") && !endDate.equals("")) {
            int days = Integer.parseInt(endDate.split("\\+")[1]);
            endDate = LocalDate.now().plusDays(days).toString();
        }
        if (endDate != null) {
            WebElement datepicker = edriver.findElement(By.xpath(Constants.formulaCreate_endDatePicker_xpath));
            fn.clearText(Constants.formulaCreate_endDate_xpath);
            Actions act = new Actions(edriver);
            act.click(datepicker).sendKeys(endDate).perform();
            act.sendKeys(Keys.TAB).perform();
        }
    }

    @When("^set the rounding mode as \"([^\"]*)\"$")
    public void set_the_rounding_mode_as(String mode) throws Throwable {
        fn.selectFromDropDown(Constants.formulaCreate_roundingMode_xpath, mode);
    }

    @When("^set the rounding precision to \"([^\"]*)\"$")
    public void set_the_rounding_precision_to(String precisionTo) throws Throwable {
        fn.enterText(Constants.formulaCreate_roundingPrecision_xpath, precisionTo);
    }

    @When("^click on add parameter$")
    public void click_on_add_parameter() throws Throwable {
        fn.clickButton(Constants.formulaCreate_addParameter_xpath);
    }

    @When("^enter the details for the paramters$")
    public void enter_the_details_for_the_paramters(DataTable arg1) throws Throwable {
        List<List<String>> params = arg1.raw();
        int i = 0;
        fn.scrollDown();
        while (i < params.size()) {
            String type = params.get(i).get(1);
            click_on_add_parameter();
            fn.enterText(Constants.formulaCreate_nameParameter_xpath, params.get(i).get(0), "last");
            fn.selectFromDropDown(Constants.formulaCreate_typeParameter_xpath, params.get(i).get(1), "last");
            if (type.equalsIgnoreCase("Index")) {
                fn.selectFromDropDown(Constants.formulaCreate_indexTypeParameter_xpath, params.get(i).get(2), "last");
                fn.selectFromDropDown(Constants.formulaCreate_indexPointParameter_xpath, params.get(i).get(3), "last");
                fn.setNameFromAutoFill(Constants.formulaCreate_indexNameParameter_xpath, params.get(i).get(4));
                fn.setNameFromAutoFill(Constants.formulaCreate_calculationPeriodParamater_xpath, params.get(i).get(5));
            } else if (type.equalsIgnoreCase("Workbook")) {
                fn.setNameFromAutoFill(Constants.formulaCreate_workBookName_xpath, params.get(i).get(2));
                fn.setNameFromAutoFill(Constants.formulaCreate_supplier_xpath, params.get(i).get(3));
                fn.setNameFromAutoFill(Constants.formulaCreate_location_xpath, params.get(i).get(4));
                fn.setNameFromAutoFill(Constants.formulaCreate_product_xpath, params.get(i).get(5));
            }
            i++;
        }
    }

    @When("^validate the expression and click on Create$")
    public void validate_the_expression_and_click_on_Create() throws Throwable {
        fn.clickButton(Constants.formulaCreate_validateExpre_xpath);
        try {
            Verify.verify(edriver.findElement(By.xpath(Constants.formulaCreate_errorMessageValidate_xpath)).isDisplayed(), "Invalid Expression!!");
        } catch (NoSuchElementException exp) {
            System.out.println("Expression is Verified !");
        }
        fn.clickButton(Constants.formulaCreate_create_xpath);
    }

    @Then("^the formula (should|should not) be (created|updated)$")
    public void the_formula_should_be_createdUpdated(String neg, String action) throws Throwable {
        if (!neg.contains("not")) {
            Verify.verify(edriver.getCurrentUrl().contains("formula/list"), "Formula is not Created !!");
        } else {
            Verify.verify(edriver.getCurrentUrl().contains("formula/create"), "ERROR : Formula is Created !!");
        }

    }

    @When("^the user clicks on \"([^\"]*)\" button$")
    public void the_user_clicks_on_button(String button) throws Throwable {
        Thread.sleep(3000);
        if (button.equalsIgnoreCase("Edit")) {
            fn.clickButton(Constants.formulaList_editAction_xpath);
        } else if (button.equalsIgnoreCase("Update")) {
            fn.clickButton(Constants.formulaList_updateAction_xpath);
        } else if (button.equalsIgnoreCase("View")) {
            fn.clickButton(Constants.formulaList_viewAction_xpath);
        } else if (button.equalsIgnoreCase("Copy")) {
            fn.clickButton(Constants.formulaList_copyAction_xpath);
        } else if (button.equalsIgnoreCase("name")) {
            fn.clickButton(Constants.formulaList_name_xpath);
        } else if (button.equalsIgnoreCase("description")) {
            fn.clickButton(Constants.formulaList_description_xpath);
        } else if (button.equalsIgnoreCase("expression")) {
            fn.clickButton(Constants.formulaList_expression_xpath);
        } else if (button.equalsIgnoreCase("startdate")) {
            fn.clickButton(Constants.formulaList_startDate_xpath);
        } else if (button.equalsIgnoreCase("enddate")) {
            fn.clickButton(Constants.formulaList_endDate_xpath);
        } else if (button.equalsIgnoreCase("rounding precision")) {
            fn.clickButton(Constants.formulaList_roundingPrecision_xpath);
        } else if (button.equalsIgnoreCase("status")) {
            fn.clickButton(Constants.formulaList_status_xpath);
        } else if (button.equalsIgnoreCase("type")) {
            fn.clickButton(Constants.formulaList_type_xpath);
        }
    }

    @Then("^the inactive formulas shouldn't have option to edit$")
    public void the_inactive_formulas_shouldn_t_have_option_to_edit() throws Throwable {
        Verify.verify(fn.getElementAtPositionFromList(Constants.formulaList_editAction_xpath, 1) == null, "In Active action has edit option");
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
        String name = fn.getFirstElementFromList(Constants.formulaList_nameColumn_xpath);
        fn.clickButton(Constants.formulaList_deactivateAction_xpath);
        click_on_filters();
        fn.enterText(Constants.formulaList_nameFilter_xpath, name);
        Verify.verify(fn.getFirstElementFromList(Constants.formulaList_statusColumn_xpath).equalsIgnoreCase("Inactive"), "Formula:" + name + " is still active !!");
    }

    @And("^the user shall be able to view all the formula details$")
    public void the_user_shall_be_able_to_view_all_the_formuladetails() {
        fn.viewableOnly(Constants.formulaCreate_name_xpath);
        fn.viewableOnly(Constants.formulaCreate_description_xpath);
        fn.viewableOnly(Constants.formulaCreate_typeSelect_xpath);
        fn.viewableOnly(Constants.formulaCreate_expression_xpath);
        fn.viewableOnly(Constants.formulaCreate_startDatePicker_xpath);
        fn.viewableOnly(Constants.formulaCreate_endDatePicker_xpath);
        fn.viewableOnly(Constants.formulaCreate_endDate_xpath);
        fn.viewableOnly(Constants.formulaCreate_roundingMode_xpath);
        fn.viewableOnly(Constants.formulaCreate_roundingPrecision_xpath);
        fn.viewableOnly(Constants.formulaCreate_nameParameter_xpath);
        fn.viewableOnly(Constants.formulaCreate_typeParameter_xpath);
    }

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
