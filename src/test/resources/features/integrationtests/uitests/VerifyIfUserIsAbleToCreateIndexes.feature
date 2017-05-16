Feature: To verify if the user is able to access create page under index.

  Acceptance Criteria: User shall be able to access features in the Create page under index.

  Background:
    Given  the user has logged into the pricing application

  @Intg
  Scenario Outline: To verify if the user is able to create an index from add index in list page.
    Given the user has navigated to the "List" page under the "Index"
    And   the user clicked on addNew action
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
      | lowPrice | midPrice | highPrice | closePrice | rateBasis | name                   | startDate  | endDate    | currency | uom | comments                |
      | 10.5     | 15.8     | 30.2      | 18.8       | Flat      | UNLEADED US GULF WATER | 2017-03-10 | 2017-03-10 | AFN      | 110 | Created for new clients |

  Scenario: To verify that an error message is displayed when end date is before start date.
    Given the user has navigated to the "Create" page under the "Index"
    When  start date as 2017-03-20 and end date as 2017-03-19
    Then  the application displays an error message as "End date cannot be before start date"

  Scenario Outline: To verify if the user is able to create an index with out end date.
    Given the user has navigated to the "Create" page under the "Index"
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
      | lowPrice | midPrice | highPrice | closePrice | rateBasis | name            | startDate  | endDate | currency | uom | comments                |
      | 10.5     | 15.8     | 30.2      | 18.8       | Flat      | UNL 89 NY BARGE | 2017-03-10 |         | AFN      | 110 | Created for new clients |

  Scenario Outline: To verify that the user is unable to create the index with out required fields
    Given the user has navigated to the "Create" page under the "Index"
    When  the user enters rate basis as <rateBasis>
    And   <lowPrice> ,<midPrice> ,<highPrice> and <closePrice> are entered
    And   name as <name>
    And   start date as <startDate> and end date as null
    And   currency as <currency>
    And   unit of measurement as <uom>
    And   comment as <comments>
    And   clicks on the submit button
    Then  the index should not be created

    Examples:
      | lowPrice | midPrice | highPrice | closePrice | rateBasis | name            | startDate  | currency | uom | comments                |
      | 10.5     | 15.8     | 30.2      | 18.8       | Flat      | UNL 89 NY BARGE | null       | AFN      | 110 | Created for new clients |
      | 10.5     | 15.8     | 30.2      | 18.8       | Flat      | UNL 89 NY BARGE | 2017-03-10 |          | 110 | Created for new clients |
      | 10.5     | 15.8     | 30.2      | 18.8       | Flat      | UNL 89 NY BARGE | 2017-03-10 | AFN      |     | Created for new clients |
      | 10.5     | 15.8     | 30.2      |            | Flat      | UNL 89 NY BARGE | 2017-03-10 | AFN      | 110 | Created for new clients |

  Scenario Outline: To verify that the user is able to create the index with only required fields
    Given the user has navigated to the "Create" page under the "Index"
    When  the user enters rate basis as <rateBasis>
    And   <lowPrice> ,<midPrice> ,<highPrice> and <closePrice> are entered
    And   name as <name>
    And   start date as <startDate> and end date as null
    And   currency as <currency>
    And   unit of measurement as <uom>
    And   comment as <comments>
    And   clicks on the submit button
    Then  the index should be created

    Examples:
      | lowPrice | midPrice | highPrice | closePrice | rateBasis | name                  | startDate  | currency | uom | comments                |
      |          |          |           | 18.8       |           | UNLEADED US GULF PIPE | 2017-04-01 | BOB      | TOT | Created for new clients |

  Scenario Outline: To verify that the user is unable to enter low,mid,high and close prices for the new index with rate basis other than unit.
    Given the user has navigated to the "Create" page under the "Index"
    When  the user enters rate basis as <rateBasis>
    Then  the user is not allowed to enter low,mid,high and close prices for new index
    And   scale rates are editable

    Examples:
      | rateBasis         |
      | Price Point Scale |
      | Price Break Scale |

  Scenario Outline: To verify that the scale rates for 'Price Point Scale' starts with 0 and ends with null or a value for an index.
    Given the user has navigated to the "Create" page under the "Index"
    When  the user enters rate basis as Price Point Scale
    And   name as JetLosAngeles_Argus
    And   start date as 2017-04-01 and end date as null
    And   currency as BOB
    And   unit of measurement as TOT
    And   comment as for testing
    And   add the scale rates
      | <scaleFrom> | <scaleTo> | <scaleRate> |
      | 100         | 500       | 1           |
      | 500         | 1000      | 0.9         |
    And   clicks on the submit button
    Then  the index <result> be created

    Examples:
      | scaleFrom | scaleTo | scaleRate | result     |
      | -1        | -100    | 2         | should not |
      | -1        | null    | 2         | should not |
      | 0         | -100    | -1        | should not |
      | 1         | 100     | 1         | should not |
      | 0         | 100     | 2         | should     |

  Scenario Outline: To verify that the scale rates for 'Price Break Scale' starts with 0 and ends with null or a value for an index.
    Given the user has navigated to the "Create" page under the "Index"
    When  the user enters rate basis as Price Break Scale
    And   name as JetGrp3Qgrade_Argus
    And   start date as 2017-04-01 and end date as null
    And   currency as BOB
    And   unit of measurement as TOT
    And   comment as for testing
    And   add the scale rates
      | <scaleFrom> | <scaleTo> | <scaleRate> |
      | 100         | 500       | 1           |
      | 500         | 1000      | 0.9         |
    And   clicks on the submit button
    Then  the index <result> be created

    Examples:
      | scaleFrom | scaleTo | scaleRate | result     |
      | -1        | -100    | 2         | should not |
      | -1        | null    | 2         | should not |
      | 0         | -100    | -1        | should not |
      | 1         | 100     | 1         | should not |
      | 0         | 100     | 2         | should     |
