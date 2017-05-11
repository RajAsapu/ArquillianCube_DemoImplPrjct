package functions.workbook;

import cucumber.api.DataTable;
import functions.GenericWebElementMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;

import java.util.List;

/**
 * Created by root on 5/1/17.
 */
public class WorkBookDataMethods extends GenericWebElementMethods {
    private EventFiringWebDriver edriver;

    private static final String SUPPLIER = "supplier";
    private static final String SUPPLIER_SITE = "suppliersite";
    private static final String LOCATION = "location";
    private static final String ITEM = "item";
    private static final String HAULER = "hauler";
    private static final String CUSTOMER_SHIP_TO = "customershipto";
    private static final String CUSTOMER = "customer";
    private static final String FBO = "fbo";
    private static final String PRICE_BASIS = "price basis";
    private static final String UOM = "uom";
    private static final String SET_CURRENCY = "set the currency for data";
    private static final String SET_AMOUNT = "set the amount for data";
    private static final String STATUS_COLUMN = "Status";
    private static final String AMOUNT = "Amount";
    private static final String SCALE_RATES = "Scale Rates";


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

        if(startDate.matches("^[a-zA-Z]*$"))
        {
            startDate= setDateWithTimeStamp(startDate);
        }
        clearText(Constants.workbookData_addNewDataStartDate_xpath);
        sendKeysToWE(Constants.workbookData_addNewDataStartDate_xpath, startDate);
        clickOnTab();
    }

    public void setEndDate(String endDate) {
        if(endDate.matches("^[a-zA-Z]*$"))
        {
            endDate= setDateWithTimeStamp(endDate);
        }
        clearText(Constants.workbookData_addNewDataEndDate_xpath);
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
            case SUPPLIER : setNameFromAutoFill(Constants.workbookData_addNewDataSupplier_xpath,attributeValue, -1);
                break;
            case SUPPLIER_SITE : setNameFromAutoFill(Constants.workbookData_addNewDataSupplierSite_xpath,attributeValue, -1);
                break;
            case LOCATION :setNameFromAutoFill(Constants.workbookData_addNewDataLocation_xpath,attributeValue, -1);
                break;
            case ITEM: setNameFromAutoFill(Constants.workbookData_addNewDataItem_xpath,attributeValue, -1);
                break;
            case HAULER: setNameFromAutoFill(Constants.workbookData_addNewDataHauler_xpath,attributeValue, -1);
                break;
            case CUSTOMER_SHIP_TO : selectFromDropDownWithSearchBar_LabelTag(Constants.workbookData_addNewDataCustomershipto_xpath,attributeValue);
                break;
            case CUSTOMER : selectFromDropDownWithSearchBar_LabelTag(Constants.workbookData_addNewDataCustomer_xpath,attributeValue);
                break;
            case FBO : selectFromDropDownWithSearchBar_LabelTag(Constants.workbookData_addNewDataFbo_xpath,attributeValue);
                break;
            case PRICE_BASIS: selectFromDropDown_LabelTag(Constants.workbookData_addNewDataPriceBasis_xpath,attributeValue,0);
                break;
            case UOM: selectFromDropDown_SelectTag(Constants.workbookData_addNewDataUom_xpath,attributeValue);
                break;
            case SET_CURRENCY : selectFromDropDown_SelectTag(Constants.workbookData_addNewDataCurrencyCode_xpath,attributeValue);
                break;
            case SET_AMOUNT: sendKeysToWE(Constants.workbookData_addNewDataAmount_xpath,attributeValue);
                break;
            default: System.out.println("Work book data operation not found in the list");
        }
    }

    public void clickOnDataWithEditEnabled(String key)
    {
        if(selectDataSearchingPages(Constants.workbookData_dataCommon_xpath.replace("(placeHolder)",String.valueOf(getColumnNumber(STATUS_COLUMN))),key))
        {
            clickOnUpdate();
        }
    }

    public int getColumnNumber(String columnName)
    {
        int position =0;
        waitFor(2);
        List<WebElement> headerList = edriver.findElements(By.xpath(Constants.workbookData_headerList_xpath));
        for(WebElement temp:headerList)
        {
            position++;
            if(temp.getText().equalsIgnoreCase(columnName))
            {
                break;
            }
        }
        return position;
    }
}
