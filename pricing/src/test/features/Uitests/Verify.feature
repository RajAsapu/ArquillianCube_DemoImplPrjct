Feature:

  Background:
    Given the docker containers are running
    And   the user has logged into the pricing application

  Scenario: check
    Given the user has navigated to the "Create" page under the "Formula"
    And  wait for sometime
