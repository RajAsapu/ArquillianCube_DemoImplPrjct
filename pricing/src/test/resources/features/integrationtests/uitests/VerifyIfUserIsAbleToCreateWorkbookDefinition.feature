Feature: Workbook Create Page

  Acceptance Criteria: User shall be able to create a workbook configuration under workbook.

  Background:
    Given   the user has logged into the pricing application

  Scenario Outline: To verify if the user is able to create a workbook configuration by adding Item,Customer Brand and Customer as the attributes and view these in definition.
    Given the user has navigated to the "Create" page under the "Workbook"
    When  name is set to "<name>"
    And   description is set to "<description>"
    And   formula type is set to "<formulaType>"
    And   segment type is set to "<segmentType>"
    And   has a default value
    And   set the default value to "<defaultValue>"
    And   select below attributes
      | Item           |
      | Customer Brand |
      | Customer       |
    And   clicks on the submit button
    Then  the workbook configuration should be created
    And   the definition should be displayed with the below details
      | <name> | <description> | <formulaType> | <segmentType> | <defaultValue> | Item | Customer Brand | Customer |
    Examples:
      | name   | description | formulaType | segmentType | defaultValue |
      | Test07 | for testing | PRICE       | LAND        | 20           |

  # Test Data - Existing workbook definition should be used
  Scenario Outline: To verify that the user is not allowed to create duplicate workbook definitions.
    Given the user has navigated to the "Create" page under the "Workbook"
    When  name is set to "<name>"
    And   description is set to "<description>"
    And   formula type is set to "<formulaType>"
    And   segment type is set to "<segmentType>"
    And   has a default value
    And   set the default value to "<defaultValue>"
    And   select below attributes
      | Item           |
      | Customer Brand |
      | Customer       |
    And   clicks on the submit button
    Then the application displays an error message as "Workbook already exists"
    And  the workbook configuration should not be created
    Examples:
      | name   | description | formulaType | segmentType | defaultValue |
      | Test07 | for testing | PRICE       | LAND        | 20           |

  Scenario Outline: To verify that the user is allowed to create workbook definition with only required attributes.
    Given the user has navigated to the "Create" page under the "Workbook"
    When  name is set to "<name>"
    And   formula type is set to "<formulaType>"
    And   segment type is set to "<segmentType>"
    And   clicks on the submit button
    Then  the workbook configuration should be created
    Examples:
      | name   | formulaType | segmentType |
      | Test08 | PRICE       | LAND        |

  Scenario Outline: To verify that the user is not allowed to create workbook definition when name is null.
    Given the user has navigated to the "Create" page under the "Workbook"
    When  name is set to "<name>"
    And   formula type is set to "<formulaType>"
    And   segment type is set to "<segmentType>"
    And   clicks on the submit button
    Then the application displays an error message as "Name is required."
    And  the workbook configuration should not be created
    Examples:
      | name | formulaType | segmentType |
      |      | PRICE       | LAND        |
