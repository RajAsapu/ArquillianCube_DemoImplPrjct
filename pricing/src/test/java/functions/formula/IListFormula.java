package functions.formula;

import setup.IPage;

public interface IListFormula extends IPage {
    public String applyFilter(String filter);
    public void verifyIfFilterIsApplied(String filter);
    public void clickOnButton(String button);
    public void verifyIfInactiveElementHasEdit();
    public void verifyIfFormulaIsInactivated();
}
