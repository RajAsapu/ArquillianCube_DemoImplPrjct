@Uitests
Feature: To verify the codes generated in the Create index page.

  Acceptance Criteria: User shall be able to view codes in the create index page.

  Scenario: To verify if the user is view the mocked codes.
    Given the docker mock containers are running
    And   the user has logged into the pricing application
    When  the user has navigated to the "Create" page under the "Index"
    And   name as Example:Gulf Water Region3
    Then  the codes shall be auto populated