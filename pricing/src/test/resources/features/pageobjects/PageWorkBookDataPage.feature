@PageObjects
Feature: Workbook Data Page

  Acceptance Criteria: User shall be able to add,upload and search workbook data.

  Background:
    Given   the user has logged into the pricing application
  #Test Data : Workbook definition with all the 14 attributes should exist - Ref : TestData01
  Scenario Outline:To verify if the user is able to create work book data.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the list workbook configuration list should be filtered with the "name" as "TestData01"
    And   clicked on "Row"
    And   clicked on "Manage Data"
    And   clicked on "Add New Data"
    And   supplier as "<supplier>"
    And   supplierSite as "<supplierSite>"
    And   location as "<location>"
    And   item as "<item>"
    And   hauler as "<hauler>"
    And   customerShipto as "<customerShipTo>"
    And   customer as "<customer>"
    And   fbo as "<fbo>"
    And   price basis as "<priceBasis>"
    And   uom as "<uom>"
    And   set the start date for data as "<startDate>"
    And   set the end date for data as "<endDate>"
    And   set the currency for data as "<currencyCode>"
    And   set the amount for data as "<amount>"
    And   clicks on the save button
    And   wait for sometime
    Then  the workbook data should be created
      |<supplier>|<supplierSite>|<location>|<item>|<hauler>|<customerShipTo>|<customer>|<fbo>|<priceBasis>|<uom>|<startDate>|<endDate>|<currencyCode>|<amount>|
    Examples:
    | supplier    | supplierSite               | location | item     | hauler           | customerShipTo | customer         | fbo              | priceBasis | uom | startDate         | endDate          | currencyCode | amount |
    | 101 LIMITED | MIAMI EXECUTIVE AVIATION-A | HOUSTON  | AA110573 | AAGAARD EURO OIL | LAND           | SKYCHASE / TAK   | KEYSON AIRWAYS-A | Unit       | 300 | 09-May-2017 10:19 | 09-May-2017 10:19| AMD          | 100    |

  Scenario Outline:To verify if the user is able to upload work book data.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the list workbook configuration list should be filtered with the "name" as "TestData01"
    And   clicked on "Row"
    And   clicked on "Manage Data"
    And   clicked on "Upload"
    And   choose file having path "<filePath>"
    And   wait for sometime
    #Yet to be implemented
  Examples:
    | filePath   |
    | filePath   |
  # Test Data required for search Ref:TestData01
  Scenario Outline:To verify if the user is able to search work book data.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the list workbook configuration list should be filtered with the "name" as "TestData01"
    And   clicked on "Row"
    And   clicked on "Manage Data"
    And   clicked on "Search"
    And   supplier as "<supplier>"
    And   supplierSite as "<supplierSite>"
    And   location as "<location>"
    And   item as "<item>"
    And   hauler as "<hauler>"
    And   customerShipto as "<customerShipTo>"
    And   customer as "<customer>"
    And   fbo as "<fbo>"
    And   price basis as "<priceBasis>"
    And   uom as "<uom>"
    And   set the start date for data as "<startDate>"
    And   set the end date for data as "<endDate>"
    And   set the currency for data as "<currencyCode>"
    And   sets the status as "<status>"
    And   search button is clicked
    Then  the workbook data should be dispalyed in the search results
      |<supplier>|<supplierSite>|<location>|<item>|<hauler>|<customerShipTo>|<customer>|<fbo>|<priceBasis>|<uom>|<startDate>|<endDate>|<currencyCode>|<status>|
    Examples:
      | supplier    | supplierSite               | location | item     | hauler           | customerShipTo | customer                               | fbo              | priceBasis | uom | startDate         | endDate          | currencyCode | status    |
      | 101 LIMITED | MIAMI EXECUTIVE AVIATION-A | HOUSTON  | AA110573 | AAGAARD EURO OIL | LAND           | SKYCHASE / TAK                         | KEYSON AIRWAYS-A | All        | 300 | 04-May-2017 10:19 | 04-May-2017 10:19| AMD          | Active    |
      | HANJIN SHIPPING CO LTD |                 | MIKKELI  |          |                  |                | COTRADER SIX LTD C/O CLIPPER BULK A/S  |                  | All        | 323 | 17-Apr-2017 15:2  |                  | ARS          | Inactive  |
