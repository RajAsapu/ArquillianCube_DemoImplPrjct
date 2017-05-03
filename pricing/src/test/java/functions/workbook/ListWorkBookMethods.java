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
        sendKeysToWE(Constants.workbookList_segmentFilter_xpath, segment);
        checkDataInRowsMatchesFilter(Constants.workbookList_segmentColumn_xpath, segment);
    }

    public String getFirstRecord(String type) {
        String temp = null;
        switch (type) {
            case "name":
                temp = getValue(Constants.workbookList_nameColumn_xpath);
                break;
            case "description":
                temp = getValue(Constants.workbookList_descrptionColumn_xpath);
                break;
            case "formulaType":
                temp = getValue(Constants.workbookList_formulaTypeColumn_xpath);
                break;
            case "segment":
                temp = getValue(Constants.workbookList_segmentColumn_xpath);
                break;
        }
        return temp;
    }

    public void clickOnData(int position) {
        getElementFromListWithPosition(Constants.workbookList_data_xpath, position).click();
    }

    public void clickOnDefinition(int position) {
        getElementFromListWithPosition(Constants.workbookList_definition_xpath, position).click();
    }

}
