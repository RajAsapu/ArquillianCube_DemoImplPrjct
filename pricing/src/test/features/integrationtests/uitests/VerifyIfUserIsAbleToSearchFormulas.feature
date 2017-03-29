@Intg
Feature: List Page

  Acceptance Criteria: User shall be able to access features in the list page under formula.

  Background:
    Given the docker containers are running
    And   the user has logged into the pricing application

  Scenario: To verify if the user is able to perform end date edit and deactivation on an active formula.
    Given the user has navigated to the "List" page under the "Formula"
    When  the user clicks on "Edit" button
    And   update the end date for formula as "next day"
    And   the user clicks on "Update" button
    Then  the formula should be updated