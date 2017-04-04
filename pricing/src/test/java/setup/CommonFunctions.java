package setup;

import com.google.common.base.Verify;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.util.List;

public class CommonFunctions {

    final static Logger logger = Logger.getLogger(CommonFunctions.class.getName());
    private static EventFiringWebDriver edriver;

    public CommonFunctions() {
        edriver = DriverBean.getDriver();
    }

    /*
     * Method to login to the application
     */
    public void login() {
        edriver.findElement(By.id(Constants.login_username_id)).sendKeys(Constants.username);
        edriver.findElement(By.id(Constants.login_password_id)).sendKeys(Constants.password);
        edriver.findElement(By.id(Constants.login_submit_id)).click();
    }

    ;

    /*
     * Method to navigate to a page under a module (eg : List under Index)
     */
    public void moveTo(module m, page p) {
        edriver.findElement(By.linkText(m.toString().replace("_", " "))).click();

        List<WebElement> list = edriver.findElements(By.linkText(p.toString()));

        for (WebElement temp : list) {
            if (temp.isDisplayed()) {
                temp.click();
                break;
            }
        }
        logger.info("User has navigated to " + p + " page under " + m + " module");
    }

    /*
     * Index page common methods
     */
    public void selectStatusIndex(String select) {
        WebElement options = edriver.findElement(By.xpath(Constants.indexList_status_xpath));
        Select s = new Select(options);
        switch (select) {
            case "All":
                s.selectByIndex(0);
                break;
            case "Active":
                s.selectByIndex(1);
                break;
            case "Inactive":
                s.selectByIndex(2);
                break;
            default:
                System.out.println("Please provide valid status value in the index page");
        }
    }

    public void selectType(type temp) {
        WebElement type = edriver.findElement(By.xpath(Constants.indexList_type_xpath));
        Select s = new Select(type);

        switch (temp) {
            case all:
                s.selectByIndex(0);
                break;
            case automatic:
                s.selectByIndex(2);
                break;
            case manual:
                s.selectByIndex(1);
                break;
            default:
                logger.error("Please provide valid type value in the index page");
        }
    }

    public void selectRateBasis(String rate) {

        WebElement options = edriver.findElement(By.xpath(Constants.indexList_rateBasis_xpath));
        Select s = new Select(options);
        s.selectByVisibleText(rate);
    }

    public void selectCurrency(String key) {
        List<WebElement> autofillList = edriver.findElements(By.xpath(Constants.indexList_currency_xpath));
        if (autofillList.size() == 0) {
            autofillList = edriver.findElements(By.xpath(Constants.indexCreate_currency_xpath));
        }
        Select currList = new Select(autofillList.listIterator().next());
        currList.selectByVisibleText(key);
    }

    public void selectUOM(String key) {
        List<WebElement> autofillList = edriver.findElements(By.xpath(Constants.indexList_uom_xpath));
        if (autofillList.size() == 0) {
            autofillList = edriver.findElements(By.xpath(Constants.indexCreate_uom_xpath));
        }
        Select currList = new Select(autofillList.listIterator().next());
        currList.selectByVisibleText(key);
    }

    /*
     * Currency Exchange - List page common methods
     */
    public void selectSearchType(String key) {
        /*
         * Key should be 'Daily' or 'Range'
		 */
        WebElement searchType = edriver.findElement(By.xpath(Constants.currencyExchangeList_type_xpath));
        Select choose = new Select(searchType);
        choose.selectByVisibleText(key);
    }

    public void selectStatus(String key) {
        /*
		 * Key should be 'All','Active' or 'Inactive'
		 */
        WebElement searchType = edriver.findElement(By.xpath(Constants.currencyExchangeList_status_xpath));
        Select choose = new Select(searchType);
        choose.selectByVisibleText(key);
    }

    public void selectConversionType(String key) {
        WebElement searchType = edriver.findElement(By.xpath(Constants.currencyExchangeList_convType_xpath));
        Select choose = new Select(searchType);
        choose.selectByVisibleText(key);
    }

    public void selectCurrencyFrom(String key) {
        WebElement searchType = edriver.findElement(By.xpath(Constants.currencyExchangeList_currForm_xpath));
        Select choose = new Select(searchType);
        choose.selectByVisibleText(key);
    }

    public void selectCurrencyTo(String key) {
        WebElement searchType = edriver.findElement(By.xpath(Constants.currencyExchangeList_currTo_xpath));
        Select choose = new Select(searchType);
        choose.selectByVisibleText(key);
    }

    /*
     * Calculation Rule - Create page common methods
     */
    public void enterName(String key) {
        WebElement nameField = edriver.findElement(By.id(Constants.calculationRuleCreate_name_id));
        nameField.sendKeys(key);
    }

