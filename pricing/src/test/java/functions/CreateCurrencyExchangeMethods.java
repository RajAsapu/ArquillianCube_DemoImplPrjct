package functions;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

public class CreateCurrencyExchangeMethods extends GenericWebElementMethods {

    private static EventFiringWebDriver edriver;

    public CreateCurrencyExchangeMethods() {
        edriver = DriverBean.getDriver();
    }

    public void setDate(String date) {
        selectDate(date, Constants.currencyExchangeCreate_datePicker_xpath, null);
    }

    public void setType(String type) throws Exception {
        selectFromDropDown_LabelTag(Constants.currencyExchangeCreate_type_xpath, type,-1);
    }

    public void setFromCurrency(String fromCurrency) throws Exception {
        selectFromDropDown_LabelTag(Constants.currencyExchangeCreate_fromCurrency_xpath, fromCurrency,-1);
    }

    public void setToCurrency(String toCurrency) throws Exception {
        selectFromDropDown_LabelTag(Constants.currencyExchangeCreate_toCurrency_xpath, toCurrency,-1);
    }

    public void setConverstionRate(String rate) {
        sendKeysToWE(Constants.currencyExchangeCreate_conversionRate_xpath, rate);
    }
}
