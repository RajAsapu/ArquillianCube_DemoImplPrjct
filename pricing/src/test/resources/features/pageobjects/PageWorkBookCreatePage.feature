@PageObjects
Feature: Workbook Create Page

  Acceptance Criteria: User shall be able to create a workbook configuration under workbook.

  Background:
    Given   the user has logged into the pricing application

  Scenario Outline: To verify if the user is able to create a workbook configuration by adding supplier and supplier site as the attribute.
    Given the user has navigated to the "Create" page under the "Workbook"
    When  name is set to "<name>"
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

  Scenario Outline: To verify that the user is able to create a workbook configuration by adding all the available attributes.
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

