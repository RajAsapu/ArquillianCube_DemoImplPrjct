Feature: To verify if the user is able to search in the list page under index.

  Acceptance Criteria: User shall be able to access features in the search in List page.

  @Intg
  Scenario Outline: To verify if the user is able to search on the list page with rate basis and currency as filters.
    Given the docker containers are running
    And   the user has logged into the pricing application
    And   the user has navigated to the "List" page under the "Index"
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

