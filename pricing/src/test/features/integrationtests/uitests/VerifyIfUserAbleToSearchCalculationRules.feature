Feature: Calculation Rule - List page

  Acceptance Criteria: User shall be able to access features in the List page under calculation period.

  Background:
    Given  the docker containers are running
    And    the user has logged into the pricing application

  @Intg
  Scenario Outline: To verify if the user is able to create a new rule from the list page.
    Given  the user has navigated to the "List" page under the "Calculation_Rule"
    When   the user clicks on the add new rule button
    And    the user enters name  as "<name>"
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
      |<name>|<startDate>|<endDate>|<type>|<description>|<ruleType>|<daysBeforeEvent>|<daysAfterEvent>|
    Examples:
      | name        | startDate   | endDate      | type       | description | ruleType | daysBeforeEvent | daysAfterEvent  |
      | TestRule89  | 2017-01-23  | 2017-03-30   | Day        | for testing | Day Wrap | 2               | 2               |

  Scenario Outline: To verify if the user is able to sort the list of calculation rules.
    Given  the user has navigated to the "List" page under the "Calculation_Rule"
    When   the user clicks on "<header>"
    Then   the calculation rules shall be displayed in sorted order
    |<header>|
    Examples:
    |   header    |
    | Status      |
    | Name        |
    | Type        |
    | Description |



