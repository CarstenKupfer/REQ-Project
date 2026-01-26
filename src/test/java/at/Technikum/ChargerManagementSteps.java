package at.Technikum;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class ChargerManagementSteps {

    private final TestContext ctx;

    public ChargerManagementSteps(TestContext ctx) {
        this.ctx = ctx;
    }

    @And("the charger {string} status is set to {string}")
    public void the_charger_status_is_set_to(String chargerId, String status) {
        Charger c = ctx.app.findCharger(chargerId);
        assertNotNull(c);
        c.updateStatus(status);
    }

    @When("the user changes charger {string} status to {string}")
    public void the_user_changes_charger_status_to(String chargerId, String newStatus) {
        Charger c = ctx.app.findCharger(chargerId);
        assertNotNull(c);
        c.updateStatus(newStatus);
    }

    @Then("the location {string} does not contain charger {string}")
    public void the_location_does_not_contain_charger(String locationName, String chargerId) {
        Location loc = ctx.app.getLocation(locationName);
        assertNotNull(loc);

        for (Charger c : loc.Chargers) {
            if (c != null && c.ChargerID.equals(chargerId)) {
                fail("Charger still exists at location");
            }
        }
        assertTrue(true);
    }
    @When("the user deletes charger {string} from location {string}")
    public void the_user_deletes_charger(String chargerId, String locationName) {
        try {
            ctx.app.removeCharger(locationName, chargerId);
            ctx.lastRejected = false;
        } catch (IllegalStateException ex) {
            ctx.lastRejected = true;
        }
    }
}
