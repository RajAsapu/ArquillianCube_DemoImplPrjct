package functions.currencyexchange;

import functions.GenericWebElementMethods;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

public class CreateCurrencyExchangeMethods extends GenericWebElementMethods {

    private EventFiringWebDriver edriver;

    public CreateCurrencyExchangeMethods()
    {
        edriver = getEdriver();
    }

    public void setDate(String date)
    {
        clearText(Constants.currencyExchangeCreate_startDate_xpath);
        sendKeysToWE(Constants.currencyExchangeCreate_startDate_xpath,date);
        clickOnTab();
    }
    public void setType(String type)
    {
        selectFromDropDown_LabelTag(Constants.currencyExchangeCreate_type_xpath,type,0);
    }
    public void setFromCurrency(String fromCurrency)
    {
        selectFromDropDown_LabelTag(Constants.currencyExchangeCreate_fromCurrency_xpath,fromCurrency,0);
    }
    public void setToCurrency(String toCurrency) {
        selectFromDropDown_LabelTag(Constants.currencyExchangeCreate_toCurrency_xpath,toCurrency,0);
    }
    public void setConverstionRate(String rate)
    {
        sendKeysToWE(Constants.currencyExchangeCreate_conversionRate_xpath,rate);
    }
}
