package functions.formula;


import com.google.common.base.Verify;
import com.google.common.base.VerifyException;
import functions.GenericWebElementMethods;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import setup.Constants;
import setup.DriverBean;

import java.util.List;

public class CreateFormulaMethods extends GenericWebElementMethods {
    private static final String EXPRESSSION_ERROR_MESSAGE = "Expression operand names dont match.";

    private EventFiringWebDriver edriver;

    public CreateFormulaMethods() {
        edriver = DriverBean.getDriver();
    }

    public void clickOnAddParameter() {
        clickButton(Constants.formulaCreate_addParameter_xpath);
    }

    public void setName(String name, boolean withTimeStamp) {
        if (withTimeStamp) {
            sendKeysToWE(Constants.formulaCreate_name_xpath, name + getDateWithTime());
        } else {
            sendKeysToWE(Constants.formulaCreate_name_xpath, name);
        }
    }

    public void setDescription(String description) {
        sendKeysToWE(Constants.formulaCreate_description_xpath, description);
    }

    public void setType(String type) {
        selectFromDropDown_LabelTag(Constants.formulaCreate_typeSelect_xpath, type, -1);
    }

    public void setStartDate(String startDate) {
        if (startDate.matches("^[a-zA-Z]*$")) {
            startDate = setDateWithTimeStamp(startDate, false);
        }
        clearText(Constants.formulaCreate_startDate_xpath);
        sendKeysToWE(Constants.formulaCreate_startDate_xpath, startDate);
        clickOnTab();
    }

    public void setEndDate(String endDate) {
        if (endDate.matches("^[a-zA-Z]*$")) {
            endDate = setDateWithTimeStamp(endDate, false);
        }
        clearText(Constants.formulaCreate_endDate_xpath);
        sendKeysToWE(Constants.formulaCreate_endDate_xpath, endDate);
        clickOnTab();
    }

    public void setRoundingMode(String roundingMode) {
        selectFromDropDown_LabelTag(Constants.formulaCreate_roundingMode_xpath, roundingMode, -1);
    }

    public void setRoundingPrecision(String roundingPrecision) {
        sendKeysToWE(Constants.formulaCreate_roundingPrecision_xpath, roundingPrecision);
    }

    public void setExpression(String expression) {
        sendKeysToWE(Constants.formulaCreate_expression_xpath, expression);
    }

    public void createFormula() {
        try {
            verifyIfTextIsDisplayed(EXPRESSSION_ERROR_MESSAGE);
        } catch (VerifyException exp) {
            System.out.println("Expression is valid");
        }
        clickButton(Constants.formulaCreate_submit_xpath);
    }

    public void addParameters(List<List<String>> parameters) {

        for (int i = 0; i < parameters.size(); i++) {
            String type = parameters.get(i).get(1);
            String attributeName = getValue(Constants.formulaCreate_nameParameter_xpath);
            if (attributeName != null && !attributeName.isEmpty()) {
                clickOnAddParameter();
            }
            sendKeysToWeAtPosition(Constants.formulaCreate_nameParameter_xpath, parameters.get(i).get(0), -1);
            selectFromDropDown_LabelTag(Constants.formulaCreate_typeParameter_xpath, parameters.get(i).get(1), -1);
            if (type.equalsIgnoreCase("Index")) {
                selectFromDropDown_LabelTag(Constants.formulaCreate_indexTypeParameter_xpath, parameters.get(i).get(2), -1);
                selectFromDropDown_LabelTag(Constants.formulaCreate_indexPointParameter_xpath, parameters.get(i).get(3), -1);
                setNameFromAutoFill(Constants.formulaCreate_indexNameParameter_xpath, parameters.get(i).get(4), -1);
                setNameFromAutoFill(Constants.formulaCreate_calculationPeriodParamater_xpath, parameters.get(i).get(5), -1);
            } else if (type.equalsIgnoreCase("Workbook")) {
                setNameFromAutoFill(Constants.formulaCreate_workBookName_xpath, parameters.get(i).get(2), -1);
                setNameFromAutoFill(Constants.formulaCreate_supplier_xpath, parameters.get(i).get(3), -1);
                setNameFromAutoFill(Constants.formulaCreate_location_xpath, parameters.get(i).get(4), -1);
                setNameFromAutoFill(Constants.formulaCreate_product_xpath, parameters.get(i).get(5), -1);
            }
        }
    }

    public void verifyIfFormulaCreatedOrNot(String perfom, String action) {
        if (!perfom.contains("not")) {
            wait.until(ExpectedConditions.visibilityOf(getElementFromListWithPosition(Constants.formulaList_status_xpath, 0)));
            Verify.verify(edriver.getCurrentUrl().contains("formula/list"), "Formula is not Created !!");
        } else {
            wait.until(ExpectedConditions.visibilityOf(getElementFromListWithPosition(Constants.formulaCreate_name_xpath, 0)));
            Verify.verify(edriver.getCurrentUrl().contains("formula/create"), "ERROR : Formula is Created !!");
        }
    }

    public void verifyIfUserIsAbleToViewAllDetails() {
        viewableOnly(Constants.formulaCreate_name_xpath);
        viewableOnly(Constants.formulaCreate_description_xpath);
        viewableOnly(Constants.formulaCreate_typeSelect_xpath);
        viewableOnly(Constants.formulaCreate_expression_xpath);
        viewableOnly(Constants.formulaCreate_startDatePicker_xpath);
        viewableOnly(Constants.formulaCreate_endDatePicker_xpath);
        viewableOnly(Constants.formulaCreate_endDate_xpath);
        viewableOnly(Constants.formulaCreate_roundingMode_xpath);
        viewableOnly(Constants.formulaCreate_roundingPrecision_xpath);
        viewableOnly(Constants.formulaCreate_nameParameter_xpath);
        viewableOnly(Constants.formulaCreate_typeParameter_xpath);
    }


}
