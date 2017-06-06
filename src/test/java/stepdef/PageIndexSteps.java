package stepdef;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import functions.index.ListIndexMethods;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;
import setup.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PageIndexSteps {

    final static Logger logger = Logger.getLogger(PageIndexSteps.class.getName());
    private EventFiringWebDriver edriver;
    private PageFactory pageFactory;

    public PageIndexSteps() {
        pageFactory = new PageFactory();
        edriver = DriverBean.getDriver();
    }


    @When("^the user enters the start date as ([^\"]*) and status as ([^\"]*)$")
    public void the_user_enters_the_start_date_as_and_status_as(String date, String status) {
        pageFactory.getListIndexMethods().setStartDate(date);
        pageFactory.getListIndexMethods().setStatus(status);
    }

    @Then("^the user shall be able to view the list of indexes with start date from \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_with_start_date_from_and_status_as(String startDate,
                                                                                                      String status) throws Exception {
        Thread.sleep(4000);
        List<WebElement> statusList = edriver.findElements(By.xpath(Constants.indexList_StatusColumn_xpath));
        for (WebElement temp : statusList) {
            if (!temp.getText().equals(status)) {
                Assert.fail("Status doesnt match , Expected:" + status + ",Actual:" + temp.getText());
            }
        }

        List<WebElement> startdateList = edriver.findElements(By.xpath(Constants.indexList_StartDateColumn_xpath));
        SimpleDateFormat formatter = new SimpleDateFormat("DD-MMM-YYYY");
        Date dateSelected = formatter.parse(startDate);
        for (WebElement temp : startdateList) {
            Date tempDate = formatter.parse(temp.getText());
            if (tempDate.compareTo(dateSelected) != 1 && tempDate.compareTo(dateSelected) != 0)
                Assert.fail("Date should be less than or equal to " + startDate + ",Expected:" + startDate + ",Actual" + tempDate);
        }
    }

    @When("^the user enters the end date as \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_enters_the_end_date_as_and_status_as(String endDate, String status) throws Throwable {
        pageFactory.getListIndexMethods().setEndDate(endDate);
        pageFactory.getListIndexMethods().setStatus(status);
    }

    /*
     * Need to move to the page class
     */
    @Then("^the user shall be able to view the list of indexes with end date from \"([^\"]*)\" and status as \"([^\"]*)\"$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_with_end_date_from_and_status_as(String endDate,
                                                                                                    String status) throws Throwable {
        List<WebElement> statusList;
        while (true) {
            try {
                statusList = edriver.findElements(By.xpath(Constants.indexList_StatusColumn_xpath));

                for (WebElement temp : statusList) {
                    if (!temp.getText().equals(status)) {
                        Assert.fail("Status doesnt match , Expected:" + status + ",Actual:" + temp.getText());
                    }
                }
                break;
            } catch (StaleElementReferenceException exp) {
                continue;
            }
        }

        List<WebElement> startdateList = edriver.findElements(By.xpath(Constants.indexList_EndDateColumn_xpath));
        SimpleDateFormat formatter = new SimpleDateFormat("DD-MMM-YYYY");
        Date dateSelected = formatter.parse(endDate);
        for (WebElement temp : startdateList) {
            Date tempDate = null;
            try {
                tempDate = formatter.parse(temp.getText());
            } catch (java.text.ParseException exp) {
                if (exp.getMessage().contains("Unparseable date: \"\"")) {
                    continue;
                } else {
                    exp.printStackTrace();
                }
            }
            if (tempDate.compareTo(dateSelected) != 1)
                Assert.fail("Date should be less than or equal to " + endDate + ",Expected:" + endDate + ",Actual" + tempDate);
        }
    }

    @When("^the user enters the type as ([^\"]*)$")
    public void the_user_enters_the_type_as(String type) throws Throwable {
        pageFactory.getListIndexMethods().setType(type);
    }

    @Then("^the user shall be able to view the list of indexes with type as \"([^\"]*)\"$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_with_type_as(String type) throws Throwable {
        while (true) {
            try {
                List<WebElement> typeList = edriver.findElements(By.xpath(Constants.indexList_typeColumn_xpath));

                for (WebElement temp : typeList) {
                    if (!temp.getText().equals(type)) {
                        throw new Exception("Type doesn't match (Expected:+" + type + ",Actual:" + temp.getText() + ") !!");
                    }
                }
                break;
            } catch (StaleElementReferenceException exp) {
                continue;
            } catch (Exception exp) {
                exp.printStackTrace();
                break;
            }
        }
    }

    @When("^the user enters rate basis as ([^\"]*)$")
    public void the_user_enters_rate_basis_as(String rateBasis) throws Exception {
        pageFactory.getCreateIndexMethods().setRateBasis(rateBasis);
    }

    @When("^the user enters rate basis in search as ([^\"]*)$")
    public void the_user_enters_rate_basis_in_search_as(String rateBasis) throws Exception {
        pageFactory.getListIndexMethods().setRateBasis(rateBasis);
    }

    @When("^name as ([^\"]*)$")
    public void name_as(String name) throws Throwable {
        pageFactory.getCreateIndexMethods().setName(name);
    }

    @Then("^the codes shall be auto populated$")
    public void the_codes_shall_be_auto_populated() throws Exception {
        // Used for readability
    }

    @And("^comment as ([^\"]*)$")
    public void comment_as(String comment) throws Throwable {
        pageFactory.getCreateIndexMethods().setComments(comment);
    }

    @When("^currency as ([^\"]*)$")
    public void currency_as(String currency) throws Throwable {
        pageFactory.getCreateIndexMethods().setCurrency(currency);
    }

    @When("^currency in search ([^\"]*)$")
    public void currency_in_search(String currency) throws Throwable {
        pageFactory.getListIndexMethods().setCurrency(currency);
    }

    @When("^unit of measurement as ([^\"]*)$")
    public void unit_of_measurement_as(String uom) throws Throwable {
        pageFactory.getCreateIndexMethods().setUom(uom);
    }

    @When("^unit of measurement in search as ([^\"]*)$")
    public void unit_of_measurement_in_search(String uom) throws Throwable {
        pageFactory.getListIndexMethods().setUom(uom);
    }

    /*
     * Move the below method to the class file
     */
    @Then("^the user shall be able to view the list of indexes matching the search criteria$")
    public void the_user_shall_be_able_to_view_the_list_of_indexes_matching_the_above_search_criteria(DataTable table)
            throws Throwable {
        Thread.sleep(3000);
        List<List<String>> list = table.raw();
        pageFactory.getListIndexMethods().verifySearchResults(list.get(0).get(0), ListIndexMethods.Column.rateBasis);
        pageFactory.getListIndexMethods().verifySearchResults(list.get(0).get(1), ListIndexMethods.Column.name);
        pageFactory.getListIndexMethods().verifySearchResults(list.get(0).get(2), ListIndexMethods.Column.currency);
        pageFactory.getListIndexMethods().verifySearchResults(list.get(0).get(3), ListIndexMethods.Column.uom);

    }

    /*
     * Method to insert high,medium,low and close prices
     */
    @When("^([^\"]*),([^\"]*),([^\"]*) and ([^\"]*) are entered$")
    public void and_are_entered(String lowPrice, String midPrice, String highPrice, String closePrice) throws Throwable {
        pageFactory.getCreateIndexMethods().setLowPrice(lowPrice);
        pageFactory.getCreateIndexMethods().setMidPrice(midPrice);
        pageFactory.getCreateIndexMethods().setHighPrice(highPrice);
        pageFactory.getCreateIndexMethods().setClosePrice(closePrice);
    }

    @And("^start date as ([^\"]*) and end date as ([^\"]*)")
    public void enterStartDateAndEndDate(String startDate, String endDate) throws Throwable {
        pageFactory.getCreateIndexMethods().setStartDate(startDate);
        pageFactory.getCreateIndexMethods().setEndDate(endDate);
    }

    @Then("^the user shall be able to view the created index in the list on filtering with ([^\"]*)$")
    public void the_user_shall_be_able_to_view_the_created_index_in_the_list_on_filtering_with(String rate)
            throws Throwable {
        pageFactory.getListIndexMethods().verifyIfListPageDisplayed(true);
    }

    @And("^the user clicked on ([^\"]*) action$")
    public void click_on_button(String action) throws Exception {
        pageFactory.getListIndexMethods().clickOnAction(action);
    }

    @Then("^the index (should|should not) be (created|updated)$")
    public void the_index_should_be_createdOrUpdated(String action, String action1) {
        if (action.equalsIgnoreCase("should")) {
            pageFactory.getListIndexMethods().verifyIfListPageDisplayed(true);
        } else {
            pageFactory.getListIndexMethods().verifyIfListPageDisplayed(false);
        }
    }

    @Then("^the user shall be able to edit only end date$")
    public void the_user_shall_be_able_to_edit_only_end_date() throws Throwable {
        pageFactory.getListIndexMethods().verifyUserIsAbleToEditOnlyEndDate();
    }

    @Then("^the status of the index should change to inactive$")
    public void the_status_of_the_index_should_change_to_inactive() {
        pageFactory.getListIndexMethods().verifyIfTheStatusOfIndexChangedToInactive();
    }

    @Then("^the user is not allowed to (update|enter) low,mid,high and close prices for (active|new) index$")
    public void the_user_is_not_allowed_to_update_lowMidHighAndClose_prices_for_active_index(String act, String type) {
        pageFactory.getListIndexMethods().verifyUserIsUnableToEditPrices();
    }

    @And("^scale rates are editable$")
    public void scale_rates_are_editable() {
        pageFactory.getListIndexMethods().verifyScaleratesAreEditable();
    }

    @And("^add the scale rates$")
    public void add_the_scale_rates(DataTable table) throws Exception {
        List<List<String>> list = table.raw();
        int i = 0;
        for (List<String> row : list) {
            if (i++ > 0) {
                pageFactory.getListIndexMethods().addScaleParamater();
            }
            pageFactory.getListIndexMethods().setScaleParameters(row.get(0), row.get(1), row.get(2));
        }
    }


}
