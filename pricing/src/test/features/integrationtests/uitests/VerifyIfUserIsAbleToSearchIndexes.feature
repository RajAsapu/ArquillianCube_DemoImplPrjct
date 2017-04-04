Feature: List Page

  Acceptance Criteria: User shall be able to access features in the search in List page.

  Background:
    Given the docker containers are running
    And   the user has logged into the pricing application

  Scenario Outline: To verify if the user is able to search on the list page with rate basis and currency as filters.
    Given   the user has navigated to the "List" page under the "Index"
    When  the user enters the start date as <startDate> and status as <status>
    And   the user enters the type as <type>
    And   the user enters rate basis as <rateBasis>
    And   name as <name>
    And   currency as <currency>
    And   unit of measurement as <uom>
    And   clicks on the search button
    Then  the user shall be able to view the list of indexes matching the search criteria as "<startDate>,<status>,<type>,<rateBasis>,<name>,<currency>,<uom>" on list page

    Examples:
      | startDate  | status | type   | rateBasis | name                | currency | uom |
      | 2016-12-10 | Active | MANUAL | Flat      | UNL87 9RVP USG PIPE | AFN      | 110 |


  Scenario: To verify that an error message is displayed when end date is updated to be before start date.
    Given the user has navigated to the "List" page under the "Index"
    And   the user clicked on edit action
    When  start date as displayed and end date as startdate-1
    Then  the application displays an error message as "End date cannot be before start date"

  Scenario: To verify that the user is allowed to enter the end date and start date as same day.
    Given the user has navigated to the "List" page under the "Index"
    And   the user clicked on edit action
    When  start date as displayed and end date as today
    And   the user clicked on submit action
    Then  the index should be updated

  # Error message need to be validated once its in dev
  Scenario: To verify that an error message is displayed when end date is updated to be before current date.
    Given the user has navigated to the "List" page under the "Index"
    And   the user clicked on edit action
    When  start date as displayed and end date as yesterday
    And   the user clicked on submit action
    Then  the index should not be updated

  Scenario: To verify that the user is able to edit only end date in the existing active index.
    Given the user has navigated to the "List" page under the "Index"
    When  the user clicked on edit action
    Then  the user shall be able to edit only end date

  Scenario: To verify that the user is able to inactivate the active index.
    Given the user has navigated to the "List" page under the "Index"
    When  the user enters the start date as 2016-12-10 and status as Active
    And   clicks on the search button
    And   the user clicked on inactive action
    Then  the status of the index should change to inactive

  Scenario Outline: To verify that the user is unable to edit low,mid,high and close prices for the active indexes with rate basis other than unit.
    Given the user has navigated to the "List" page under the "Index"
    When  the user enters the start date as 2016-12-10 and status as Active
    And  the user enters rate basis as <rateBasis>
    And   clicks on the search button
    And   the user clicked on edit action
    Then  the user is not allowed to update low,mid,high and close prices for active index

    Examples:
    | rateBasis         |
    | Price Point Scale |
    | Price Break Scale |
