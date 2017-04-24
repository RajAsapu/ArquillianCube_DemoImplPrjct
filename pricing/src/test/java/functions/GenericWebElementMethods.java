package functions;

import com.google.common.base.Verify;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setup.DriverBean;
import java.util.List;
/*
 * Class has the methods to perform operations on the web elements
 */
public class GenericWebElementMethods {

    private static Logger log =null;
    private static EventFiringWebDriver edriver;
    private JavascriptExecutor js = ((JavascriptExecutor) edriver);

    /*
     * Constructor to intialize the dependent classes
     */
    protected GenericWebElementMethods()
    {
        log = LoggerFactory.getLogger(GenericWebElementMethods.class);
        edriver = DriverBean.getDriver();
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
        List<WebElement> list = null;
        list = edriver.findElements(By.xpath(identifier));
        for (WebElement e : list) {
            if (!e.getText().toLowerCase().contains(value.toLowerCase())) {
                Assert.fail("Filter doesn't match: Actual:" + e.getText() + " Expected:" + value);
            }
        }
    }
    /*
     * Method to select the values from the drop down
     */
    protected void selectFromDropDown(String identifier, String... value)  {
        WebElement dropdown = null;
        if (value.length == 1) {
            dropdown = edriver.findElement(By.xpath(identifier));
        } else if (value[1].equalsIgnoreCase("last")) {
            dropdown = getElementFromListWithPosition(identifier,-1);
        }

        try {
            Select choose = new Select(dropdown);
            choose.selectByVisibleText(value[0]);
        } catch (UnexpectedTagNameException | NoSuchElementException | ElementNotVisibleException |NullPointerException exp) {
            dropdown.click();
            WebElement element = getElementFromListWithPosition("//*[normalize-space()='" + value[0] + "']",-1);
            element.click();
        }
    }
    /*
     * Method to select the date
     */
    protected void selectDate(String date,String datePickerId, String dateField) {
        clearText(dateField);
        WebElement datePicker = edriver.findElement(By.xpath(datePickerId));
        Actions act = new Actions(edriver);
        act.click(datePicker).sendKeys(date).perform();
        act.sendKeys(Keys.TAB).perform();
    }
    /*
     * Method to send keys to a text field at a specific position
     */
    protected void sendKeysToWeAtPosition(String identifier, String... value) {
        List<WebElement> field = edriver.findElements(By.xpath(identifier));

        int i = 0;
        if (value.length > 1) {
            /* To enter text for an element at a specific position */
            if (value[1].matches("[0-9]*")) {
                for (WebElement temp : field) {
                    if (i++ == Integer.parseInt(value[1])) {
                        temp.clear();
                        temp.sendKeys(value[0]);
                    }
                }

            } else if (value[1].equals("last")) {
                getElementFromListWithPosition(identifier,-1).clear();
                getElementFromListWithPosition(identifier,-1).sendKeys(value[0]);
            }

        } else {
            field.get(0).sendKeys(value[0]);
        }
    }
    /*
     * Method to click button
     */
    protected void clickButton(String identifier)  {
        WebElement button = edriver.findElement(By.xpath(identifier));
        if (button.isEnabled()) {
            button.click();
        } else {
            Assert.fail("Button is disabled !!");
        }
    }
    /*
     * Method to set value from the auto fill list
     */
    protected void setNameFromAutoFill(String identifier, String key) throws Exception {
        getElementFromListWithPosition(identifier,-1).clear();
        getElementFromListWithPosition(identifier,-1).sendKeys(key.substring(0, key.length() - 1));
        Thread.sleep(3000);
        try {
            getElementFromListWithPosition("//li/span[normalize-space()='" + key.toUpperCase() + "']",-1).click();
        } catch (ElementNotVisibleException|NullPointerException exp) {
            getElementFromListWithPosition("//*[normalize-space()='" + key.toUpperCase() + "']",-1).click();
        }
    }
    /*
     * Method to clear the text
     */
    protected void clearText(String identifier) {
        List<WebElement> list = edriver.findElements(By.xpath(identifier));
        if(list.size()>0){
            list.get(0).clear();
        }
    }

    /*
     * Method to scroll down
     */
    protected void scrollDown() {
        js.executeScript("scroll(0,400)");
    }
    /*
     * Method to scroll inside an element
     */
    protected void scrollIntoView(String identifier)
    {
        WebElement list = edriver.findElement(By.xpath(identifier));
        js.executeScript("arguments[0].scrollIntoview(true);",list);
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
    public int getSizeOfList(String identifier){
        return edriver.findElements(By.xpath(identifier)).size();
    }
}
