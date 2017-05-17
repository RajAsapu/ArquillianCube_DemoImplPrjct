Feature: Calculation Rule - Create page

  Acceptance Criteria: User shall be able to create a calculation rule under calculation period.

  Background:
    Given  the user has logged into the pricing application

  @Intg
  Scenario Outline: To verify if the user is able to view an error when end date is earlier than start date.
    Given  the user has navigated to the "Create" page under the "Calculation_Rule"
    When   the user clicks on the add new rule button
    And    the user enters name  as "<name>"
    And    set the start date as "<startDate>"
    And    set the end date as "<endDate>"
    Then   the application displays an error message as "<errorMessage>"
    Examples:
      | name    | startDate  | endDate    | errorMessage                         |
      | Testing | 2017-03-31 | 2017-03-11 | End date cannot be before start date |
