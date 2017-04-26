package functions.index;

import functions.GenericWebElementMethods;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

public class CreateIndexMethods extends GenericWebElementMethods implements ICreateIndex {

        private EventFiringWebDriver edriver;

        public CreateIndexMethods()
        {
            edriver = getEdriver();
        }
        public void setName(String name)
        {
            setNameFromAutoFill(Constants.indexList_name_xpath, name);
        }
        public void setStartDate(String startDate)
        {
            selectDate(startDate,Constants.indexCreate_startDatePicker_xpath,Constants.indexCreate_startDate_xpath);
        }
        public void setEndDate(String endDate)
        {
            selectDate(endDate,Constants.indexCreate_endDatePicker_xpath,Constants.indexCreate_endDate_xpath);
        }
        public void setCurrency(String currency)
        {
            selectFromDropDown(Constants.indexCreate_currency_xpath,currency);
        }
        public void setUom(String uom)
        {
            selectFromDropDown(Constants.indexCreate_uom_xpath,uom);
        }
        public void setRateBasis(String rateBasis)
        {
            selectFromDropDown(Constants.indexList_rateBasis_xpath,rateBasis);
        }
        public void setLowPrice(String lowPrice)
        {
            sendKeysToWE(Constants.indexCreate_lowprice_xpath,lowPrice.trim());
        }
        public void setMidPrice(String midPrice)
        {
            sendKeysToWE(Constants.indexCreate_midprice_xpath,midPrice.trim());
        }
        public void setHighPrice(String highPrice)
        {
            sendKeysToWE(Constants.indexCreate_highprice_xpath,highPrice.trim());
        }
        public void setClosePrice(String closePrice)
        {
            sendKeysToWE(Constants.indexCreate_closeprice_xpath,closePrice.trim());
        }
        public void setComments(String comment)
        {
            sendKeysToWE(Constants.indexCreate_comment_xpath,comment);
        }
}
