package at.Technikum;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChargingSteps {

    private final TestContext ctx;

    public ChargingSteps(TestContext ctx) {
        this.ctx = ctx;
    }

    @Given("a customer {string} exists with name {string} and balance {int}")
    public void a_customer_exists(String customerId, String name, int balance) {
        ctx.app.registerCustomer(name, customerId);
        ctx.app.setBalance(customerId, balance);
    }

    @And("an AC charger {string} exists at location {string} with status {string}")
    public void an_ac_charger_exists(String chargerId, String locationName, String status) {
        ctx.app.createLocation(locationName);
        ctx.app.addCharger(locationName, chargerId, "AC", status);
    }

    @And("a DC charger {string} exists at location {string} with status {string}")
    public void a_dc_charger_exists(String chargerId, String locationName, String status) {
        ctx.app.createLocation(locationName);
        ctx.app.addCharger(locationName, chargerId, "DC", status);
    }

    @When("customer {string} starts charging at {string} for {int} kWh at location {string}")
    public void customer_starts_charging(String customerId, String chargerId, int kwh, String locationName) {
        ctx.chargingRejected = false;
        ctx.lastInvoice = null;

        try {
            ctx.lastInvoice = ctx.app.startCharging(locationName, chargerId, customerId, kwh);
        } catch (IllegalStateException ex) {
            ctx.chargingRejected = true;
        }
    }

    @When("customer {string} tries to start charging at {string} for {int} kWh at location {string}")
    public void customer_tries_to_start_charging(String customerId, String chargerId, int kwh, String locationName) {
        customer_starts_charging(customerId, chargerId, kwh, locationName);
    }

    @Then("the charger {string} status is {string}")
    public void the_charger_status_is(String chargerId, String expectedStatus) {
        Charger c = ctx.app.findCharger(chargerId);
        assertNotNull(c);
        assertEquals(expectedStatus, c.Status);
    }

    @And("the customer {string} balance is {int}")
    public void the_customer_balance_is(String customerId, int expectedBalance) {
        assertEquals(expectedBalance, ctx.app.getBalance(customerId));
    }

    @Then("charging is rejected")
    public void charging_is_rejected() {
        assertTrue(ctx.chargingRejected);
    }
}
