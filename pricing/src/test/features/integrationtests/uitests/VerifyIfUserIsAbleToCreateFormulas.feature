@Intg
Feature: Formula Create page

  Acceptance Criteria: User shall be able to create a formula with  valid details.

  Background:
    Given the docker containers are running
    And   the user has logged into the pricing application

  Scenario Outline: To verify that the end date is greater than or equal to start date on formula create page.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "<name>"
    And   description as "<description>"
    And   set type as "<type>"
    And   expression as "<expression>"
    And   set the start date for formula as "<startDate>"
    And   set the end date for formula as "<endDate>"
    And   set the rounding mode as "<roundingMode>"
    And   set the rounding precision to "<roundingPrecision>"
    And   click on add parameter
    And   enter the details for the paramters
      |<expression>|<paramType>|<indexType>|<indexPoint>|<indexName>|<calculationPeriod>|
    And   validate the expression and click on Create
    Then  the application displays an error message as "<errorMessage>"
    Examples:
      |name    |description  | type   | expression|  startDate   |  endDate   | roundingMode | roundingPrecision|paramType| indexType | indexPoint | indexName        | calculationPeriod | errorMessage                                |
      |TestOne | for testing | COST   | Test      |  TOMORROW    |  TODAY     | Round Up     | 3                | Index   | Argus     | Mid        | NY RBOB Prem Brg | Testing           | End date cannot be before start date        |
      |TestOne | for testing | COST   | Test      |  2017-03-24  |  YESTERDAY | Round Up     | 3                | Index   | Argus     | Mid        | NY RBOB Prem Brg | Testing           | End date cannot be before system start date |

  Scenario Outline: To verify that the user is able to create a formula when end date is equal to start date
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "<name>"
    And   description as "<description>"
    And   set type as "<type>"
    And   expression as "<expression>"
    And   set the start date for formula as "<startDate>"
    And   set the end date for formula as "<endDate>"
    And   set the rounding mode as "<roundingMode>"
    And   set the rounding precision to "<roundingPrecision>"
    And   click on add parameter
    And   enter the details for the paramters
      |<expression>|<paramType>|<indexType>|<indexPoint>|<indexName>|<calculationPeriod>|
    And   validate the expression and click on Create
    Then  the formula should be created
    Examples:
      |name      |description  | type   | expression|  startDate   |  endDate    | roundingMode | roundingPrecision|paramType| indexType | indexPoint | indexName        | calculationPeriod |
      |TestThree | for testing | COST   | Test      |  TODAY       |  TODAY      | Round Up     | 3                | Index   | Argus     | Mid        | NY RBOB Prem Brg | Testing           |

  Scenario Outline: To verify that the user is able to create a formula with out end date and description
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "<name>"
    And   description as "<description>"
    And   set type as "<type>"
    And   expression as "<expression>"
    And   set the start date for formula as "<startDate>"
    And   set the end date for formula as "<endDate>"
    And   set the rounding mode as "<roundingMode>"
    And   set the rounding precision to "<roundingPrecision>"
    And   click on add parameter
    And   enter the details for the paramters
      |<expression>|<paramType>|<indexType>|<indexPoint>|<indexName>|<calculationPeriod>|
    And   validate the expression and click on Create
    Then  the formula should be created
    Examples:
      |name      |description  | type   | expression|  startDate   |  endDate    | roundingMode | roundingPrecision|paramType| indexType | indexPoint | indexName        | calculationPeriod |
      |TestFour  |             | COST   | Test      |  2017-03-27  |             | Round Up     | 3                | Index   | Argus     | Mid        | NY RBOB Prem Brg | Testing           |

  Scenario: To verify that the application displays an error when validate expression is used with out value.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "TestThree"
    And   validate the expression and click on Create
    Then  the application displays an error message as "Invalid formula"

  Scenario: To verify that the application displays an error when validate expression is used with invalid value.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "TestThree"
    And   expression as "None"
    And   validate the expression and click on Create
    Then  the application displays an error message as "formula parameters do not match expression"

  Scenario Outline: To verify that the application displays an error when an invalid start date is entered.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "TestThree"
    And   set the start date for formula as "<startDate>"
    Then  the application displays an error message as "Must be in YYYY-MM-DD format."
    Examples:
      | startDate |
      | 3289923   |
      | 01-03-2017|

  Scenario Outline: To verify that the application displays an error when an invalid end date is entered.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "TestThree"
    And   set the end date for formula as "<endDate>"
    Then  the application displays an error message as "Must be in YYYY-MM-DD format."
    Examples:
    | endDate   |
    | 3289923   |
    | 01-03-2017|

  Scenario Outline: To verify if the user is able to create the formula with only required fields.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "<name>"
    And   set type as "<type>"
    And   expression as "<expression>"
    And   set the start date for formula as "<startDate>"
    And   set the rounding mode as "<roundingMode>"
    And   set the rounding precision to "<roundingPrecision>"
    And   click on add parameter
    And   enter the details for the paramters
      |<expression>|<paramType>|<indexType>|<indexPoint>|<indexName>|<calculationPeriod>|
    And   validate the expression and click on Create
    Then  the formula should be created
    Examples:
      |name    | type   | expression|  startDate   | roundingMode   | roundingPrecision|paramType| indexType | indexPoint | indexName        | calculationPeriod |
      |TestNine | COST   | Test      |  2017-03-27  | Round Half Even| 3               | Index   | Argus     | Mid        | NY RBOB Prem Brg | Testing           |
