package functions.currencyexchange;

import functions.GenericWebElementMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListCurrencyExchangeMethods extends GenericWebElementMethods {

    private EventFiringWebDriver edriver;

    public ListCurrencyExchangeMethods() {
        edriver = DriverBean.getDriver();
    }

    public void setSearchType(String searchType) {
        selectFromDropDown_SelectTag(Constants.currencyExchangeList_type_xpath, searchType);
    }

    public void setStartDate(String startDate) {
        selectDate(startDate, Constants.currencyExchangeList_startDatePicker_xpath, null);
    }

    public void setEndDate(String endDate) {
        selectDate(endDate, Constants.currencyExchangeList_endDatePicker_xpath, null);
    }

    public void setStatus(String status) {
        selectFromDropDown_SelectTag(Constants.currencyExchangeList_status_xpath, status);
    }

    public void setConverstionType(String converstionType) {
        selectFromDropDown_SelectTag(Constants.currencyExchangeList_convType_xpath, converstionType);
    }

    public void setCurrencyFrom(String currencyFrom) {
        selectFromDropDown_SelectTag(Constants.currencyExchangeList_currForm_xpath, currencyFrom);
    }

    public void setCurrencyTo(String currencyTo) {
        selectFromDropDown_SelectTag(Constants.currencyExchangeList_currTo_xpath, currencyTo);
    }

    public void clickOnSearch() {

    }

    public void verifyIfThePageIsListPage() {
        if (!edriver.getCurrentUrl().contains("/currency-exchange/list")) {
            Assert.fail("Currency Exchange info is not created");
        }
    }

    public void verifyIfStatusMatchingTheFilter(String status) {
        List<WebElement> statusList;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        statusList = edriver.findElements(By.xpath(Constants.currencyExchangeList_statusColumn_xpath));
        if (statusList.size() != 0 && !statusList.listIterator().next().getText().equals("No records found")) {
            while (true) {
                try {
                    statusList = edriver.findElements(By.xpath(Constants.currencyExchangeList_statusColumn_xpath));
                    for (WebElement temp : statusList) {
                        if (!temp.getText().equalsIgnoreCase(status)) {
                            Assert.fail("Status Column doesn't match, Expected :" + status + " Actual:" + temp.getText());
                        }
                    }
                    break;
                } catch (StaleElementReferenceException exp) {
                    continue;
                }
            }
        }
    }

    public void verifyIfRateTypeMatchingTheFilter(String rateType) {
        List<WebElement> typeList;
        typeList = edriver.findElements(By.xpath(Constants.currencyExchangeList_rateTypeColumn_xpath));
        if (typeList.size() != 0 && !typeList.listIterator().next().getText().equals("No records found")) {
            typeList = edriver.findElements(By.xpath(Constants.currencyExchangeList_rateTypeColumn_xpath));
            for (WebElement temp : typeList) {
                if (!temp.getText().equalsIgnoreCase(rateType)) {
                    Assert.fail(
                            "Rate type column doesn't match, Expected :" + rateType + " Actual:" + temp.getText());
                }
            }
        }
    }

    public void verifyIfRateMatchingTheFilter() {
        List<WebElement> typeList;
        typeList = edriver.findElements(By.xpath(Constants.currencyExchangeList_rateColumn_xpath));
        if (typeList.size() != 0 && !typeList.listIterator().next().getText().equals("No records found")) {
            typeList = edriver.findElements(By.xpath(Constants.currencyExchangeList_rateColumn_xpath));
            for (WebElement temp : typeList) {
                if (!temp.getText().matches("^[0-9.]*$")) {
                    Assert.fail("Rate column is not a number");
                }
            }
        }
    }

    public void verifyIfCurrenyFromIsMatchingTheFilter(String currencyFrom) {
        List<WebElement> fromList;
        fromList = edriver.findElements(By.xpath(Constants.currencyExchangeList_fromColumn_xpath));
        if (fromList.size() != 0 && !fromList.listIterator().next().getText().equals("No records found")) {
            fromList = edriver.findElements(By.xpath(Constants.currencyExchangeList_fromColumn_xpath));
            for (WebElement temp : fromList) {
                if (!temp.getText().equalsIgnoreCase(currencyFrom)) {
                    Assert.fail(
                            "Currency from Column doesn't match, Expected :" + currencyFrom + " Actual:" + temp.getText());
                }
            }
        }
    }

    public void verifyIfCurrenyToIsMatchingTheFilter(String currencyTo) {
        List<WebElement> fromList;
        fromList = edriver.findElements(By.xpath(Constants.currencyExchangeList_toColumn_xpath));
        if (fromList.size() != 0 && !fromList.listIterator().next().getText().equals("No records found")) {
            fromList = edriver.findElements(By.xpath(Constants.currencyExchangeList_toColumn_xpath));
            for (WebElement temp : fromList) {
                if (!temp.getText().equalsIgnoreCase(currencyTo)) {
                    Assert.fail(
                            "Currency to Column doesn't match, Expected :" + currencyTo + " Actual:" + temp.getText());
                }
            }
        }
    }

    public void verifyIfDateIsMatchingTheFilter(String fromDate) {
        List<WebElement> statusList;
        statusList = edriver.findElements(By.xpath(Constants.currencyExchangeList_dateColumn_xpath));
        try {
            SimpleDateFormat format = new SimpleDateFormat("DD-MMM-YYYY");
            Date filter = format.parse(fromDate);

            if (statusList.size() != 0 && !statusList.listIterator().next().getText().equals("No records found")) {
                for (WebElement temp : statusList) {
                    Date result = format.parse(temp.getText());
                    if (filter.compareTo(result) > 0) {
                        Assert.fail("Date column doesn't match the filter, Expected :" + fromDate + " Actual:"
                                + temp.getText());
                    }
                }
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
