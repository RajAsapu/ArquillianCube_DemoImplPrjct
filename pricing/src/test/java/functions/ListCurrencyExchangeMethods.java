package functions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListCurrencyExchangeMethods extends GenericWebElementMethods {

    private static EventFiringWebDriver edriver;

    public ListCurrencyExchangeMethods() {
        edriver = DriverBean.getDriver();
    }

    public void setSearchType(String searchType) throws Exception {
        selectFromDropDown(Constants.currencyExchangeList_type_xpath, searchType);
    }

    public void setStartDate(String startDate) {
        selectDate(startDate, Constants.currencyExchangeList_startDatePicker_xpath, null);
    }

    public void setEndDate(String endDate) {
        selectDate(endDate, Constants.currencyExchangeList_endDatePicker_xpath, null);
    }

    public void setStatus(String status) throws Exception {
        selectFromDropDown(Constants.currencyExchangeList_status_xpath, status);
    }

    public void setConverstionType(String converstionType) throws Exception {
        selectFromDropDown(Constants.currencyExchangeList_convType_xpath, converstionType);
    }

    public void setCurrencyFrom(String currencyFrom) throws Exception {
        selectFromDropDown(Constants.currencyExchangeList_currForm_xpath, currencyFrom);
    }

    public void setCurrencyTo(String currencyTo) throws Exception {
        selectFromDropDown(Constants.currencyExchangeList_currTo_xpath, currencyTo);
    }

    public void clickOnSearch() {

    }

    public void verifyIfThePageIsListPage() {
        if (!edriver.getCurrentUrl().contains("/currency-exchange/list")) {
            Assert.fail("Currency Exchange info is not created");
        }
    }

    public void verifyIfStatusMatchingTheFilter(String status) throws Exception {

        List<WebElement> statusList;
        Thread.sleep(3000);
        statusList = edriver.findElements(By.xpath(Constants.currencyExchangeList_statusColumn_xpath));
        if (statusList.size() != 0 && !statusList.listIterator().next().getText().equals("No records found")) {
            statusList = edriver.findElements(By.xpath(Constants.currencyExchangeList_statusColumn_xpath));
            for (WebElement temp : statusList) {
                if (!temp.getText().equalsIgnoreCase(status)) {
                    throw new Exception("Status Column doesn't match, Expected :" + status + " Actual:" + temp.getText());
                }
            }
        }
    }

    public void verifyIfRateTypeMatchingTheFilter(String rateType) throws Exception {

        List<WebElement> typeList;
        Thread.sleep(3000);
        typeList = edriver.findElements(By.xpath(Constants.currencyExchangeList_rateTypeColumn_xpath));
        if (typeList.size() != 0 && !typeList.listIterator().next().getText().equals("No records found")) {
            typeList = edriver.findElements(By.xpath(Constants.currencyExchangeList_rateTypeColumn_xpath));
            for (WebElement temp : typeList) {
                if (!temp.getText().equalsIgnoreCase(rateType)) {
                    throw new Exception(
                            "Rate type column doesn't match, Expected :" + rateType + " Actual:" + temp.getText());
                }
            }
        }
    }

    public void verifyIfRateMatchingTheFilter() throws Exception {

        List<WebElement> typeList;
        Thread.sleep(3000);
        typeList = edriver.findElements(By.xpath(Constants.currencyExchangeList_rateColumn_xpath));
        if (typeList.size() != 0 && !typeList.listIterator().next().getText().equals("No records found")) {
            typeList = edriver.findElements(By.xpath(Constants.currencyExchangeList_rateColumn_xpath));
            for (WebElement temp : typeList) {
                if (!temp.getText().matches("^[0-9.]*$")) {
                    throw new Exception("Rate column is not a number");
                }
            }
        }
    }

    public void verifyIfCurrenyFromIsMatchingTheFilter(String currencyFrom) throws Exception {

        List<WebElement> fromList;
        Thread.sleep(3000);
        fromList = edriver.findElements(By.xpath(Constants.currencyExchangeList_fromColumn_xpath));
        if (fromList.size() != 0 && !fromList.listIterator().next().getText().equals("No records found")) {
            fromList = edriver.findElements(By.xpath(Constants.currencyExchangeList_fromColumn_xpath));
            for (WebElement temp : fromList) {
                if (!temp.getText().equalsIgnoreCase(currencyFrom)) {
                    throw new Exception(
                            "Currency from Column doesn't match, Expected :" + currencyFrom + " Actual:" + temp.getText());
                }
            }
        }
    }

    public void verifyIfCurrenyToIsMatchingTheFilter(String currencyTo) throws Exception {

        List<WebElement> fromList;
        Thread.sleep(3000);
        fromList = edriver.findElements(By.xpath(Constants.currencyExchangeList_toColumn_xpath));
        if (fromList.size() != 0 && !fromList.listIterator().next().getText().equals("No records found")) {
            fromList = edriver.findElements(By.xpath(Constants.currencyExchangeList_toColumn_xpath));
            for (WebElement temp : fromList) {
                if (!temp.getText().equalsIgnoreCase(currencyTo)) {
                    throw new Exception(
                            "Currency to Column doesn't match, Expected :" + currencyTo + " Actual:" + temp.getText());
                }
            }
        }
    }

    public void verifyIfDateIsMatchingTheFilter(String fromDate) throws Exception {

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
