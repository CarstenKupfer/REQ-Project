package com.TextEditor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    Location[] Locations = new Location[4];


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

}
