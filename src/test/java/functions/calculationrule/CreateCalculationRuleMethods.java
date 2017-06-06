package functions.calculationrule;


import cucumber.api.DataTable;
import functions.GenericWebElementMethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import setup.Constants;
import setup.DriverBean;

import java.time.LocalDate;
import java.util.List;

public class CreateCalculationRuleMethods extends GenericWebElementMethods {

    private EventFiringWebDriver edriver;

    public CreateCalculationRuleMethods()
    {
        edriver= DriverBean.getDriver();
    }

    public void setName(String name) {
        sendKeysToWE(Constants.calculationRuleCreate_name_xpath, name+getDateWithTime());
    }

    public void setStartDate(String startDate) {
        selectDate(startDate, Constants.calculationRuleCreate_startDatePicker_xpath, null);
    }

    public void setEndDate(String endDate) {
        if (endDate.matches("^[a-zA-Z]*$")) {
            endDate = setDateWithTimeStamp(endDate, false);
        }
        clearText(Constants.calculationRuleCreate_endDate_xpath);
        sendKeysToWE(Constants.calculationRuleCreate_endDate_xpath, endDate);
        clickOnTab();
    }

    public void setType(String type) {
        selectFromDropDown_LabelTag(Constants.calculationRuleCreate_type_xpath, type.toUpperCase(), -1);
    }

    public void setDescription(String description) {
        sendKeysToWE(Constants.calculationRuleCreate_description_xpath, description);
    }

    public void setDayRuleType(String dayRuleType) {
        selectFromDropDown_SelectTag(Constants.calculationRuleCreate_dayrule_xpath, dayRuleType);
    }

    public void setDaysBeforeEvent(String daysBeforeEvent) {
        sendKeysToWE(Constants.calculationRuleCreate_daysBeforeEvent_xpath, daysBeforeEvent);
    }

    public void setDaysAfterEvent(String daysAfterEvent) {
        sendKeysToWE(Constants.calculationRuleCreate_daysAfterEvent_xpath, daysAfterEvent);
    }

    public void includeEventDay(boolean check) {
        selectCheckbox(check, Constants.calculationRuleCreate_includeEventDay_xpath);
    }

    public void overlapAllowed(boolean check) {
        selectCheckbox(check, Constants.calculationRuleCreate_cpOverlap_xpath);
    }

    public int getTotalNumberOfDays() {
        return Integer.parseInt(getElementFromListWithPosition(Constants.calculationRuleCreate_totalNumberofDays_xpath, 0).getText());
    }

    public void setEffectiveStartDay(String efStartDay) {
        selectFromDropDown_SelectTag(Constants.calculationRuleCreate_efStartDayOfWeek_xpath, efStartDay.toUpperCase());
    }

    public void setEffTotalNoOfWeeks(String effTotalNoOfWeeks) {
        clearText(Constants.calculationRuleCreate_efTotalNoOfWeeks_xpath);
        sendKeysToWE(Constants.calculationRuleCreate_efTotalNoOfWeeks_xpath, effTotalNoOfWeeks);
    }

    public void setCalculationStartDay(String startDay) {
        selectDate(startDay, Constants.calculationRuleCreate_cpStartDayOfWeek_xpath, null);
    }

    public void setCalTotalNoOfWeeks(String totalNoOfWeeks) {
        sendKeysToWE(Constants.calculationRuleCreate_cpTotalNoOfWeeks_xpath, totalNoOfWeeks);
    }

    public void setCalOffset(String calOffset) {
        clearText(Constants.calculationRuleCreate_cpOffset_xpath);
        sendKeysToWE(Constants.calculationRuleCreate_cpOffset_xpath, calOffset);
    }

    public void setMonthRuleType(String monthRuleType) {
        selectFromDropDown_SelectTag(Constants.calculationRuleCreate_monthruletype_xpath, monthRuleType);
    }

    public void setNoOfMonthsEffPeriof(String noOfMonthsEffPeriof) {
        sendKeysToWE(Constants.calculationRuleCreate_noOfMonthsForEffPeriod_xpath, noOfMonthsEffPeriof);
    }

    public void setEffStartDayOfMonth(String effStartDayOfMonth) {
        sendKeysToWE(Constants.calculationRuleCreate_epStartDayOfMonth_xpath, effStartDayOfMonth);
    }

    public void setNoOfMonthsCalPeriod(String noOfMonthsCalPeriod) {
        sendKeysToWE(Constants.calculationRuleCreate_noOfMonthsForCalPeriod_xpath, noOfMonthsCalPeriod);
    }

    public void setCalStartDayOfMonth(String calStartDayOfMonth) {
        sendKeysToWE(Constants.calculationRuleCreate_cpStartDayOfMonth_xpath, calStartDayOfMonth);
    }

    /*
     * Calculation rule tester
     */
    public void setTestDate(String testDate) {
        selectDate(testDate, Constants.calculationRuleCreate_testDate_xpath, null);
    }

    public void clickOnTestRule() {
        clickButton(Constants.calculationRuleCreate_testRuleButton_xpath);
    }

    public void verifyIfPageIsCalculationRuleList() {
        waitFor(5);
        if (!edriver.getCurrentUrl().contains("/calc-rule/list")) {
            Assert.fail("Calculation Rule is not created");
        }
    }

    public void verifyIfUserIsAbleToViewOrEditDetails(String action, DataTable table) {
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
