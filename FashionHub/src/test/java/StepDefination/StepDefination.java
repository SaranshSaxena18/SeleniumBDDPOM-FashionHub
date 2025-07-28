package StepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import PageObject.HomePage;
import TestComponents.BaseTest;

public class StepDefination extends BaseTest
{
	public HomePage homePage;
	@Given("User visit the FashionHub Website")
	public void user_visit_the_fashion_hub_website() {
		homePage = launchApplication();
		System.out.println("here********");
		
	}

	@Given("scroll through the featured products")
	public void scroll_through_the_featured_products() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("user click on the view all products")
	public void user_click_on_the_view_all_products() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("visit the all products page")
	public void visit_the_all_products_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("search a product")
	public void search_a_product() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("product should be searched")
	public void product_should_be_searched() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
