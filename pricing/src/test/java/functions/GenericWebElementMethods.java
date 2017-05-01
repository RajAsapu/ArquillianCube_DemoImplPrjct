package functions;

import com.google.common.base.Verify;
import cucumber.deps.com.thoughtworks.xstream.mapper.Mapper;
import io.netty.util.internal.StringUtil;
import org.apache.maven.shared.utils.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setup.DateOperations;
import setup.DriverBean;

import java.util.List;
/*
 * Class has the methods to perform operations on the web elements
 */
public class GenericWebElementMethods extends PageCommonMethods {

    private EventFiringWebDriver edriver;
    private static Logger log = LoggerFactory.getLogger(GenericWebElementMethods.class);
    private DateOperations dateOperations;
    private JavascriptExecutor js;
    private Actions actions = null;
    /*
     * Constructor
     */
    public GenericWebElementMethods()
    {
        this.edriver = DriverBean.getDriver();
        dateOperations = new DateOperations();
        actions = new Actions(edriver);
        js = ((JavascriptExecutor) edriver);
    }
    /*
     * Getter method for Event Firing WebDriver
     */
    public EventFiringWebDriver getEdriver() {
        return edriver;
    }
    /*
     * Method to return the first web element from the list
     * position = -1 to get the last web element
     */
    protected WebElement getElementFromListWithPosition(String identifier,int position) {
        List<WebElement> list = null;
        list = edriver.findElements(By.xpath(identifier));

        if(list.size()==0){
            log.info("No elements found matching the xpath:"+identifier);
        }
        if(position>=0){
            return list.size() > 0 ? list.get(position) : null;
        }else if(position==-1){
            return list.size() > 0 ? list.get(list.size() - 1) : null;
        }else{
            return null;
        }
    }
    /*
     *  Method to enter text into a field using sendKeys
     */
    protected void sendKeysToWE(String identifier, String value) {
        edriver.findElement(By.xpath(identifier)).sendKeys(value);
    }
    /*
     * Method to click on Tab
     */
    protected void clickOnTab()
    {
        actions.sendKeys(Keys.TAB).perform();
    }
    /*
     * Method to get the value of the web element
     */
    protected String getValue(String filter) {
        String value = null;
        value = edriver.findElement(By.xpath(filter)).getAttribute("ng-reflect-value");
        return value == null ? edriver.findElement(By.xpath(filter)).getText() : value;
    }
    /*
     * Check if the data in the rows is matching the value which is used as filter
     */
    protected void checkDataInRowsMatchesFilter(String identifier, String value)  {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> list = null;
        list = edriver.findElements(By.xpath(identifier));
        for (WebElement e : list) {
            if (!e.getText().toLowerCase().contains(value.toLowerCase())) {
                Assert.fail("Filter doesn't match: Actual:" + e.getText() + " Expected:" + value);
            }
        }
        if(value!=null)
        {
            Verify.verify(list.size()>0,"No Records are populated with the filter:"+value);
        }
    }
    /*
     * Method to select the values from the drop down having select tag
     */
    protected void selectFromDropDown_SelectTag(String identifier,String value)
    {
        try {
            WebElement dropdown = getElementFromListWithPosition(identifier, 0);
            Select choose = new Select(dropdown);
            choose.selectByVisibleText(value);
        }
        catch (Exception exp){
            exp.printStackTrace();
        }
    }
    /*
     * Method to select the values from the drop down
     */
    protected void selectFromDropDown_LabelTag(String identifier, String value , int position)  {
            WebElement autoFill=null;
            WebElement dropdown = getElementFromListWithPosition(identifier, 0);
            actions.click(dropdown).perform();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scrollIntoView("//*[normalize-space()='" + value + "']");
        if(getSizeOfList("//*[normalize-space()='" + value + "']")>0) {
                autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value + "']",-1);
            }else if(getSizeOfList("//*[normalize-space()='" + value.toUpperCase() + "']")>0){
                autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value.toUpperCase() + "']",-1);
            }else if(getSizeOfList("//*[normalize-space()='" + value.replaceAll(" ","") + "']")>0){
                autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value.replaceAll(" ","") + "']",-1);
            }else{
                Assert.fail("Auto Fill options are not displayed");
            }
            actions.click(autoFill).perform();
    }
    /*
     * Method to select the date
     */
    protected void selectDate(String date,String datePickerId, String dateField) {
        clearText(dateField);
        WebElement datePicker = getElementFromListWithPosition(datePickerId,-1);
        actions.click(datePicker).sendKeys(date).perform();
        actions.sendKeys(Keys.TAB).perform();
    }
    /*
     * Method to send keys to a text field at a specific position
     */
    protected void sendKeysToWeAtPosition(String identifier, String value,int position) {
        WebElement element=getElementFromListWithPosition(identifier,position);
        element.sendKeys(value);
    }
    /*
     * Method to click button
     */
    protected void clickButton(String identifier)  {
        WebElement button = edriver.findElement(By.xpath(identifier));
        if (button.isEnabled() && button.isDisplayed()) {
            button.click();
        } else {
            Assert.fail("Button is disabled !!");
        }
    }
    /*
     * Method to set value from the auto fill list
     */
    protected void setNameFromAutoFill(String identifier, String value) {
        WebElement autoFill = null;
        getElementFromListWithPosition(identifier,-1).clear();
        getElementFromListWithPosition(identifier,-1).sendKeys(value.substring(0, value.length() - 1));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(getSizeOfList("//*[normalize-space()='" + value + "']")>0){
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value + "']",-1);
        } else if(getSizeOfList("//*[normalize-space()='" + value.toUpperCase() + "']")>0) {
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value.toUpperCase() + "']",-1);
        }else if(getSizeOfList("//*[normalize-space()='" + value.replaceAll(" ","") + "']")>0){
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value.replaceAll(" ","") + "']",-1);
        }else if(getSizeOfList("//*[normalize-space()='" + StringUtils.capitalise(value) + "']")>0){
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + StringUtils.capitalise(value) + "']",-1);
        }
        else{
            Assert.fail("Auto Fill options are not displayed");
        }
        actions.click(autoFill).perform();
    }
    /*
     * Method to clear the text
     */
    protected void clearText(String identifier) {
        if(identifier!=null)
        {
            List<WebElement> list = edriver.findElements(By.xpath(identifier));
            if(list.size()>0){
                list.get(0).clear();
            }
        }
    }

    /*
     * Method to scroll down
     */
    protected void scrollDown() {
//        js.executeScript("scroll(0,400)");
    }
    /*
     * Method to scroll inside an element
     */
    protected void scrollIntoView(String identifier)
    {
        WebElement list = edriver.findElement(By.xpath(identifier));
        actions.moveToElement(list).perform();
       // js.executeScript("arguments[0].scrollIntoview(true);",list);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
     * Method to validate that the element is viewable Only
     */
    protected void viewableOnly(String identifier) {
        List<WebElement> list = edriver.findElements(By.xpath(identifier));
        for (WebElement temp : list) {
            Verify.verify(temp.isDisplayed());
            Verify.verify(!temp.isEnabled(),"Element is enabled !!");
        }
        if(list.size()==0){
            Assert.fail("No elements found matching:"+identifier);
        }
    }
    /*
     * Method to check if the elements are editable
     */
    protected void isEditable(String identifier) {
        List<WebElement> list = edriver.findElements(By.xpath(identifier));
        for (WebElement temp : list) {
            Verify.verify(temp.isDisplayed(),"Element is not dispalyed");
            Verify.verify(temp.isEnabled(),"Element is not enabled");
        }
        if(list.size()==0){
            Assert.fail("No elements found matching:"+identifier);
        }
    }
    /*
     * method to select the check box
     */
    protected void selectCheckbox(boolean check,String identifier)
    {
        WebElement checkbox = getElementFromListWithPosition(identifier,0);
        if ((check && !checkbox.isSelected()) || (!check && checkbox.isSelected())) {
            checkbox.click();
        }
    }
    /*
     * Method to get the size of web element list
     */
    protected int getSizeOfList(String identifier){
        return edriver.findElements(By.xpath(identifier)).size();
    }
    /*
     * Date Operations : Set date
     */
    protected void setDate(String operation,String datePicker , String dateField)
    {
        dateOperations.setDate(operation,datePicker,dateField);
    }
    protected String getDate(String identifier)
    {
        return dateOperations.getDate(identifier);
    }

    protected void verifyTextOnWeIsEqualToValue(String identifier,String value)
    {
        Verify.verify(getValue(identifier).equals(value),"Actual:"+getValue(identifier)+" Expected:"+value);
    }
}
