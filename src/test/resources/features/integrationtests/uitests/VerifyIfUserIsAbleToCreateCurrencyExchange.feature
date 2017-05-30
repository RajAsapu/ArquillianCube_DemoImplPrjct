Feature: Currency Exchange
  Acceptance Criteria: User shall be able to create currency exchange

  Background:
    Given  the user has logged into the pricing application

  Scenario Outline: To verify that the user is not allowed to create duplicate record of currency exchange with same date.
    Given the user has navigated to the "Create" page under the "Currency_Exchange"
    When  the user selects the sets the date as "<date>"
    And   sets the currency type as "<type>"
    And   sets the currencyFrom field in create page to "<currencyFrom>"
    And   sets the currencyTo field in create page to "<currencyTo>"
    And   converstion rate to "<rate>"
    And   clicks on the submit button
    Then  the application displays an <status> message as "<message>"
    Examples:
      | date        | type       | currencyFrom | currencyTo | rate |  message                                | status  |
      | today       | Corporate  | USD          | AOA        | 5.00 |  Created currency exchange rate.        | success |
      | today       | Corporate  | USD          | AOA        | 5.00 |  Currency exchange rate already exists. | error   |

#
#  Scenario: To verify if the user is able to create a currency exchange  with the same name as deactivated record.
#
#    Given the user has navigated to the "Create" page under the "Currency_Exchange"
#    When  the user selects the sets the date as "today"
#    And   sets the currency type as "Corporate"
#    And   sets the currencyFrom field in create page to "USD"
#    And   sets the currencyTo field in create page to "DOP"
#    And   converstion rate to "4.00"
#    And   clicks on the submit button
#    Then  the application displays an success message as "Created currency exchange rate."