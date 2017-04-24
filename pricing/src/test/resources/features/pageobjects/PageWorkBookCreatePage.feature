@PageObjects
Feature: Workbook Create Page

  Acceptance Criteria: User shall be able to create a workbook configuration under workbook.

  Background:
    Given   the user has logged into the pricing application

  Scenario: To verify if the user is able to create a workbook configuration by adding supplier as the attribute.
    Given the user has navigated to the "Create" page under the "Workbook"
    And   wait for sometime