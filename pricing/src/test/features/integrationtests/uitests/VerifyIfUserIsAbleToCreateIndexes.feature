Feature: To verify if the user is able to access create page under index.

  Acceptance Criteria: User shall be able to access features in the Create page under index.


  @Intg
  Scenario Outline: To verify if the user is able to create an index in the create page.
    Given the docker containers are running
    And   the user has logged into the pricing application
    And   the user has navigated to the "List" page under the "Index"
    When  the user enters rate basis as <rateBasis>
    And   <lowPrice> ,<midPrice> ,<highPrice> and <closePrice> are entered
    And   name as <name>
    And   start date as <startDate> and end date as <endDate>
    And   currency as <currency>
    And   unit of measurement as <uom>
    And   comment as <comments>
    And   clicks on the submit button
    Then  the user shall be able to view the created index in the list on filtering with <rateBasis>

    Examples:
      | lowPrice | midPrice | highPrice | closePrice | rateBasis | name               | startDate  | endDate    | currency | uom | comments                |
      | 10.5     | 15.8     | 30.2      | 18.8       | Flat      | NYMEX H.O. 1ST MTH | 2017-01-10 | 2017-03-10 | AFN      | 110 | Created for new clients |

