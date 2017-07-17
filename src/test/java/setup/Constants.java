package setup;

public class Constants {
    /*
     * Enterprise pricing identifiers
     */
    /*
     * Environments
     */
    public final static String ENV_KEY = "ENV";
    public final static String TEST_ENV   = "Test";
    public final static String DEV_ENV    = "Dev";
    public final static String DOCKER_ENV = "Docker";
    /*
     * Default user id and password
	 */
    public static String username = "cxl_admin1";
    public static String password = "Welcome01";
    /*
     * Login page identifiers
     */
    public static String login_username_xpath = "//*[@id='username']";
    public static String login_password_xpath = "//*[@id='password']";
    public static String login_submit_xpath = "//*[@id='kc-login']";
    /*
     * Index : List page identifiers
     */
    public static String indexList_startdate_xpath = "//input[@placeholder='DD-MMM-YYYY']";
    public static String indexList_status_xpath = ".//*[@id='content']/calc-rule-list/div/div/div/p-panel/div/div[2]/div/form/div/div[1]/div[3]/div/p-dropdown/div/label";
    public static String indexList_startdatepicker_xpath = ".//*[@id='content']/calc-rule-list/div/div/div/p-panel/div/div[2]/div/form/div/div[1]/div[1]/div/p-calendar/span/button";
    public static String indexList_StartDateColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[13]";
    public static String indexList_enddatepicker_xapth = ".//*[@id='content']/calc-rule-list/div/div/div/p-panel/div/div[2]/div/form/div/div[1]/div[2]/div/p-calendar/span/button";
    public static String indexList_endDate_xpath = ".//*[@id='content']/calc-rule-list/div/div/div/p-panel/div/div[2]/div/form/div/div[1]/div[2]/div/p-calendar/span/input";
    public static String indexList_EndDateColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[14]";
    public static String indexList_search_xpath = "//button[@label='Search']";
    public static String indexList_StatusColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[15]";
    public static String indexList_type_xpath = ".//*[@id='content']/calc-rule-list/div/div/div/p-panel/div/div[2]/div/form/div/div[2]/div[1]/div/p-dropdown/div/label";
    public static String indexList_rateBasis_xpath = ".//*[@id='content']/calc-rule-list/div/div/div/p-panel/div/div[2]/div/form/div/div[2]/div[2]/div/p-dropdown/div/label";
    public static String indexList_name_xpath = "//*/p-autocomplete/span/input";
    public static String indexList_currency_xpath = ".//*[@id='content']/calc-rule-list/div/div/div/p-panel/div/div[2]/div/form/div/div[2]/div[4]/div/select";
    public static String indexList_uom_xpath = ".//*[@id='content']/calc-rule-list/div/div/div/p-panel/div/div[2]/div/form/div/div[2]/div[5]/div/select";
    public static String indexList_typeColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[3]";
    public static String indexList_name_autofill_xpath = "//*/calc-rule-list/div/div/div/div[2]/form/p-fieldset/fieldset/div/div/div[2]/div[3]/div/p-autocomplete/span/div/ul/li";
    public static String indexList_ratebasisColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[6]";
    public static String indexList_nameColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[2]";
    public static String indexList_currencyColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[4]";
    public static String indexList_uomColumn_xpath = "//*/p-datatable/div/div[2]/table/tbody/tr[*]/td[5]";
    public static String indexList_addNewIndex_xpath = "//button[normalize-space()='Add Index']";
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
    public static String indexCreate_startDatePicker_xpath = ".//*[@id='content']/app-index/div/div/div/div/div/div/form/div[2]/div/p-calendar/span/button";
    public static String indexCreate_startDate_xpath = ".//*[@id='content']/app-index/div/div/div/div/div/div/form/div[2]/div/p-calendar/span/input";
    public static String indexCreate_endDatePicker_xpath = ".//*[@id='content']/app-index/div/div/div/div/div/div/form/div[3]/div/p-calendar/span/button";
    public static String indexCreate_endDate_xpath = ".//*[@id='content']/app-index/div/div/div/div/div/div/form/div[3]/div/p-calendar/span/input";
    public static String indexCreate_currency_xpath = ".//*[@id='content']/app-index/div/div/div/div/div/div/form/div[4]/div/select";
    public static String indexCreate_uom_xpath = ".//*[@id='content']/app-index/div/div/div/div/div/div/form/div[5]/div/select";
    public static String indexCreate_comment_xpath = ".//*[@id='txtDesc']";
    public static String indexCreate_submit_xpath = "//*/button[normalize-space()='Submit']";
    public static String indexCreate_search_xpath = "//button[@ng-reflect-label='Search']";
    public static String indexCreate_priceBreak_xpath = "//*/input[@type='number']";
    public static String indexCreate_addScale_xpath = "//*/button[normalize-space()='Add Scale']";
    public static String indexCreate_fromScale_xpath = "//input[@formcontrolname='from']";
    public static String indexCreate_toScale_xpath = "//input[@formcontrolname='to']";
    public static String indexCreate_rateScale_xpath = "//input[@formcontrolname='rate']";
    public static String indexCreate_rateBasis_xpath = ".//*[@id='content']/app-index/div/div/div/div/div/div/form/div[6]/div/select";

