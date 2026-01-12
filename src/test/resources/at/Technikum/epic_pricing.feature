Feature: Manage Prices - Location Pricing

  Scenario: owner sets AC and DC prices for a location
    Given a location "Vienna Center" exists
    When the owner sets AC price 0.50 and DC price 0.80 for location "Vienna Center"
    Then the location "Vienna Center" has AC price 0.50 and DC price 0.80

  Scenario: owner changes DC price in real time
    Given a location "Vienna Center" exists
    And the owner set AC price 0.50 and DC price 0.80 for location "Vienna Center"
    When the owner changes DC price to 0.90 for location "Vienna Center"
    Then the location "Vienna Center" has AC price 0.50 and DC price 0.90
