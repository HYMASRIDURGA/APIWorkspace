package stepDefinations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class StepDefinition {

    @Given("^User is on landing page$")
    public void user_is_on_landing_page() throws Throwable {
       //code on landing page
    	System.out.println("navigated to landing page");
    }

    @When("^user login into application with username and password$")
    public void user_login_into_application_with_username_and_password() throws Throwable {
     //code to login
    	System.out.println("navigated to login page");
    }

    @Then("^Home page is populated$")
    public void home_page_is_populated() throws Throwable {
      //home page validation
    	System.out.println("home page");
    }

    @And("^Cards are displayed $")
    public void cards_are_displayed() throws Throwable {
       //cards are displayed
    	System.out.println("cards");
    }

}