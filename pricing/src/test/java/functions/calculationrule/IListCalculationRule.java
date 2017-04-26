package functions.calculationrule;

import setup.IPage;

public interface IListCalculationRule extends IPage{
    public void clickOnAddNewRule();
    public void setFilterName(String filterName);
    public void setTypeName(String typeName);
    public void setDescriptionName(String descriptionName);
    public void clickOnAction(String button);
    public void verifyDataInRowsMatchTheSearhFilter(String filter,String value);
}
