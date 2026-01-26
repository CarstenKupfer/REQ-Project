Feature: Location Management

  Scenario Outline: create a new location
    When the user creates a new location "<location>"
    Then the location "<location>" exists
    And the location "<location>" has no charging stations

    Examples:
      | location      |
      | Vienna Center |

  Scenario Outline: create a location based on an existing location
    Given a location "<draftLocation>" exists
    And the location "<draftLocation>" contains an <type> charger "<chargerId>"
    When the user creates a new location "<newLocation>" based on "<draftLocation>"
    Then the location "<newLocation>" exists
    And the location "<newLocation>" has an <type> charger "<chargerId>"

    Examples:
      | draftLocation | newLocation   | type | chargerId |
      | Vienna Center | Vienna Copy   | AC   | AC1       |

  Scenario Outline: delete an existing location
    Given a location "<location>" exists
    When the user deletes the location "<location>"
    Then the location "<location>" status is "deleted"

    Examples:
      | location      |
      | Vienna Center |

  Scenario Outline: add a charger to an existing location
    Given a location "<location>" exists
    When the user adds an <type> charger "<chargerId>" to location "<location>"
    Then the location "<location>" has an <type> charger "<chargerId>"
    And the location "<location>" status is "featureactive"

    Examples:
      | location      | type | chargerId |
      | Vienna Center | AC   | AC1       |

  Scenario: creating the same location twice does not create duplicates
    Given a location "Vienna Center" exists
    When the user creates a new location "Vienna Center"
    Then the location "Vienna Center" exists
