@PageObjects
Feature: To verify if the user is able to access create page under index.

  Acceptance Criteria: User shall be able to access features in the Create page under index.

  Background:
    Given the user has logged into the pricing application

  Scenario Outline: To verify if the user is able to create an index.
    Given the user has navigated to the "Create" page under the "Index"
    When  the user enters rate basis as Price Point Scale
    And   <lowPrice> ,<midPrice> ,<highPrice> and <closePrice> are entered
    And   name as NY RBOB Prem Brg
    And   start date as 2016-12-10 and end date as 2017-03-10
    And   currency as USD
    And   unit of measurement as USG
    And   comment as Created for new clients
    And   clicks on the submit button
    Then  the user shall be able to view the created index in the list on filtering with Price Point Scale

    Examples:
      | lowPrice | midPrice | highPrice | closePrice |
      | 10.5     | 15.8     | 30.2      | 18.8       |
