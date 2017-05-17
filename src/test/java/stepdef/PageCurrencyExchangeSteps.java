package stepdef;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import setup.PageFactory;

import java.util.List;

public class PageCurrencyExchangeSteps {
    final static Logger logger = Logger.getLogger(PageCurrencyExchangeSteps.class.getName());
    /*
     * Intialize required objects
     */
    private PageFactory pageFactory = null;

    public PageCurrencyExchangeSteps() {
        pageFactory = new PageFactory();
    }

    @When("^the user selects the search criteria as \"([^\"]*)\"$")
    public void the_user_selects_the_search_criteria_as(String searchType) throws Throwable {
        pageFactory.getListCurrencyExchangeMethods().setSearchType(searchType);
    }

    @And("^sets the status as \"([^\"]*)\"$")
    public void sets_the_status_as(String status) throws Exception {
        pageFactory.getListCurrencyExchangeMethods().setStatus(status);
    }

    @When("^sets the converstion type as \"([^\"]*)\"$")
    public void sets_the_converstion_type_as(String type) throws Throwable {
        pageFactory.getListCurrencyExchangeMethods().setConverstionType(type);
    }

    @And("^sets date as \"([^\"]*)\"$")
    public void sets_date_as(String date) throws Throwable {
        pageFactory.getListCurrencyExchangeMethods().setStartDate(date);
    }

    @And("^sets date range as \"([^\"]*)\" to \"([^\"]*)\"$")
    public void sets_date_range_as(String from, String to) throws Throwable {
        pageFactory.getListCurrencyExchangeMethods().setStartDate(from);
        pageFactory.getListCurrencyExchangeMethods().setEndDate(to);
    }

    @And("^sets the currencyFrom field to \"([^\"]*)\"$")
    public void sets_the_currencyFrom_field_to(String from) throws Throwable {
        pageFactory.getListCurrencyExchangeMethods().setCurrencyFrom(from);
    }

    @And("^sets the currencyTo field to \"([^\"]*)\"$")
    public void sets_the_currencyTo_field_to(String to) throws Throwable {
        pageFactory.getListCurrencyExchangeMethods().setCurrencyTo(to);
    }

    @Then("^the application displays the search results as per the filters$")
    public void the_application_displays_the_search_results_as_per_the_filters(DataTable table) throws Throwable {

        List<List<String>> list = table.raw();
        pageFactory.getListCurrencyExchangeMethods().verifyIfStatusMatchingTheFilter(list.get(0).get(5));
        pageFactory.getListCurrencyExchangeMethods().verifyIfCurrenyFromIsMatchingTheFilter(list.get(0).get(3));
        pageFactory.getListCurrencyExchangeMethods().verifyIfCurrenyToIsMatchingTheFilter(list.get(0).get(4));
        pageFactory.getListCurrencyExchangeMethods().verifyIfDateIsMatchingTheFilter(list.get(0).get(1));
        pageFactory.getListCurrencyExchangeMethods().verifyIfRateTypeMatchingTheFilter(list.get(0).get(2));
        pageFactory.getListCurrencyExchangeMethods().verifyIfRateMatchingTheFilter();
    }

    @When("^the user selects the sets the date as \"([^\"]*)\"$")
    public void the_user_selects_the_sets_the_date_as(String date) throws Throwable {
        pageFactory.getCreateCurrencyExchangeMethods().setDateWithTimeStamp(date, false);
    }

    @When("^sets the currency type as \"([^\"]*)\"")
    public void sets_the_currency_type_as(String type) throws Throwable {
        pageFactory.getCreateCurrencyExchangeMethods().setType(type);
    }

    @When("^sets the currencyFrom field in create page to \"([^\"]*)\"")
    public void sets_the_currencyFrom_field_in_create_page_to(String fromCurrency) throws Throwable {
        pageFactory.getCreateCurrencyExchangeMethods().setFromCurrency(fromCurrency);
    }

    @When("^sets the currencyTo field in create page to \"([^\"]*)\"$")
    public void sets_the_currencyTo_field_in_create_page_to_INR(String toCurrency) throws Throwable {
        pageFactory.getCreateCurrencyExchangeMethods().setToCurrency(toCurrency);
    }

    @When("^converstion rate to \"([^\"]*)\"$")
    public void converstion_rate_to(String rate) throws Throwable {
        pageFactory.getCreateCurrencyExchangeMethods().setConverstionRate(rate);
    }

    @Then("^the currency exchange info should be created$")
    public void the_currency_exchange_info_should_be_created(DataTable table) throws Exception {
        List<List<String>> data = table.raw();
        pageFactory.getListCurrencyExchangeMethods().verifyIfThePageIsListPage();
    }


}
