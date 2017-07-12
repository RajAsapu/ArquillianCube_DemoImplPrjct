@SmokeTest
@WorkBookList
Feature: Workbook List Page

  Acceptance Criteria: User shall be able to filter the workbook configurations from list page.

  Background:
    Given   the user has logged into the pricing application

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

