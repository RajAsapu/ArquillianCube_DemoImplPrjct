@PageObjects
Feature: To verify if the user is able to access list page under index.

  Acceptance Criteria: User shall be able to access features in the list page under index.

  Background:
    Given  the user has logged into the pricing application

  Scenario: To verify if the user is able to search on the list page with start date as filter.
    Given the user has navigated to the "List" page under the "Index"
    When  the user enters the start date as 12-Dec-2016 and status as Active
    And   clicks on the search button
    Then  the user shall be able to view the list of indexes with start date from "12-Dec-2016" and status as "Active"

  Scenario: To verify if the user is able to search on the list page with end date as filter.
    Given the user has navigated to the "List" page under the "Index"
    When  the user enters the end date as "12-Dec-2016" and status as "Inactive"
    And   clicks on the search button
    Then  the user shall be able to view the list of indexes with end date from "12-Dec-2016" and status as "Inactive"

  Scenario: To verify if the user is able to search on the list page with type as filter.
    Given the user has navigated to the "List" page under the "Index"
    When  the user enters the type as Manual
    And   clicks on the search button
    Then  the user shall be able to view the list of indexes with type as "MANUAL"

  Scenario: To verify if the user is able to search on the list page with rate basis and currency as filters.
    Given the user has navigated to the "List" page under the "Index"
    When  the user enters rate basis in search as Unit
    And   name as Unl 89 NY Barge
    And   currency in search USD
    And   unit of measurement in search as USG
    And   clicks on the search button
    Then  the user shall be able to view the list of indexes matching the search criteria as "null,null,null,Unit,UNL 89 NY BARGE,USD,USG" on list page




