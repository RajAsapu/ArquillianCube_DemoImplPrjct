package functions.formula;


import com.google.common.base.Verify;
import functions.GenericWebElementMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;

import java.util.List;

public class CreateFormulaMethods extends GenericWebElementMethods {

    private EventFiringWebDriver edriver;

    public CreateFormulaMethods() {
        edriver = getEdriver();
    }

    public void clickOnAddParameter() {
        clickButton(Constants.formulaCreate_addParameter_xpath);
    }

    public void setName(String name) {
        sendKeysToWE(Constants.formulaCreate_name_xpath, name);
    }

    public void setDescription(String description) {
        sendKeysToWE(Constants.formulaCreate_description_xpath, description);
    }

    public void setType(String type) {
        selectFromDropDown_LabelTag(Constants.formulaCreate_typeSelect_xpath, type, 0);
    }

    public void setStartDate(String startDate) {
        selectDate(startDate, Constants.formulaCreate_startDatePicker_xpath, Constants.formulaCreate_startDate_xpath);
    }

    public void setEndDate(String endDate) {
        selectDate(endDate, Constants.formulaCreate_endDatePicker_xpath, Constants.formulaCreate_endDate_xpath);
    }

    public void setRoundingMode(String roundingMode) {
        selectFromDropDown_LabelTag(Constants.formulaCreate_roundingMode_xpath, roundingMode, 0);
    }

    public void setRoundingPrecision(String roundingPrecision) {
        sendKeysToWE(Constants.formulaCreate_roundingPrecision_xpath, roundingPrecision);
    }

    public void setExpression(String expression) {
        sendKeysToWE(Constants.formulaCreate_expression_xpath, expression);
    }

    public void createFormula() {
        try {
            Verify.verify(edriver.findElement(By.xpath(Constants.formulaCreate_errorMessageValidate_xpath)).isDisplayed(), "Invalid Expression!!");
        } catch (NoSuchElementException exp) {
            System.out.println("Expression is Verified !");
        }
        clickButton(Constants.formulaCreate_submit_xpath);
    }

    public void addParameters(List<List<String>> parameters) {
        int i = 0;
        scrollDown();
        while (i < parameters.size()) {
            String type = parameters.get(i).get(1);
            if (i > 0) {
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
            i++;
        }
    }

    public void verifyIfFormulaCreatedOrNot(String perfom, String action) {
        if (!perfom.contains("not")) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Verify.verify(edriver.getCurrentUrl().contains("formula/list"), "Formula is not Created !!");
        } else {
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