    /*
     * Currency Exchange List IPage Identifiers Filters on search
     */
    public static String currencyExchangeList_type_xpath = "//*/select[@ng-reflect-name='type']";
    public static String currencyExchangeList_status_xpath = "//*/select[@ng-reflect-name='status']";
    public static String currencyExchangeList_convType_xpath = "//*/select[@ng-reflect-name='conversionType']";
    public static String currencyExchangeList_currForm_xpath = "//*/select[@ng-reflect-name='currencyFrom']";
    public static String currencyExchangeList_currTo_xpath = "//*/select[@ng-reflect-name='currencyTo']";
    public static String currencyExchangeList_search_xpath = "//button[@label='Search']";
    public static String currencyExchangeList_addCurrencyExchange_xpath = "//button[normalize-space()='Add Currency Exchange Rate']";
    public static String currencyExchangeList_copyCurrencyExchange_xpath = "//button[normalize-space()='Copy Currency Exchange Rate']";
    public static String currencyExchangeList_deactivateCurrencyExchange_xpath = "//button[normalize-space()='Deactivate Currency Exchange Rate']";

    /*
     * Currency Exchange List IPage Identifiers Currency Exchange table column
     * identifiers
     */
    public static String currencyExchangeList_statusColumn_xpath = "//table/tbody/tr[*]/td[7]";
    public static String currencyExchangeList_fromColumn_xpath = "//table/tbody/tr[*]/td[2]";
    public static String currencyExchangeList_toColumn_xpath = "//table/tbody/tr[*]/td[3]";
    public static String currencyExchangeList_dateColumn_xpath = "//table/tbody/tr[*]/td[6]";
    public static String currencyExchangeList_rateTypeColumn_xpath = "//table/tbody/tr[*]/td[4]";
    public static String currencyExchangeList_rateColumn_xpath = "//table/tbody/tr[*]/td[5]";
    public static String currencyExchangeList_startDatePicker_xpath = "//div/div[1]/div[2]/div/p-calendar/span/button";
    public static String currencyExchangeList_endDatePicker_xpath = "//div[1]/div[3]/div/div/p-calendar/span/button";
    public static String currencyExchangeList_columnHdrList_xpath = "//table/thead/tr/th[*]";

