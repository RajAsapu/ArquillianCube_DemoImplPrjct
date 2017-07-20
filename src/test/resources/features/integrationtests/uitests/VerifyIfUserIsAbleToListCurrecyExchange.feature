@CurrencyExchange
Feature: List page of Currency Exchange

  Acceptance Criteria: User shall be able to search currency exchanges using filters.

  Background:
    Given the user has logged into the pricing application

  Scenario Outline: To verify if user is able to create currency exchange from add curency exchange in list page.
    Given the user has navigated to the "List" page under the "Currency_Exchange"
    And   clicks on add currency exchange
    When  the user selects the sets the date as "<date>"
    And   sets the currency type as "<type>"
    And   sets the currencyFrom field in create page to "<currencyFrom>"
    And   sets the currencyTo field in create page to "<currencyTo>"
    And   converstion rate to "<rate>"
    And   clicks on the submit button
    Then  the application displays an success message as "Created currency exchange rate."
    Examples:
      | date  | type      | currencyFrom | currencyTo | rate |
      | today | Corporate | USD          | GBP        | 6.00 |

  Scenario: To verify if the columns in the list page are in the order of  Status, From, To, Date, Rate Type, Rate and Actions.
    Given the user has navigated to the "List" page under the "Currency_Exchange"
    When  the user clicks on search in currency exchange
    Then  the columns of the currency exchange should be in the order of Status, From, To, Date, Rate Type, Rate and Actions

  Scenario: To verify if the user is able to copy currency exchange and create a new record.
    Given the user has navigated to the "List" page under the "Currency_Exchange"
    When  sets the status as "Inactive"
    And   clicks on the search button
    And   clicked on "row"
    And   clicks on copy currency exchange
    And   clicks on the submit button
    Then  the application displays an success message as "Created currency exchange rate."


  Scenario: To verify if the user is able to deactive currency exchange record.
    Given the user has navigated to the "List" page under the "Currency_Exchange"
    When  sets the status as "Active"
    And   clicks on the search button
    And   clicked on "row"
    And   clicks on deactivate currency exchange
    Then  the application displays an success message as "Deactivated currency exchange."




