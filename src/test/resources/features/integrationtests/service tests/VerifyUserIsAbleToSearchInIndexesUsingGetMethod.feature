Feature: List Page - Index.

  Acceptance Criteria: User shall be able to access features in the search in List page.

  @Services
  Scenario Outline: To verify if the response from index search end point.
    Given the user querys the GET on search end point in list page with parameters as "<startDate>,<status>,<type>,<rateBasis>,<name>,<currency>,<uom>"
    When  the response from the service is captured
    Then  the service should return a successful response

    Examples:
      | startDate  | status | type        | rateBasis | name             | currency | uom |
      | 2016-12-01 | Active | PRICE-POINT | Flat      | NY RBOB PREM BRG | USD      | USG |

