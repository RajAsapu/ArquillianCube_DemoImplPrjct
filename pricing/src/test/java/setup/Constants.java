package setup;

public class Constants {
	/*
	 * Enterprise pricing identifiers
	 */
	/*
	 * Default user id and password
	 */
	public static String username = "cxl_admin1";
	public static String password = "Welcome01";
	/*
	 * Login page identifiers
	 */
	public static String login_username_id = "username";
	public static String login_password_id = "password";
	public static String login_submit_id = "kc-login";
	/*
	 * Index : List page identifiers
	 */
	public static String indexList_startdate_id = "dp1488300844455";
	public static String indexList_status_xpath = "//*/calc-rule-list/div/div/div/div[2]/form/p-fieldset/fieldset/div/div/div[1]/div[3]/div/select";
	public static String indexList_startdatepicker_xpath = "//*/app-root/calc-rule-list/div/div/div/div[2]/form/p-fieldset/fieldset/div/div/div[1]/div[1]/div/p-calendar/span/button";
	public static String indexList_StartDateColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[2]";
	public static String indexList_enddatepicker_xapth = "//*/app-root/calc-rule-list/div/div/div/div[2]/form/p-fieldset/fieldset/div/div/div[1]/div[2]/div/p-calendar/span/button";
	public static String indexList_EndDateColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[1]/td[3]";
	public static String indexList_search_xpath = "//*/p-fieldset/fieldset/div/div/div[2]/div[*]/button";
	public static String indexList_StatusColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[1]";
	public static String indexList_type_xpath = "//*/select[@ng-reflect-name='type']";
	public static String indexList_rateBasis_xpath = "//*/select[@formcontrolname='rateBasis']";
	public static String indexList_name_xpath = "//*/p-autocomplete/span/input";
	public static String indexList_currency_xpath = "//*/fieldset/div/div/div[2]/div[4]/div/select";
	public static String indexList_uom_xpath = "//*/fieldset/div/div/div[2]/div[5]/div/select";
	public static String indexList_typeColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[4]";
	public static String indexList_name_autofill_xpath = "//*/calc-rule-list/div/div/div/div[2]/form/p-fieldset/fieldset/div/div/div[2]/div[3]/div/p-autocomplete/span/div/ul/li";
	public static String indexList_ratebasisColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[12]";
	public static String indexList_nameColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[5]";
	public static String indexList_currencyColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[10]";
	public static String indexList_uomColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[11]";
	/*
	 * Index : Create page identifiers
	 */
	public static String indexCreate_name_autofill_path = "//*/div/div/div/form/p-fieldset/fieldset/div/div/div[1]/div/p-autocomplete/span/div/ul/li";
	public static String indexCreate_lowprice_id = "txtLow";
	public static String indexCreate_midprice_id = "txtMid";
	public static String indexCreate_highprice_id = "txtHigh";
	public static String indexCreate_closeprice_xpath = ".//input[@type='number']";
	public static String indexCreate_startDatePicker_xpath = "//*/div/div/div[3]/div/p-calendar/span/button";
	public static String indexCreate_endDatePicker_xpath = "//*/div/div/div[4]/div/p-calendar/span/button";
	public static String indexCreate_currency_xpath = "//*/p-fieldset/fieldset/div/div/div[9]/div/select";
	public static String indexCreate_uom_xpath = "//*/p-fieldset/fieldset/div/div/div[10]/div/select";
	public static String indexCreate_comment_xpath = "//*[@id='txtDesc']";
	public static String indexCreate_submit_xpath = "//*/button[normalize-space()='Submit']";

