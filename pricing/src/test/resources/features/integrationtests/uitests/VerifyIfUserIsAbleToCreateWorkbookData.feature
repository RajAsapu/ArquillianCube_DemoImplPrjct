Feature: Workbook Data Page

  Acceptance Criteria: User shall be able to create a workbook data for a workbook configuration.

  Background:
    Given   the user has logged into the pricing application
  #Test Data : Workbook definition with all the 14 attributes should exist - Ref : TestData01
  Scenario Outline:To verify if the user is able to create work book data with end date as null.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user clicks on manage data for a workbook with name as "TestData01"
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
    And   set the currency for data as "<currencyCode>"
    And   set the amount for data as "<amount>"
    And   clicks on the save button
    Then  the workbook data should be created
      |<supplier>|<supplierSite>|<location>|<item>|<hauler>|<customerShipTo>|<customer>|<fbo>|<priceBasis>|<uom>|<startDate>|<endDate>|<currencyCode>|<amount>|
    Examples:
      | supplier    | supplierSite               | location | item     | hauler           | customerShipTo | customer         | fbo              | priceBasis | uom | startDate         |  currencyCode | amount |
      | 101 LIMITED | MIAMI EXECUTIVE AVIATION-A | HOUSTON  | AA110573 | AAGAARD EURO OIL | LAND           | SKYCHASE / TAK   | KEYSON AIRWAYS-A | Unit       | 300 | 09-May-2017 10:19 |  AMD          | 100    |

  # Depends on test data of workbook definition
  Scenario Outline: To verify that the user is not allowed to create workbook data with end date before todays date.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user clicks on manage data for a workbook with name as "Test"
    And   clicked on "Add New Data"
    And   set the start date for data as "<startDate>"
    And   set the end date for data as "<endDate>"
    Then  the application displays an error message as "End date cannot be before todays date."
    And   the application displays an error message as "End date cannot be before start date."
    Examples:
      | startDate         | endDate           |
      | today             | yesterday         |
      | tomorrow          | today             |

  #Depends on test data of workbook configuration with few active data records
  Scenario: To verify that the user is not allowed to update workbook data with end date before todays date.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user clicks on manage data for a workbook with name as "Test"
    And   clicked on "Edit Data"
    And   set the end date for data as "yesterday"
    And   clicks on the update button
    Then  the application displays an error message as "end date cannot be before today's date"

  Scenario Outline: To verify that the user is able to update the end date successfully.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user clicks on manage data for a workbook with name as "Test"
    And   clicked on "Edit Data"
    And   set the end date for data as "<day>"
    And   clicks on the update button
    Then  the application displays an success message as "Workbook data updated successfully."
    Examples:
    |  day     |
    | today    |
    | tomorrow |
 # Test Data : Workbook configuration with default value
  Scenario: To verify that all the attributes in work book definition are read only.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user clicks on view workbook configuration for a workbook with name as "RajTesting"
    Then  the user is only allowed to read the attributes in workbook configuration
#    And   set the end date for data as ""
#    And   clicks on the update button
#    Then  the application displays an success message as "Workbook data updated successfully."