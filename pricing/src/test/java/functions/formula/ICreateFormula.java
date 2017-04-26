package functions.formula;

import setup.IPage;

import java.util.List;

public interface ICreateFormula extends IPage{
    public void clickOnAddParameter();
    public void setName(String name);
    public void setDescription(String description);
    public void setType(String type);
    public void setEndDate(String endDate);
    public void setRoundingMode(String roundingMode);
    public void setRoundingPrecision(String roundingPrecision);
    public void setExpression(String expression);
    public void createFormula();
    public void addParameters(List<List<String>> parameters);
    public void verifyIfFormulaCreatedOrNot(String perfom, String action);
    public void verifyIfUserIsAbleToViewAllDetails();
}
