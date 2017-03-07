package stepdef;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import setup.CommonFunctions;
import setup.Constants;
import setup.DriverBean;

public class PageCurrencyExchangeSteps extends CommonFunctions {
	final static Logger logger = Logger.getLogger(PageCurrencyExchangeSteps.class.getName());
	/*
	 * Intialize required objects
	 */
	private static EventFiringWebDriver edriver = DriverBean.getDriver();
	public PageCommonSteps steps = new PageCommonSteps();

	@When("^the user selects the search criteria as \"([^\"]*)\"$")
	public void the_user_selects_the_search_criteria_as(String searchType) throws Throwable {
		selectSearchType(searchType);
	}

	@And("^sets the status as \"([^\"]*)\"$")
	public void sets_the_status_as(String status) throws Exception {
		selectStatus(status);
	}

	@When("^sets the converstion type as \"([^\"]*)\"$")
	public void sets_the_converstion_type_as(String type) throws Throwable {
		selectConversionType(type);
	}

	@And("^sets date as \"([^\"]*)\"$")
	public void sets_date_as(String date) throws Throwable {
		WebElement datepicker = edriver.findElement(By.xpath(Constants.currencyExchangeList_startDatePicker_xpath));

		Actions act = new Actions(edriver);
		act.click(datepicker).sendKeys(date).perform();
		act.sendKeys(Keys.TAB).perform();
	}

	@And("^sets date range as \"([^\"]*)\" to \"([^\"]*)\"$")
	public void sets_date_range_as(String from, String to) throws Throwable {
		WebElement datepicker = edriver.findElement(By.xpath(Constants.currencyExchangeList_startDatePicker_xpath));

		Actions act = new Actions(edriver);
		act.click(datepicker).sendKeys(from).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(to).perform();
	}

	@And("^sets the currencyFrom field to \"([^\"]*)\"$")
	public void sets_the_currencyFrom_field_to(String from) throws Throwable {
		selectCurrencyFrom(from);
	}

	@And("^sets the currencyTo field to \"([^\"]*)\"$")
	public void sets_the_currencyTo_field_to(String to) throws Throwable {
		selectCurrencyTo(to);
	}

	@Then("^the application displays the search results as per the filters$")
	public void the_application_displays_the_search_results_as_per_the_filters(DataTable table) throws Throwable {

		List<List<String>> list = table.raw();
		checkStatus_CurrencyExchangeTabe(list.get(0).get(5));
		checkFrom_CurrencyExchangeTable(list.get(0).get(3));
		checkTo_CurrencyExchangeTable(list.get(0).get(4));
		checkDate_CurrencyExchangeTabe(list.get(0).get(1));
		checkRateType_CurrencyExchangeTabe(list.get(0).get(2));
		checkRate_CurrencyExchangeTabe();
	}

	@When("^the user selects the sets the date as \"([^\"]*)\"$")
	public void the_user_selects_the_sets_the_date_as(String date) throws Throwable {
		WebElement datepicker = edriver.findElement(By.xpath(Constants.currencyExchangeCreate_datePicker_xpath));
		Actions act = new Actions(edriver);
		act.sendKeys(date).sendKeys(Keys.TAB).perform();
	}

	@When("^sets the currency type as \"([^\"]*)\"")
	public void sets_the_currency_type_as(String key) throws Throwable {
		WebElement searchType = edriver.findElement(By.xpath(Constants.currencyExchangeCreate_type_xpath));
		Select choose = new Select(searchType);
		choose.selectByVisibleText(key);
	}

	@When("^sets the currencyFrom field in create page to \"([^\"]*)\"")
	public void sets_the_currencyFrom_field_in_create_page_to(String key) throws Throwable {
		WebElement searchType = edriver.findElement(By.xpath(Constants.currencyExchangeCreate_fromCurrency_xpath));
		Select choose = new Select(searchType);
		choose.selectByVisibleText(key);
	}

	@When("^sets the currencyTo field in create page to \"([^\"]*)\"$")
	public void sets_the_currencyTo_field_in_create_page_to_INR(String key) throws Throwable {
		WebElement searchType = edriver.findElement(By.xpath(Constants.currencyExchangeCreate_toCurrency_xpath));
		Select choose = new Select(searchType);
		choose.selectByVisibleText(key);
	}

	@When("^converstion rate to \"([^\"]*)\"$")
	public void converstion_rate_to(String rate) throws Throwable {
		WebElement conRate = edriver.findElement(By.xpath(Constants.currencyExchangeCreate_conversionRate_xpath));
		conRate.sendKeys(rate);
		Thread.sleep(3000);
	}

