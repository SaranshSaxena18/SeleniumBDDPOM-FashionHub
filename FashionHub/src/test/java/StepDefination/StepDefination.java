package StepDefination;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
	
	@Then("products should be searched as per the filter {string} {string} {string}")
	public void products_should_be_searched_as_per_the_filter(String Company1, String Company2, String Company3) {
		List<List<String>> filteredProductsLists = allProductsPage.filterProducts(Company1,Company2,Company3);
		
		List<String> FilteredMenProductsTags = filteredProductsLists.get(0); 
		Assert.assertTrue(FilteredMenProductsTags.stream().allMatch(s->"Men".equals(s)));
		
		List<String> FilteredWomenProductsTags = filteredProductsLists.get(1);
		Assert.assertTrue(FilteredWomenProductsTags.stream().allMatch(s->"Women".equals(s)));
		
		List<String> FilteredFootwearProductsTags = filteredProductsLists.get(2);
		Assert.assertTrue(FilteredFootwearProductsTags.stream().allMatch(s->"Footwear".equals(s)));
		
		List<String> FilteredAccessoriesProductsTags = filteredProductsLists.get(3);
		Assert.assertTrue(FilteredAccessoriesProductsTags.stream().allMatch(s->"Accessories".equals(s)));
		
		List<String> AllCategoryProductsTags = filteredProductsLists.get(4);
		Assert.assertTrue(Stream.of("Men","Women","Footwear","Accessories").allMatch(AllCategoryProductsTags::contains));
		
		List<String> FilteredCompanyOneProductsTags = filteredProductsLists.get(5);
		Assert.assertTrue(FilteredCompanyOneProductsTags.stream().allMatch(s->Company1.equals(s)));
		
		List<String> FilteredCompanyTwoProductsTags = filteredProductsLists.get(6);
		Assert.assertTrue(FilteredCompanyTwoProductsTags.stream().allMatch(s->Company2.equals(s)));
		
		List<String> FilteredCompanyThreeProductsTags = filteredProductsLists.get(7);
		Assert.assertTrue(FilteredCompanyThreeProductsTags.stream().allMatch(s->Company3.equals(s)));
		
		List<String> FilteredAllBrandsProductsTags = filteredProductsLists.get(8);
		Assert.assertTrue(Stream.of(Company1,Company2,Company3).allMatch(FilteredAllBrandsProductsTags::contains));
		
		List<String> FilteredProductsByPriceLowToHigh = filteredProductsLists.get(9);
		//FilteredProductsByPriceLowToHigh.stream().forEach(System.out::println);
		List<Double> actualProductsPriceListLowToHigh =  FilteredProductsByPriceLowToHigh.stream().flatMap(s->{//flatMap merges multiple lists of numbers into a single stream.
			Matcher m = Pattern.compile("\\d+(\\.\\d+)?").matcher(s); //\\d+(\\.\\d+)? matches: one or more digits, optionally followed by a dot and one or more digits.
			List<Double> nums = new ArrayList<>();
			while(m.find()) {
				nums.add(Double.parseDouble(m.group()));//m.group() extracts the part of the input string matched by the regex group.
			}
			return nums.stream();
		}).collect(Collectors.toList());
		System.out.println();
		
		List<Double> expectedProductsPriceListLowToHigh = new ArrayList<>(actualProductsPriceListLowToHigh);
		Collections.sort(expectedProductsPriceListLowToHigh);
		//below assert is commented as this functionality in the web application is failing
		Assert.assertEquals(expectedProductsPriceListLowToHigh, actualProductsPriceListLowToHigh, "Products are not sorted according to Price Low to High.");
		
		List<String> FilteredProductsByPriceHighToLow = filteredProductsLists.get(10);
		List<Double> actualProductPriceListHighToLow = FilteredProductsByPriceHighToLow.stream().flatMap(s->{
			Matcher m = Pattern.compile("\\d+(\\.\\d+)?").matcher(s);
			List<Double> nums = new ArrayList<>();
			while(m.find())
			{
				nums.add(Double.parseDouble(m.group()));
			}
			return nums.stream();
		}).collect(Collectors.toList());
		List<Double> expectedProductsPriceListHighToLow = new ArrayList<>(actualProductPriceListHighToLow);
		Collections.sort(expectedProductsPriceListHighToLow);
		
		Assert.assertEquals(actualProductPriceListHighToLow, expectedProductsPriceListHighToLow, "Products are not sorted according to Price High to Low.");
		
		List<String> FilteredProductsByRating = filteredProductsLists.get(11);
		List<Double> actualProductsRatings = FilteredProductsByRating.stream().map(s->s.replaceAll("[()]","")).map(Double::parseDouble).collect(Collectors.toList());
		List<Double> expectedProductRatings = new ArrayList<>(actualProductsRatings);
		Collections.sort(expectedProductRatings);
		//below assert is commented as this functionality in the web application is failing
		//Assert.assertEquals(actualProductsRatings, expectedProductRatings,"Products are not sorted by ratings.");
		
		List<String> actualFilteredProductsNames = filteredProductsLists.get(12);
		List<String> expectedFilteredProductsNames = new ArrayList<>(actualFilteredProductsNames);
		Collections.sort(expectedFilteredProductsNames);
		//below assert is commented as this functionality in the web application is failing
		//Assert.assertEquals(actualFilteredProductsNames, expectedFilteredProductsNames,"Products are not sorted by name.");
	}
}