    /*
     * Currency Exchange Create IPage Identifiers
     */
    public static String currencyExchangeCreate_datePicker_xpath = "//*/button[@ng-reflect-icon='fa-calendar']";
    public static String currencyExchangeCreate_type_xpath = ".//*[@id='content']/app-currency-exchange/div/div/div/div/form/div[1]/div[2]/div/p-dropdown/div/label";
    public static String currencyExchangeCreate_fromCurrency_xpath = ".//*[@id='content']/app-currency-exchange/div/div/div/div/form/div[1]/div[3]/div/p-dropdown/div/label";
    public static String currencyExchangeCreate_toCurrency_xpath = ".//*[@id='content']/app-currency-exchange/div/div/div/div/form/div[1]/div[4]/div/p-dropdown/div/label";
    public static String currencyExchangeCreate_conversionRate_xpath = "//*/input[@type='number']";
    public static String currencyExchangeCreate_startDate_xpath = "//*[@id='content']/app-currency-exchange/div/div/div/div/form/div[1]/div[1]/div/p-calendar/span/input";
    /*
     * Calculation period create page identifiers
     * Type: Day
     */
    public static String calculationRuleCreate_name_xpath = "//input[@ng-reflect-name='name']";
    public static String calculationRuleCreate_startDatePicker_xpath = ".//*[@id='content']/ng-component/div/div/form/div[2]/div[1]/div[1]/div[1]/div/div/div[2]/div[2]/p-calendar/span/button";
    public static String calculationRuleCreate_endDatePicker_xpath = ".//*[@id='endDate']/span/button";
    public static String calculationRuleCreate_endDate_xpath = ".//*[@id='endDate']/span/input";
    public static String calculationRuleCreate_type_xpath = ".//*[@id='type']/div/label";
    public static String calculationRuleCreate_description_xpath = ".//*[@id='description']";
    public static String calculationRuleCreate_dayrule_xpath = ".//*[@id='content']/ng-component/div/div/form/div[2]/div[1]/div[2]/div/div/div/div/span/app-day-rule/div/div[1]/div/select";
    public static String calculationRuleCreate_daysBeforeEvent_xpath = ".//*[@id='daysBefore']";
    public static String calculationRuleCreate_daysAfterEvent_xpath = ".//*[@id='daysAfter']";
    public static String calculationRuleCreate_includeEventDay_xpath = ".//*[@id='includeEvent']";
    public static String calculationRuleCreate_totalNumberofDays_xpath = ".//*[@id='totalNumberOfDays']";
    public static String calculationRuleCreate_testDate_xpath = ".//*[@id='testDate']/span/input";
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
    public static String calculationRuleList_searchByName_xpath = "//tr/th[2]/input";
    public static String calculationRuleList_searchByType_xpath = "//tr/th[4]/input";
    public static String calculationRuleList_searchByDescrp_xpath = "//tr/th[3]/input";
    public static String calculationRuleList_statusColumn_xpath = "//table/tbody/tr[*]/td[7]";
    public static String calculationRuleList_nameColumn_xpath = "//table/tbody/tr[*]/td[2]";
    public static String calculationRuleList_typeColumn_xpath = "//table/tbody/tr[*]/td[4]";
    public static String calculationRuleList_descpColumn_xpath = "//table/tbody/tr[*]/td[3]";
    public static String calculationRuleList_addNewRule_xpath = "//button[normalize-space()='Add Rule']";
    public static String calculationRuleList_hdrStatusColumn_xpath = "//table/thead/tr/th[7]";
    public static String calculationRuleList_hdrNameColumn_xpath = "//table/thead/tr/th[2]";
    public static String calculationRuleList_hdrTypeColumn_xpath = "//table/thead/tr/th[4]";
    public static String calculationRuleList_hdrDescrpColumn_xpath = "//table/thead/tr/th[3]";
    public static String calculationRuleList_actionView_xpath = "//button[normalize-space()='View Rule']";
    public static String calculationRuleList_actionDeactivate_xpath = "//button[normalize-space()='Deactivate Rule']";
    public static String calculationRuleList_actionEdit_xpath = "//button[normalize-space()='Edit Rule']";
    /*
     * Formula - List page identifiers
     */
    public static String formulaList_filter_xpath = "//*/button[normalize-space()='Filter']";
    public static String formulaList_nameFilter_xpath = "//table/thead/tr/th[2]/input";
    public static String formulaList_descrpFilter_xpath = "//table/thead/tr/th[3]/input";
    public static String formulaList_typeFilter_xpath = "//table/thead/tr/th[4]/input";
    public static String formulaList_exprsFilter_xpath = "//table/thead/tr/th[5]/input";
    public static String formulaList_startDateFilter_xpath = "//table/thead/tr/th[8]/input";
    public static String formulaList_endDateFilter_xpath = "//table/thead/tr/th[9]/input";
    public static String formulaList_roundPrecsFilter_xpath = "//table/thead/tr/th[7]/input";
    public static String formulaList_roundModeFilter_xpath = "//table/thead/tr/th[6]/input";
    /*
     * Actions
     */
    public static String formulaList_viewAction_xpath = "//button[normalize-space()='View Formula']";
    public static String formulaList_copyAction_xpath = "//button[normalize-space()='Copy Formula']";
    public static String formulaList_editAction_xpath = "//button[normalize-space()='Edit Formula']";
    public static String formulaList_deactivateAction_xpath = "//button[normalize-space()='Deactivate Formula']";
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
    public static String formulaList_nameColumn_xpath = "//div/div[2]/table/tbody/tr[*]/td[2]";
    public static String formulaList_descrpColumn_xpath = "//div/div[2]/table/tbody/tr[*]/td[3]";
    public static String formulaList_typeColumn_xpath = "//div/div[2]/table/tbody/tr[*]/td[4]";
    public static String formulaList_expressionColumn_xpath = "//div/div[2]/table/tbody/tr[*]/td[5]";
    public static String formulaList_startDateColumn_xpath = "//div/div[2]/table/tbody/tr[*]/td[8]";
    public static String formulaList_endDateColumn_xpath = "//div/div[2]/table/tbody/tr[*]/td[9]";
    public static String formulaList_statusColumn_xpath = "//div/div[2]/table/tbody/tr[*]/td[10]";
    public static String formulaList_roundingPrecisionColumn_xpath = "//div/div[2]/table/tbody/tr[*]/td[7]";
    public static String formulaList_roundingMode_xpath = "//div/div[2]/table/tbody/tr[*]/td[6]";
    /*
     * Formula - Create page
     */
    public static String formulaCreate_name_xpath = "//*/input[@ng-reflect-name='name']";
    public static String formulaCreate_description_xpath = "//*/input[@ng-reflect-name='description']";
    public static String formulaCreate_typeSelect_xpath = ".//*[@id='type']/div/label";
    public static String formulaCreate_typeList_xpath = "//*/p-dropdown/div/div[4]/div/ul/li[*]";
    public static String formulaCreate_expression_xpath = "//*/input[@ng-reflect-name='expression']";
    public static String formulaCreate_validateExpre_xpath = "//*/button[normalize-space()='Validate Expression']";
    public static String formulaCreate_startDatePicker_xpath = "//*/button[@ng-reflect-icon='fa-calendar']";
    public static String formulaCreate_startDate_xpath = ".//*[@id='content']/app-update-formula/div/div/div/div/form/form/div/div[1]/div[5]/div[2]/p-calendar/span/input";
    public static String formulaCreate_endDatePicker_xpath = ".//*[@id='content']/app-update-formula/div/div/div/div/form/form/div/div[1]/div[6]/div[2]/p-calendar/span/button";
    public static String formulaCreate_endDate_xpath = ".//*[@id='content']/app-update-formula/div/div/div/div/form/form/div/div[1]/div[6]/div[2]/p-calendar/span/input";
    public static String formulaCreate_roundingMode_xpath = ".//*[@id='content']/app-update-formula/div/div/div/div/form/form/div/div[1]/div[7]/div[2]/p-dropdown/div/label";
    public static String formulaCreate_roundingModeList_xpath = "//*/p-dropdown/div/div[4]/div/ul/li[*]";
    public static String formulaCreate_roundingPrecision_xpath = ".//*[@id='content']/app-update-formula/div/div/div/div/form/form/div/div[1]/div[8]/div[2]/input";
    public static String formulaCreate_addParameter_xpath = "//*/button[normalize-space()='Add Parameter']";
    public static String formulaCreate_nameParameter_xpath = "//*/div/form/div[1]/input";
    public static String formulaCreate_typeParameter_xpath = "//*/form/div[2]/p-dropdown/div";
    public static String formulaCreate_indexTypeParameter_xpath = "//*/formula-params/form/div/form/div[3]/div/div[1]/p-dropdown/div";
    public static String formulaCreate_indexPointParameter_xpath = "//*/formula-params/form/div/form/div[3]/div/div[2]/p-dropdown/div";
    public static String formulaCreate_indexNameParameter_xpath = "//*/div/div[3]/p-autocomplete/span/input";
    public static String formulaCreate_calculationPeriodParamater_xpath = "//*/div/div[4]/p-autocomplete/span/input";
    public static String formulaCreate_submit_xpath = "//*/button[normalize-space()='Submit']";
    public static String formulaCreate_cancel_xpath = "//*/button[normalize-space()='Cancel']";
    public static String formulaCreate_errorMessageValidate_xpath = "//*[normalize-space()='formula parameters do not match expression']";
    public static String formulaCreate_workBookName_xpath = "//*/formula-params/form/div[*]/form/div[3]/div/div[1]/p-autocomplete/span/input";
    public static String formulaCreate_supplier_xpath = "//formula-params/form/div[*]/form/div[3]/div/div[2]/p-autocomplete/span/input";
    public static String formulaCreate_location_xpath = "//formula-params/form/div[*]/form/div[3]/div/div[3]/p-autocomplete/span/input";
    public static String formulaCreate_product_xpath = "//formula-params/form/div[*]/form/div[3]/div/div[4]/p-autocomplete/span/input";
    /*
     * Workbook - Create page
     */
    public static String workbookCreate_name_xpath = ".//*[@id='txtName']";
    public static String workbookCreate_description_xpath = ".//*[@id='txtDesc']";
    public static String workbookCreate_formulaType_xpath = ".//select[@formcontrolname='formulaType']";
    public static String workbookCreate_segmentType_xpath = ".//select[@formcontrolname='segmentType']";
    public static String workbookCreate_defaultValueYes_xpath = ".//*[@id='chboxDefaultVal']/div/div[2]/span";
    public static String workbookCreate_defaultValue_xpath = ".//*[@id='numbDefaultVal']";
    public static String workbookCreate_defaultValueNo_xpath = ".//*[@id='chboxDefaultVal']/div/div[1]/span";
    public static String workbookCreate_addSingleAttribute = "//button[@icon='fa-angle-right']";
    public static String workbookCreate_addMultipleAttributes = "//button[@icon='fa-angle-double-right']";
    public static String workbookCreate_removeSingleAttributes = "//button[@icon='fa-angle-left']";
    public static String workbookCreate_removeMultipleAttributes = "//button[@icon='fa-angle-double-left']";
    public static String workbookCreate_attribute_xpath = "//li[@class='ui-picklist-item']/div";
    public static String workbookCreate_attributeListView_xpath = "//p-picklist/div/div[2]/ul";
    public static String workbookCreate_attributeListViewDefinition_xpath = "//ul[@class='ui-datalist-data']/li[*]/div/div";
    /*
     * Workbook - View definition
     */
    public static String workbookView_formulaType_xpath = ".//*[@id='content']/app-workbook/div/div/div/div/form/div/div[1]/div[4]/div/input";
    public static String workbookView_segmentType_xpath = ".//*[@id='content']/app-workbook/div/div/div/div/form/div/div[1]/div[5]/div/input";

