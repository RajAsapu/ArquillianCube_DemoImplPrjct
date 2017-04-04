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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class PageIndexSteps {
    final static Logger logger = Logger.getLogger(PageIndexSteps.class.getName());
    private static EventFiringWebDriver edriver;
    public PageCommonSteps steps;
    private CommonFunctions fn;

    public PageIndexSteps(){
        edriver = DriverBean.getDriver();
        steps = new PageCommonSteps();
        fn = new CommonFunctions();
    }


    @When("^the user enters the start date as ([^\"]*) and status as ([^\"]*)$")
    public void the_user_enters_the_start_date_as_and_status_as(String date, String status) throws Exception {

        WebElement datepicker = edriver.findElement(By.xpath(Constants.indexList_startdatepicker_xpath));

        Actions act = new Actions(edriver);
        act.click(datepicker).sendKeys(date).perform();
        act.sendKeys(Keys.TAB).perform();

        fn.selectStatusIndex(status);
        fn.selectType(CommonFunctions.type.manual);
    }

    @Then("^the user shall be able to view the list of indexes with start date from \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_with_start_date_from_and_status_as(String arg1,
                                                                                                      String arg2) throws Exception {

        List<WebElement> statusList = edriver.findElements(By.xpath(Constants.indexList_StatusColumn_xpath));

        for (WebElement temp : statusList) {
            if (!temp.equals(arg2)) {
                assert false;
            }
        }
        Thread.sleep(4000);
        List<WebElement> startdateList = edriver.findElements(By.xpath(Constants.indexList_StartDateColumn_xpath));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date dateSelected = formatter.parse(arg1);
        for (WebElement temp : startdateList) {
            Date tempDate = formatter.parse(temp.getText());
            if (tempDate.compareTo(dateSelected) != 1)
                assert false;
        }

        edriver.quit();
    }

    @When("^the user enters the end date as \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_enters_the_end_date_as_and_status_as(String date, String status) throws Throwable {
        WebElement datepicker = edriver.findElement(By.xpath(Constants.indexList_enddatepicker_xapth));

        Actions act = new Actions(edriver);
        act.click(datepicker).sendKeys(date).perform();
        act.sendKeys(Keys.TAB).perform();

        fn.selectStatusIndex(status);
        fn.selectType(CommonFunctions.type.manual);
    }

    @Then("^the user shall be able to view the list of indexes with end date from \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_with_end_date_from_and_status_as(String arg1,
                                                                                                    String arg2) throws Throwable {
        List<WebElement> statusList = edriver.findElements(By.xpath(Constants.indexList_StatusColumn_xpath));

        for (WebElement temp : statusList) {
            if (!temp.getText().equals(arg2)) {
                assert false;
            }
        }

        List<WebElement> startdateList = edriver.findElements(By.xpath(Constants.indexList_EndDateColumn_xpath));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date dateSelected = formatter.parse(arg1);
        for (WebElement temp : startdateList) {
            Date tempDate = null;
            try {
                tempDate = formatter.parse(temp.getText());
            } catch (java.text.ParseException exp) {
                if (exp.getMessage().contains("Unparseable date: \"\"")) {
                    throw new Exception("End date is empty !!");
                } else {
                    exp.printStackTrace();
                }
            }
            if (tempDate.compareTo(dateSelected) != 1)
                assert false;
        }

        edriver.quit();
    }

    @When("^the user enters the type as ([^\"]*)$")
    public void the_user_enters_the_type_as(String type) throws Throwable {
        if (type.equals("MANUAL"))
            fn.selectType(CommonFunctions.type.manual);
        else if (type.equals("AUTOMATIC"))
            fn.selectType(CommonFunctions.type.automatic);
        else
            logger.error("Invalid type");
    }

    @Then("^the user shall be able to view the list of indexes with type as \"([^\"]*)\"$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_with_type_as(String type) throws Throwable {

        List<WebElement> typeList = edriver.findElements(By.xpath(Constants.indexList_typeColumn_xpath));

        for (WebElement temp : typeList) {
            if (!temp.getText().equals(type)) {
                throw new Exception("Type doesn't match (Expected:+" + type + ",Actual:" + temp.getText() + ") !!");
            }
        }

    }

    @When("^the user enters rate basis as ([^\"]*)$")
    public void the_user_enters_rate_basis_as(String rateBase) throws Throwable {
        fn.selectFromDropDown(Constants.indexList_rateBasis_xpath,rateBase);
    }

    @When("^name as ([^\"]*)$")
    public void name_as(String name) throws Throwable {
        fn.setNameFromAutoFill(Constants.indexList_name_xpath, name);
    }

    @Then("^the codes shall be auto populated$")
    public void the_codes_shall_be_auto_populated() throws Exception {

    }

    @And("^comment as ([^\"]*)$")
    public void comment_as(String comment) throws Throwable {
        edriver.findElement(By.xpath(Constants.indexCreate_comment_xpath)).sendKeys(comment);
    }

    @When("^currency as ([^\"]*)$")
    public void currency_as(String curr) throws Throwable {
        fn.selectCurrency(curr);
    }

    @When("^unit of measurement as ([^\"]*)$")
    public void unit_of_measurement_as(String uom) throws Throwable {
        fn.selectUOM(uom);
    }

    @Then("^the user shall be able to view the list of indexes matching the search criteria as \"([^\"]*)\" on list page$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_matching_the_above_search_criteria(String args)
            throws Throwable {
        Thread.sleep(3000);
        String[] params = args.split(",");
        checkRateBasis_IndexTabe(params[3]);
        checkName_IndexTabe(params[4]);
        checkCurrency_IndexTabe(params[5]);
        checkUom_IndexTabe(params[6]);
    }

    /*
     * Check if the index list has expected Rate basis type
     */
    public void checkRateBasis_IndexTabe(String rate) throws Exception {
        List<WebElement> rateList = edriver.findElements(By.xpath(Constants.indexList_ratebasisColumn_xpath));

        for (WebElement temp : rateList) {
            if (!temp.getText().equalsIgnoreCase(rate)) {
                throw new Exception("Rate Basis doesn't match, Expected :" + rate + " Actual:" + temp.getText());
            }
        }
    }

    /*
     * Check if the index list has expected name
     */
    public void checkName_IndexTabe(String name) throws Exception {
        List<WebElement> nameList = edriver.findElements(By.xpath(Constants.indexList_nameColumn_xpath));

        for (WebElement temp : nameList) {
            if (!temp.getText().equals(name.toUpperCase())) {
                throw new Exception("Name doesn't match, Expected :" + name + " Actual:" + temp.getText());
            }
        }
    }

    /*
     * Check if the index list has expected currency
     */
    public void checkCurrency_IndexTabe(String currency) throws Exception {
        List<WebElement> currList = edriver.findElements(By.xpath(Constants.indexList_currencyColumn_xpath));

        for (WebElement temp : currList) {
            if (!temp.getText().equals(currency)) {
                throw new Exception("Currency doesn't match, Expected :" + currency + " Actual:" + temp.getText());
            }
        }
    }

    /*
     * Check if the index list has expected currency
     */
    public void checkUom_IndexTabe(String uom) throws Exception {
        List<WebElement> uomList = edriver.findElements(By.xpath(Constants.indexList_uomColumn_xpath));

        for (WebElement temp : uomList) {
            if (!temp.getText().equals(uom)) {
                throw new Exception("UOM doesn't match, Expected :" + uom + " Actual:" + temp.getText());
            }
        }
    }

    /*
     * Method to insert high,medium,low and close prices
     */
    @When("^([^\"]*),([^\"]*),([^\"]*) and ([^\"]*) are entered$")
    public void and_are_entered(String low, String mid, String high, String close) throws Throwable {
        edriver.findElement(By.xpath(Constants.indexCreate_lowprice_xpath)).sendKeys(low.trim());
        edriver.findElement(By.xpath(Constants.indexCreate_midprice_xpath)).sendKeys(mid.trim());
        edriver.findElement(By.xpath(Constants.indexCreate_highprice_xpath)).sendKeys(high.trim());
        edriver.findElement(By.xpath(Constants.indexCreate_closeprice_xpath)).sendKeys(close.trim());
    }

    @And("^start date as ([^\"]*) and end date as ([^\"]*)")
    public void enterStartDateAndEndDate(String startDate, String endDate) throws Throwable {
        WebElement datepicker = edriver.findElement(By.xpath(Constants.indexCreate_startDatePicker_xpath));
        LocalDate today = LocalDate.now();
        if(startDate.equalsIgnoreCase("displayed")){
            startDate = fn.getValue(Constants.indexCreate_startDate_xpath);
        }

        if(endDate.contains("startdate-")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            today = LocalDate.parse(startDate, formatter);
            int days = Integer.parseInt(endDate.split("-")[1]);
            endDate = today.minusDays(1).toString();
        }

        LocalDate da = LocalDate.now();
        if (startDate.equalsIgnoreCase("today")) {
            startDate = LocalDate.now().toString();
        } else if (startDate.equalsIgnoreCase("tomorrow")) {
            startDate = LocalDate.now().plusDays(1).toString();
        } else if (startDate.equalsIgnoreCase("yesterday")) {
            startDate = LocalDate.now().minusDays(1).toString();
        } else if (startDate.contains("[a-z+0-9]*")) {
            int days = Integer.parseInt(startDate.split("\\+")[1]);
            startDate = LocalDate.now().plusDays(days).toString();
        }

        if (endDate.equalsIgnoreCase("today")) {
            endDate = today.toString();
        } else if (endDate.equalsIgnoreCase("tomorrow")) {
            endDate = today.plusDays(1).toString();
        } else if (endDate.equalsIgnoreCase("yesterday")) {
            endDate = today.minusDays(1).toString();
        } else if (endDate.matches("\\+") && !endDate.equals("")) {
            int days = Integer.parseInt(endDate.split("\\+")[1]);
            endDate = LocalDate.now().plusDays(days).toString();
        }
        if(endDate!=null && startDate!=null){
        Actions act = new Actions(edriver);
        act.click(datepicker).sendKeys(startDate).perform();
        act.sendKeys(Keys.TAB).perform();
        Thread.sleep(1000);
        datepicker = edriver.findElement(By.xpath(Constants.indexCreate_endDatePicker_xpath));
        act.click(datepicker).sendKeys(endDate).perform();
        act.sendKeys(Keys.TAB).perform();}
    }

    @Then("^the user shall be able to view the created index in the list on filtering with ([^\"]*)$")
    public void the_user_shall_be_able_to_view_the_created_index_in_the_list_on_filtering_with(String rate)
            throws Throwable {
        Thread.sleep(5000);
        Verify.verify(edriver.getCurrentUrl().contains("/index/list"),"Index is not created");
        fn.selectRateBasis(rate);
        fn.selectStatusIndex("Active");
        steps.clicks_on_the_search_button();
        /*
         * Need to implement code to check the created index !!
		 */
    }

    @And("^the user clicked on ([^\"]*) action$")
    public void click_on_button(String action)throws Exception
    {
        switch(action.toLowerCase())
        {
            case "edit": fn.clickButton(Constants.indexList_editAction_xpath);
                            break;
            case "addnewindex": fn.clickButton(Constants.indexList_addNewIndex_xpath);
                            break;
            case "submit" : fn.clickButton(Constants.indexCreate_submit_xpath);
                            break;
            case "inactive" : fn.clickButton(Constants.indexList_deactivateAction_xpath);
                            break;
        }
    }

    @Then("^the index (should|should not) be (created|updated)$")
    public void the_index_should_be_createdOrUpdated(String action,String action1)
    {
        if(action.equalsIgnoreCase("should")){
            Verify.verify(edriver.getCurrentUrl().contains("/index/list"),"Index is not created or updated !!");
        } else{
            Verify.verify(!edriver.getCurrentUrl().contains("/index/list"),"Index is created or updated !!");
        }
    }

    @Then("^the user shall be able to edit only end date$")
    public void the_user_shall_be_able_to_edit_only_end_date()throws Throwable
    {
        fn.checkOnlyView(Constants.indexList_name_xpath);
        fn.checkOnlyView(Constants.indexList_rateBasis_xpath);
        fn.checkOnlyView(Constants.indexCreate_startDate_xpath);
        fn.checkOnlyView(Constants.indexCreate_lowprice_xpath);
        fn.checkOnlyView(Constants.indexCreate_midprice_xpath);
        fn.checkOnlyView(Constants.indexCreate_highprice_xpath);
        fn.checkOnlyView(Constants.indexCreate_closeprice_xpath);
        fn.checkOnlyView(Constants.indexCreate_currency_xpath);
        fn.checkOnlyView(Constants.indexCreate_uom_xpath);
        fn.checkOnlyView(Constants.indexCreate_priceBreak_xpath);
    }

    @Then("^the status of the index should change to inactive$")
    public void the_status_of_the_index_should_change_to_inactive()
    {
        String status = fn.getFirstElementFromList(Constants.indexList_StatusColumn_xpath);
        Verify.verify(status.equalsIgnoreCase("inactive"),"Index is not deactivated !!");
    }


}
