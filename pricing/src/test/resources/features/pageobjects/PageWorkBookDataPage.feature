@PageObjects
Feature: Workbook Data Page

  Acceptance Criteria: User shall be able to add,upload and search workbook data.

  Background:
    Given   the user has logged into the pricing application

  Scenario:To verify if the user is able to create work book data.
    Given the user has navigated to the "List" page under the "Workbook"
    And   clicked on data
    And   wait for sometime