	@Then("^the currency exchange info should be created$")
	public void the_currency_exchange_info_should_be_created(DataTable table) throws Exception {
		List<List<String>> data = table.raw();
		/*
		 * Write Validation to check the tables in the list
		 */
		if (!edriver.getCurrentUrl().contains("/currency-exchange/list")) {
			throw new Exception("Currency Exchange info is not created");
		}
	}

	public void checkStatus_CurrencyExchangeTabe(String rate) throws Exception {

		List<WebElement> statusList;
		Thread.sleep(3000);
		statusList = edriver.findElements(By.xpath(Constants.currencyExchangeList_statusColumn_xpath));
		logger.info("Size:" + statusList.size());
		if (statusList.size() != 0 && !statusList.listIterator().next().getText().equals("No records found")) {
			statusList = edriver.findElements(By.xpath(Constants.currencyExchangeList_statusColumn_xpath));
			for (WebElement temp : statusList) {
				if (!temp.getText().equalsIgnoreCase(rate)) {
					throw new Exception("Status Column doesn't match, Expected :" + rate + " Actual:" + temp.getText());
				}
			}
		}
	}

	public void checkRateType_CurrencyExchangeTabe(String rate) throws Exception {

		List<WebElement> typeList;
		Thread.sleep(3000);
		typeList = edriver.findElements(By.xpath(Constants.currencyExchangeList_rateTypeColumn_xpath));
		logger.info("Size:" + typeList.size());
		if (typeList.size() != 0 && !typeList.listIterator().next().getText().equals("No records found")) {
			typeList = edriver.findElements(By.xpath(Constants.currencyExchangeList_rateTypeColumn_xpath));
			for (WebElement temp : typeList) {
				if (!temp.getText().equalsIgnoreCase(rate)) {
					throw new Exception(
							"Rate type column doesn't match, Expected :" + rate + " Actual:" + temp.getText());
				}
			}
		}
	}

	public void checkRate_CurrencyExchangeTabe() throws Exception {

		List<WebElement> typeList;
		Thread.sleep(3000);
		typeList = edriver.findElements(By.xpath(Constants.currencyExchangeList_rateColumn_xpath));
		logger.info("Size:" + typeList.size());
		if (typeList.size() != 0 && !typeList.listIterator().next().getText().equals("No records found")) {
			typeList = edriver.findElements(By.xpath(Constants.currencyExchangeList_rateColumn_xpath));
			for (WebElement temp : typeList) {
				if (!temp.getText().matches("^[0-9.]*$")) {
					throw new Exception("Rate column doesn't a number");
				}
			}
		}
	}

	public void checkFrom_CurrencyExchangeTable(String from) throws Exception {

		List<WebElement> fromList;
		Thread.sleep(3000);
		fromList = edriver.findElements(By.xpath(Constants.currencyExchangeList_fromColumn_xpath));
		logger.info("Size:" + fromList.size());
		if (fromList.size() != 0 && !fromList.listIterator().next().getText().equals("No records found")) {
			fromList = edriver.findElements(By.xpath(Constants.currencyExchangeList_fromColumn_xpath));
			for (WebElement temp : fromList) {
				if (!temp.getText().equalsIgnoreCase(from)) {
					throw new Exception(
							"Currency from Column doesn't match, Expected :" + from + " Actual:" + temp.getText());
				}
			}
		}
	}

	public void checkTo_CurrencyExchangeTable(String to) throws Exception {

		List<WebElement> fromList;
		Thread.sleep(3000);
		fromList = edriver.findElements(By.xpath(Constants.currencyExchangeList_toColumn_xpath));
		logger.info("Size:" + fromList.size());
		if (fromList.size() != 0 && !fromList.listIterator().next().getText().equals("No records found")) {
			fromList = edriver.findElements(By.xpath(Constants.currencyExchangeList_toColumn_xpath));
			for (WebElement temp : fromList) {
				if (!temp.getText().equalsIgnoreCase(to)) {
					throw new Exception(
							"Currency to Column doesn't match, Expected :" + to + " Actual:" + temp.getText());
				}
			}
		}
	}

	public void checkDate_CurrencyExchangeTabe(String fromDate) throws Exception {

		List<WebElement> statusList;
		statusList = edriver.findElements(By.xpath(Constants.currencyExchangeList_dateColumn_xpath));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date filter = format.parse(fromDate);

		if (statusList.size() != 0 && !statusList.listIterator().next().getText().equals("No records found")) {
			for (WebElement temp : statusList) {
				Date result = format.parse(temp.getText());
				if (filter.compareTo(result) > 0) {
					throw new Exception("Date column doesn't match the filter, Expected :" + fromDate + " Actual:"
							+ temp.getText());
				}
			}
		}
	}
}
