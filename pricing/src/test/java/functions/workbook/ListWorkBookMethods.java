package functions.workbook;

import functions.GenericWebElementMethods;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;

public class ListWorkBookMethods extends GenericWebElementMethods {
    private EventFiringWebDriver edriver;

    public ListWorkBookMethods() {
        edriver = getEdriver();
    }

    public void clickAddNewWorkBookConfiguration() {
        clickButton(Constants.workbookList_addNewWorkbookConfig_xpath);
    }

    public void checkIfRecordExistsUsingNameFilter(String name) {
        sendKeysToWE(Constants.workbookList_nameFilter_xpath, name);
        checkDataInRowsMatchesFilter(Constants.workbookList_nameColumn_xpath, name);
    }

    public void checkIfRecordExistsUsingDescriptionFilter(String description) {
        sendKeysToWE(Constants.workbookList_descrpFilter_xpath, description);
        checkDataInRowsMatchesFilter(Constants.workbookList_descrptionColumn_xpath, description);
    }

    public void checkIfRecordExistsUsingFormulaTypeFilter(String formulaType) {
        sendKeysToWE(Constants.workbookList_formulaTypeFilter_xpath, formulaType);
        checkDataInRowsMatchesFilter(Constants.workbookList_formulaTypeColumn_xpath, formulaType);
    }

    public void checkIfRecordExistsUsingSegmentFilter(String segment) {
        selectFromDropDown(Constants.workbookList_segmentFilter_xpath,Constants.workbookList_segmentListWe_xpath,segment);
    }

    public String getFirstRecord(String type) {
        String temp = null;
        switch (type) {
            case "name":
                temp = getFirstValueNotNull(Constants.workbookList_nameColumn_xpath);
                break;
            case "description":
                temp = getFirstValueNotNull(Constants.workbookList_descrptionColumn_xpath);
                break;
            case "formulaType":
                temp = getFirstValueNotNull(Constants.workbookList_formulaTypeColumn_xpath);
                break;
            case "segment":
                temp = getFirstValueNotNull(Constants.workbookList_segmentColumn_xpath);
                break;
        }
        return temp;
    }

    public void clickOnRadioButton(int position) {
        getElementFromListWithPosition(Constants.workbookList_radioButtonColumn_xpath, position).click();
    }

    public void clickOnViewWorkBookConfiguration(int position) {
        getElementFromListWithPosition(Constants.workbookList_viewWorkBookConfiguration_xpath, position).click();
    }

    public void clickOnManageData(int position) {
        getElementFromListWithPosition(Constants.workbookList_manageData_xpath, position).click();
    }

}
