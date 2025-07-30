package StepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import PageObject.AllProductsPage;
import PageObject.HomePage;
import TestComponents.BaseTest;

public class StepDefination extends BaseTest
{
	SoftAssert softAssert = new SoftAssert();
	public HomePage homePage;
	public AllProductsPage allProductsPage;
	@Given("User visit the FashionHub Website")
	public void user_visit_the_fashion_hub_website() {
		homePage = launchApplication();
		
	}

	@Given("user scroll through the featured products")
	public void user_scroll_through_the_featured_products() {
		List<String> ActualProducts = homePage.returnFeaturedProducts();
		List<String> ExpectedProducts = new ArrayList<>();
		ExpectedProducts.add("Classic White T-Shirt");
		ExpectedProducts.add("Floral Summer Dress");
		ExpectedProducts.add("Slim Fit Jeans");
		ExpectedProducts.add("Leather Crossbody Bag");
		softAssert.assertEquals(ActualProducts, ExpectedProducts, "Featured Products are unexpected.");
		softAssert.assertAll();
	}

	@When("user click on the view all products")
	public void user_click_on_the_view_all_products() {
		allProductsPage = homePage.visitAllProductsPage();
	}

	 @When("search a (.+)$")
	 public void search_a_product(String product){
		 allProductsPage.searchProduct(product);
	}

	@Then("(.+) should be searched$")
	public void product_should_be_searched(String product) {
		List<String> searchedProductsName = allProductsPage.getSearchedProducts();
		Assert.assertEquals(product, searchedProductsName.getFirst()," "+product+" was not searched, hence search functionality is not working.");
	}
	
	@Then("products should be searched as per the filter")
	public void products_should_be_searched_as_per_the_filter() {
		allProductsPage.filterProducts();
	}


}
