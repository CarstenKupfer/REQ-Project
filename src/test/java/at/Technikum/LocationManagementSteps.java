package at.Technikum;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class LocationManagementSteps {

    private final TestContext ctx;

    public LocationManagementSteps(TestContext ctx) {
        this.ctx = ctx;
    }

    @When("the user creates a new location {string}")
    public void the_user_creates_a_new_location(String locationName) {
        ctx.app.createLocation(locationName);
    }

    @Then("the location {string} exists")
    public void the_location_exists(String locationName) {
        Location loc = ctx.app.getLocation(locationName);
        assertNotNull(loc);
    }

    @And("the location {string} has no charging stations")
    public void the_location_has_no_charging_stations(String locationName) {
        Location loc = ctx.app.getLocation(locationName);
        assertNotNull(loc);

        boolean hasCharger = false;
        for (Charger c : loc.Chargers) {
            if (c != null) {
                hasCharger = true;
                break;
            }
        }
        assertFalse(hasCharger);
    }

    @Given("a location {string} exists")
    public void a_location_exists(String locationName) {
        ctx.app.createLocation(locationName);
    }

    @And("the location {string} contains an {word} charger {string}")
    public void the_location_contains_a_charger(String locationName, String type, String chargerId) {
        ctx.app.addCharger(locationName, chargerId, type, "free");
    }

    @When("the user creates a new location {string} based on {string}")
    public void the_user_creates_a_new_location_based_on(String newLocation, String baseLocation) {
        ctx.app.copyLocation(newLocation, baseLocation);
    }

    @When("the user deletes the location {string}")
    public void the_user_deletes_the_location(String locationName) {
        ctx.app.deleteLocation(locationName);
    }

    @Then("the location {string} status is {string}")
    public void the_location_status_is(String locationName, String expectedStatus) {
        Location loc = ctx.app.getLocation(locationName);
        assertNotNull(loc);
        assertEquals(expectedStatus, loc.status);
    }

    @When("the user adds an {word} charger {string} to location {string}")
    public void the_user_adds_a_charger_to_location(String type, String chargerId, String locationName) {
        ctx.app.addCharger(locationName, chargerId, type, "free");
    }

    @Then("the location {string} has an {word} charger {string}")
    public void the_location_has_a_charger(String locationName, String type, String chargerId) {
        Location loc = ctx.app.getLocation(locationName);
        assertNotNull(loc);

        boolean found = false;
        for (Charger c : loc.Chargers) {
            if (c != null && chargerId.equals(c.ChargerID) && type.equals(c.Type)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
}
