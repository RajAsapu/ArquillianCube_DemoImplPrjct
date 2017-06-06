@SmokeTest
@CalcCreate
Feature: Create calculation rule.

  Acceptance Criteria: User shall be able to create a calculation rule.

  Background:
    Given  the user has logged into the pricing application

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
      | name      | startDate   | endDate | type | description | ruleType | daysBeforeEvent | daysAfterEvent |
      | TestCalc1 | 23-JAN-2017 | today   | Day  | for testing | Day Wrap | 2               | 2              |

  @DefectPrice767
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
      | name      | startDate   | endDate | type | description | ep_StartDate | ep_NoOfWeeks | cp_StartDate | ep_NoOfWeeks | offset |
      | TestCalc2 | 23-JAN-2017 | today   | Week | for testing | Monday       | 2            | Tuesday      | 2            | 1      |

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
      | name      | startDate   | endDate | type  | description | mn_RuleType   | ep_NoOfMonths | ep_startDayMn | noOfMonthsCalc | cp_startDayMn | offset |
      | TestCalc3 | 23-JAN-2017 | today   | Month | for testing | Single Period | 2             | 5             | 2              | 7             | 2      |