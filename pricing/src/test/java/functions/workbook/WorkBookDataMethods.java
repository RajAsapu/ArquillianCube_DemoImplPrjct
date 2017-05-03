package functions.workbook;

import functions.GenericWebElementMethods;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;

/**
 * Created by root on 5/1/17.
 */
public class WorkBookDataMethods extends GenericWebElementMethods {
    private EventFiringWebDriver edriver;

    public WorkBookDataMethods() {
        edriver = getEdriver();
    }

    public void clickOnAddNewData() {
        clickButton(Constants.workbookData_addNewData_xpath);
    }

    public void clickOnSearch() {
        clickButton(Constants.workbookData_search_xpath);
    }

    public void clickOnUpload() {
        clickButton(Constants.workbookData_upload_xpath);
    }

    public void setStartDate(String startDate) {
        sendKeysToWE(Constants.workbookData_addNewDataStartDate_xpath, startDate);
        clickOnTab();
    }

    public void setEndDate(String endDate) {
        sendKeysToWE(Constants.workbookData_addNewDataEndDate_xpath, endDate);
        clickOnTab();
    }

    public void clickOnUpdate() {
        clickButton(Constants.workbookData_editAction_xpath);
    }


}
