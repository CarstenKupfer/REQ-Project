package at.Technikum;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class PricingSteps {

    private final TestContext ctx;

    public PricingSteps(TestContext ctx) {
        this.ctx = ctx;
    }

    @When("the owner sets AC price {double} and DC price {double} for location {string}")
    public void the_owner_sets_prices(double acPrice, double dcPrice, String locationName) {
        ctx.lastRejected = false;
        try {
            ctx.app.setPrices(locationName, acPrice, dcPrice);
        }catch (IllegalStateException ex){
            ctx.lastRejected = true;
        }
    }

    @And("the owner set AC price {double} and DC price {double} for location {string}")
    public void the_owner_set_prices(double acPrice, double dcPrice, String locationName) {
        ctx.app.setPrices(locationName, acPrice, dcPrice);
    }

    @When("the owner changes DC price to {double} for location {string}")
    public void the_owner_changes_dc_price(double newDcPrice, String locationName) {
        double currentAc = ctx.app.getAcPrice(locationName);
        ctx.app.setPrices(locationName, currentAc, newDcPrice);
    }

    @Then("the location {string} has AC price {double} and DC price {double}")
    public void the_location_has_prices(String locationName, double acPrice, double dcPrice) {
        ctx.app.setPrices(locationName, acPrice, dcPrice);
    }
}
