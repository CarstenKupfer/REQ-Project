Feature: Invoice Status - Invoice is created after charging

  Scenario: invoice is created with status open and correct total cost
    Given a customer "C1" exists with name "Alice" and balance 20
    And a location "Vienna Center" exists
    And the owner sets AC price 0.50 and DC price 0.80 for location "Vienna Center"
    And an AC charger "AC1" exists at location "Vienna Center" with status "free"
    When customer "C1" starts charging at "AC1" for 4 kWh at location "Vienna Center"
    Then an invoice exists for customer "C1" with total cost 2.0
    And the invoice status is "open"
