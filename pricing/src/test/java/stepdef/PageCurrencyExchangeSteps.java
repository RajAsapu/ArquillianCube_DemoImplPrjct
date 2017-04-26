package stepdef;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import functions.currencyexchange.CreateCurrencyExchangeMethods;
import functions.currencyexchange.ListCurrencyExchangeMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.DriverBean;
import java.util.List;

public class PageCurrencyExchangeSteps {
    final static Logger logger = Logger.getLogger(PageCurrencyExchangeSteps.class.getName());
    /*
     * Intialize required objects
     */
    private static EventFiringWebDriver edriver;
    private PageCommonSteps steps;
    private CreateCurrencyExchangeMethods createCurrencyExchangeMethods;
    private ListCurrencyExchangeMethods listCurrencyExchangeMethods;

    public PageCurrencyExchangeSteps() {
        createCurrencyExchangeMethods = new CreateCurrencyExchangeMethods();
        listCurrencyExchangeMethods = new ListCurrencyExchangeMethods();
        edriver = DriverBean.getDriver();
        steps = new PageCommonSteps();
    }

    @When("^the user selects the search criteria as \"([^\"]*)\"$")
    public void the_user_selects_the_search_criteria_as(String searchType) throws Throwable {
        listCurrencyExchangeMethods.setSearchType(searchType);
    }

    @And("^sets the status as \"([^\"]*)\"$")
    public void sets_the_status_as(String status) throws Exception {
        listCurrencyExchangeMethods.setStatus(status);
    }

    @When("^sets the converstion type as \"([^\"]*)\"$")
    public void sets_the_converstion_type_as(String type) throws Throwable {
        listCurrencyExchangeMethods.setConverstionType(type);
    }

    @And("^sets date as \"([^\"]*)\"$")
    public void sets_date_as(String date) throws Throwable {
        listCurrencyExchangeMethods.setStartDate(date);
    }

    @And("^sets date range as \"([^\"]*)\" to \"([^\"]*)\"$")
    public void sets_date_range_as(String from, String to) throws Throwable {
        listCurrencyExchangeMethods.setStartDate(from);
        listCurrencyExchangeMethods.setEndDate(to);
    }

    @And("^sets the currencyFrom field to \"([^\"]*)\"$")
    public void sets_the_currencyFrom_field_to(String from) throws Throwable {
        listCurrencyExchangeMethods.setCurrencyFrom(from);
    }

    @And("^sets the currencyTo field to \"([^\"]*)\"$")
    public void sets_the_currencyTo_field_to(String to) throws Throwable {
        listCurrencyExchangeMethods.setCurrencyTo(to);
    }

    @Then("^the application displays the search results as per the filters$")
    public void the_application_displays_the_search_results_as_per_the_filters(DataTable table) throws Throwable {

        List<List<String>> list = table.raw();
        listCurrencyExchangeMethods.verifyIfStatusMatchingTheFilter(list.get(0).get(5));
        listCurrencyExchangeMethods.verifyIfCurrenyFromIsMatchingTheFilter(list.get(0).get(3));
        listCurrencyExchangeMethods.verifyIfCurrenyToIsMatchingTheFilter(list.get(0).get(4));
        listCurrencyExchangeMethods.verifyIfDateIsMatchingTheFilter(list.get(0).get(1));
        listCurrencyExchangeMethods.verifyIfRateTypeMatchingTheFilter(list.get(0).get(2));
        listCurrencyExchangeMethods.verifyIfRateMatchingTheFilter();
    }

    @When("^the user selects the sets the date as \"([^\"]*)\"$")
    public void the_user_selects_the_sets_the_date_as(String date) throws Throwable {
        createCurrencyExchangeMethods.setDate(date);
    }

    @When("^sets the currency type as \"([^\"]*)\"")
    public void sets_the_currency_type_as(String type) throws Throwable {
       createCurrencyExchangeMethods.setType(type);
    }

    @When("^sets the currencyFrom field in create page to \"([^\"]*)\"")
    public void sets_the_currencyFrom_field_in_create_page_to(String fromCurrency) throws Throwable {
        createCurrencyExchangeMethods.setFromCurrency(fromCurrency);
    }

    @When("^sets the currencyTo field in create page to \"([^\"]*)\"$")
    public void sets_the_currencyTo_field_in_create_page_to_INR(String toCurrency) throws Throwable {
        createCurrencyExchangeMethods.setToCurrency(toCurrency);
    }

    @When("^converstion rate to \"([^\"]*)\"$")
    public void converstion_rate_to(String rate) throws Throwable {
       createCurrencyExchangeMethods.setConverstionRate(rate);
    }

    @Then("^the currency exchange info should be created$")
    public void the_currency_exchange_info_should_be_created(DataTable table) throws Exception {
        List<List<String>> data = table.raw();
        listCurrencyExchangeMethods.verifyIfThePageIsListPage();
    }


}
