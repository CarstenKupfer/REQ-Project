package at.Technikum;

import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

public class CommonSteps {

    private final TestContext ctx;

    public CommonSteps(TestContext ctx) {
        this.ctx = ctx;
    }

    @Then("the operation is rejected")
    public void the_operation_is_rejected() {
        assertTrue(ctx.lastRejected);
    }
}
