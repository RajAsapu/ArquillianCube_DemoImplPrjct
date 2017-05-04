@PageObjects
Feature: Workbook List Page

  Acceptance Criteria: User shall be able to filter the workbook configurations from list page.

  Background:
    Given   the user has logged into the pricing application

  Scenario Outline: To verify if the user is able to create a workbook configuration by clicking on the add new workbook configuration button.
    Given the user has navigated to the "List" page under the "Workbook"
    When  click on add new workbook configuration
    And   name is set to "<name>"
    And   description is set to "<description>"
    And   formula type is set to "<formulaType>"
    And   segment type is set to "<segmentType>"
    And   has a default value
    And   set the default value to "<defaultValue>"
    And   select below attributes
      | Supplier      |
      | Supplier Site |
    And   clicks on the submit button
    Then  the workbook configuration should be created

    Examples:
      | name   | description | formulaType | segmentType | defaultValue |
      | Test01 | for testing | PRICE       | LAND        | 20           |

  Scenario Outline: To verify if the user is able to filter the work book configuration list using name,description,formulaType and segment.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user filters workbook configuration list using "<filter>"
    Then  the list workbook configuration list should be filtered with the "<filter>" as "firstWorkbook"
    Examples:
      | filter      |
      | name        |
      | description |
      | formulaType |
      | segment     |

