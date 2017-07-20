@SmokeTest
@WorkBookData
Feature: Workbook Data Page

  Acceptance Criteria: User shall be able to add,upload and search workbook data.

  Background:
    Given   the user has logged into the pricing application

  @DefectPrice770
  Scenario Outline:To verify if the user is able to create work book data.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user navigates to the workbook data of TestWorkbookAllAttr
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
    And   the user sets end date for the workbook data as <endDate>
    And   the user sets currency for the workbook data as <currencyCode>
    And   the user sets amount for the workbook data as <amount>
    And   the user saves data
    Then  the workbook data should be created
      | <supplier> | <supplierSite> | <location> | <item> | <hauler> | <customerShipTo> | <customer> | <fbo> | <priceBasis> | <uom> | <startDate> | <endDate> | <currencyCode> | <amount> |
    Examples:
      | supplier    | supplierSite               | location       | item    | hauler           | customerShipTo | customer       | fbo              | priceBasis | uom | startDate         | endDate | currencyCode | amount |
      | 101 LIMITED | MIAMI EXECUTIVE AVIATION-A | HOUSTON COUNTY | AEROJET | AAGAARD EURO OIL | LAND           | SKYCHASE / TAK | KEYSON AIRWAYS-A | Unit       | 300 | 09-May-2017 10:19 | today   | AMD          | 100    |

  #Yet to be implemented
  Scenario Outline:To verify if the user is able to upload work book data.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user navigates to the workbook data of TestWorkbook3Attr
    And   the user chooses a <filePath> to upload

    Examples:
      | filePath |
      | filePath |

  @DefectPrice770
  Scenario Outline:To verify if the user is able to search work book data.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user navigates to the workbook data of <workbook>
    And   the user navigates to the search page of workbook data
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
    And   the user sets end date for the workbook data as <endDate>
    And   the user sets currency for the workbook data as <currencyCode>
    And   the user sets status for the workbook data as <status>
    And   the user searches for the data
    Then  the workbook data should be dispalyed in the search results
      | <supplier> | <supplierSite> | <location> | <item> | <hauler> | <customerShipTo> | <customer> | <fbo> | <priceBasis> | <uom> | <startDate> | <endDate> | <currencyCode> | <status> |
    Examples:
      | workbook            | supplier               | supplierSite               | location                  | item    | hauler           | customerShipTo | customer                              | fbo              | priceBasis | uom | startDate         | endDate           | currencyCode | status   |
      | TestWorkbookAllAttr | 101 LIMITED            | MIAMI EXECUTIVE AVIATION-A | HOUSTON COUNTY            | AEROJET | AAGAARD EURO OIL | LAND           | SKYCHASE / TAK                        | KEYSON AIRWAYS-A | All        | 300 | 04-May-2017 10:19 | 04-May-2017 10:19 | AMD          | Active   |
      | TestWorkbookAllAttr | HANJIN SHIPPING CO LTD |                            | MIAMI INTL1               |         |                  |                | COTRADER SIX LTD C/O CLIPPER BULK A/S |                  | All        | 323 | 17-Apr-2017 15:2  |                   | ARS          | Inactive |
      | TestWorkbook3Attr   | JEFFREY WEISSMAN       |                            | HOUSTON EXECUTIVE AIRPORT |         |                  |                | JET FORWARD AVIATION                  |                  | UNIT       | 300 |                   |                   | AMD          | Active   |

  Scenario Outline:To verify if the user is able to create work book data with price basis as price scale.
    Given the user has navigated to the "List" page under the "Workbook"
    When  the user navigates to the workbook data of TestWorkbook3Attr
    And   the user adds new workbook data
    And   the user sets supplier for the workbook data as <supplier>
    And   the user sets location for the workbook data as <location>
    And   the user sets customer for the workbook data as <customer>
    And   the user sets priceBasis for the workbook data as <priceBasis>
    And   the user sets uom for the workbook data as <uom>
    And   the user sets start date for the workbook data as <startDate>
    And   the user sets end date for the workbook data as <endDate>
    And   the user sets currency for the workbook data as <currencyCode>
    And   adds the scale rates
      | 0   | 100  | 1.2 |
      | 100 | 500  | 1   |
      | 500 | 1000 | 0.9 |
    And   the user saves data
    Then  the application displays an success message as "Workbook data created successfully."
    Examples:
      | supplier    | location                  | customer       | priceBasis        | uom | startDate         | currencyCode |
      | 3 HONG KONG | HOUSTON EXECUTIVE AIRPORT | SKYCHASE / TAK | Price Point Scale | 300 | 09-May-2017 10:19 | USD          |
      | 3 HONG KONG | HOUSTON EXECUTIVE AIRPORT | SKYCHASE / TAK | Price Break Scale | 300 | 09-May-2017 10:19 | USD          |

