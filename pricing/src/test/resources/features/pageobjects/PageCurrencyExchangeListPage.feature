@PageObjects
Feature: To verify if the user is able to access list page under curreny exchange.

  Acceptance Criteria: User shall be able to access features in the currency exchange under index.

  Background:
    Given the user has logged into the pricing application

  Scenario Outline: To verify if the user is able to search on the list page with start date as filter.
    Given the user has navigated to the "List" page under the "Currency_Exchange"
    When  the user selects the search criteria as "<searchType>"
    And   sets the status as "<status>"
    And   sets date as "<date>"
    And   sets the converstion type as "<converstionType>"
    And   sets the currencyFrom field to "<currencyFrom>"
    And   sets the currencyTo field to "<currencyTo>"
    And   clicks on the search button
    Then  the application displays the search results as per the filters
      | <searchType> | <date> | <converstionType> | <currencyFrom> | <currencyTo> | <status> |
    Examples:
      | searchType | date       | converstionType | currencyFrom | currencyTo | status |
      | Daily      | 2017-02-24 | Average         | VEF          | XCD        | Active |

  Scenario Outline: To verify if the user is able to search on the list page with range of dates as filter.
    Given the user has navigated to the "List" page under the "Currency_Exchange"
    When  the user selects the search criteria as "<searchType>"
    And   sets the status as "<status>"
    And   sets date range as "<fromDate>" to "<toDate>"
    And   sets the converstion type as "<converstionType>"
    And   sets the currencyFrom field to "<currencyFrom>"
    And   sets the currencyTo field to "<currencyTo>"
    And   clicks on the search button
    Then  the application displays the search results as per the filters
      | <searchType> | <fromDate> | <converstionType> | <currencyFrom> | <currencyTo> | <status> | <toDate> |
    Examples:
      | searchType | fromDate   | converstionType | currencyFrom | currencyTo | status | toDate     |
      | Range      | 2017-02-24 | Average         | VEF          | XCD        | Active | 2017-09-24 |
