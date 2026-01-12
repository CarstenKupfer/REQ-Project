Feature: Manage Locations -  Open new Location

  Scenario: comparable location is not existent
    Given no location is used as draft
    When the user creates a new location
    Then the location will come with no features at the initial state

  Scenario: comparable location is existent
    Given an existing location is used as draft
    When the user creates a new location
    Then the charging stations will be copied from the draft as well


