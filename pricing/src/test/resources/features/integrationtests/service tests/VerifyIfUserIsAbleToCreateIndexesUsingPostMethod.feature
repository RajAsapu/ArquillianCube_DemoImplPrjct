Feature: Create Page - Index.

  Acceptance Criteria: User shall be able to access features in the Create page under index.

  @Services
  Scenario Outline: To verify if the response from Create index end point.
    Given the user querys POST method on create index end point
      | <lowPrice> | <midPrice> | <highPrice> | <closePrice> | <rateBasis> | <name> | <startDate> | <endDate> | <currency> | <uom> | <comments> |
    When  the response from the service is captured
    Then  the service should return a successful response
    Examples:
      | lowPrice | midPrice | highPrice | closePrice | rateBasis | name               | startDate  | endDate    | currency | uom | comments                |
      | 10.5     | 15.8     | 30.2      | 18.9       | Flat      | NYMEX H.O. 1ST MTH | 2017-01-10 | 2017-03-11 | USD      | 110 | Created for new clients |