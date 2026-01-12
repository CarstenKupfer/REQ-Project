Feature: Monitor Network - Network Status Overview

  Scenario: owner sees status counts for a location
    Given a location "Vienna Center" exists
    And an AC charger "AC1" exists at location "Vienna Center" with status "free"
    And a DC charger "DC1" exists at location "Vienna Center" with status "occupied"
    And an AC charger "AC2" exists at location "Vienna Center" with status "out_of_order"
    When the owner requests the network status overview for location "Vienna Center"
    Then the overview for location "Vienna Center" shows 1 free, 1 occupied, 1 out_of_order chargers
