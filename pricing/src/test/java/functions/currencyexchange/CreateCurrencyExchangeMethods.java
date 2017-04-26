package functions.currencyexchange;

import functions.GenericWebElementMethods;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

public class CreateCurrencyExchangeMethods extends GenericWebElementMethods implements ICreateCurrencyExchange {

    private EventFiringWebDriver edriver;

    public CreateCurrencyExchangeMethods()
    {
        edriver = getEdriver();
    }

    public void setDate(String date)
    {
        selectDate(date, Constants.currencyExchangeCreate_datePicker_xpath,null);
    }
    public void setType(String type)
    {
        selectFromDropDown(Constants.currencyExchangeCreate_type_xpath,type);
    }
    public void setFromCurrency(String fromCurrency)
    {
        selectFromDropDown(Constants.currencyExchangeCreate_fromCurrency_xpath,fromCurrency);
    }
    public void setToCurrency(String toCurrency) {
        selectFromDropDown(Constants.currencyExchangeCreate_toCurrency_xpath,toCurrency);
    }
    public void setConverstionRate(String rate)
    {
        sendKeysToWE(Constants.currencyExchangeCreate_conversionRate_xpath,rate);
    }
}