    /*
     * Workbook - List page
     */
    public static String workbookList_addNewWorkbookConfig_xpath = "//*/button[normalize-space()='Add Workbook Configuration']";
    public static String workbookList_nameFilter_xpath = "//thead/tr/th[2]/input";
    public static String workbookList_descrpFilter_xpath = "//thead/tr/th[3]/input";
    public static String workbookList_formulaTypeFilter_xpath = "//thead/tr/th[4]/input";
    public static String workbookList_segmentFilter_xpath = "//thead/tr/th[5]/p-dropdown/div/label";
    public static String workbookList_viewWorkBookConfiguration_xpath = "//*/button[normalize-space()='View Workbook Configuration']";
    public static String workbookList_manageData_xpath = "//*/button[normalize-space()='Manage Data']";
    public static String workbookList_nextPage_xpath = "//*[@id='content']/ng-component/div/div/div/div/div/div/p-datatable/div/p-paginator/div/a[3]";
    public static String workbookList_noOfPages_xpath = "//p-paginator/div/span/a";
    public static String workbookList_radioButtonColumn_xpath = "//p-dtradiobutton/div/div[2]";
    public static String workbookList_nameColumn_xpath = "//*[@id='content']/ng-component/div/div/div/div/div/div/p-datatable/div/div[2]/table/tbody/tr[*]/td[2]";
    public static String workbookList_descrptionColumn_xpath = "//*[@id='content']/ng-component/div/div/div/div/div/div/p-datatable/div/div[2]/table/tbody/tr[*]/td[3]";
    public static String workbookList_formulaTypeColumn_xpath = "//*[@id='content']/ng-component/div/div/div/div/div/div/p-datatable/div/div[2]/table/tbody/tr[*]/td[4]";
    public static String workbookList_segmentColumn_xpath = "//*[@id='content']/ng-component/div/div/div/div/div/div/p-datatable/div/div[2]/table/tbody/tr[*]/td[5]";
    public static String workbookList_segmentListWe_xpath = "//p-dropdown/div/div[4]/div/ul/li[*]";
    /*
     * Workbook - Data page
     */
    public static String workbookData_radioButtonColumn_xpath = ".//*[@id='content']/workbook-data/div/div/div/div/p-datatable/div/div[1]/div/div[2]/div/table/tbody/tr[*]/td[2]/p-dtradiobutton";
    public static String workbookData_priceBasisColumn_xpath = ".//*[@id='content']/workbook-data/div/div/div/div/p-datatable/div/div[1]/table/tbody/tr[*]/td[3]";
    public static String workbookData_currencyColumn_xpath = ".//*[@id='content']/workbook-data/div/div/div/div/p-datatable/div/div[1]/table/tbody/tr[*]/td[4]";
    public static String workbookData_uomColumn_xpath = ".//*[@id='content']/workbook-data/div/div/div/div/p-datatable/div/div[1]/table/tbody/tr[*]/td[5]";
    public static String workbookData_startDateColumn_xpath = ".//*[@id='content']/workbook-data/div/div/div/div/p-datatable/div/div[1]/table/tbody/tr[*]/td[6]";
    public static String workbookData_endDateColumn_xpath = ".//*[@id='content']/workbook-data/div/div/div/div/p-datatable/div/div[1]/table/tbody/tr[*]/td[7]";
    public static String workbookData_dataCommon_xpath = ".//*[@id='content']/workbook-data/div/div/div/div/p-datatable/div/div[1]/div/div[2]/div/table/tbody/tr[*]/td[(placeHolder)]";
    public static String workbookData_statusColumn_xpath = ".//*[@id='content']/workbook-data/div/div/div/div/p-datatable/div/div[1]/table/tbody/tr[*]/td[8]";
    public static String workbookData_amountColumn_xpath = ".//*[@id='content']/workbook-data/div/div/div/div/p-datatable/div/div[1]/table/tbody/tr[*]/td[9]";
    public static String workbookData_scaleRatesColumn_xpath = ".//*[@id='content']/workbook-data/div/div/div/div/p-datatable/div/div[1]/table/tbody/tr[*]/td[10]";
    public static String workbookData_editDataAction_xpath = "//*/button[normalize-space()='Edit Data']";
    public static String workbookData_deActivateAction_xpath = "//*/button[normalize-space()='Deactivate Data']";
    public static String workbookData_addDataAction_xpath = "//*/button[normalize-space()='Add Data']";
    public static String workbookData_upload_xpath = "//*/button[normalize-space()='Upload']";
    public static String workbookData_search_xpath = "//*/button[normalize-space()='Search']";
    public static String workbookData_chooseFile_xpath = "//button[@ng-reflect-label='Choose']";
    public static String workbookData_headerList_xpath = "//p-datatable/div/div[1]/div/div[1]/div/table/thead/tr/th[*]";
    public static String workbookData_deActivateConfirmationYes_xpath = "//*/button[normalize-space()='Yes']";
    /*
     * Add new workbook data
     */
    public static String workbookData_addNewDataEndDate_xpath = "//div[2]/div[2]/div/p-calendar/span/input";
    public static String workbookData_addNewDataStartDate_xpath = "//div[2]/div[1]/div/p-calendar/span/input";
    public static String workbookData_addNewDataCurrencyCode_xpath = "//select[@formcontrolname='currencyCode']";
    public static String workbookData_addNewDataAmount_xpath = "//input[@formcontrolname='amount']";
    public static String workbookData_addNewDataPriceBasis_xpath = ".//*[@id='priceBasis']/div/div/select";
    public static String workbookData_addNewDataUom_xpath = "//select[@formcontrolname='uomCode']";
    public static String workbookData_addNewDataCommon_xpath = "//p-autocomplete[@ng-reflect-name='ToBeReplaced']/span/input";
    public static String workbookData_addNewDataSupplier_xpath = "//p-autocomplete[@ng-reflect-name='supplier']/span/input";
    public static String workbookData_addNewDataSupplierSite_xpath = "//p-autocomplete[@ng-reflect-name='supplierSite']/span/input";
    public static String workbookData_addNewDataLocation_xpath = "//p-autocomplete[@ng-reflect-name='location']/span/input";
    public static String workbookData_addNewDataItem_xpath = "//p-autocomplete[@ng-reflect-name='item']/span/input";
    public static String workbookData_addNewDataHauler_xpath = "//p-autocomplete[@ng-reflect-name='hauler']/span/input";
    public static String workbookData_addNewDataCustomershipto_xpath = "//p-dropdown[@ng-reflect-name='customerShipTo']/div/label";
    public static String workbookData_addNewDataCustomer_xpath = "//p-dropdown[@ng-reflect-name='customer']/div/label";
    public static String workbookData_addNewDataFbo_xpath = "//p-dropdown[@ng-reflect-name='fbo']/div/label";
    public static String workbookData_save_xpath = "//*/button[normalize-space()='Save']";
    public static String workbookData_searchInDropdown_xpath = "//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']";
    /*
     * Search workbook data
     */
    public static String workbookData_searchStatus_xpath = "//select[@ng-reflect-name='status']";
}


