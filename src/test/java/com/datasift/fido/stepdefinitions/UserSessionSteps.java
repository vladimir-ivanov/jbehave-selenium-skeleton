package com.datasift.fido.stepdefinitions;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class UserSessionSteps extends BaseSteps {

    @Given("the user goes to the website")
    public void visitTheHomePage() {
        selenium.open("/auth/login");
    }

    @When("the user populates the username and password fields with $username and $password")
    public void populateLoginForm(String username, String password) {

    }

   @When("the user submits the form")
    public void submitTheForm() {
       selenium.click("login_btn");
   }

    @Then("the user should see login success message")
    public void loggedInSuccess()
    {
        // implement
    }
}