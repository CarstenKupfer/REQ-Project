Feature: Charger Management

  Scenario Outline: create a charger at an existing location
    Given a location "<location>" exists
    When the user adds an <type> charger "<chargerId>" to location "<location>"
    Then the location "<location>" has an <type> charger "<chargerId>"

    Examples:
      | location      | type | chargerId |
      | Vienna Center | AC   | AC1       |

  Scenario Outline: read a charger status
    Given a location "<location>" exists
    And the user adds an <type> charger "<chargerId>" to location "<location>"
    And the charger "<chargerId>" status is set to "<status>"
    Then the charger "<chargerId>" status is "<status>"

    Examples:
      | location      | type | chargerId | status       |
      | Vienna Center | AC   | AC1       | free         |
      | Vienna Center | DC   | DC1       | out_of_order |

  Scenario Outline: update a charger status
    Given a location "<location>" exists
    And the user adds an <type> charger "<chargerId>" to location "<location>"
    And the charger "<chargerId>" status is set to "free"
    When the user changes charger "<chargerId>" status to "<newStatus>"
    Then the charger "<chargerId>" status is "<newStatus>"

    Examples:
      | location      | type | chargerId | newStatus    |
      | Vienna Center | AC   | AC1       | occupied     |
      | Vienna Center | DC   | DC1       | out_of_order |

  Scenario Outline: delete a charger from a location
    Given a location "<location>" exists
    And the user adds an <type> charger "<chargerId>" to location "<location>"
    When the user deletes charger "<chargerId>" from location "<location>"
    Then the location "<location>" does not contain charger "<chargerId>"

    Examples:
      | location      | type | chargerId |
      | Vienna Center | AC   | AC1       |

  Scenario: deleting a non-existing charger is rejected
    Given a location "Vienna Center" exists
    When the user deletes charger "NOPE" from location "Vienna Center"
    Then the operation is rejected
