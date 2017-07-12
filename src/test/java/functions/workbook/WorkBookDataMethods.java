package functions.workbook;

import com.google.common.base.Verify;
import cucumber.api.DataTable;
import functions.GenericWebElementMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import setup.Constants;
import setup.DriverBean;

import java.util.List;
import java.util.Set;

/**
 * Created by root on 5/1/17.
 */
public class WorkBookDataMethods extends GenericWebElementMethods {
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
    private static final String SUPPLIER_COLUMN = "Supplier";
    private static final String AMOUNT = "Amount";
    private static final String SCALE_RATES = "Scale Rates";
    private static final String ALERT_MESSAGE = "Warning: The record will be deactivated permanently. You can't undo this action.";

    private EventFiringWebDriver edriver;

    public WorkBookDataMethods() {
        edriver = DriverBean.getDriver();
    }

    public void clickOnAddNewData() {
        wait.until(ExpectedConditions.elementToBeClickable(edriver.findElement(By.xpath(Constants.workbookData_addDataAction_xpath)))).click();
    }

    public void clickOnSearch() {
        clickButton(Constants.workbookData_search_xpath);
    }

    public void clickOnUpload() {
        clickButton(Constants.workbookData_upload_xpath);
    }

    public void clickOnChoose(String filePath) {
        sendKeysToWE(Constants.workbookData_chooseFile_xpath, filePath);
    }

    public void setStartDate(String startDate) {

        if (startDate.matches("^[a-zA-Z]*$")) {
            startDate = setDateWithTimeStamp(startDate, true);
        }
        clearText(Constants.workbookData_addNewDataStartDate_xpath);
        sendKeysToWE(Constants.workbookData_addNewDataStartDate_xpath, startDate);
        clickOnTab();
    }

    public void setEndDate(String endDate) {
        if (endDate.matches("^[a-zA-Z]*$")) {
            endDate = setDateWithTimeStamp(endDate, true);
        }
        clearText(Constants.workbookData_addNewDataEndDate_xpath);
        sendKeysToWE(Constants.workbookData_addNewDataEndDate_xpath, endDate);
        clickOnTab();
    }

    public void clickOnUpdate() {
        clickButton(Constants.workbookData_editDataAction_xpath);
    }

    public void clickOnDataWithDeActivateEnabled(String key) {
        if (selectDataSearchingPages(Constants.workbookData_dataCommon_xpath.replace("(placeHolder)", String.valueOf(getColumnNumber(STATUS_COLUMN))), key)) {
            clickButton(Constants.workbookData_deActivateAction_xpath);
        }
    }

    public void clickOnDataWithInactiveStatus(String key) {
        selectDataSearchingPages(Constants.workbookData_dataCommon_xpath.replace("(placeHolder)", String.valueOf(getColumnNumber(STATUS_COLUMN))), key);
    }

    public void clickOnDataWithSupplier(String key) {
        int supplierColumn = getColumnNumber(SUPPLIER_COLUMN);
        selectDataSearchingPages(Constants.workbookData_dataCommon_xpath.replace("(placeHolder)", String.valueOf(getColumnNumber(SUPPLIER_COLUMN))), key);
    }

    public void deactivateRecord() {
        String currentWindow = edriver.getWindowHandle();
        Set<String> windowHandles = edriver.getWindowHandles();

        for (String temp : windowHandles) {
            if (!temp.equals(currentWindow)) {
                edriver.switchTo().window(temp);
            }
        }
        getElementFromListWithPosition(Constants.workbookData_deActivateConfirmationYes_xpath, 0).click();
    }

    public void verifyIfWorkbookDataIsCreated(boolean condition, DataTable dataTable) throws Exception {
        String currentUrl = edriver.getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOf(getElementFromListWithPosition(Constants.workbookData_search_xpath, 0)));
        boolean flag = true;
        List<List<String>> list = dataTable.raw();

        for (int i = 0; i < list.size(); i++) {
            List<String> subList = list.get(i);
            for (String temp : subList) {
                if (!isSizeOfWEListGtZero(temp)) {
                    flag = false;
                    break;
                }
            }
        }

