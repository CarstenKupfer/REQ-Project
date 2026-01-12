Feature: Manage Locations - Update Locations with new Features
  Scenario: Update Locations
    Given there is an existant location
    When the user adds new features to an exiting location
    Then the location parameters are updated
    And the location is set to active