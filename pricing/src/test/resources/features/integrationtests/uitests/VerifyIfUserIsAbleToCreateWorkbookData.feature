Feature: Workbook Data Page

  Acceptance Criteria: User shall be able to create a workbook data for a workbook configuration.

  Background:
    Given   the user has logged into the pricing application
  # Depends on test data of workbook definition
  Scenario Outline: To verify that the user is not allowed to create workbook data with end date before todays date.
    Given the user has navigated to the "List" page under the "Workbook"
    When  clicked on "Row"
    And   clicked on "Manage Data"
    And   clicked on "Add New Data"
    And   set the start date for data as "<startDate>"
    And   set the end date for data as "<endDate>"
    Then  the application displays an error message as "End date cannot be before todays date."
    Examples:
      | startDate         | endDate           |
      | today             | yesterday         |
      | tomorrow          | today             |

  Scenario Outline: To verify that the user is not allowed to update workbook data with end date before todays date.
    Given the user has navigated to the "List" page under the "Workbook"
    When  clicked on "Row"
    And   clicked on "Manage Data"
    And   clicked on "Row"
    And   clicked on "Edit Data"
    And   set the end date for data as "<endDate>"
    Then  the application displays an error message as "End date cannot be before todays date."
    Examples:
      | endDate           |
      | 02-May-2017 10:39 |
      | 03-May-2017 10:39 |