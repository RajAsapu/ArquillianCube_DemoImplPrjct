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
    And    clicks on the submit button
    Then   the calculation rule should be created
      | <name> | <startDate> | <endDate> | <type> | <description> | <ruleType> | <daysBeforeEvent> | <daysAfterEvent> |
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
    And    clicks on the submit button
    Then   the calculation rule should be created
      | <name> | <startDate> | <endDate> | <type> | <description> | <ep_StartDate> | <ep_NoOfWeeks> | <cp_StartDate> | <ep_NoOfWeeks> | <offset> |
    Examples:
      | name             | startDate   | endDate | type | description | ep_StartDate | ep_NoOfWeeks | cp_StartDate | ep_NoOfWeeks | offset |
      | TestCalcWeekRule | 23-JAN-2017 |         | Week | for testing | Monday       | 1            | Tuesday      | 2            | 1      |

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
    And    clicks on the submit button
    Then   the calculation rule should be created
      | <name> | <startDate> | <endDate> | <type> | <description> | <mn_RuleType> | <ep_NoOfMonths> | <ep_startDayMn> | <noOfMonthsCalc> | <cp_startDayMn> | <offset> |
    Examples:
      | name              | startDate   | endDate | type  | description | mn_RuleType   | ep_NoOfMonths | ep_startDayMn | noOfMonthsCalc | cp_startDayMn | offset |
      | TestCalcMonthRule | 23-JAN-2017 | today   | Month | for testing | Single Period | 2             | 5             | 2              | 7             | 2      |
  # Test data : Index
  Scenario Outline: To verify if the user is able to create an index.
    Given the user has navigated to the "Create" page under the "Index"
    When  the user enters rate basis as Unit
    And   <lowPrice> ,<midPrice> ,<highPrice> and <closePrice> are entered
    And   name as PREMCBOBNYCARGO
    And   start date as 12-Dec-2016 and end date as 26-May-2017
    And   currency as USD
    And   unit of measurement as USG
    And   comment as Created for new clients
    And   clicks on the submit button
    Then  the user shall be able to view the created index in the list on filtering with Price Point Scale

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
    And   clicks on the submit button
    Then  the currency exchange info should be created
      | <date> | <type> | <currencyFrom> | <currencyTo> | <rate> |
    Examples:
      | date        | type     | currencyFrom | currencyTo | rate  |
      | 24-APR-2017 | Average  | USD          | INR        | 63.00 |
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
    And   clicks on the submit button
    Then  the workbook configuration should be created

    Examples:
      | name   | description | formulaType | segmentType | defaultValue |
      | Test01 | for testing | PRICE       | LAND        | 20           |

  Scenario Outline: To verify if the user is able to create a workbook configuration by adding all the available attributes.
    Given the user has navigated to the "Create" page under the "Workbook"
    When  name is set to "<name>"
    And   description is set to "<description>"
    And   formula type is set to "<formulaType>"
    And   segment type is set to "<segmentType>"
    And   has a default value
    And   set the default value to "<defaultValue>"
    And   select all the attributes
    And   clicks on the submit button
    Then  the workbook configuration should be created

    Examples:
      | name   | description | formulaType | segmentType | defaultValue |
      | Test02 | for testing | PRICE       | LAND        | 20           |
    # Test data : Work book data
  Scenario Outline:To verify if the user is able to create work book data.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the list workbook configuration list should be filtered with the "name" as "TestData01"
    And   clicked on "Row"
    And   clicked on "Manage Data"
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
    And   clicks on the save button
    Then  the workbook data should be created
      |<supplier>|<location>|<customer>|<priceBasis>|<uom>|<startDate>|<endDate>|<currencyCode>|<amount>|
    Examples:
      | supplier         | location | customer         | priceBasis | uom | startDate         | endDate          | currencyCode | amount |
      | 101 LIMITED      | HOUSTON  | SKYCHASE / TAK   | Unit       | 300 | 09-May-2017 10:19 | 09-May-2017 10:19| AMD          | 100    |
      | JEFFREY WEISSMAN | HOUSTON  | SKYCHASE / TAK   | Unit       | 300 | 09-May-2017 10:19 | 09-May-2017 10:19| AMD          | 100    |
