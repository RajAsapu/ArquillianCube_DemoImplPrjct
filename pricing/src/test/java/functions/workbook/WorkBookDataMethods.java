package functions.workbook;

import com.google.common.base.Verify;
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

    public void clickOnChoose(String filePath) {
        sendKeysToWE(Constants.workbookData_chooseFile_xpath,filePath);
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


    public void verifyIfWorkbookDataIsCreated(boolean condition) throws Exception {
        String currentUrl = edriver.getCurrentUrl();
        Thread.sleep(4000);
        if (condition) {
            Verify.verify(currentUrl.contains("workbook/data"), "Workbook data is not created");
        } else {
            Verify.verify(!currentUrl.contains("workbook/data"), "Workbook data is created");
        }
    }

    public void setAttribute(String attribute,String attributeValue)
    {
        switch (attribute.toLowerCase())
        {
            case "supplier" : setNameFromAutoFill(Constants.workbookData_addNewDataSupplier_xpath,attributeValue);
                break;
            case "suppliersite" : setNameFromAutoFill(Constants.workbookData_addNewDataSupplierSite_xpath,attributeValue);
                break;
            case "location" :setNameFromAutoFill(Constants.workbookData_addNewDataLocation_xpath,attributeValue);
                break;
            case "item": setNameFromAutoFill(Constants.workbookData_addNewDataItem_xpath,attributeValue);
                break;
            case "hauler": setNameFromAutoFill(Constants.workbookData_addNewDataHauler_xpath,attributeValue);
                break;
            case "customershipto" : selectFromDropDown_LabelTag(Constants.workbookData_addNewDataCustomershipto_xpath,attributeValue,0);
                break;
            case "customer" : selectFromDropDownWithSearchBar_LabelTag(Constants.workbookData_addNewDataCustomer_xpath,attributeValue);
                break;
            case "fbo" : selectFromDropDown_LabelTag(Constants.workbookData_addNewDataFbo_xpath,attributeValue,0);
                break;
            case "pricebasis": selectFromDropDown_LabelTag(Constants.workbookData_addNewDataPriceBasis_xpath,attributeValue,0);
                break;
            case "uom": selectFromDropDown_SelectTag(Constants.workbookData_addNewDataUom_xpath,attributeValue);
                break;
            case "set the currency for data" : selectFromDropDown_SelectTag(Constants.workbookData_addNewDataCurrencyCode_xpath,attributeValue);
                break;
            case "set the amount for data": sendKeysToWE(Constants.workbookData_addNewDataAmount_xpath,attributeValue);
                break;
            default: System.out.println("Operation not found in the list");
        }
    }

}
