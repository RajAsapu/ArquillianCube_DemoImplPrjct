Feature: To verify if the user is able to access create page under index.

  Acceptance Criteria: User shall be able to access create page under index.

  Background:

      Given the user has logged into the pricing application

  Scenario: To verify if the user is able to search on the list page
      Given the user has navigated to the "List" page under the "Index"
      When  the user enters the start date as "2016-12-10" and status as "Active"
      And   clicks on the search button
      Then  the user shall be able to view the list of indexes with start date from "2016-12-10" and status as "Active"




