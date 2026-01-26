package at.Technikum;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceStatusSteps {

    private final TestContext ctx;

    public InvoiceStatusSteps(TestContext ctx) {
        this.ctx = ctx;
    }

    @Then("an invoice exists for customer {string} with total cost {double}")
    public void an_invoice_exists(String customerId, double totalCost) {
        assertNotNull(ctx.lastInvoice);
        assertEquals(totalCost, ctx.lastInvoice.totalCost, 0.0001);
    }

    @And("the invoice status is {string}")
    public void the_invoice_status_is(String status) {
        assertNotNull(ctx.lastInvoice);
        assertEquals(status, ctx.lastInvoice.Status);
    }

    @And("the invoice shows customer {string}, location {string}, charger {string}, energy {int} kWh")
    public void the_invoice_shows_details(String customerId, String location, String chargerId, int kwh) {
        assertNotNull(ctx.lastInvoice);
        assertEquals(customerId, ctx.lastInvoice.CustomerID);
        assertEquals(location, ctx.lastInvoice.LocationName);
        assertEquals(chargerId, ctx.lastInvoice.ChargerID);
        assertEquals(kwh, ctx.lastInvoice.kWh);
    }
}
