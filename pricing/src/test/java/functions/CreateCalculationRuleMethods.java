package functions;


import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

import java.util.List;

public class CreateCalculationRuleMethods extends GenericWebElementMethods {

    private static EventFiringWebDriver edriver;

    public CreateCalculationRuleMethods()
    {
        edriver = DriverBean.getDriver();
    }

    public void setName(String name)
    {
        sendKeysToWE(Constants.calculationRuleCreate_name_xpath,name);
    }
    public void setStartDate(String startDate)
    {
        selectDate(startDate,Constants.calculationRuleCreate_startDatePicker_xpath,null);
    }
    public void setEndDate(String endDate)
    {
        selectDate(endDate,Constants.calculationRuleCreate_endDatePicker_xpath,Constants.calculationRuleCreate_endDate_xpath);
    }
    public void setType(String type)throws Exception
    {
        selectFromDropDown(Constants.calculationRuleCreate_type_xpath,type.toUpperCase());
    }
    public void setDescription(String description)
    {
        sendKeysToWE(Constants.calculationRuleCreate_description_xpath,description);
    }
    public void setDayRuleType(String dayRuleType)throws Exception
    {
        selectFromDropDown(Constants.calculationRuleCreate_dayrule_xpath,dayRuleType);
    }
    public void setDaysBeforeEvent(String daysBeforeEvent)
    {
        sendKeysToWE(Constants.calculationRuleCreate_daysBeforeEvent_xpath,daysBeforeEvent);
    }
    public void setDaysAfterEvent(String daysAfterEvent)
    {
        sendKeysToWE(Constants.calculationRuleCreate_daysAfterEvent_xpath,daysAfterEvent);
    }
    public void includeEventDay(boolean check)
    {
        selectCheckbox(check,Constants.calculationRuleCreate_includeEventDay_xpath);
    }
    public void overlapAllowed(boolean check)
    {
        selectCheckbox(check,Constants.calculationRuleCreate_cpOverlap_xpath);
    }
    public int getTotalNumberOfDays()
    {
        return Integer.parseInt(getElementFromListWithPosition(Constants.calculationRuleCreate_totalNumberofDays_xpath,0).getText());
    }
    public void setEffectiveStartDay(String efStartDay)throws Exception
    {
        selectFromDropDown(Constants.calculationRuleCreate_efStartDayOfWeek_xpath,efStartDay.toUpperCase());
    }
    public void setEffTotalNoOfWeeks(String effTotalNoOfWeeks)
    {
        sendKeysToWE(Constants.calculationRuleCreate_efTotalNoOfWeeks_xpath,effTotalNoOfWeeks);
    }
    public void setCalculationStartDay(String startDay) throws Exception{
        selectFromDropDown(Constants.calculationRuleCreate_cpStartDayOfWeek_xpath,startDay);
    }
    public void setCalTotalNoOfWeeks(String totalNoOfWeeks) {
        sendKeysToWE(Constants.calculationRuleCreate_cpTotalNoOfWeeks_xpath,totalNoOfWeeks);
    }
    public void setCalOffset(String calOffset) {
        sendKeysToWE(Constants.calculationRuleCreate_cpOffset_xpath,calOffset);
    }
    public void setMonthRuleType(String monthRuleType)throws Exception
    {
        selectFromDropDown(Constants.calculationRuleCreate_monthruletype_xpath,monthRuleType);
    }
    public void setNoOfMonthsEffPeriof(String noOfMonthsEffPeriof)
    {
        sendKeysToWE(Constants.calculationRuleCreate_noOfMonthsForEffPeriod_xpath,noOfMonthsEffPeriof);
    }
    public void setEffStartDayOfMonth(String effStartDayOfMonth) {
        sendKeysToWE(Constants.calculationRuleCreate_epStartDayOfMonth_xpath,effStartDayOfMonth);
    }
    public void setNoOfMonthsCalPeriod(String noOfMonthsCalPeriod) {
       sendKeysToWE(Constants.calculationRuleCreate_noOfMonthsForCalPeriod_xpath,noOfMonthsCalPeriod);
    }
    public void setCalStartDayOfMonth(String calStartDayOfMonth) {
        sendKeysToWE(Constants.calculationRuleCreate_cpStartDayOfMonth_xpath,calStartDayOfMonth);
    }
    /*
     * Calculation rule tester
     */
    public void setTestDate(String testDate)
    {
        selectDate(testDate,Constants.calculationRuleCreate_testDate_xpath,null);
    }
    public void clickOnTestRule()throws Exception
    {
        clickButton(Constants.calculationRuleCreate_testRuleButton_xpath);
    }
    public void verifyIfPageIsCalculationRuleList()throws Exception
    {
        if (!edriver.getCurrentUrl().contains("/calc-rule/list")) {
            throw new Exception("Calculation Rule is not created");
        }
    }
    public void verifyIfUserIsAbleToViewOrEditDetails(String action, DataTable table)
    {
        viewableOnly(Constants.calculationRuleCreate_name_xpath);
        viewableOnly(Constants.calculationRuleCreate_type_xpath);
        viewableOnly(Constants.calculationRuleCreate_description_xpath);

        if (action.equals("view")) {
            viewableOnly(Constants.calculationRuleCreate_daysBeforeEvent_xpath);
            viewableOnly(Constants.calculationRuleCreate_daysAfterEvent_xpath);
            viewableOnly(Constants.calculationRuleCreate_includeEventDay_xpath);
        } else if (action.equals("edit")) {
            List<List<String>> row = table.raw();
            setEndDate(row.get(0).get(0));
        }
    }

}
