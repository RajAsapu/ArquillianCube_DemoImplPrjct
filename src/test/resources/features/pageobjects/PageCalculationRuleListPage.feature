@PageObjects
Feature: List calculation rule.

  Acceptance Criteria: User shall be able to search from the list of calculation rule.

  Background:
    Given the user has logged into the pricing application

  Scenario Outline: To verify if the user is able to search the list by name.
    Given the user has navigated to the "List" page under the "Calculation_Rule"
    When  the user enters "<filter>" as "<nameFilter>"
    Then  the list should display the records matching the filter criteria
      | <filter> | <nameFilter> |
    Examples:
      | filter | nameFilter |
      | Name   | TestRule1  |

  Scenario Outline: To verify if the user is able to search the list by type.
    Given the user has navigated to the "List" page under the "Calculation_Rule"
    When  the user enters "<filter>" as "<typeFilter>"
    Then  the list should display the records matching the filter criteria
      | <filter> | <typeFilter> |
    Examples:
      | filter | typeFilter |
      | Type   | DAY        |

  Scenario Outline: To verify if the user is able to search the list by descrption.
    Given the user has navigated to the "List" page under the "Calculation_Rule"
    When  the user enters "<filter>" as "<descrpFilter>"
    Then  the list should display the records matching the filter criteria
      | <filter> | <descrpFilter> |
    Examples:
      | filter      | descrpFilter |
      | Description | for testing  |