    public void selectType(String key) {
        WebElement calType = edriver.findElement(By.xpath(Constants.calculationRuleCreate_type_xpath));
        Select choose = new Select(calType);
        choose.selectByVisibleText(key.toUpperCase());
    }

    public void enterDescription(String key) {
        WebElement descrp = edriver.findElement(By.name(Constants.calculationRuleCreate_description_name));
        descrp.sendKeys(key);
    }

    /*
     * params : key - Day Wrap or Single Day
     */
    public void selectDayRuleType(String key) {
        WebElement ruleType = edriver.findElement(By.xpath(Constants.calculationRuleCreate_dayrule_xpath));
        Select choose = new Select(ruleType);
        choose.selectByVisibleText(key);
    }

    public void enterDaysBeforeEvent(String key) {
        WebElement daysBefEvent = edriver.findElement(By.id(Constants.calculationRuleCreate_daysBeforeEvent_id));
        daysBefEvent.sendKeys(key);
    }

    public void enterDaysAfterEvent(String key) {
        WebElement daysAftEvent = edriver.findElement(By.id(Constants.calculationRuleCreate_daysAfterEvent_id));
        daysAftEvent.sendKeys(key);
    }

    public void includeEvent(Boolean key) {
        WebElement daysAftEvent = edriver.findElement(By.id(Constants.calculationRuleCreate_includeEventDay_id));

        if ((key && !daysAftEvent.isSelected()) || (!key && daysAftEvent.isSelected())) {
            daysAftEvent.click();
        }
    }

    public int getTotalNumberOfDays() {
        WebElement noOfDays = edriver.findElement(By.id(Constants.calculationRuleCreate_totalNumberofDays_id));
        return Integer.parseInt(noOfDays.getText());
    }

    /*
     * Type : Week
     */
    public void selectEffectiveStartDay(String key) {
        WebElement day = edriver.findElement(By.id(Constants.calculationRuleCreate_efStartDayOfWeek_id));
        Select choose = new Select(day);
        choose.selectByVisibleText(key.toUpperCase());
    }

    public void enterEffTotalNoOfWeeks(String key) {
        WebElement day = edriver.findElement(By.id(Constants.calculationRuleCreate_efTotalNoOfWeeks_id));
        day.sendKeys(key);
    }

    public void selectCalculationStartDay(String key) {
        WebElement day = edriver.findElement(By.id(Constants.calculationRuleCreate_cpStartDayOfWeek_id));
        Select choose = new Select(day);
        choose.selectByVisibleText(key.toUpperCase());
    }

    public void enterCalTotalNoOfWeeks(String key) {
        WebElement day = edriver.findElement(By.id(Constants.calculationRuleCreate_cpTotalNoOfWeeks_id));
        day.sendKeys(key);
    }

    public void enterCalOffset(String key) {
        WebElement day = edriver.findElement(By.id(Constants.calculationRuleCreate_cpOffset_id));
        day.sendKeys(key);
    }

    public void overLapAllowed(Boolean key) {
        WebElement overlap = edriver.findElement(By.id(Constants.calculationRuleCreate_cpOverlap_id));

        if ((key && !overlap.isSelected()) || (!key && overlap.isSelected())) {
            overlap.click();
        }
    }

    /*
     * Type : month
     */
    public void setNoOfMonthsEffPeriod(String key) {
        WebElement noOfMonths = edriver.findElement(By.id(Constants.calculationRuleCreate_noOfMonthsForEffPeriod_id));
        noOfMonths.sendKeys(key);
    }

    public void setEffStartDayOfMonth(String key) {
        WebElement noOfMonths = edriver.findElement(By.id(Constants.calculationRuleCreate_epStartDayOfMonth_id));
        noOfMonths.sendKeys(key);
    }


    public void setNoOfMonthsCalPeriod(String key) {
        WebElement noOfMonths = edriver.findElement(By.id(Constants.calculationRuleCreate_noOfMonthsForCalPeriod_id));
        noOfMonths.sendKeys(key);
    }

    public void setCalStartDayOfMonth(String key) {
        WebElement noOfMonths = edriver.findElement(By.id(Constants.calculationRuleCreate_cpStartDayOfMonth_id));
        noOfMonths.sendKeys(key);
    }

    public void selectMonthRuleType(String key) {
        WebElement noOfMonths = edriver.findElement(By.xpath(Constants.calculationRuleCreate_monthruletype_xpath));
        Select choose = new Select(noOfMonths);
        choose.selectByVisibleText(key);
    }

    /*
     * User is expected to be on the right page
     */
    public String getFirstElementFromList(String identifier) {
        List<WebElement> list = null;

        list = edriver.findElements(By.xpath(identifier));

        return list.size() > 0 ? list.get(0).getText() : null;
    }

    public WebElement getLastWebElementFromList(String identifier) {
        List<WebElement> list = edriver.findElements(By.xpath(identifier));
        int x = list.size();
        return list.size() > 0 ? list.get(list.size() - 1) : null;
    }

