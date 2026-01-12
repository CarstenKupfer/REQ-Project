Feature: Manage Charging Stations -  Build new charching stations

  Scenario: comparable charching station is not existent
    Given no charching station is used as draft
    When the user creates a new charching station
    Then it is automatically AC

  Scenario: comparable location is existent
    Given an existing location is used as draft
    When the user creates a new location
    Then he gets sugestions for a charching station type

