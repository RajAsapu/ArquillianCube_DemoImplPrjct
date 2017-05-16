package functions;

import com.google.common.base.Verify;
import com.google.common.collect.Lists;
import org.apache.maven.shared.utils.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setup.Constants;
import setup.DateOperations;
import setup.DriverBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * Class has the methods to perform operations on the web elements
 */
public class GenericWebElementMethods extends PageCommonMethods {

    private static Logger log = LoggerFactory.getLogger(GenericWebElementMethods.class);
    private EventFiringWebDriver edriver;
    private DateOperations dateOperations;
    private JavascriptExecutor js;
    private Actions actions = null;

    /*
     * Constructor
     */
    public GenericWebElementMethods() {
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
    protected WebElement getElementFromListWithPosition(String identifier, int position) {
        List<WebElement> list = null;
        list = edriver.findElements(By.xpath(identifier));

        if (list.size() == 0) {
            log.info("No elements found matching the xpath:" + identifier);
        }
        if (position >= 0) {
            return list.size() > 0 ? list.get(position) : null;
        } else if (position == -1) {
            return list.size() > 0 ? list.get(list.size() - 1) : null;
        } else {
            return null;
        }
    }
    /*
     * Method to get the enabled element fro list
     */
    protected WebElement getLastEnabledElementFromList(String identifier) {
        List<WebElement> list = null;
        list = edriver.findElements(By.xpath(identifier));
        list = Lists.reverse(list);
        for(WebElement temp:list)
        {
            if(temp.isDisplayed())
            {
                return temp;
            }
        }
        return null;
    }
    /*
     *  Method to enter text into a field using sendKeys
     */
    protected void sendKeysToWE(String identifier, String value) {
        List<WebElement> webElementList = edriver.findElements(By.xpath(identifier));
        for(WebElement temp:webElementList)
        {
            if(temp.isDisplayed())
            {
                temp.sendKeys(value);
                break;
            }
        }
    }

    /*
     * Method to click on Tab
     */
    protected void clickOnTab() {
        actions.sendKeys(Keys.TAB).perform();
    }

    /*
     * Method to get the value of the web element
     */
    protected String getValue(String filter) {
        String value = null;
        WebElement webElement = null;
        webElement = edriver.findElement(By.xpath(filter));
        value = webElement.getAttribute("ng-reflect-value");
        if (value == null) {
            value = webElement.getAttribute("value");
        }
        return value == null ? webElement.getText() : value;
    }

    /*
     * Overloaded Method-to get the value of the web element
     */
    protected String getValue(WebElement webElement) {
        String value = null;
        value = webElement.getAttribute("ng-reflect-value");
        if (value == null) {
            value = webElement.getAttribute("value");
        }
        return value == null ? webElement.getText() : value;
    }

    /*
     * Method to get the first value not null of the web element
     */
    protected String getFirstValueNotNull(String filter) {
        List<WebElement> list = edriver.findElements(By.xpath(filter));
        for(WebElement temp:list)
        {
            String x=getValue(temp);
            if(!getValue(temp).equals(""))
            {
                return getValue(temp);
            }
        }
        return null;
    }

    /*
     * Check if the data in the rows is matching the value which is used as filter
     */
    protected void checkDataInRowsMatchesFilter(String identifier, String value) {
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
        if (value != null) {
            Verify.verify(list.size() > 0, "No Records are populated with the filter:" + value);
        }
    }

    /*
     * Method to select the values from the drop down having select tag
     */
    protected void selectFromDropDown_SelectTag(String identifier, String value) {
        try {
            WebElement dropdown = getElementFromListWithPosition(identifier, 0);
            Select choose = new Select(dropdown);
            choose.selectByVisibleText(value);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    /*
     * Method to select the values from the drop down
     */
    protected void selectFromDropDown_LabelTag(String identifier, String value, int position) {
        WebElement autoFill = null;
        WebElement dropdown = getElementFromListWithPosition(identifier, -1);
        actions.click(dropdown).perform();
        waitFor(3);
        scrollIntoView("//*[normalize-space()='" + value + "']");
        if (getSizeOfList("//*[normalize-space()='" + value + "']") > 0) {
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value + "']", position);
        } else if (getSizeOfList("//*[normalize-space()='" + value.toUpperCase() + "']") > 0) {
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value.toUpperCase() + "']", position);
        } else if (getSizeOfList("//*[normalize-space()='" + value.replaceAll(" ", "") + "']") > 0) {
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value.replaceAll(" ", "") + "']", position);
        } else {
            Assert.fail("Auto Fill options are not displayed");
        }
        actions.click(autoFill).perform();
    }
  /*
   *  Method to select the values from the drop down with search option
   */
    protected void selectFromDropDownWithSearchBar_LabelTag(String dropdownId,String value) {
        WebElement autoFill = null;
        WebElement dropdown = getElementFromListWithPosition(dropdownId, 0);
        actions.click(dropdown).perform();
        waitFor(3);
        sendKeysToWE(Constants.workbookData_searchInDropdown_xpath,value);
        waitFor(1);
        scrollIntoView("//*[normalize-space()='" + value + "']");
        if (getSizeOfList("//*[normalize-space()='" + value + "']") > 0) {
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value + "']", -1);
        } else if (getSizeOfList("//*[normalize-space()='" + value.toUpperCase() + "']") > 0) {
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value.toUpperCase() + "']", -1);
        } else if (getSizeOfList("//*[normalize-space()='" + value.replaceAll(" ", "") + "']") > 0) {
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value.replaceAll(" ", "") + "']", -1);
        } else {
            Assert.fail("Auto Fill options are not displayed");
        }
        actions.click(autoFill).perform();
    }

    /*
     * Method  to select from drop down using the drop down identifiers
     */
    public void selectFromDropDown(String identifier,String listIdentifier,String value)
    {
        WebElement autoFill = null;
        WebElement dropdown = getElementFromListWithPosition(identifier, 0);
        actions.click(dropdown).perform();
        waitFor(2);
        List<WebElement> elementList = edriver.findElements(By.xpath(listIdentifier));
        for(WebElement temp:elementList)
        {
            if(temp.getText().equalsIgnoreCase(value))
            {
                temp.click();
                break;
            }
        }
    }
    /*
     * Method to select the date
     */
    protected void selectDate(String date, String datePickerId, String dateField) {
        clearText(dateField);
        WebElement datePicker = getElementFromListWithPosition(datePickerId, -1);
        actions.click(datePicker).sendKeys(date).perform();
        actions.sendKeys(Keys.TAB).perform();
    }

    /*
     * Method to send keys to a text field at a specific position
     */
    protected void sendKeysToWeAtPosition(String identifier, String value, int position) {
        WebElement element = getElementFromListWithPosition(identifier, position);
        element.sendKeys(value);
    }

    /*
     * Method to click button
     */
    protected void clickButton(String identifier) {
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
    protected void setNameFromAutoFill(String identifier, String value, int position) {
        WebElement autoFill = null;
        getElementFromListWithPosition(identifier, -1).clear();
        getElementFromListWithPosition(identifier, -1).sendKeys(value.substring(0, value.length() - 1));
        waitFor(3);
        if (getSizeOfList("//*[normalize-space()='" + value + "']") > 0) {
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value + "']", position);
        } else if (getSizeOfList("//*[normalize-space()='" + value.toUpperCase() + "']") > 0) {
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value.toUpperCase() + "']", position);
        } else if (getSizeOfList("//*[normalize-space()='" + value.replaceAll(" ", "") + "']") > 0) {
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + value.replaceAll(" ", "") + "']", position);
        } else if (getSizeOfList("//*[normalize-space()='" + StringUtils.capitalise(value) + "']") > 0) {
            autoFill = getElementFromListWithPosition("//*[normalize-space()='" + StringUtils.capitalise(value) + "']", position);
        } else {
            Assert.fail("Auto Fill options are not displayed");
        }
        actions.click(autoFill).perform();
    }

    /*
     * Method to clear the text
     */
    protected void clearText(String identifier) {
        if (identifier != null) {
            List<WebElement> list = edriver.findElements(By.xpath(identifier));
            if (list.size() > 0) {
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
    protected void scrollIntoView(String identifier) {
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
        boolean flag=false;
        String testString = "(SendKeys)";
        List<WebElement> list = edriver.findElements(By.xpath(identifier));
        for (WebElement temp : list) {
            Verify.verify(temp.isDisplayed());
            flag = !temp.isEnabled();
            if(!flag)
            {
                temp.sendKeys(testString);
                Verify.verify(!getValue(temp).equals(testString),"Field is not readOnly");
            }
        }
        if (list.size() == 0) {
            Assert.fail("No elements found matching:" + identifier);
        }
    }

    /*
     * Method to check if the elements are editable
     */
    protected void isEditable(String identifier) {
        List<WebElement> list = edriver.findElements(By.xpath(identifier));
        for (WebElement temp : list) {
            Verify.verify(temp.isDisplayed(), "Element is not dispalyed");
            Verify.verify(temp.isEnabled(), "Element is not enabled");
        }
        if (list.size() == 0) {
            Assert.fail("No elements found matching:" + identifier);
        }
    }

    /*
     * method to select the check box
     */
    protected void selectCheckbox(boolean check, String identifier) {
        WebElement checkbox = getElementFromListWithPosition(identifier, 0);
        if ((check && !checkbox.isSelected()) || (!check && checkbox.isSelected())) {
            checkbox.click();
        }
    }

    /*
     * Method to get the size of web element list
     */
    protected int getSizeOfList(String identifier) {
        return edriver.findElements(By.xpath(identifier)).size();
    }

    /*
     * Date Operations : Set date
     */
    protected void setDateWithTimeStamp(String operation, String datePicker, String dateField) {
        dateOperations.setDate(operation, datePicker, dateField);
    }

    protected String getDate(String identifier) {
        return dateOperations.getDate(identifier);
    }

    protected void verifyTextOnWeIsEqualToValue(String identifier, String value) {
        Verify.verify(getValue(identifier).equals(value), "Actual:" + getValue(identifier) + " Expected:" + value);
    }

    protected void verifyIfTextIsDisplayed(String text) {
        Verify.verify(getSizeOfList("//*[normalize-space()='" + text + "']") > 0, "Text:" + text + " is not displayed");
    }

    protected boolean isSizeOfWEListGtZero(String text) {
        return getSizeOfList("//*[normalize-space()='" + text + "']") > 0;
    }

    /*
     * Method to set the date with time stamp
     * day : today,tomorrow or yesterday
     */
    public String setDateWithTimeStamp(String day)
    {
        String date = null;
        day = day.toLowerCase();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        Date timeStamp = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeStamp);

        if(day.contains("tomorrow"))
        {
            calendar.add(Calendar.DATE,1);
        }
        else if(day.contains("yesterday"))
        {
            calendar.add(Calendar.DATE,-1);
        }

        date = formatter.format(calendar.getTime());

        if(day.equals("")){
            date="";
        }

        return date;
    }
    /*
     * Method to search the list of records in all the pages and click on the
     * radio button , if identified
     */
    public boolean selectDataSearchingPages(String identifer,String name)
    {
        int position=0;
        boolean flag=false;
        List<WebElement> webElementList;
        List<WebElement> pageList;
        waitFor(2);
        pageList = edriver.findElements(By.xpath(Constants.workbookList_noOfPages_xpath));

        for(WebElement page:pageList) {
            if(position!=0)
            {
                clickButton(Constants.workbookList_nextPage_xpath);
            }
            position=0;
            waitFor(3);
            webElementList = edriver.findElements(By.xpath(identifer));
            for (WebElement temp : webElementList) {
                if (temp.getText().equalsIgnoreCase(name)) {
                    temp.click();
                    flag = true;
                    break;
                }
                ++position;
            }
            if(flag){
                break;
            }
        }

        if(!flag)
        {
            Assert.fail("Record with name:"+name+" not found");
        }
        return flag;
    }
}
