package functions.formula;

import com.google.common.base.Verify;
import functions.GenericWebElementMethods;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

public class ListFormulaMethods extends GenericWebElementMethods implements IListFormula {

    private EventFiringWebDriver edriver;

    public ListFormulaMethods()
    {
        edriver = getEdriver();
    }

    public String applyFilter(String filter){
        String temp = null;
        switch (filter.toLowerCase()) {
            case "name":
                temp = getElementFromListWithPosition(Constants.formulaList_nameColumn_xpath,0).getText();
                sendKeysToWE(Constants.formulaList_nameFilter_xpath, temp);
                break;
            case "description":
                temp = getElementFromListWithPosition(Constants.formulaList_descrpColumn_xpath,0).getText();
                sendKeysToWE(Constants.formulaList_descrpFilter_xpath, temp);
                break;
            case "type":
                temp = getElementFromListWithPosition(Constants.formulaList_typeColumn_xpath,0).getText();
                sendKeysToWE(Constants.formulaList_typeFilter_xpath, temp);
                break;
            case "expression":
                temp = getElementFromListWithPosition(Constants.formulaList_expressionColumn_xpath,0).getText();
                sendKeysToWE(Constants.formulaList_exprsFilter_xpath, temp);
                break;
            case "startdate":
                temp = getElementFromListWithPosition(Constants.formulaList_startDateColumn_xpath,0).getText();
                sendKeysToWE(Constants.formulaList_startDateFilter_xpath, temp);
                break;
            case "enddate":
                temp = getElementFromListWithPosition(Constants.formulaList_endDateColumn_xpath,0).getText();
                sendKeysToWE(Constants.formulaList_endDateFilter_xpath, temp);
                break;
            case "rounding precision":
                temp = getElementFromListWithPosition(Constants.formulaList_roundingPrecisionColumn_xpath,0).getText();
                sendKeysToWE(Constants.formulaList_roundPrecsFilter_xpath, temp);
                break;
            case "status":
                clickButton(Constants.formulaList_status_xpath);
                break;

            default:
                System.out.println("Filter Option is not from the list");
                break;
        }
        return temp;
    }

    public void verifyIfFilterIsApplied(String filter){
        switch (filter.toLowerCase()) {
            case "name":
                checkDataInRowsMatchesFilter(Constants.formulaList_nameColumn_xpath, getValue(Constants.formulaList_nameFilter_xpath));
                break;
            case "description":
                checkDataInRowsMatchesFilter(Constants.formulaList_descrpColumn_xpath,getValue(Constants.formulaList_descrpFilter_xpath));
                break;
            case "type":
                checkDataInRowsMatchesFilter(Constants.formulaList_typeColumn_xpath, getValue(Constants.formulaList_typeFilter_xpath));
                break;
            case "expression":
                checkDataInRowsMatchesFilter(Constants.formulaList_expressionColumn_xpath, getValue(Constants.formulaList_exprsFilter_xpath));
                break;
            case "startdate":
                checkDataInRowsMatchesFilter(Constants.formulaList_startDateColumn_xpath, getValue(Constants.formulaList_startDateFilter_xpath));
                break;
            case "enddate":
                checkDataInRowsMatchesFilter(Constants.formulaList_endDateColumn_xpath, getValue(Constants.formulaList_endDateFilter_xpath));
                break;
            case "rounding precision":
                checkDataInRowsMatchesFilter(Constants.formulaList_roundingPrecisionColumn_xpath, getValue(Constants.formulaList_roundPrecsFilter_xpath));
                break;
            default:
                System.out.println("Filter Option is not from the list");
                break;
        }
    }

    public void clickOnButton(String button){
        switch(button.toLowerCase())
        {
            case "edit": clickButton(Constants.formulaList_editAction_xpath);
                break;
            case "update": clickButton(Constants.formulaList_updateAction_xpath);
                break;
            case "view": clickButton(Constants.formulaList_viewAction_xpath);
                break;
            case "copy": clickButton(Constants.formulaList_copyAction_xpath);
                break;
            case "name": clickButton(Constants.formulaList_name_xpath);
                break;
            case "description": clickButton(Constants.formulaList_description_xpath);
                break;
            case "expression": clickButton(Constants.formulaList_expression_xpath);
                break;
            case "startdate": clickButton(Constants.formulaList_startDate_xpath);
                break;
            case "enddate": clickButton(Constants.formulaList_endDate_xpath);
                break;
            case "rounding precision": clickButton(Constants.formulaList_roundingPrecision_xpath);
                break;
            case "type": clickButton(Constants.formulaList_type_xpath);
                break;
            case "status":  clickButton(Constants.formulaList_status_xpath);
                break;

        }
    }
    public void verifyIfInactiveElementHasEdit()
    {
        Verify.verify(getSizeOfList(Constants.formulaList_editAction_xpath) > 0, "One of the active formula has edit option");

    }
    public void verifyIfFormulaIsInactivated()
    {
        /*
         * Perform inactivation
         */
        String name = getElementFromListWithPosition(Constants.formulaList_nameColumn_xpath,0).getText();
        clickButton(Constants.formulaList_deactivateAction_xpath);
        /*
         * Check if it is de activated
         */
        sendKeysToWeAtPosition(Constants.formulaList_nameFilter_xpath, name);
        Verify.verify(getElementFromListWithPosition(Constants.formulaList_statusColumn_xpath,0).getText().equalsIgnoreCase("Inactive"), "Formula:" + name + " is still active !!");
    }
}
