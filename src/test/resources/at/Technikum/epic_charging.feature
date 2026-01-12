Feature: Vehicle Charging - Start Charging and Deduct Balance

  Scenario: customer starts charging and prepaid balance is deducted
    Given a customer "C1" exists with name "Alice" and balance 20
    And a location "Vienna Center" exists
    And the owner sets AC price 0.50 and DC price 0.80 for location "Vienna Center"
    And an AC charger "AC1" exists at location "Vienna Center" with status "free"
    When customer "C1" starts charging at "AC1" for 10 kWh at location "Vienna Center"
    Then the charger "AC1" status is "occupied"
    And the customer "C1" balance is 15

  Scenario: charging is rejected if charger is out of order
    Given a customer "C1" exists with name "Alice" and balance 20
    And a location "Vienna Center" exists
    And the owner sets AC price 0.50 and DC price 0.80 for location "Vienna Center"
    And an AC charger "AC1" exists at location "Vienna Center" with status "out_of_order"
    When customer "C1" tries to start charging at "AC1" for 5 kWh at location "Vienna Center"
    Then charging is rejected
    And the charger "AC1" status is "out_of_order"
    And the customer "C1" balance is 20
