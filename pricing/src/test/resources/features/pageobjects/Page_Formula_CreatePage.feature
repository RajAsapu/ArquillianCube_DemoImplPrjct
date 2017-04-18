@PageObjects
Feature: To verify if the user is able to create formula.

  Acceptance Criteria: User shall be able to create a formula.

  Background:
    Given the docker containers are running
    And   the user has logged into the pricing application

  Scenario Outline: To verify if the user is able to access the list page.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "<name>"
    And   description as "<description>"
    And   set type as "<type>"
    And   expression as "<expression>"
    And   set the start date for formula as "<startDate>"
    And   set the end date for formula as "<endDate>"
    And   set the rounding mode as "<roundingMode>"
    And   set the rounding precision to "<roundingPrecision>"
    And   enter the details for the paramters
      | <expression> | <paramType> | <indexType> | <indexPoint> | <indexName> | <calculationPeriod> |
    And   validate the expression and click on Create
    Then  the formula should be created
    Examples:
      | name    | description | type | expression | startDate  | endDate    | roundingMode | roundingPrecision | paramType | indexType | indexPoint | indexName        | calculationPeriod |
      | TestOne | for testing | COST | Test       | 2017-03-27 | 2017-04-27 | Round Up     | 3                 | Index     | Argus     | Mid        | NY RBOB Prem Brg | Testing           |