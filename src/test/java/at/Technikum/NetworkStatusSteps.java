package at.Technikum;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class NetworkStatusSteps {

    private final TestContext ctx;

    public NetworkStatusSteps(TestContext ctx) {
        this.ctx = ctx;
    }

    @When("the owner requests the network status overview for location {string}")
    public void the_owner_requests_overview(String locationName) {
        ctx.lastOverview = ctx.app.getOverview(locationName);
        assertNotNull(ctx.lastOverview);
    }

    @Then("the overview for location {string} shows {int} free, {int} occupied, {int} out_of_order chargers")
    public void the_overview_shows(String locationName, int free, int occupied, int outOfOrder) {
        assertNotNull(ctx.lastOverview);
        assertEquals(free, ctx.lastOverview.free);
        assertEquals(occupied, ctx.lastOverview.occupied);
        assertEquals(outOfOrder, ctx.lastOverview.outOfOrder);
    }
    @Then("the overview for location {string} shows prices AC {double} and DC {double}")
    public void overview_shows_prices(String location, double ac, double dc) {
        assertNotNull(ctx.lastOverview);
        assertEquals(ac, ctx.lastOverview.acPrice, 0.0001);
        assertEquals(dc, ctx.lastOverview.dcPrice, 0.0001);
    }

}
