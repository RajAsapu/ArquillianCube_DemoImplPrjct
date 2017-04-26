@PageObjects
Feature: Workbook Create Page

  Acceptance Criteria: User shall be able to create a workbook configuration under workbook.

  Background:
    Given   the user has logged into the pricing application

  Scenario Outline: To verify if the user is able to create a workbook configuration by adding supplier as the attribute.
    Given the user has navigated to the "Create" page under the "Workbook"
    When  name is set to "<name>"
    And   description is set to "<description>"
    And   formula type is set to "<formulaType>"
    And   segment type is set to "<segmentType>"
    And   has a default value
    And   set the default value to "<defaultValue>"
    And   wait for sometime

    Examples:
    | name     |  description | formulaType   |  segmentType  |  defaultValue     |
    | Test     |  for testing | PRICE         |  LAND         |  20               |

