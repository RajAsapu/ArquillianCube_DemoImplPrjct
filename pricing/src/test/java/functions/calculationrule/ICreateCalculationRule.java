package functions.calculationrule;


import cucumber.api.DataTable;
import setup.IPage;

public interface ICreateCalculationRule extends IPage{
    public void setDayRuleType(String dayRuleType);
    public void setDaysBeforeEvent(String daysBeforeEvent);
    public void setDaysAfterEvent(String daysAfterEvent);
    public void includeEventDay(boolean check);
    public void overlapAllowed(boolean check);
    public void setEffectiveStartDay(String efStartDay);
    public void setEffTotalNoOfWeeks(String effTotalNoOfWeeks);
    public void setCalculationStartDay(String startDay);
    public void setCalTotalNoOfWeeks(String totalNoOfWeeks);
    public void setCalOffset(String calOffset);
    public void setMonthRuleType(String monthRuleType);
    public void setNoOfMonthsEffPeriof(String noOfMonthsEffPeriof);
    public void setEffStartDayOfMonth(String effStartDayOfMonth) ;
    public void setNoOfMonthsCalPeriod(String noOfMonthsCalPeriod) ;
    public void setCalStartDayOfMonth(String calStartDayOfMonth);
    public void setTestDate(String testDate);
    public void clickOnTestRule();
    public void verifyIfPageIsCalculationRuleList();
    public void verifyIfUserIsAbleToViewOrEditDetails(String action, DataTable table);
}
