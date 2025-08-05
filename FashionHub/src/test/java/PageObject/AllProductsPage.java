package PageObject;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import Utilities.Waits;

public class AllProductsPage extends Waits{
	
	public WebDriver driver;
	SoftAssert softAssert = new SoftAssert();
	
	@FindBy(xpath="//h1[text()='All Products']")
	WebElement AllProductsHeading_xpath;
	
	@FindBy(xpath="//label[text()='Search']//following-sibling::input[@placeholder='Search products...']")
	WebElement SearchProducts_xpath;
	
	@FindBy(xpath="(//div[@class='row'])[1]/descendant::div[@class='card-body d-flex flex-column']/h6")
	List<WebElement> SearchedProducts_xpath;  
	
	@FindBy(xpath="//label[text()='Category']/following-sibling::select")
	WebElement CategoryDropDown_xpath;
	
	@FindBy(xpath="//label[text()='Brand']/following-sibling::select")
	WebElement BrandDropDown_xpath;
	
	@FindBy(xpath="//label[text()='Sort By']/following-sibling::select")
	WebElement SortByDropDown_xpath;
	
	@FindBy(xpath="//span[@class='badge bg-primary']")
	List<WebElement> FilteredProducts_xpath;
	
	@FindBy(xpath="(//div[@class='row'])[1]/descendant::div[@class='card-body d-flex flex-column']/p")
	List<WebElement> FilteredCompanyProductTags_xpath;
	
	@FindBy(xpath="//div[@class='mt-auto']/descendant::span[1]")
	List<WebElement> FilteredProductsPrice_xpath;
	
	@FindBy(xpath="//small[@class='text-muted']")
	List<WebElement> FilteredProductsRatings_xpath;
	
	@FindBy(xpath="(//div[@class='row'])[1]/descendant::div[@class='card-body d-flex flex-column']/h6")
	List<WebElement> FilteredProductsNames_xpath;

	
	public AllProductsPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void verifyAllProductPageLoaded()
	{
		waitToBeVisible(AllProductsHeading_xpath);
		Assert.assertTrue(AllProductsHeading_xpath.isDisplayed(),"All Products page is not loaded.");
	}
	
	public void searchProduct(String product)
	{
		waitToBeVisible(SearchProducts_xpath);
		SearchProducts_xpath.sendKeys(product);
		
	}
	
	public List<String> getSearchedProducts()
	{
		List<String> SearchProductsName = SearchedProducts_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		return SearchProductsName;
	}
	
	public List<List<String>> filterProducts(String companyOne, String companyTwo, String companyThree)
	{
		//Category Drop Down -------------------
		waitToBeVisible(CategoryDropDown_xpath);
		Select categorySelect = new Select(CategoryDropDown_xpath);
		
		categorySelect.selectByValue("Men");
		softAssert.assertEquals("Men (3)",categorySelect.getFirstSelectedOption().getText());
		List<String> FilteredMenProductsTags = FilteredProducts_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		
		categorySelect.selectByValue("Women");
		softAssert.assertEquals("Women (3)", categorySelect.getFirstSelectedOption().getText());
		List<String> FilteredWomenProductsTags = FilteredProducts_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		
		categorySelect.selectByValue("Footwear");
		softAssert.assertEquals("Footwear (1)", categorySelect.getFirstSelectedOption().getText());
		List<String> FilteredFootwearProductsTags = FilteredProducts_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		
		categorySelect.selectByValue("Accessories");
		softAssert.assertEquals("Accessories (1)", categorySelect.getFirstSelectedOption().getText());
		List<String> FilteredAccessoriesProductsTags = FilteredProducts_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		
		categorySelect.selectByVisibleText("All Categories");
		softAssert.assertEquals("All Categories",categorySelect.getFirstSelectedOption().getText());
		List<String> AllCategoryProductsTags = FilteredProducts_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		
		//Brand Drop Down -------------------
		Select brandSelect = new Select(BrandDropDown_xpath);
		
		brandSelect.selectByValue(companyOne);
		List<String> FilteredCompanyOneProductsTags = FilteredCompanyProductTags_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		
		brandSelect.selectByValue(companyTwo);
		List<String> FilteredCompanyTwoProductsTags = FilteredCompanyProductTags_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		
		brandSelect.selectByValue(companyThree);
		List<String> FilteredCompanyThreeProductsTags = FilteredCompanyProductTags_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		
		brandSelect.selectByVisibleText("All Brands");
		softAssert.assertEquals("All Brands",brandSelect.getFirstSelectedOption().getText());
		List<String> FilteredAllBrandsProductsTags = FilteredCompanyProductTags_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		
		//SortByDropDown---------------------------
		Select sortBySelect = new Select(SortByDropDown_xpath);
		
		sortBySelect.selectByValue("price-low");
		softAssert.assertEquals("Price: Low to High",sortBySelect.getFirstSelectedOption().getText());
		List<String> FilteredProductsByPriceLowToHigh = FilteredProductsPrice_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		
		sortBySelect.selectByValue("price-high");
		softAssert.assertEquals("Price: High to Low",sortBySelect.getFirstSelectedOption().getText());
		List<String> FilteredProductsByPriceHighToLow = FilteredProductsPrice_xpath.stream().map(WebElement::getText).collect(Collectors.toList());

		sortBySelect.selectByValue("rating");
		softAssert.assertEquals("Rating",sortBySelect.getFirstSelectedOption().getText());
		List<String> FilteredProductsByRating = FilteredProductsRatings_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		
		sortBySelect.selectByValue("name");
		softAssert.assertEquals("Name",sortBySelect.getFirstSelectedOption().getText());
		List<String> FilteredProductsNames = FilteredProductsNames_xpath.stream().map(WebElement::getText).collect(Collectors.toList());
		
		softAssert.assertAll();
		return Arrays.asList(FilteredMenProductsTags,FilteredWomenProductsTags, FilteredFootwearProductsTags, FilteredAccessoriesProductsTags, AllCategoryProductsTags, 
				FilteredCompanyOneProductsTags, FilteredCompanyTwoProductsTags, FilteredCompanyThreeProductsTags, FilteredAllBrandsProductsTags, FilteredProductsByPriceLowToHigh, 
				FilteredProductsByPriceHighToLow, FilteredProductsByRating, FilteredProductsNames);
	}
}
