Feature: Manage Locations -  Remove Location
  Scenario: remove existing location
    Given there is an existant location
    When the user deletes an existant location
    Then the location is not deleted from the database but moved to the folder of deleted elements


