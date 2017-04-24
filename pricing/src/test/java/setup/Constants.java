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
    public static String indexList_startdate_xpath = "//input[@placeholder='DD-MMM-YYYY']";
    public static String indexList_status_xpath = "//*/calc-rule-list/div/div/div/div[2]/form/p-fieldset/fieldset/div/div/div[1]/div[3]/div/select";
    public static String indexList_startdatepicker_xpath = "//*/app-root/calc-rule-list/div/div/div/div[2]/form/p-fieldset/fieldset/div/div/div[1]/div[1]/div/p-calendar/span/button";
    public static String indexList_StartDateColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[2]";
    public static String indexList_enddatepicker_xapth = "//*/app-root/calc-rule-list/div/div/div/div[2]/form/p-fieldset/fieldset/div/div/div[1]/div[2]/div/p-calendar/span/button";
    public static String indexList_endDate_xpath = ".//p-calendar[@formcontrolname='endDate']/span/input";
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
    public static String indexList_addNewIndex_xpath = "//*/a[normalize-space()='Add New Index']";
    public static String indexList_deactivateAction_xpath = "//*[@title='Deactivate']";
    public static String indexList_copyAction_xpath = "//*[@title='Copy']";
    public static String indexList_editAction_xpath = "//*[@title='Edit']";
    /*
     * Index : Create page identifiers
     */
    public static String indexCreate_name_autofill_path = "//*/div/div/div/form/p-fieldset/fieldset/div/div/div[1]/div/p-autocomplete/span/div/ul/li";
    public static String indexCreate_lowprice_xpath = "//*[@id='txtLow']";
    public static String indexCreate_midprice_xpath = "//*[@id='txtMid']";
    public static String indexCreate_highprice_xpath = "//*[@id='txtHigh']";
    public static String indexCreate_closeprice_xpath = ".//input[@type='number']";
    public static String indexCreate_startDatePicker_xpath = "//*/div/div/div[3]/div/p-calendar/span/button";
    public static String indexCreate_startDate_xpath = ".//*[@ng-reflect-name=\"startDate\"]/span/input";
    public static String indexCreate_endDatePicker_xpath = "//*/div/div/div[4]/div/p-calendar/span/button";
    public static String indexCreate_endDate_xpath = ".//*[@ng-reflect-name=\"endDate\"]/span/input";
    public static String indexCreate_currency_xpath = "//*/p-fieldset/fieldset/div/div/div[9]/div/select";
    public static String indexCreate_uom_xpath = "//*/p-fieldset/fieldset/div/div/div[10]/div/select";
    public static String indexCreate_comment_xpath = "//*[@id='txtDesc']";
    public static String indexCreate_submit_xpath = "//*/button[normalize-space()='Submit']";
    public static String indexCreate_priceBreak_xpath = "//*/input[@type='number']";
    public static String indexCreate_addScale_xpath = "//*/button[normalize-space()='Add Scale']";
    public static String indexCreate_fromScale_xpath = "//input[@formcontrolname='from']";
    public static String indexCreate_toScale_xpath = "//input[@formcontrolname='to']";
    public static String indexCreate_rateScale_xpath = "//input[@formcontrolname='rate']";

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
    public static String currencyExchangeList_endDatePicker_xpath = "//p-calendar[@formcontrolname='toDate']/span/input";
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
    public static String calculationRuleCreate_name_xpath = "//*[@id='name']";
    public static String calculationRuleCreate_startDatePicker_xpath = "//*[@id=\"startDate\"]/span/button";
    public static String calculationRuleCreate_endDatePicker_xpath = "//*[@id=\"endDate\"]/span/button";
    public static String calculationRuleCreate_endDate_xpath = "//*/p-calendar[@id='endDate']/span/input";
    public static String calculationRuleCreate_type_xpath = "//*/p-fieldset/fieldset/div/div/div[4]/div/select";
    public static String calculationRuleCreate_description_xpath = "//*[@name='description']";
    public static String calculationRuleCreate_dayrule_xpath = "//*/app-day-rule/div/div[1]/div[1]/div/select";
    public static String calculationRuleCreate_daysBeforeEvent_xpath = "//*[@id='daysBefore']";
    public static String calculationRuleCreate_daysAfterEvent_xpath = "//*[@id='daysAfter']";
    public static String calculationRuleCreate_includeEventDay_xpath = "//*[@id='includeEvent']";
    public static String calculationRuleCreate_totalNumberofDays_xpath = "//*[@id='totalNumberOfDays']";
    public static String calculationRuleCreate_testDate_xpath = "//p-calendar[@formcontrolname='testDate']/span/input";
    public static String calculationRuleCreate_testRuleButton_xpath = "//*/button[normalize-space()='Test Rule']";
    /*
     * Type: Week
     */
    public static String calculationRuleCreate_efStartDayOfWeek_xpath = "//*[@id='startDayOfWeekEP']";
    public static String calculationRuleCreate_efTotalNoOfWeeks_xpath = "//*[@id='effectivePeriodLength']";
    public static String calculationRuleCreate_cpStartDayOfWeek_xpath = "//*[@id='startDayOfWeekCP']";
    public static String calculationRuleCreate_cpTotalNoOfWeeks_xpath = "//*[@id='calculationPeriodLength']";
    public static String calculationRuleCreate_cpOffset_xpath = "//*[@id='offset']";
    public static String calculationRuleCreate_cpOverlap_xpath = "//*[@id='overlapAllowed']";
    /*
      * Type: Month
      */
    public static String calculationRuleCreate_monthruletype_xpath = "//*/app-month-rule/div/div[1]/div[1]/div/select";
    public static String calculationRuleCreate_noOfMonthsForEffPeriod_xpath = "//*[@id='effectiveAmount']";
    public static String calculationRuleCreate_epStartDayOfMonth_xpath = "//*[@id='effectiveStart']";
    public static String calculationRuleCreate_noOfMonthsForCalPeriod_xpath = "//*[@id='calculationAmount']";
    public static String calculationRuleCreate_cpStartDayOfMonth_xpath = "//*[@id='calculationStart']";
    /*
     * Calculation period List page identifiers
     */
    public static String calculationRuleList_searchByName_xpath = "//*/th[4]/input";
    public static String calculationRuleList_searchByType_xpath = "//*/th[5]/input";
    public static String calculationRuleList_searchByDescrp_xpath = "//*/th[6]/input";
    public static String calculationRuleList_statusColumn_xpath = "//*/table/tbody/tr[*]/td[1]";
    public static String calculationRuleList_nameColumn_xpath = "//*/table/tbody/tr[*]/td[4]";
    public static String calculationRuleList_typeColumn_xpath = "//*/table/tbody/tr[*]/td[5]";
    public static String calculationRuleList_descpColumn_xpath = "//*/table/tbody/tr[*]/td[6]";
    public static String calculationRuleList_addNewRule_xpath = "//a[normalize-space()='Add New Rule']";
    public static String calculationRuleList_hdrStatusColumn_xpath = "//*/tr/th[1]";
    public static String calculationRuleList_hdrNameColumn_xpath = "//*/tr/th[4]/span[2]";
    public static String calculationRuleList_hdrTypeColumn_xpath = "//*/tr/th[5]/span[2]";
    public static String calculationRuleList_hdrDescrpColumn_xpath = "//*/tr/th[6]/span[2]";
    public static String calculationRuleList_actionView_xpath = "//*/tr[*]/td[7]/span/div/button[1]";
    public static String calculationRuleList_actionInactive_xpath = "//*/tr[*]/td[7]/span/div/button[2]";
    public static String calculationRuleList_actionEdit_xpath = "//*/tr[*]/td[7]/span/div/button[3]";
    /*
     * Formula - List page identifiers
     */
    public static String formulaList_filter_xpath = "//*/button[normalize-space()='Filter']";
    public static String formulaList_nameFilter_xpath = "//*/p-datatable/div/div/table/thead/tr/th[1]/input";
    public static String formulaList_descrpFilter_xpath = "//*/p-datatable/div/div/table/thead/tr/th[2]/input";
    public static String formulaList_typeFilter_xpath = "//*/p-datatable/div/div/table/thead/tr/th[3]/input";
    public static String formulaList_exprsFilter_xpath = "//*/p-datatable/div/div/table/thead/tr/th[4]/input";
    public static String formulaList_startDateFilter_xpath = "//*/p-datatable/div/div/table/thead/tr/th[5]/input";
    public static String formulaList_endDateFilter_xpath = "//*/p-datatable/div/div/table/thead/tr/th[6]/input";
    public static String formulaList_roundPrecsFilter_xpath = "//*/p-datatable/div/div/table/thead/tr/th[8]/input";
    /*
     * Actions
     */
    public static String formulaList_viewAction_xpath = "//table/tbody/tr[*]/td[9]/span[2]/button[@title='View']";
    public static String formulaList_copyAction_xpath = "//table/tbody/tr[*]/td[9]/span[2]/button[@title='Copy']";
    public static String formulaList_editAction_xpath = "//table/tbody/tr[*]/td[9]/span[2]/button[@title='Edit']";
    public static String formulaList_deactivateAction_xpath = "//table/tbody/tr[*]/td[9]/span[2]/button[@title='Deactivate']";
    public static String formulaList_updateAction_xpath = "//button[normalize-space()='Update']";
    public static String formulaList_cancelAction_xpath = "//button[normalize-space()='Cancel']";
    public static String formulaList_status_xpath = "//span[normalize-space()='Status']";
    public static String formulaList_name_xpath = "//span[normalize-space()='Name']";
    public static String formulaList_description_xpath = "//span[normalize-space()='Description']";
    public static String formulaList_type_xpath = "//span[normalize-space()='Type']";
    public static String formulaList_expression_xpath = "//span[normalize-space()='Expression']";
    public static String formulaList_startDate_xpath = "//span[normalize-space()='Start Date']";
    public static String formulaList_endDate_xpath = "//span[normalize-space()='End Date']";
    public static String formulaList_roundingPrecision_xpath = "//span[normalize-space()='Rounding Precision']";
    /*
     * List page Column names
     */
    public static String formulaList_nameColumn_xpath = "//*/p-datatable/div/div/table/tbody/*/td[1]/span[2]";
    public static String formulaList_descrpColumn_xpath = "//*/p-datatable/div/div/table/tbody/*/td[2]";
    public static String formulaList_typeColumn_xpath = "//*/p-datatable/div/div/table/tbody/*/td[3]";
    public static String formulaList_expressionColumn_xpath = "//*/p-datatable/div/div/table/tbody/*/td[4]";
    public static String formulaList_startDateColumn_xpath = "//*/p-datatable/div/div/table/tbody/*/td[5]";
    public static String formulaList_endDateColumn_xpath = "//*/p-datatable/div/div/table/tbody/*/td[6]";
    public static String formulaList_statusColumn_xpath = "//*/p-datatable/div/div/table/tbody/*/td[7]";
    public static String formulaList_roundingPrecisionColumn_xpath = "//*/p-datatable/div/div/table/tbody/*/td[8]";
    /*
     * Formula - Create page
     */
    public static String formulaCreate_name_xpath = "//*/input[@ng-reflect-name='name']";
    public static String formulaCreate_description_xpath = "//*/input[@ng-reflect-name='description']";
    public static String formulaCreate_typeSelect_xpath = "//*/p-dropdown/div/div[1]/select";
    public static String formulaCreate_typeList_xpath = "//*/p-dropdown/div/div[4]/div/ul/li[*]";
    public static String formulaCreate_expression_xpath = "//*/input[@ng-reflect-name='expression']";
    public static String formulaCreate_validateExpre_xpath = "//*/button[normalize-space()='Validate Expression']";
    public static String formulaCreate_startDatePicker_xpath = "//*/button[@ng-reflect-icon='fa-calendar']";
    public static String formulaCreate_endDatePicker_xpath = "//*/div[2]/p-calendar/span/button";
    public static String formulaCreate_endDate_xpath = "//input[not(contains(@ng-reflect-disabled,'true')) and @placeholder='yyyy-mm-dd']";
    public static String formulaCreate_roundingMode_xpath = "//*/div[3]/div[3]/div/p-dropdown/div";
    public static String formulaCreate_roundingModeList_xpath = "//*/p-dropdown/div/div[4]/div/ul/li[*]";
    public static String formulaCreate_roundingPrecision_xpath = "//*/form/div[3]/div[4]/input";
    public static String formulaCreate_addParameter_xpath = "//*/button[normalize-space()='Add Parameter']";
    public static String formulaCreate_nameParameter_xpath = "//*/div/form/div[1]/input";
    public static String formulaCreate_typeParameter_xpath = "//*/form/div[2]/p-dropdown/div";
    public static String formulaCreate_indexTypeParameter_xpath = "//*/formula-params/form/div/form/div[3]/div/div[1]/p-dropdown/div";
    public static String formulaCreate_indexPointParameter_xpath = "//*/formula-params/form/div/form/div[3]/div/div[2]/p-dropdown/div";
    public static String formulaCreate_indexNameParameter_xpath = "//*/div/div[3]/p-autocomplete/span/input";
    public static String formulaCreate_calculationPeriodParamater_xpath = "//*/div/div[4]/p-autocomplete/span/input";
    public static String formulaCreate_create_xpath = "//*/button[normalize-space()='Create']";
    public static String formulaCreate_cancel_xpath = "//*/form/div/button[2]";
    public static String formulaCreate_errorMessageValidate_xpath = "//*[normalize-space()='formula parameters do not match expression']";
    public static String formulaCreate_workBookName_xpath = "//*/formula-params/form/div[*]/form/div[3]/div/div[1]/p-autocomplete/span/input";
    public static String formulaCreate_supplier_xpath = "//formula-params/form/div[*]/form/div[3]/div/div[2]/p-autocomplete/span/input";
    public static String formulaCreate_location_xpath = "//formula-params/form/div[*]/form/div[3]/div/div[3]/p-autocomplete/span/input";
    public static String formulaCreate_product_xpath = "//formula-params/form/div[*]/form/div[3]/div/div[4]/p-autocomplete/span/input";
}


