Feature: Workbook Data Page

  Acceptance Criteria: User shall be able to create a workbook data for a workbook configuration.

  Background:
    Given   the user has logged into the pricing application

  Scenario Outline:To verify if the user is able to create work book data with end date as null.
    Given the user has navigated to the "List" page under the "Workbook"
    And   the user navigates to the workbook data of TestWorkbookAllAttr
    And   the user adds new workbook data
    And   the user sets supplier for the workbook data as <supplier>
    And   the user sets supplierSite for the workbook data as <supplierSite>
    And   the user sets location for the workbook data as <location>
    And   the user sets item for the workbook data as <item>
    And   the user sets hauler for the workbook data as <hauler>
    And   the user sets customerShipto for the workbook data as <customerShipTo>
    And   the user sets customer for the workbook data as <customer>
    And   the user sets fbo for the workbook data as <fbo>
    And   the user sets priceBasis for the workbook data as <priceBasis>
    And   the user sets uom for the workbook data as <uom>
    And   the user sets start date for the workbook data as <startDate>
    And   the user sets currency for the workbook data as <currencyCode>
    And   the user sets amount for the workbook data as <amount>
    When  the user saves data
    Then  the workbook data should be created
      | <supplier> | <supplierSite> | <location> | <item> | <hauler> | <customerShipTo> | <customer> | <fbo> | <priceBasis> | <uom> | <startDate> | <endDate> | <currencyCode> | <amount> |
    Examples:
      | supplier    | supplierSite               | location | item     | hauler           | customerShipTo | customer       | fbo              | priceBasis | uom | startDate         | currencyCode | amount |
      | 101 LIMITED | MIAMI EXECUTIVE AVIATION-A | HOUSTON  | AA110573 | AAGAARD EURO OIL | LAND           | SKYCHASE / TAK | KEYSON AIRWAYS-A | Unit       | 300 | 09-May-2017 10:19 | AMD          | 100    |

  Scenario Outline: To verify that the user is not allowed to create workbook data with end date before today's date.
    Given the user has navigated to the "List" page under the "Workbook"
    And   the user navigates to the workbook data of TestWorkbook3Attr
    And   the user adds new workbook data
    When  the user sets start date for the workbook data as <startDate>
    And   the user sets end date for the workbook data as <endDate>
    Then  the application displays an error message as "End date cannot be before todays date."
    And   the application displays an error message as "End date cannot be before start date."
    Examples:
      | startDate | endDate   |
      | today     | yesterday |
      | tomorrow  | yesterday |

  Scenario: To verify that the user is not allowed to update workbook data with end date before today's date.
    Given the user has navigated to the "List" page under the "Workbook"
    And   the user navigates to the workbook data of TestWorkbook3Attr
    And   the user edits workbook data
    And   the user sets end date for the workbook data as yesterday
    When  the user updates the record
    Then  the application displays an error message as "end date cannot be before today's date"

  Scenario Outline: To verify that the user is able to update the end date successfully.
    Given the user has navigated to the "List" page under the "Workbook"
    And   the user navigates to the workbook data of TestWorkbook3Attr
    And   the user edits workbook data
    And   the user sets end date for the workbook data as <endDate>
    When  the user updates the record
    Then  the application displays an success message as "Workbook data updated successfully."
    Examples:
      | endDate  |
      | today    |
      | tomorrow |
  # Depends on test data with attributes
  Scenario: To verify that all the work book data fields except end date are read only.
    Given the user has navigated to the "List" page under the "Workbook"
    And   the user navigates to the workbook data of TestWorkbook3Attr
    When  the user edits workbook data
    Then  the user is only allowed to read the attributes in workbook data

  Scenario: To verify that the user is able to deactive existing active work book data record.
    Given the user has navigated to the "List" page under the "Workbook"
    And   the user navigates to the workbook data of TestWorkbook3Attr
    When  the user deactivates workbook data
    Then  the application displays an success message as "Deactivated workbook data."

  Scenario: To verify that application doesn't display edit and deactivate options for the inactive record.
    Given the user has navigated to the "List" page under the "Workbook"
    And   the user navigates to the workbook data of TestWorkbook3Attr
    When  the user selects inactive workbook data
    Then  the application doesn't display edit and deactive actions

  Scenario:To verify if the application displays an error message on trying to update a data overlapping on record with same name.
    Given the user has navigated to the "List" page under the "Workbook"
    And   the user navigates to the workbook data of TestWorkbook3Attr
    When  the user creates the workbook data
      | supplier                 | location    | customer                 | priceBasis | uom | startDate         | endDate  | currencyCode | amount |
      | JET AVIATION HOUSTON INC | MIAMI INTL1 | BUSINESS AVIATION CENTRE | Unit       | 300 | 09-May-2017 10:19 | today    | BBD          | 100    |
      | JET AVIATION HOUSTON INC | MIAMI INTL1 | BUSINESS AVIATION CENTRE | Unit       | 300 | tomorrow          | tomorrow | BBD          | 100    |
    And   the user edits the record with the supplier name as JET AVIATION HOUSTON INC
    And   the user sets end date for the workbook data as tomorrow
    And   the user updates the record
    Then  the application displays an error message as "Overlap exception"

  Scenario:To verify if the application displays an error message on trying to create a data overlapping on record with same name.
    Given the user has navigated to the "List" page under the "Workbook"
    And   the user navigates to the workbook data of TestWorkbook3Attr
    When  the user creates the workbook data
      | supplier               | location   | customer                 | priceBasis | uom | startDate         | endDate  | currencyCode | amount |
      | JAMUNA OIL COMPANY LTD | MIAMI INTL | BUSINESS AVIATION CENTRE | Unit       | 300 | 09-May-2017 10:19 | tomorrow | BBD          | 100    |
      | JAMUNA OIL COMPANY LTD | MIAMI INTL | BUSINESS AVIATION CENTRE | Unit       | 300 | today             | tomorrow | BBD          | 100    |
    And   the user saves data
    Then  the application displays an error message as "Overlap exception"

  # Error messages has to be implemented
  Scenario Outline:To verify if the application displays error message when the scale rates are invalid in the price point scale.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user navigates to the workbook data of TestWorkbook3Attr
    And   the user adds new workbook data
    And   the user sets supplier for the workbook data as 3 HONG KONG
    And   the user sets location for the workbook data as HOUSTON EXECUTIVE AIRPORT
    And   the user sets customer for the workbook data as SKYCHASE / TAK
    And   the user sets priceBasis for the workbook data as Price Point Scale
    And   the user sets uom for the workbook data as 300
    And   the user sets start date for the workbook data as today
    And   the user sets currency for the workbook data as USD
    And   adds the scale rates
      | <scaleFrom> | <scaleTo> | <scaleRate> |
      | 100         | 500       | 1           |
      | 500         | 1000      | 0.9         |
    And   the user saves data
    Then  the application displays an error message as "<errorMessage>"
    Examples:
      | scaleFrom | scaleTo | scaleRate | errorMessage                              |
      | 0         | 100     |           | Rate cannot be null or zero               |
      | 0         |         | 2         | Rate from cannot be null or zero          |
      | 1         | 100     | 2         | Rate should start from zero               |
      | 100       | 1       | 2         | Range is invalid                          |
      | 0         | -100    | 2         | Range cannot be negative                  |
      | 0         | 100     | -1        | Rate should be greater than or equal to 0 |

  Scenario Outline:To verify if the application displays error message when the scale rates are invalid in the price break scale.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user navigates to the workbook data of TestWorkbook3Attr
    And   the user adds new workbook data
    And   the user sets supplier for the workbook data as 3 HONG KONG
    And   the user sets location for the workbook data as HOUSTON EXECUTIVE AIRPORT
    And   the user sets customer for the workbook data as SKYCHASE / TAK
    And   the user sets priceBasis for the workbook data as Price Break Scale
    And   the user sets uom for the workbook data as 300
    And   the user sets start date for the workbook data as today
    And   the user sets currency for the workbook data as USD
    And   adds the scale rates
      | <scaleFrom> | <scaleTo> | <scaleRate> |
      | 100         | 500       | 1           |
      | 500         | 1000      | 0.9         |
    And   the user saves data
    Then  the application displays an error message as "<errorMessage>"
    Examples:
      | scaleFrom | scaleTo | scaleRate | errorMessage                              |
      | 0         | 100     |           | Rate cannot be null or zero               |
      | 0         |         | 2         | Rate from cannot be null or zero          |
      | 1         | 100     | 2         | Rate should start from zero               |
      | 100       | 1       | 2         | Range is invalid                          |
      | 0         | -100    | 2         | Range cannot be negative                  |
      | 0         | 100     | -1        | Rate should be greater than or equal to 0 |

  Scenario: To verify that the application displays error messages when required fields are not filled.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user navigates to the workbook data of TestWorkbook3Attr
    And   the user adds new workbook data
    And   the user saves data
    Then  the application displays an error message as "supplier is required."
    And   the application displays an error message as "location is required."
    And   the application displays an error message as "customer is required."
    And   the application displays an error message as "UOM is required."
    And   the application displays an error message as "Start date is required."
    And   the application displays an error message as "Currency is required."
    And   the application displays an error message as "Amount is required."


