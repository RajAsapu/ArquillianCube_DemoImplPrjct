@Intg
Feature: List Page

  Acceptance Criteria: User shall be able to access features in the list page under formula.

  Background:
    Given the user has logged into the pricing application

  Scenario: To verify if the user is able to perform end date edit and deactivation on an active formula.
    Given the user has navigated to the "List" page under the "Formula"
    When  the user clicks on "Edit" button
    And   update the end date for formula as "next day"
    And   the user clicks on "Update" button
    Then  the formula should be updated

  # Re-Test when defect-275 is fixed
  Scenario: To verify that the user is unable to edit the inactive formula
    Given the user has navigated to the "List" page under the "Formula"
    When  the user searches the inactive record
    Then  the inactive formulas shouldn't have option to edit

 # Re-Test when defect-275 is fixed
  Scenario: To verify that the user is able to inactivate the active formula
    Given the user has navigated to the "List" page under the "Formula"
    When  the user searches the active record
    And   the user inactivates the formula
    Then  the formula should be displayed as inactive

  Scenario: To verify that the user is able to copy the active formula
    Given the user has navigated to the "List" page under the "Formula"
    When  the user searches the active record
    And   the user clicks on "Copy" button
    When  the user creates a formula with "CopyFormulas"
    And   set type as "BOTH"
    And   validate the expression and click on Create
    Then  the formula should be created

  Scenario: To verify that the user is unable to create formula with same name of when copy is made.
    Given the user has navigated to the "List" page under the "Formula"
    When  the user searches the active record
    And   the user clicks on "Copy" button
    And   set type as "BOTH"
    And   validate the expression and click on Create
    Then  the formula should not be created

  Scenario Outline: To verify that the user is able to view the active formula
    Given the user has navigated to the "List" page under the "Formula"
    When  the user searches the <status> record
    And   the user clicks on "View" button
    Then  the user shall be able to view all the formula details
    Examples:
      | status   |
      | active   |
      | inactive |

  Scenario Outline: To verify that the user has access to sort the columns in the list page.
    Given the user has navigated to the "List" page under the "Formula"
    When   the user clicks on "<header>" button
    And   the formula rules shall be displayed in ascending order
      | <header> |
    And   the user clicks on "<header>" button
    Then   the formula rules shall be displayed in descending order
      | <header> |
    Examples:
      | header             |
      | name               |
      | description        |
      | type               |
      | expression         |
      | startdate          |
      | enddate            |
      | rounding precision |
      | status             |
