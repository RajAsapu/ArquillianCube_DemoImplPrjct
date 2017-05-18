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
      | <supplier> | <supplierSite> | <location> | <item> | <hauler> | <customerShipTo> | <customer> | <fbo> | <priceBasis> | <uom> | <startDate> | <endDate> | <currencyCode> | <amount> |
    Examples:
      | supplier    | supplierSite               | location | item     | hauler           | customerShipTo | customer       | fbo              | priceBasis | uom | startDate         | currencyCode | amount |
      | 101 LIMITED | MIAMI EXECUTIVE AVIATION-A | HOUSTON  | AA110573 | AAGAARD EURO OIL | LAND           | SKYCHASE / TAK | KEYSON AIRWAYS-A | Unit       | 300 | 09-May-2017 10:19 | AMD          | 100    |

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
      | startDate | endDate   |
      | today     | yesterday |
      | tomorrow  | today     |

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
      | day      |
      | today    |
      | tomorrow |
  # Depends on test data with attributes
  Scenario: To verify that all the work book data fields except end date are read only.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user clicks on manage data for a workbook with name as "Test"
    And   clicked on "Edit Data"
    And   the user is only allowed to read the attributes in workbook data

  Scenario: To verify that the user is able to deactive existing active work book data record.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user clicks on manage data for a workbook with name as "Test"
    And   clicked on "deactivate"
    Then  the application displays an success message as "Deactivated workbook data."

  Scenario: To verify that application doesn't display edit and deactivate options for the inactive record.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user clicks on manage data for a workbook with name as "Test"
    And   clicked on "Inactive Data"
    Then  the application doesn't display edit and deactive actions

  # Test Case for the overlap check
  Scenario:To verify if the application displays an error message on trying to update a data overlapping on record with same name.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user clicks on manage data for a workbook with name as "TestWorkbook3Attr"
    And   create the workbook data
      | supplier                  | location   | customer                 | priceBasis | uom | startDate         | endDate | currencyCode | amount |
      | JET AVIATION HOUSTON INC  | MIAMI INTL | BUSINESS AVIATION CENTRE | Unit       | 300 | 09-May-2017 10:19 | today   | BBD          | 100    |
      | JET AVIATION HOUSTON INC  | MIAMI INTL | BUSINESS AVIATION CENTRE | Unit       | 300 | tomorrow          | tomorrow| BBD          | 100    |
    And   edit the record with the supplier name as "JET AVIATION HOUSTON INC"
    And   set the end date for data as "tomorrow"
    And   clicks on the update button
    Then  the application displays an error message as "Overlap exception"

  # Test Case for the overlap check
  # Defect : Application is not displaying error message
  Scenario:To verify if the application displays an error message on trying to create a data overlapping on record with same name.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user clicks on manage data for a workbook with name as "TestWorkbook3Attr"
    And   create the workbook data
      | supplier                  | location   | customer                 | priceBasis | uom | startDate         | endDate | currencyCode | amount |
      | JAMUNA OIL COMPANY LTD    | MIAMI INTL | BUSINESS AVIATION CENTRE | Unit       | 300 | 09-May-2017 10:19 | tomorrow| BBD          | 100    |
      | JAMUNA OIL COMPANY LTD    | MIAMI INTL | BUSINESS AVIATION CENTRE | Unit       | 300 | today             | tomorrow| BBD          | 100    |
    And  clicks on the save button
    Then  the application displays an error message as "Overlap exception"