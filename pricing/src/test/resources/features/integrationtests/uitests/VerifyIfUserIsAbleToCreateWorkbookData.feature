Feature: Workbook Data Page

  Acceptance Criteria: User shall be able to create a workbook data for a workbook configuration.

  Background:
    Given   the user has logged into the pricing application

  Scenario Outline: To verify that the user is not allowed to create workbook data with end date before todays date.
    Given the user has navigated to the "List" page under the "Workbook"
    And   clicked on "data"
    When  clicked on "add new data"
    And   set the start date for data as "<startDate>"
    And   set the end date for data as "<endDate>"
    Then  the application displays an error message as "End date cannot be before todays date."
    Examples:
      | startDate         | endDate           |
      | 03-May-2017 10:23 | 02-May-2017 10:39 |
      | 04-May-2017 10:23 | 03-May-2017 10:39 |

  Scenario Outline: To verify that the user is not allowed to update workbook data with end date before todays date.
    Given the user has navigated to the "List" page under the "Workbook"
    And   clicked on "data"
    When  clicked on "update"
    And   set the end date for data as "<endDate>"
    Then  the application displays an error message as "End date cannot be before todays date."
    Examples:
      | endDate           |
      | 02-May-2017 10:39 |
      | 03-May-2017 10:39 |