    public WebElement getElementAtPositionFromList(String identifier, int position) {
        List<WebElement> list = null;

        list = edriver.findElements(By.xpath(identifier.replace("*", String.valueOf(position))));

        return list.size() > 0 ? list.get(0) : null;
    }

    /*
      * Generic Methods
      */
    public void setValue(String filter, String value) {
        edriver.findElement(By.xpath(filter)).sendKeys(value);
    }

    public String getValue(String filter) {
        String value = null;
        value = edriver.findElement(By.xpath(filter)).getAttribute("ng-reflect-value");
        return value == null ? edriver.findElement(By.xpath(filter)).getText() : value;
    }

    public void checkIfTheRowsMatchFilters(String identifier, String value) throws Exception {
        List<WebElement> list = null;
        list = edriver.findElements(By.xpath(identifier));
        for (WebElement e : list) {
            if (!e.getText().toLowerCase().contains(value.toLowerCase())) {
                throw new Exception("Filter doesnt match: Actual:" + e.getText() + " Expected:" + value);
            }
        }
    }

    public void selectFromDropDown(String identifier, String... value) throws Exception {
        WebElement dropdown = null;
        if (value.length == 1) {
            dropdown = edriver.findElement(By.xpath(identifier));
        } else if (value[1].equalsIgnoreCase("last")) {
            dropdown = getLastWebElementFromList(identifier);
        }

        try {
            Select choose = new Select(dropdown);
            choose.selectByValue(value[0].toUpperCase());
            String typeText = getLastWebElementFromList("//*/*[normalize-space()='" + value[0] + "']").getText();
            if (!typeText.equalsIgnoreCase(value[0])) {
                WebElement element = getLastWebElementFromList("//span[normalize-space()='" + value[0] + "']");
                element.click();
            }
        } catch (UnexpectedTagNameException | NoSuchElementException | ElementNotVisibleException|NullPointerException exp) {
            dropdown.click();
            WebElement element = getLastWebElementFromList("//*/span[normalize-space()='" + value[0] + "']");
            element.click();
        }
    }

    public void selectDate(String identifier, String value) {
        WebElement datePicker = edriver.findElement(By.xpath(identifier));
        Actions act = new Actions(edriver);
        act.click(datePicker).sendKeys(value).perform();
        act.sendKeys(Keys.TAB).perform();
    }

    public void enterText(String identifier, String... value) {
        List<WebElement> field = edriver.findElements(By.xpath(identifier));
        clearText(identifier);
        int i = 0;
        if (value.length > 1) {
            if (value[1].matches("[0-9]*")) {
                for (WebElement temp : field) {
                    if (i++ == Integer.parseInt(value[1])) {
                        temp.sendKeys(value[0]);
                    }
                }

            } else if (value[1].equals("last")) {
                getLastWebElementFromList(identifier).sendKeys(value[0]);
            }

        } else {
            field.get(0).sendKeys(value[0]);
        }
    }

    public void clickButton(String identifier) throws Exception {
        Thread.sleep(3000);
        List<WebElement> list = edriver.findElements(By.xpath(identifier));
        boolean flag=false;
        for(WebElement temp:list)
        {
            if(temp.isEnabled())
            {
                temp.click();
                flag=true;
                break;
            }
        }
        if(!flag)
        {
            Assert.fail("Button is disabled !!");
        }
        Thread.sleep(2000);
    }

    public void setNameFromAutoFill(String identifier, String key) throws Exception {
        getLastWebElementFromList(identifier).sendKeys(key.substring(0, key.length() - 1));
        Thread.sleep(3000);
        try {
            getLastWebElementFromList("//li/span[normalize-space()='" + key + "']").click();
        } catch (ElementNotVisibleException|NullPointerException exp) {
            clickButton("//span[normalize-space()='" + key + "']");
        }
    }

    public void clearText(String identifier) {
        edriver.findElement(By.xpath(identifier)).clear();
    }

    public void scrollDown() {
        JavascriptExecutor js = ((JavascriptExecutor) edriver);
        js.executeScript("scroll(0,400)");
    }

    public void checkOnlyView(String identifier) {
        List<WebElement> list = edriver.findElements(By.xpath(identifier));
        for (WebElement temp : list) {
            assert temp.isDisplayed();
            assert !temp.isEnabled();
            assert !temp.isSelected();
        }
        if(list.size()==0){
            Assert.fail("No elements found matching:"+identifier);
        }
    }

    public enum page {
        List, Create
    }

    public enum module {
        Calculation_Rule, Workbook, Index, Currency_Exchange, Formula
    }

    public enum type {
        all, manual, automatic
    }

    public enum rateBasis {
        All, Flat, Price_Point_Scale, Point_Break_Scale
    }

}