        if (condition && !flag) {
            Assert.fail("Workbook data is not created or not found in the search");
        } else if (!condition && flag) {
            Assert.fail("Workbook data is created or found in the search");
        }

    }

    public void setAttribute(String attribute, String attributeValue) {
        switch (attribute.toLowerCase()) {
            case SUPPLIER:
                setNameFromAutoFill(Constants.workbookData_addNewDataSupplier_xpath, attributeValue, -1);
                break;
            case SUPPLIER_SITE:
                setNameFromAutoFill(Constants.workbookData_addNewDataSupplierSite_xpath, attributeValue, -1);
                break;
            case LOCATION:
                setNameFromAutoFill(Constants.workbookData_addNewDataLocation_xpath, attributeValue, -1);
                break;
            case ITEM:
                setNameFromAutoFill(Constants.workbookData_addNewDataItem_xpath, attributeValue, -1);
                break;
            case HAULER:
                setNameFromAutoFill(Constants.workbookData_addNewDataHauler_xpath, attributeValue, -1);
                break;
            case CUSTOMER_SHIP_TO:
                selectFromDropDownWithSearchBar_LabelTag(Constants.workbookData_addNewDataCustomershipto_xpath, attributeValue);
                break;
            case CUSTOMER:
                selectFromDropDownWithSearchBar_LabelTag(Constants.workbookData_addNewDataCustomer_xpath, attributeValue);
                break;
            case FBO:
                selectFromDropDownWithSearchBar_LabelTag(Constants.workbookData_addNewDataFbo_xpath, attributeValue);
                break;
            case PRICE_BASIS:
                selectFromDropDown_LabelTag(Constants.workbookData_addNewDataPriceBasis_xpath, attributeValue, -1);
                break;
            case UOM:
                selectFromDropDown_SelectTag(Constants.workbookData_addNewDataUom_xpath, attributeValue);
                break;
            case SET_CURRENCY:
                selectFromDropDown_SelectTag(Constants.workbookData_addNewDataCurrencyCode_xpath, attributeValue);
                break;
            case SET_AMOUNT:
                sendKeysToWE(Constants.workbookData_addNewDataAmount_xpath, attributeValue);
                break;
            default:
                System.out.println("Work book data operation not found in the list");
                break;
        }
    }

    public void clickOnDataWithEditEnabled(String key) {
        if (selectDataSearchingPages(Constants.workbookData_dataCommon_xpath.replace("(placeHolder)", String.valueOf(getColumnNumber(STATUS_COLUMN))), key)) {
            clickOnUpdate();
        }
    }

    public int getColumnNumber(String columnName) {
        int position = 3;
        List<WebElement> headerList = null;
        wait.until(ExpectedConditions.visibilityOfAllElements(headerList = edriver.findElements(By.xpath(Constants.workbookData_headerList_xpath))));
        for (WebElement temp : headerList) {
            if (temp.getText().equalsIgnoreCase(columnName)) {
                break;
            }
            ++position;
        }
        return position;
    }

    public void clickOnSaveButton() {
        clickButton(Constants.workbookData_save_xpath);
    }


    public void verifyIfWorkbookDataIsReadOnly() {
        if (getSizeOfList(Constants.workbookData_addNewDataStartDate_xpath) > 0) {
            viewableOnly(Constants.workbookData_addNewDataStartDate_xpath);
        }
        if (getSizeOfList(Constants.workbookData_addNewDataCurrencyCode_xpath) > 0) {
            viewableOnly(Constants.workbookData_addNewDataCurrencyCode_xpath);
        }
        if (getSizeOfList(Constants.workbookData_addNewDataAmount_xpath) > 0) {
            viewableOnly(Constants.workbookData_addNewDataAmount_xpath);
        }
        if (getSizeOfList(Constants.workbookData_addNewDataPriceBasis_xpath) > 0) {
            viewableOnly(Constants.workbookData_addNewDataPriceBasis_xpath);
        }
        if (getSizeOfList(Constants.workbookData_addNewDataUom_xpath) > 0) {
            viewableOnly(Constants.workbookData_addNewDataUom_xpath);
        }
        if (getSizeOfList(Constants.workbookData_addNewDataSupplier_xpath) > 0) {
            viewableOnly(Constants.workbookData_addNewDataSupplier_xpath);
        }
        if (getSizeOfList(Constants.workbookData_addNewDataSupplierSite_xpath) > 0) {
            viewableOnly(Constants.workbookData_addNewDataSupplierSite_xpath);
        }
        if (getSizeOfList(Constants.workbookData_addNewDataLocation_xpath) > 0) {
            viewableOnly(Constants.workbookData_addNewDataLocation_xpath);
        }
        if (getSizeOfList(Constants.workbookData_addNewDataItem_xpath) > 0) {
            viewableOnly(Constants.workbookData_addNewDataItem_xpath);
        }
        if (getSizeOfList(Constants.workbookData_addNewDataHauler_xpath) > 0) {
            viewableOnly(Constants.workbookData_addNewDataHauler_xpath);
        }
        if (getSizeOfList(Constants.workbookData_addNewDataCustomershipto_xpath) > 0) {
            viewableOnly(Constants.workbookData_addNewDataCustomershipto_xpath);
        }
        if (getSizeOfList(Constants.workbookData_addNewDataCustomer_xpath) > 0) {
            viewableOnly(Constants.workbookData_addNewDataCustomer_xpath);
        }
    }

    public void verifyIfEditAndDeactivateIsDisplayed() {
        Verify.verify(getSizeOfList(Constants.workbookData_editDataAction_xpath) == 0, "Edit action is displayed for the inactive record");
        Verify.verify(getSizeOfList(Constants.workbookData_deActivateAction_xpath) == 0, "Deactivate action is displayed for the inactive record");
    }
}
