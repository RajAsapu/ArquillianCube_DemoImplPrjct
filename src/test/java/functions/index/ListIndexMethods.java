package functions.index;

import com.google.common.base.Verify;
import functions.GenericWebElementMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import setup.Constants;
import setup.DriverBean;

import java.util.List;

public class ListIndexMethods extends GenericWebElementMethods {
    private EventFiringWebDriver edriver;

    public ListIndexMethods()
    {
        edriver= DriverBean.getDriver();
    }
    /*
     * Methods to search
     */
    public void setStartDate(String startDate) {
        selectDate(startDate, Constants.indexList_startdatepicker_xpath, Constants.indexList_startdate_xpath);
    }

    public void setEndDate(String endDate) {
        selectDate(endDate, Constants.indexList_enddatepicker_xapth, Constants.indexList_endDate_xpath);
    }

    public void setStatus(String status) {
        WebElement options = edriver.findElement(By.xpath(Constants.indexList_status_xpath));
        try {
            Select s = new Select(options);
            s.selectByVisibleText(status);
        } catch (UnexpectedTagNameException unexpectedTagNameException) {
            Actions actions = new Actions(edriver);
            actions.click(options).perform();
            WebElement element = getElementFromListWithPosition("//*[normalize-space()='" + status + "']", -1);
            element.click();
        }
    }

    public void setType(String type) {
        selectFromDropDown_LabelTag(Constants.indexList_type_xpath, type, 0);
    }

    public void addScaleParamater() {
        clickButton(Constants.indexCreate_addScale_xpath);
    }

    public void setScaleParameters(String from, String to, String rate) {
        sendKeysToWeAtPosition(Constants.indexCreate_fromScale_xpath, from, -1);
        sendKeysToWeAtPosition(Constants.indexCreate_toScale_xpath, to, -1);
        sendKeysToWeAtPosition(Constants.indexCreate_rateScale_xpath, rate, -1);
    }

    public void setRateBasis(String rateBasis) {
        selectFromDropDown_LabelTag(Constants.indexList_rateBasis_xpath, rateBasis, 0);
    }

    public void setCurrency(String currency) {
        selectFromDropDown_SelectTag(Constants.indexList_currency_xpath, currency);
    }

    public void setUom(String uom) {
        selectFromDropDown_SelectTag(Constants.indexList_uom_xpath, uom);
    }

    public void clickOnColumn(Column clmn) {
        switch (clmn) {
            case startDate:
            case endDate:
            case type:
            case name:
            case currency:
            case uom:
            case rateBasis:
            case comments:
        }
    }

    /*
     * Method to click on the action methods
     */
    public void clickOnAction(String action) {
        switch (action.toLowerCase()) {
            case "edit":
                clickButton(Constants.indexList_editAction_xpath);
                break;
            case "addnewindex":
                clickButton(Constants.indexList_addNewIndex_xpath);
                break;
            case "submit":
                clickButton(Constants.indexCreate_submit_xpath);
                break;
            case "inactive":
                clickButton(Constants.indexList_deactivateAction_xpath);
                break;
            case "copy":
                clickButton(Constants.indexList_copyAction_xpath);
                break;
        }
    }

    public void verifySearchResults(String value, Column clmn) {
        List<WebElement> list = null;

        switch (clmn) {
            case rateBasis:
                list = edriver.findElements(By.xpath(Constants.indexList_ratebasisColumn_xpath));
                break;
            case name:
                list = edriver.findElements(By.xpath(Constants.indexList_nameColumn_xpath));
                break;
            case currency:
                list = edriver.findElements(By.xpath(Constants.indexList_currencyColumn_xpath));
                break;
            case uom:
                list = edriver.findElements(By.xpath(Constants.indexList_uomColumn_xpath));
                break;
        }

        for (WebElement temp : list) {
            if (!temp.getText().equalsIgnoreCase(value)) {
                Assert.fail("Column values Doesn't match, Expected :" + value + " Actual:" + temp.getText());
            }
        }
    }

    public void verifyUserIsAbleToEditOnlyEndDate() {
        viewableOnly(Constants.indexList_name_xpath);
        viewableOnly(Constants.indexList_rateBasis_xpath);
        viewableOnly(Constants.indexCreate_startDate_xpath);
        viewableOnly(Constants.indexCreate_lowprice_xpath);
        viewableOnly(Constants.indexCreate_midprice_xpath);
        viewableOnly(Constants.indexCreate_highprice_xpath);
        viewableOnly(Constants.indexCreate_closeprice_xpath);
        viewableOnly(Constants.indexCreate_currency_xpath);
        viewableOnly(Constants.indexCreate_uom_xpath);
        viewableOnly(Constants.indexCreate_priceBreak_xpath);
    }

    public void verifyUserIsUnableToEditPrices() {
        viewableOnly(Constants.indexCreate_lowprice_xpath);
        viewableOnly(Constants.indexCreate_midprice_xpath);
        viewableOnly(Constants.indexCreate_highprice_xpath);
        viewableOnly(Constants.indexCreate_closeprice_xpath);
    }

    public void verifyScaleratesAreEditable() {
        isEditable(Constants.indexCreate_fromScale_xpath);
        isEditable(Constants.indexCreate_toScale_xpath);
        isEditable(Constants.indexCreate_rateScale_xpath);
    }

    public void verifyIfTheStatusOfIndexChangedToInactive() {
        String status = getElementFromListWithPosition(Constants.indexList_StatusColumn_xpath, 0).getText();
        Verify.verify(status.equalsIgnoreCase("inactive"), "Index is not deactivated !!");
    }

    /*
     * Methods to read from the results from search
     */
    public enum Column {
        status, startDate, endDate, type, name, low, mid, high, close, currency, uom, rateBasis, scaleRates, comments
    }

}
