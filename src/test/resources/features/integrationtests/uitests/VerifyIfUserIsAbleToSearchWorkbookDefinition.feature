Feature: Workbook List Page

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
    Then  the application displays an success message as "Workbook configuration TestAddFromList created."

    Examples:
      | name            | description | formulaType | segmentType | defaultValue |
      | TestAddFromList | for testing | PRICE       | LAND        | 20           |