package stepdef;

import com.google.common.base.Verify;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.CommonFunctions;
import setup.Constants;
import setup.DriverBean;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PageForumlaSteps {
    private CommonFunctions fn;
    private String temp;
    private static EventFiringWebDriver edriver;

    public PageForumlaSteps()
    {
        fn = new CommonFunctions();
        edriver = DriverBean.getDriver();
    }

    @When("^the user filters the list using \"([^\"]*)\"$")
    public void the_user_filters_the_list_using(String filter) throws Throwable {
        Thread.sleep(3000);
        switch (filter.toLowerCase())
        {
            case "name": temp = fn.getFirstElement(Constants.formulaList_nameColumn_xpath);
                         fn.setValue(Constants.formulaList_nameFilter_xpath,temp);
                         break;
            case "description": temp = fn.getFirstElement(Constants.formulaList_descrpColumn_xpath);
                                fn.setValue(Constants.formulaList_descrpFilter_xpath,temp);
                                break;
            case "type": temp = fn.getFirstElement(Constants.formulaList_typeColumn_xpath);
                         fn.setValue(Constants.formulaList_typeFilter_xpath,temp);
                         break;
            case "expression": temp = fn.getFirstElement(Constants.formulaList_expressionColumn_xpath);
                               fn.setValue(Constants.formulaList_exprsFilter_xpath,temp);
                               break;
            case "startdate":   temp = fn.getFirstElement(Constants.formulaList_startDateColumn_xpath);
                                fn.setValue(Constants.formulaList_startDateFilter_xpath,temp);
                                break;
            case "enddate": temp = fn.getFirstElement(Constants.formulaList_endDateColumn_xpath);
                            fn.setValue(Constants.formulaList_endDateFilter_xpath,temp);
                            break;
            case "rounding precision": temp = fn.getFirstElement(Constants.formulaList_roundingPrecisionColumn_xpath);
                                       fn.setValue(Constants.formulaList_roundPrecsFilter_xpath,temp);
                                       break;
            default: System.out.println("Filter Option is not from the list");
                break;
        }

    }

    @Then("^the list should display the results applying the filters$")
    public void the_list_should_display_the_results_applying_the_filters(DataTable table) throws Throwable {
        List<List<String>> row=table.raw();
        String filter = row.get(0).get(0);
        Thread.sleep(3000);
        switch (filter.toLowerCase())
        {
            case "name":
                fn.checkIfTheRowsMatchFilters(Constants.formulaList_nameColumn_xpath,fn.getValue(Constants.formulaList_nameFilter_xpath));
                break;
            case "description": fn.checkIfTheRowsMatchFilters(Constants.formulaList_descrpColumn_xpath,fn.getValue(Constants.formulaList_descrpFilter_xpath));
                break;
            case "type": fn.checkIfTheRowsMatchFilters(Constants.formulaList_typeColumn_xpath,fn.getValue(Constants.formulaList_typeFilter_xpath));
                break;
            case "expression": fn.checkIfTheRowsMatchFilters(Constants.formulaList_expressionColumn_xpath,fn.getValue(Constants.formulaList_exprsFilter_xpath));
                break;
            case "startdate": fn.checkIfTheRowsMatchFilters(Constants.formulaList_startDateColumn_xpath,fn.getValue(Constants.formulaList_startDateFilter_xpath));
                break;
            case "enddate": fn.checkIfTheRowsMatchFilters(Constants.formulaList_endDateColumn_xpath,fn.getValue(Constants.formulaList_endDateFilter_xpath));
                break;
            case "rounding precision": fn.checkIfTheRowsMatchFilters(Constants.formulaList_roundingPrecisionColumn_xpath,fn.getValue(Constants.formulaList_roundPrecsFilter_xpath));
                break;
            default: System.out.println("Filter Option is not from the list");
                break;
        }
    }

    @And("^click on filter$")
    public void click_on_filters()
    {
        edriver.findElement(By.xpath(Constants.formulaList_filter_xpath)).click();
    }

    /*
     * Create Formula with name
     */
    @When("^the user creates a formula with \"([^\"]*)\"$")
    public void the_user_creates_a_formula_with(String formula) throws Throwable {
        fn.enterText(Constants.formulaCreate_name_xpath,formula);
    }

    @When("^description as \"([^\"]*)\"$")
    public void description_as(String descrp) throws Throwable {
        fn.enterText(Constants.formulaCreate_description_xpath,descrp);
    }

    @When("^set type as \"([^\"]*)\"$")
    public void set_type_as(String type) throws Throwable {
       fn.selectFromDropDown(Constants.formulaCreate_typeSelect_xpath,type);
    }

    @When("^expression as \"([^\"]*)\"$")
    public void expression_as(String expression) throws Throwable {
        fn.enterText(Constants.formulaCreate_expression_xpath,expression);
    }

    @When("^set the start date for formula as \"([^\"]*)\"$")
    public void set_the_start_date_for_formula_as(String startDate) throws Throwable {

        LocalDate da = LocalDate.now();
        if (startDate.equalsIgnoreCase("today")) {
            startDate = LocalDate.now().toString();
        }else if(startDate.equalsIgnoreCase("tomorrow")){
            startDate = LocalDate.now().plusDays(1).toString();
        }else if(startDate.equalsIgnoreCase("yesterday")){
            startDate = LocalDate.now().minusDays(1).toString();
        }

        WebElement datepicker = edriver.findElement(By.xpath(Constants.formulaCreate_startDatePicker_xpath));

        Actions act = new Actions(edriver);
        act.click(datepicker).sendKeys(startDate).perform();
        act.sendKeys(Keys.TAB).perform();
    }

    @When("^set the end date for formula as \"([^\"]*)\"$")
    public void set_the_end_date_for_formula_as(String endDate) throws Throwable {

        LocalDate da = LocalDate.now();
        if (endDate.equalsIgnoreCase("today")) {
            endDate = LocalDate.now().toString();
        }else if(endDate.equalsIgnoreCase("tomorrow")){
            endDate = LocalDate.now().plusDays(1).toString();
        }else if(endDate.equalsIgnoreCase("yesterday")){
            endDate = LocalDate.now().minusDays(1).toString();
        }
        WebElement datepicker = edriver.findElement(By.xpath(Constants.formulaCreate_endDatePicker_xpath));

        Actions act = new Actions(edriver);
        act.click(datepicker).sendKeys(endDate).perform();
        act.sendKeys(Keys.TAB).perform();
    }

    @When("^set the rounding mode as \"([^\"]*)\"$")
    public void set_the_rounding_mode_as(String mode) throws Throwable {
        fn.selectFromDropDown(Constants.formulaCreate_roundingMode_xpath,mode);
    }

    @When("^set the rounding precision to \"([^\"]*)\"$")
    public void set_the_rounding_precision_to(String precisionTo) throws Throwable {
        fn.enterText(Constants.formulaCreate_roundingPrecision_xpath,precisionTo);
    }

    @When("^click on add parameter$")
    public void click_on_add_parameter() throws Throwable {
       fn.clickButton(Constants.formulaCreate_addParameter_xpath);
    }

    @When("^enter the details for the paramters$")
    public void enter_the_details_for_the_paramters(DataTable arg1) throws Throwable {
       List<List<String>> params = arg1.raw();
        fn.enterText(Constants.formulaCreate_nameParameter_xpath,params.get(0).get(0));
        fn.selectFromDropDown(Constants.formulaCreate_typeParameter_xpath,params.get(0).get(1));
        fn.selectFromDropDown(Constants.formulaCreate_indexTypeParameter_xpath,params.get(0).get(2));
        fn.selectFromDropDown(Constants.formulaCreate_indexPointParameter_xpath,params.get(0).get(3));
        fn.setNameFromAutoFill(Constants.formulaCreate_indexNameParameter_xpath,params.get(0).get(4));
        fn.setNameFromAutoFill(Constants.formulaCreate_calculationPeriodParamater_xpath,params.get(0).get(5));
    }

    @When("^validate the expression and click on Create$")
    public void validate_the_expression_and_click_on_Create() throws Throwable {
        fn.clickButton(Constants.formulaCreate_validateExpre_xpath);
        try{ Verify.verify(edriver.findElement(By.xpath(Constants.formulaCreate_errorMessageValidate_xpath)).isDisplayed(),"Invalid Expression!!");}
        catch (NoSuchElementException exp){ System.out.println("Expression is Verified !");}
        fn.clickButton(Constants.formulaCreate_create_xpath);
    }

    @Then("^the formula should be created$")
    public void the_formula_should_be_created() throws Throwable {
        Verify.verify(edriver.getCurrentUrl().contains("formula/list"),"Formula is not Created !!");
    }


}
