@TestData
Feature: Test Data

  Background:
    Given  the user has logged into the pricing application

  # Test data :Calculation Rules

  Scenario Outline: To verify if the user is able to create a calculation rule with type as Day.
    Given the user has navigated to the "Create" page under the "Calculation_Rule"
    When  the user enters name  as "<name>"
    And    set the start date as "<startDate>"
    And    set the end date as "<endDate>"
    And    select type as "<type>"
    And    enter the description as "<description>"
    And    select the day rule type as "<ruleType>"
    And    enter days before event as "<daysBeforeEvent>"
    And    enter days after event as "<daysAfterEvent>"
    And    include event day
    Then    clicks on the submit button
    Examples:
      | name            | startDate   | endDate | type | description | ruleType | daysBeforeEvent | daysAfterEvent |
      | TestCalcDayRule | 23-JAN-2017 | today   | Day  | for testing | Day Wrap | 2               | 2              |

  Scenario Outline: To verify if the user is able to create a calculation rule with type as Week.
    Given the user has navigated to the "Create" page under the "Calculation_Rule"
    When  the user enters name  as "<name>"
    And    set the start date as "<startDate>"
    And    set the end date as "<endDate>"
    And    select type as "<type>"
    And    enter the description as "<description>"
    And    start day of the week for the effective period as "<ep_StartDate>"
    And    set number of weeks for the effective period as "<ep_NoOfWeeks>"
    And    start day of the week for the calculation period as "<cp_StartDate>"
    And    set number of weeks for the calculation period as "<ep_NoOfWeeks>"
    And    set offset as "<offset>
    And    overlap is allowed
    Then    clicks on the submit button
    Examples:
      | name             | startDate   | endDate | type | description | ep_StartDate | ep_NoOfWeeks | cp_StartDate | ep_NoOfWeeks | offset |
      | TestCalcWeekRule | 23-JAN-2017 | today   | Week | for testing | Monday       | 1            | Tuesday      | 2            | 1      |
  @TestData1
  Scenario Outline: To verify if the user is able to create a calculation rule with type as Month.
    Given the user has navigated to the "Create" page under the "Calculation_Rule"
    When  the user enters name  as "<name>"
    And    set the start date as "<startDate>"
    And    set the end date as "<endDate>"
    And    select type as "<type>"
    And    enter the description as "<description>"
    And    set month rule type as "<mn_RuleType>"
    And    number of months for effective period as "<ep_NoOfMonths>"
    And    effective start day of month as "<ep_startDayMn>"
    And    number of months for calculation period as "<noOfMonthsCalc>"
    And    set calculation start day of month as "<cp_startDayMn>"
    And    set offset as "<offset>
    Then    clicks on the submit button
    Examples:
      | name              | startDate   | endDate | type  | description | mn_RuleType   | ep_NoOfMonths | ep_startDayMn | noOfMonthsCalc | cp_startDayMn | offset |
      | TestCalcMonthRule | 23-JAN-2017 | today   | Month | for testing | Single Period | 2             | 5             | 2              | 7             | 2      |
  # Test data : Index
  Scenario Outline: To verify if the user is able to create an index.
    Given the user has navigated to the "Create" page under the "Index"
    When  the user enters rate basis as Unit
    And   <lowPrice> ,<midPrice> ,<highPrice> and <closePrice> are entered
    And   name as PREMCBOBNYCARGO
    And   start date as 12-Dec-2016 and end date as today
    And   currency as USD
    And   unit of measurement as USG
    And   comment as Created for new clients
    Then  clicks on the submit button
    Examples:
      | lowPrice | midPrice | highPrice | closePrice |
      | 10.5     | 15.8     | 30.2      | 18.8       |
    # Test data : Currency Exchange
  Scenario Outline: To verify if the user is able to search on the list page with start date as filter.
    Given the user has navigated to the "Create" page under the "Currency_Exchange"
    When  the user selects the sets the date as "<date>"
    And   sets the currency type as "<type>"
    And   sets the currencyFrom field in create page to "<currencyFrom>"
    And   sets the currencyTo field in create page to "<currencyTo>"
    And   converstion rate to "<rate>"
    Then  clicks on the submit button
    Examples:
      | date        | type    | currencyFrom | currencyTo | rate  |
      | 24-APR-2017 | Average | USD          | INR        | 63.00 |
    # Test data : Work book definition
  Scenario Outline: To verify if the user is able to create a workbook configuration by adding supplier and supplier site as the attribute.
    Given the user has navigated to the "Create" page under the "Workbook"
    When  name is set to "<name>"
    And   description is set to "<description>"
    And   formula type is set to "<formulaType>"
    And   segment type is set to "<segmentType>"
    And   has a default value
    And   set the default value to "<defaultValue>"
    And   select below attributes
      | Supplier |
      | Location |
      | Customer |
    Then   clicks on the submit button
    Examples:
      | name              | description | formulaType | segmentType | defaultValue |
      | TestWorkbook3Attr | for testing | PRICE       | LAND        | 20           |

  Scenario Outline: To verify if the user is able to create a workbook configuration by adding all the available attributes.
    Given the user has navigated to the "Create" page under the "Workbook"
    When  name is set to "<name>"
    And   description is set to "<description>"
    And   formula type is set to "<formulaType>"
    And   segment type is set to "<segmentType>"
    And   has a default value
    And   set the default value to "<defaultValue>"
    And   select all the attributes
    Then  clicks on the submit button
    Examples:
      | name                | description | formulaType | segmentType | defaultValue |
      | TestWorkbookAllAttr | for testing | PRICE       | LAND        | 20           |
    # Test data : Work book data
  Scenario Outline:To verify if the user is able to create work book data.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user clicks on manage data for a workbook with name as "TestWorkbook3Attr"
    And   clicked on "Add New Data"
    And   supplier as "<supplier>"
    And   location as "<location>"
    And   customer as "<customer>"
    And   price basis as "<priceBasis>"
    And   uom as "<uom>"
    And   set the start date for data as "<startDate>"
    And   set the end date for data as "<endDate>"
    And   set the currency for data as "<currencyCode>"
    And   set the amount for data as "<amount>"
    Then  clicks on the save button
    Examples:
      | supplier                  | location                    | customer             | priceBasis | uom | startDate         | endDate | currencyCode | amount |
      | 121 INFLIGHT CATERING LLC | MINNEAPOLIS-ANOKA CO-BLAINE | ATLAS BLUE           | Unit       | 300 | 09-May-2017 10:19 | today   | AMD          | 100    |
      | JEFFREY WEISSMAN          | HOUSTON EXECUTIVE AIRPORT   | JET FORWARD AVIATION | Unit       | 300 | 09-May-2017 10:19 | today   | AMD          | 100    |
    # Test data : Formula
  Scenario Outline: To verify if the user is able to create formula.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "<name>"
    And   description as "<description>"
    And   set type as "<type>"
    And   expression as "<expression>"
    And   set the start date for formula as "<startDate>"
    And   set the end date for formula as "<endDate>"
    And   set the rounding mode as "<roundingMode>"
    And   set the rounding precision to "<roundingPrecision>"
    And   enter the details for the paramters
      | ITest | Index | Ice | Mid | Prem CBOB NY Cargo | TestCalcDayRule |
    And   enter the details for the paramters
      | WTest | Workbook | TestWorkbookAllAttr | 121 INFLIGHT CATERING LLC | MINNEAPOLIS-FLYING CLOUD | GASOLINE |
    Then   validate the expression and click on Create
    Examples:
      | name        | description | type | expression  | startDate   | endDate | roundingMode | roundingPrecision |
      | TestFormula | for testing | COST | ITest+WTest | 12-Dec-2016 | today   | Round Up     | 3                 |