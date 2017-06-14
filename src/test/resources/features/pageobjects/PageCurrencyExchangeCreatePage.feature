@SmokeTest
@CurrExCreate
@CurrencyExchange
Feature: To verify if the user is able to create currency exchange rate info under curreny exchange.

  Acceptance Criteria: User shall be able to create a currency exchange info under currency exchange.

  Background:
    Given the user has logged into the pricing application

  Scenario Outline: To verify if the user is able to search on the list page with start date as filter.
    Given the user has navigated to the "Create" page under the "Currency_Exchange"
    When  the user selects the sets the date as "<date>"
    And   sets the currency type as "<type>"
    And   sets the currencyFrom field in create page to "<currencyFrom>"
    And   sets the currencyTo field in create page to "<currencyTo>"
    And   converstion rate to "<rate>"
    And   clicks on the submit button
    Then  the currency exchange info should be created
      | <date> | <type> | <currencyFrom> | <currencyTo> | <rate> |
    Examples:
      | date  | type | currencyFrom | currencyTo | rate |
      | today | CWB  | AED          | USD        | 6.00 |