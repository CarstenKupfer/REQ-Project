package at.Technikum;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    Location[] Locations = new Location[4];
    LocationManager locationManager = new LocationManager();
    CustomerManager customerManager = new CustomerManager();
    ChargingStationManager chargingStationManager = new ChargingStationManager();
    InvoiceManager invoiceManager = new InvoiceManager();

    boolean chargingRejected = false;
    Invoice lastInvoice = null;
    NetworkOverview lastOverview = null;


    @Given("no location is used as draft")
    public void no_location_is_used_as_draft() {
    }
    @When("the user creates a new location")
    public void the_user_creates_a_new_location() {
    Locations[0] = new Location("Test",31.5,31.5);
    }
    @Then("the location will come with no features at the initial state")
    public void he_is_asked_to_create_a_new_charching_station_as_well() {
    assertEquals("Test;31.5;31.5;new",Locations[0].toString());
    assertEquals(null,Locations[0].Chargers[0] );
    }

    @Given("an existing location is used as draft")
    public void an_existing_location_is_used_as_draftt() {
        Locations[0] = new Location("Test",31.5,31.5);

    }
    @When("the user creates a new location2")
    public void the_user_creates_a_new_location2() {
        Locations[1] = new Location (Locations[0]);
    }
    @Then("the charging stations will be copied from the draft as well")
    public void the_charging_stations_will_be_copied_from_the_draft_as_well() {
        for (int i = 0; i < Locations[0].Chargers.length;i++)
        {
            if (Locations[0].Chargers[i] != null)
            {
            assertEquals(Locations[0].Chargers[i].toString(),Locations[1].Chargers[i].toString());}}
    }






    @Given("there is an existant location")
    public void there_is_an_existant_location() {
        Locations[0] = new Location("Test",31.5,31.5);
    }
    @When("the user deletes an existant location")
    public void the_user_deletes_an_existant_location() {
        Locations[0].delete();
    }
    @Then("the location is not deleted from the database but moved to the folder of deleted elements")
    public void the_location_is_not_deleted_from_the_database_but_moved_to_the_folder_of_deleted_elements() {
        assertEquals("Test;31.5;31.5;deleted",Locations[0].toString());
    }


    @Given("there is an existant location1")
      public void there_is_an_existant_location1() {
        Locations[0] = new Location("Test",31.5,31.5);
    }

    @When("the user adds new features to an exiting location")
    public void the_user_adds_new_features_to_an_exiting_location() {
        Locations[0].addfeature("AC","AC1",0);
    }
    @Then("the location parameters are updated")
    public void the_location_parameters_are_updated() {
        assertEquals("AC1;AC;null",Locations[0].Chargers[0].toString());
    }
    @And ("the location is set to active")
    public void the_location_is_set_to_active ()
    {
        assertEquals("Test;31.5;31.5;featureactive",Locations[0].toString());
    }

    // IA7 Abgabe

    // ----Pricing----

    @Given("a location {string} exists")
    public void a_location_exists(String locationName) {
        locationManager.openNewLocation(locationName);
    }

    @When("the owner sets AC price {double} and DC price {double} for location {string}")
    public void the_owner_sets_prices(double acPrice, double dcPrice, String locationName) {
        locationManager.setPrices(locationName, acPrice, dcPrice);
    }

    @And("the owner set AC price {double} and DC price {double} for location {string}")
    public void the_owner_set_prices(double acPrice, double dcPrice, String locationName) {
        locationManager.setPrices(locationName, acPrice, dcPrice);
    }

    @When("the owner changes DC price to {double} for location {string}")
    public void the_owner_changes_dc_price(double newDcPrice, String locationName) {
        double currentAc = locationManager.getAcPrice(locationName);
        locationManager.setPrices(locationName, currentAc, newDcPrice);
    }

    @Then("the location {string} has AC price {double} and DC price {double}")
    public void the_location_has_prices(String locationName, double acPrice, double dcPrice) {
        assertEquals(acPrice, locationManager.getAcPrice(locationName), 0.0001);
        assertEquals(dcPrice, locationManager.getDcPrice(locationName), 0.0001);
    }

    // ----Charging----

    @Given("a customer {string} exists with name {string} and balance {int}")
    public void a_customer_exists(String customerId, String name, int balance) {
        customerManager.registerAccount(name, customerId);
        customerManager.setBalance(customerId, balance);
    }

    @And("an AC charger {string} exists at location {string} with status {string}")
    public void an_ac_charger_exists(String chargerId, String locationName, String status) {
        locationManager.addCharger(locationName, chargerId, "AC");
        locationManager.setChargerStatus(locationName, chargerId, status);
    }

    @And("a DC charger {string} exists at location {string} with status {string}")
    public void a_dc_charger_exists(String chargerId, String locationName, String status) {
        locationManager.addCharger(locationName, chargerId, "DC");
        locationManager.setChargerStatus(locationName, chargerId, status);
    }

    @When("customer {string} starts charging at {string} for {int} kWh at location {string}")
    public void customer_starts_charging(String customerId, String chargerId, int kwh, String locationName) {
        chargingRejected = false;
        lastInvoice = null;

        try {
            lastInvoice = chargingStationManager.startCharging(
                    locationManager,
                    customerManager,
                    invoiceManager,
                    locationName,
                    chargerId,
                    customerId,
                    kwh
            );
        } catch (IllegalStateException ex) {
            chargingRejected = true;
        }
    }

    @When("customer {string} tries to start charging at {string} for {int} kWh at location {string}")
    public void customer_tries_to_start_charging(String customerId, String chargerId, int kwh, String locationName) {
        customer_starts_charging(customerId, chargerId, kwh, locationName);
    }

    @Then("the charger {string} status is {string}")
    public void the_charger_status_is(String chargerId, String expectedStatus) {
        Charger c = locationManager.findChargerById(chargerId);
        assertNotNull(c);
        assertEquals(expectedStatus, c.Status);
    }

    @And("the customer {string} balance is {int}")
    public void the_customer_balance_is(String customerId, int expectedBalance) {
        assertEquals(expectedBalance, customerManager.getBalance(customerId));
    }

    @Then("charging is rejected")
    public void charging_is_rejected() {
        assertTrue(chargingRejected);
    }

    // ----Network Status----

    @When("the owner requests the network status overview for location {string}")
    public void the_owner_requests_overview(String locationName) {
        Location loc = locationManager.getLocation(locationName);
        assertNotNull(loc);
        lastOverview = chargingStationManager.getOverview(loc);
    }

    @Then("the overview for location {string} shows {int} free, {int} occupied, {int} out_of_order chargers")
    public void the_overview_shows(String locationName, int free, int occupied, int outOfOrder) {
        assertNotNull(lastOverview);
        assertEquals(free, lastOverview.free);
        assertEquals(occupied, lastOverview.occupied);
        assertEquals(outOfOrder, lastOverview.outOfOrder);
    }

    // ----Invoice Status----

    @Then("an invoice exists for customer {string} with total cost {double}")
    public void an_invoice_exists(String customerId, double totalCost) {
        assertNotNull(lastInvoice);
        assertEquals(totalCost, lastInvoice.totalCost, 0.0001);
    }

    @And("the invoice status is {string}")
    public void the_invoice_status_is(String status) {
        assertNotNull(lastInvoice);
        assertEquals(status, lastInvoice.Status);
    }





}
