package functions.workbook;

import cucumber.api.DataTable;
import functions.GenericWebElementMethods;
import org.junit.Assert;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;

import java.util.List;

/**
 * Created by root on 5/1/17.
 */
public class WorkBookDataMethods extends GenericWebElementMethods {
    private EventFiringWebDriver edriver;

    public WorkBookDataMethods() {
        edriver = getEdriver();
    }

    public void clickOnAddNewData() {
        clickButton(Constants.workbookData_addDataAction_xpath);
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
        clickButton(Constants.workbookData_editDataAction_xpath);
    }


    public void verifyIfWorkbookDataIsCreated(boolean condition, DataTable dataTable) throws Exception {
        String currentUrl = edriver.getCurrentUrl();
        waitFor(4);
        boolean flag = true;
        List<List<String>> list=dataTable.raw();

            for(int i=0;i<list.size();i++)
            {
                List<String> subList = list.get(i);
                for(String temp:subList)
                {
                    if(!isSizeOfWEListGtZero(temp))
                    {
                       flag = false;
                       break;
                    }
                }
            }

            if(condition && !flag){
                Assert.fail("Workbook data is not created or not found in the search");
            }else if(!condition && flag)
            {
                Assert.fail("Workbook data is created or found in the search");
            }

    }

    public void setAttribute(String attribute,String attributeValue)
    {
        switch (attribute.toLowerCase())
        {
            case "supplier" : setNameFromAutoFill(Constants.workbookData_addNewDataSupplier_xpath,attributeValue, -1);
                break;
            case "suppliersite" : setNameFromAutoFill(Constants.workbookData_addNewDataSupplierSite_xpath,attributeValue, -1);
                break;
            case "location" :setNameFromAutoFill(Constants.workbookData_addNewDataLocation_xpath,attributeValue, -1);
                break;
            case "item": setNameFromAutoFill(Constants.workbookData_addNewDataItem_xpath,attributeValue, -1);
                break;
            case "hauler": setNameFromAutoFill(Constants.workbookData_addNewDataHauler_xpath,attributeValue, -1);
                break;
            case "customershipto" : selectFromDropDownWithSearchBar_LabelTag(Constants.workbookData_addNewDataCustomershipto_xpath,attributeValue);
                break;
            case "customer" : selectFromDropDownWithSearchBar_LabelTag(Constants.workbookData_addNewDataCustomer_xpath,attributeValue);
                break;
            case "fbo" : selectFromDropDownWithSearchBar_LabelTag(Constants.workbookData_addNewDataFbo_xpath,attributeValue);
                break;
            case "price basis": selectFromDropDown_LabelTag(Constants.workbookData_addNewDataPriceBasis_xpath,attributeValue,0);
                break;
            case "uom": selectFromDropDown_SelectTag(Constants.workbookData_addNewDataUom_xpath,attributeValue);
                break;
            case "set the currency for data" : selectFromDropDown_SelectTag(Constants.workbookData_addNewDataCurrencyCode_xpath,attributeValue);
                break;
            case "set the amount for data": sendKeysToWE(Constants.workbookData_addNewDataAmount_xpath,attributeValue);
                break;
            default: System.out.println("Work book data operation not found in the list");
        }
    }

}
