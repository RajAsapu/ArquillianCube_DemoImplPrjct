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
    And   enter the details for the paramters
      | <expression> | <paramType> | <indexType> | <indexPoint> | <indexName> | <calculationPeriod> |
    And   validate the expression and click on Create
    Then  the application displays an error message as "<errorMessage>"
    Examples:
      | name    | description | type | expression | startDate  | endDate   | roundingMode | roundingPrecision | paramType | indexType | indexPoint | indexName        | calculationPeriod | errorMessage                                |
      | TestOne | for testing | COST | Test       | TOMORROW   | TODAY     | Round Up     | 3                 | Index     | Argus     | Mid        | NY RBOB Prem Brg | Testing           | End date cannot be before start date        |
      | TestOne | for testing | COST | Test       | 2017-03-24 | YESTERDAY | Round Up     | 3                 | Index     | Argus     | Mid        | NY RBOB Prem Brg | Testing           | End date cannot be before system start date |

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
    And   enter the details for the paramters
      | <expression> | <paramType> | <indexType> | <indexPoint> | <indexName> | <calculationPeriod> |
    And   validate the expression and click on Create
    Then  the formula should be created
    Examples:
      | name      | description | type | expression | startDate | endDate | roundingMode | roundingPrecision | paramType | indexType | indexPoint | indexName        | calculationPeriod |
      | TestThree | for testing | COST | Test       | TODAY     | TODAY   | Round Up     | 3                 | Index     | Argus     | Mid        | NY RBOB Prem Brg | Testing           |

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
    And   enter the details for the paramters
      | <expression> | <paramType> | <indexType> | <indexPoint> | <indexName> | <calculationPeriod> |
    And   validate the expression and click on Create
    Then  the formula should be created
    Examples:
      | name     | description | type | expression | startDate  | endDate | roundingMode | roundingPrecision | paramType | indexType | indexPoint | indexName        | calculationPeriod |
      | TestFour |             | COST | Test       | 2017-03-27 |         | Round Up     | 3                 | Index     | Argus     | Mid        | NY RBOB Prem Brg | Testing           |

  Scenario: To verify that the application displays an error on validating expression with out expression and parameters.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "TestThree"
    And   validate the expression and click on Create
    Then  the application displays an error message as "Invalid formula"

  Scenario Outline: To verify that the application displays an error when invalid expression is used with parameters.
    Given the user has navigated to the "Create" page under the "Formula"
    And   click on add parameter
    And   click on add parameter
    When  expression as "<expression>"
    And   validate the expression and click on Create
    Then  the application displays an error message as "Invalid formula"
    Examples:
      | expression |
      | a!b        |
      | a@b        |
      | a#a        |
      | a$a        |
      | a&b        |
      | a=b+c      |

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
      | startDate  |
      | 3289923    |
      | 01-03-2017 |

  Scenario Outline: To verify that the application displays an error when an invalid end date is entered.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "TestThree"
    And   set the end date for formula as "<endDate>"
    Then  the application displays an error message as "Must be in YYYY-MM-DD format."
    Examples:
      | endDate    |
      | 3289923    |
      | 01-03-2017 |

  Scenario Outline: To verify if the user is able to create the formula with only required fields.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "<name>"
    And   set type as "<type>"
    And   expression as "<expression>"
    And   set the start date for formula as "<startDate>"
    And   set the rounding mode as "<roundingMode>"
    And   set the rounding precision to "<roundingPrecision>"
    And   enter the details for the paramters
      | <expression> | <paramType> | <indexType> | <indexPoint> | <indexName> | <calculationPeriod> |
    And   validate the expression and click on Create
    Then  the formula should be created
    Examples:
      | name     | type | expression | startDate  | roundingMode    | roundingPrecision | paramType | indexType | indexPoint | indexName        | calculationPeriod |
      | TestNine | COST | Test       | 2017-03-27 | Round Half Even | 3                 | Index     | Argus     | Mid        | NY RBOB Prem Brg | Testing           |

  Scenario Outline: To verify if the user is able to create the formula with valid mathematical expressions.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "<name>"
    And   set type as "<type>"
    And   expression as "<expression>"
    And   set the start date for formula as "<startDate>"
    And   set the rounding mode as "<roundingMode>"
    And   set the rounding precision to "<roundingPrecision>"
    And   enter the details for the paramters
      | Index | Index | Ice | Mid | NymexCrude1stMth | Testing |
    And   enter the details for the paramters
      | Workbook | Workbook | test1 | 121 INFLIGHT CATERING LLC | MINNEAPOLIS-FLYING CLOUD | GASOIL-AUTO |
    And   validate the expression and click on Create
    Then  the formula should be created
    Examples:
      | name    | type  | expression             | startDate  | roundingMode    | roundingPrecision |
      | TestSev | COST  | Index+Workbook         | 2017-03-27 | Round Half Even | 3                 |
      | TestEle | PRICE | Index-Workbook         | 2017-03-27 | Round Half Even | 3                 |
      | TestTwe | COST  | Index*Workbook         | 2017-03-27 | Round Half Even | 3                 |
      | TestThi | PRICE | Index/Workbook         | 2017-03-27 | Round Half Even | 3                 |
      | TestFor | COST  | Index%Workbook         | 2017-03-27 | Round Half Even | 3                 |
      | TestFif | PRICE | Index^Workbook         | 2017-03-27 | Round Half Even | 3                 |
      | TestSix | PRICE | (Index+Workbook)*Index | 2017-03-27 | Round Half Even | 3                 |


  # Bug : PRICE - 401
  Scenario Outline: To verify that the user is unable to create formula with past end and start date.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "<name>"
    And   set type as "<type>"
    And   expression as "<expression>"
    And   set the start date for formula as "<startDate>"
    And   set the end date for formula as "yesterday"
    And   set the rounding mode as "<roundingMode>"
    And   set the rounding precision to "<roundingPrecision>"
    And   enter the details for the paramters
      | <expression> | <paramType> | <indexType> | <indexPoint> | <indexName> | <calculationPeriod> |
    And   validate the expression and click on Create
    Then  the formula should not be created
    Examples:
      | name       | type | expression | startDate  | roundingMode    | roundingPrecision | paramType | indexType | indexPoint | indexName        | calculationPeriod |
      | TestPast13 | COST | Test       | 2017-03-01 | Round Half Even | 3                 | Index     | Argus     | Mid        | NY RBOB Prem Brg | Testing           |

  Scenario Outline: To verify that the user is able to create formula with end date as today.l
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "<name>"
    And   set type as "<type>"
    And   expression as "<expression>"
    And   set the start date for formula as "<startDate>"
    And   set the end date for formula as "today"
    And   set the rounding mode as "<roundingMode>"
    And   set the rounding precision to "<roundingPrecision>"
    And   enter the details for the paramters
      | <expression> | <paramType> | <indexType> | <indexPoint> | <indexName> | <calculationPeriod> |
    And   validate the expression and click on Create
    Then  the formula should be created
    Examples:
      | name       | type | expression | startDate  | roundingMode    | roundingPrecision | paramType | indexType | indexPoint | indexName        | calculationPeriod |
      | TestPast21 | COST | Test       | 2017-03-01 | Round Half Even | 3                 | Index     | Argus     | Mid        | NY RBOB Prem Brg | Testing           |

  # Bug : Price - 410
  Scenario Outline: To verify that no overlap of dates is allowed for formulas with same name.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "<name>"
    And   set type as "BOTH"
    And   expression as "Test"
    And   set the start date for formula as "<startDate>"
    And   set the end date for formula as "<endDate>"
    And   set the rounding mode as "Round Half Even"
    And   set the rounding precision to "3"
    And   enter the details for the paramters
      | Test | Index | Argus | Mid | NY RBOB Prem Brg | Testing |
    And   validate the expression and click on Create
    Then  the formula <condition> be created
    Examples:
      | name     | startDate  | endDate  | condition  |
      | Overlap1 | 2017-03-01 | today    | should     |
      | Overlap2 | 2017-03-01 | today    | should     |
      | Overlap1 | 2017-03-01 | today    | should not |
      | Overlap1 | today      | today+30 | should not |
      | Overlap1 | today+2    | today+30 | should     |
      | Overlap1 | tomorrow   | tomorrow | should     |
      | Overlap3 | tomorrow   |          | should     |
      | Overlap3 | today+365  |          | should not |
      | Overlap4 | today+60   | today+90 | should     |
      | Overlap4 | 2017-03-01 | today+59 | should     |
      | Overlap4 | today+91   |          | should     |

  # Need to add more validations once the implementation is completed
  Scenario Outline: To verify that expression has the valid formula parameters.
    Given the user has navigated to the "Create" page under the "Formula"
    When  the user creates a formula with "ValidateExprs"
    And   set type as "PRICE"
    And   expression as "<expression>"
    And   set the start date for formula as "<startDate>"
    And   set the end date for formula as "<endDate>"
    And   set the rounding mode as "Round Floor"
    And   set the rounding precision to "2"
    And   enter the details for the paramters
      | <param1> | Index | Ice | Mid | NymexCrude1stMth | Testing |
    And   enter the details for the paramters
      | <param2> | Workbook | test1 | 121 INFLIGHT CATERING LLC | MINNEAPOLIS-FLYING CLOUD | GASOIL-AUTO |
    And   validate the expression and click on Create
    Then  the formula <condition> be created
    Examples:
      | expression | param1 | param2 | condition  | startDate | endDate |
      | a          | a      | b      | should not | today     | today   |
      | a          | c      | b      | should not | today+1   | today+1 |
      | A+B        | a      | b      | should     | today+2   | today+2 |
      | a+b        | b      | a      | should     | today+3   | today+3 |
      | a+c        | b      | c      | should not | today+4   | today+4 |