	/*
	 * Currency Exchange List Page Identifiers Filters on search
	 */
	public static String currencyExchangeList_type_xpath = "//*/select[@ng-reflect-name='type']";
	public static String currencyExchangeList_status_xpath = "//*/select[@ng-reflect-name='status']";
	public static String currencyExchangeList_convType_xpath = "//*/select[@ng-reflect-name='conversionType']";
	public static String currencyExchangeList_currForm_xpath = "//*/select[@ng-reflect-name='currencyFrom']";
	public static String currencyExchangeList_currTo_xpath = "//*/select[@ng-reflect-name='currencyTo']";
	public static String currencyExchangeList_submit_xpath = "//*/button[text()='Submit']";
	/*
	 * Currency Exchange List Page Identifiers Currency Exchange table column
	 * identifiers
	 */
	public static String currencyExchangeList_statusColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[1]";
	public static String currencyExchangeList_fromColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[2]";
	public static String currencyExchangeList_toColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[3]";
	public static String currencyExchangeList_dateColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[4]";
	public static String currencyExchangeList_rateTypeColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[5]";
	public static String currencyExchangeList_rateColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[6]";
	public static String currencyExchangeList_startDatePicker_xpath = "//*/p-fieldset/fieldset/div/div/div[1]/div[2]/div/p-calendar/span/button";
	/*
	 * Currency Exchange Create Page Identifiers
	 */
	public static String currencyExchangeCreate_datePicker_xpath = "//*/button[@ng-reflect-icon='fa-calendar']";
	public static String currencyExchangeCreate_type_xpath = "//*/select[@ng-reflect-name='type']";
	public static String currencyExchangeCreate_fromCurrency_xpath = "//*/select[@ng-reflect-name='currencyFrom']";
	public static String currencyExchangeCreate_toCurrency_xpath = "//*/select[@ ng-reflect-name='currencyTo']";
	public static String currencyExchangeCreate_conversionRate_xpath = "//*/input[@type='number']";
	/*
	 * Calculation period create page identifiers
	 * Type: Day
	 */
	public static String calculationRuleCreate_name_id = "name";
	public static String calculationRuleCreate_startDatePicker_xpath = "//*[@id=\"startDate\"]/span/button";
	public static String calculationRuleCreate_endDatePicker_xpath = "//*[@id=\"endDate\"]/span/button";
	public static String calculationRuleCreate_type_xpath = "//*/p-fieldset/fieldset/div/div/div[4]/div/select";
	public static String calculationRuleCreate_description_name = "description";
	public static String calculationRuleCreate_dayrule_xpath = "//*/app-day-rule/div/div[1]/div[1]/div/select";
	public static String calculationRuleCreate_daysBeforeEvent_id = "daysBefore";
	public static String calculationRuleCreate_daysAfterEvent_id = "daysAfter";
	public static String calculationRuleCreate_includeEventDay_id = "includeEvent";
	public static String calculationRuleCreate_totalNumberofDays_id = "totalNumberOfDays";
	/*
	 * Type: Week
	 */
	public static String calculationRuleCreate_efStartDayOfWeek_id = "startDayOfWeekEP";
	public static String calculationRuleCreate_efTotalNoOfWeeks_id = "effectivePeriodLength";
	public static String calculationRuleCreate_cpStartDayOfWeek_id = "startDayOfWeekCP";
	public static String calculationRuleCreate_cpTotalNoOfWeeks_id = "calculationPeriodLength";
	public static String calculationRuleCreate_cpOffset_id = "offset";
	public static String calculationRuleCreate_cpOverlap_id = "overlapAllowed";
	/*
 	 * Type: Month
 	 */
	public static String calculationRuleCreate_monthruletype_xpath = "//*/app-month-rule/div/div[1]/div[1]/div/select";
	public static String calculationRuleCreate_noOfMonthsForEffPeriod_id = "effectiveAmount";
	public static String calculationRuleCreate_epStartDayOfMonth_id = "effectiveStart";
	public static String calculationRuleCreate_noOfMonthsForCalPeriod_id = "calculationAmount";
	public static String calculationRuleCreate_cpStartDayOfMonth_id = "calculationStart";
	/*
	 * Calculation period List page identifiers
	 */

	public static String calculationRuleList_searchByName_xpath = "//*/th[4]/input";
	public static String calculationRuleList_searchByType_xpath = "//*/th[5]/input";
	public static String calculationRuleList_searchByDescrp_xpath = "//*/th[6]/input";

	public static String calculationRuleList_nameColumn_xpath = "//*/table/tbody/tr[*]/td[4]";
	public static String calculationRuleList_typeColumn_xpath = "//*/table/tbody/tr[*]/td[5]";
	public static String calculationRuleList_descpColumn_xpath = "//*/table/tbody/tr[*]/td[6]";
}
