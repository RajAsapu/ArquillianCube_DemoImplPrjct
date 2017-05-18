package functions.calculationrule;

import functions.GenericWebElementMethods;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

public class ListCalculationRuleMethods extends GenericWebElementMethods {

    private EventFiringWebDriver edriver;
    public ListCalculationRuleMethods()
    {
        edriver= DriverBean.getDriver();
    }

    public void clickOnAddNewRule() {
        clickButton(Constants.calculationRuleList_addNewRule_xpath);
    }

    public void setFilterName(String filterName) {
        sendKeysToWE(Constants.calculationRuleList_searchByName_xpath, filterName);
    }

    public void setTypeName(String typeName) {
        sendKeysToWE(Constants.calculationRuleList_searchByType_xpath, typeName);
    }

    public void setDescriptionName(String descriptionName) {
        sendKeysToWE(Constants.calculationRuleList_searchByDescrp_xpath, descriptionName);
    }

    public void clickOnAction(String button) {
        switch (button.toLowerCase()) {
            case "status":
                clickButton(Constants.calculationRuleList_hdrStatusColumn_xpath);
                break;
            case "name":
                clickButton(Constants.calculationRuleList_hdrNameColumn_xpath);
                break;
            case "type":
                clickButton(Constants.calculationRuleList_hdrTypeColumn_xpath);
                break;
            case "description":
                clickButton(Constants.calculationRuleList_hdrDescrpColumn_xpath);
                break;
            case "inactive":
                clickButton(Constants.calculationRuleList_actionInactive_xpath);
                break;
            case "statushdr":
                clickButton(Constants.calculationRuleList_hdrStatusColumn_xpath);
                break;
            case "view":
                clickButton(Constants.calculationRuleList_actionView_xpath);
                break;
            case "edit":
                clickButton(Constants.calculationRuleList_actionEdit_xpath);
                break;
        }
    }

    public void verifyDataInRowsMatchTheSearhFilter(String filter, String value) {
        switch (filter.toLowerCase()) {
            case "name":
                checkDataInRowsMatchesFilter(Constants.calculationRuleList_nameColumn_xpath, value);
                break;
            case "type":
                checkDataInRowsMatchesFilter(Constants.calculationRuleList_typeColumn_xpath, value);
                break;
            case "description":
                checkDataInRowsMatchesFilter(Constants.calculationRuleList_descpColumn_xpath, value);
                break;
        }
    }
}
