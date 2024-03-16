package stepDefinition;

import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.When;
import cucumber.annotation.en.Then;

public class StepDefinition {
	

	@Given("^User is on landing page$")
	public void user_is_on_landing_page()
	{
		System.out.println("user login");
	}
	
	@When("^User login to application  with useername and password$")
	public void user_login_with_username_password()
	{
		System.out.println("user login with creds");
	}
	
	@Then("^Home page is displayed$")
	public void home_page_displayed( ) {
		System.out.println("home page displayed");	
	}
	
	@And("^Cards are displayed$")
	public void cards_are_displayed() {
		System.out.println("cards are displayed");
	}
	
}
