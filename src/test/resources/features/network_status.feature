Feature: Monitor Network - Network Status Overview

  Scenario: owner sees status counts for a location
    Given a location "Vienna Center" exists
    And an AC charger "AC1" exists at location "Vienna Center" with status "free"
    And a DC charger "DC1" exists at location "Vienna Center" with status "occupied"
    And an AC charger "AC2" exists at location "Vienna Center" with status "out_of_order"
    And the location "Vienna Center" has AC price 0.50 and DC price 0.80
    When the owner requests the network status overview for location "Vienna Center"
    Then the overview for location "Vienna Center" shows 1 free, 1 occupied, 1 out_of_order chargers
    Then the overview for location "Vienna Center" shows prices AC 0.50 and DC 0.80

  Scenario: overview shows 0 counts when location has no chargers
    Given a location "Vienna Center" exists
    And the location "Vienna Center" has AC price 0.50 and DC price 0.80
    When the owner requests the network status overview for location "Vienna Center"
    Then the overview for location "Vienna Center" shows 0 free, 0 occupied, 0 out_of_order chargers
    And the overview for location "Vienna Center" shows prices AC 0.50 and DC 0.80
