package functions.index;

import functions.GenericWebElementMethods;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;

public class CreateIndexMethods extends GenericWebElementMethods {

    public void setName(String name) {
        setNameFromAutoFill(Constants.indexList_name_xpath, name, -1);
    }

    public void setStartDate(String startDate) {
        if (startDate.matches("^[a-zA-Z]*$")) {
            startDate = setDateWithTimeStamp(startDate, false);
        }
        clearText(Constants.indexCreate_startDate_xpath);
        sendKeysToWE(Constants.indexCreate_startDate_xpath, startDate);
        clickOnTab();
    }

    public void setEndDate(String endDate) {
        if (endDate.matches("^[a-zA-Z]*$")) {
            endDate = setDateWithTimeStamp(endDate, false);
        }
        clearText(Constants.indexCreate_endDate_xpath);
        sendKeysToWE(Constants.indexCreate_endDate_xpath, endDate);
        clickOnTab();
    }

    public void setCurrency(String currency) {
        selectFromDropDown_SelectTag(Constants.indexCreate_currency_xpath, currency);
    }

    public void setUom(String uom) {
        selectFromDropDown_SelectTag(Constants.indexCreate_uom_xpath, uom);
    }

    public void setRateBasis(String rateBasis) {
        selectFromDropDown_SelectTag(Constants.indexCreate_rateBasis_xpath, rateBasis);
    }

    public void setLowPrice(String lowPrice) {
        sendKeysToWE(Constants.indexCreate_lowprice_xpath, lowPrice.trim());
    }

    public void setMidPrice(String midPrice) {
        sendKeysToWE(Constants.indexCreate_midprice_xpath, midPrice.trim());
    }

    public void setHighPrice(String highPrice) {
        sendKeysToWE(Constants.indexCreate_highprice_xpath, highPrice.trim());
    }

    public void setClosePrice(String closePrice) {
        sendKeysToWE(Constants.indexCreate_closeprice_xpath, closePrice.trim());
    }

    public void setComments(String comment) {
        sendKeysToWE(Constants.indexCreate_comment_xpath, comment);
    }
}
