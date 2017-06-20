@SmokeTest
@FormulaList
Feature: List Formula Page

  Acceptance Criteria: User shall be able to access features in the list page under formula.

  Background:
    Given   the user has logged into the pricing application

  Scenario Outline: To verify if the user is able to access the list page.
    Given the user has navigated to the "List" page under the "Formula"
    When  the user filters the list using "<filterType>"
    Then  the list should display the results applying the filters
      | <filterType> |
    Examples:
      | filterType         |
      | name               |
      | description        |
      | type               |
      | expression         |
      | startdate          |
      | enddate            |
      | rounding precision |

