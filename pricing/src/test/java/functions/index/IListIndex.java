package functions.index;

import setup.IPage;

public interface IListIndex extends IPage {
    public void setStartDate(String startDate);
    public void setEndDate(String endDate);
    public void setStatus(String status);
    public void setType(String type);
    public void addScaleParamater();
    public void setScaleParameters(String from,String to,String rate);
    public void clickOnColumn(ListIndexMethods.Column clmn);
    public void clickOnAction(String action);
    public void verifySearchResults(String value,ListIndexMethods.Column clmn);
    public void verifyUserIsAbleToEditOnlyEndDate();
    public void verifyUserIsUnableToEditPrices();
    public void verifyScaleratesAreEditable();
    public void verifyIfTheStatusOfIndexChangedToInactive();
}
