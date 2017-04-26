package functions.formula;


import com.google.common.base.Verify;
import functions.GenericWebElementMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

import java.util.List;

public class CreateFormulaMethods extends GenericWebElementMethods {

    private EventFiringWebDriver edriver;

    public CreateFormulaMethods()
    {
        edriver = getEdriver();
    }

    public void clickOnAddParameter()
    {
        clickButton(Constants.formulaCreate_addParameter_xpath);
    }

    public void setName(String name)
    {
        sendKeysToWeAtPosition(Constants.formulaCreate_name_xpath, name);
    }
    public void setDescription(String description){
        sendKeysToWeAtPosition(Constants.formulaCreate_description_xpath, description);
    }
    public void setType(String type){
        selectFromDropDown(Constants.formulaCreate_typeSelect_xpath, type);
    }
    public void setStartDate(String startDate){
        selectDate(startDate,Constants.formulaCreate_startDatePicker_xpath,null);
    }
    public void setEndDate(String endDate){
        selectDate(endDate,Constants.formulaCreate_endDatePicker_xpath,Constants.formulaCreate_endDate_xpath);
    }
    public void setRoundingMode(String roundingMode){
        selectFromDropDown(Constants.formulaCreate_roundingMode_xpath, roundingMode);
    }
    public void setRoundingPrecision(String roundingPrecision){
        sendKeysToWeAtPosition(Constants.formulaCreate_roundingPrecision_xpath, roundingPrecision);
    }
    public void setExpression(String expression){
        sendKeysToWeAtPosition(Constants.formulaCreate_expression_xpath, expression);
    }
    public void createFormula(){
        try {
            Verify.verify(edriver.findElement(By.xpath(Constants.formulaCreate_errorMessageValidate_xpath)).isDisplayed(), "Invalid Expression!!");
        } catch (NoSuchElementException exp) {
            System.out.println("Expression is Verified !");
        }
        clickButton(Constants.formulaCreate_create_xpath);
    }

    public void addParameters(List<List<String>> parameters)
    {
        int i=0;
        scrollDown();
        while (i < parameters.size()) {
            String type = parameters.get(i).get(1);
            clickOnAddParameter();
            sendKeysToWeAtPosition(Constants.formulaCreate_nameParameter_xpath, parameters.get(i).get(0), "last");
            selectFromDropDown(Constants.formulaCreate_typeParameter_xpath, parameters.get(i).get(1), "last");
            if (type.equalsIgnoreCase("Index")) {
                selectFromDropDown(Constants.formulaCreate_indexTypeParameter_xpath, parameters.get(i).get(2), "last");
                selectFromDropDown(Constants.formulaCreate_indexPointParameter_xpath, parameters.get(i).get(3), "last");
                setNameFromAutoFill(Constants.formulaCreate_indexNameParameter_xpath, parameters.get(i).get(4));
                setNameFromAutoFill(Constants.formulaCreate_calculationPeriodParamater_xpath, parameters.get(i).get(5));
            } else if (type.equalsIgnoreCase("Workbook")) {
                setNameFromAutoFill(Constants.formulaCreate_workBookName_xpath, parameters.get(i).get(2));
                setNameFromAutoFill(Constants.formulaCreate_supplier_xpath, parameters.get(i).get(3));
                setNameFromAutoFill(Constants.formulaCreate_location_xpath, parameters.get(i).get(4));
                setNameFromAutoFill(Constants.formulaCreate_product_xpath, parameters.get(i).get(5));
            }
            i++;
        }
    }

    public void verifyIfFormulaCreatedOrNot(String perfom, String action){
        if (!perfom.contains("not")) {
            Verify.verify(edriver.getCurrentUrl().contains("formula/list"), "Formula is not Created !!");
        } else {
            Verify.verify(edriver.getCurrentUrl().contains("formula/create"), "ERROR : Formula is Created !!");
        }
    }
    public void verifyIfUserIsAbleToViewAllDetails(){
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
