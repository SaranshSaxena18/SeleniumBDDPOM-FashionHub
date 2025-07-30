package PageObject;


import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import Utilities.Utility;

public class AllProductsPage extends Utility{
	
	public WebDriver driver;
	SoftAssert softAssert = new SoftAssert();
	
	@FindBy(xpath="//h1[text()='All Products']")
	WebElement AllProductsHeading;
	
	@FindBy(xpath="//label[text()='Search']//following-sibling::input[@placeholder='Search products...']")
	WebElement SearchProducts;
	
	@FindBy(xpath="(//div[@class='row'])[1]/descendant::div[@class='card-body d-flex flex-column']/h6")
	List<WebElement> SearchedProducts;  
	
	@FindBy(xpath="//label[text()='Category']/following-sibling::select")
	WebElement CategoryDropDown;
	
	@FindBy(xpath="//label[text()='Brand']/following-sibling::select")
	WebElement BrandDropDown;
	
	@FindBy(xpath="(//div[@class='row'])[1]/descendant::div[@class='card-body d-flex flex-column']/h6")
	List<WebElement> FilteredProducts;
	
	public AllProductsPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void verifyAllProductPageLoaded()
	{
		waitToBeVisible(AllProductsHeading);
		Assert.assertTrue(AllProductsHeading.isDisplayed(),"All Products page is not loaded.");
	}
	
	public void searchProduct(String product)
	{
		waitToBeVisible(SearchProducts);
		SearchProducts.sendKeys(product);
		
	}
	
	public List<String> getSearchedProducts()
	{
		List<String> SearchProductsName = SearchedProducts.stream().map(WebElement::getText).collect(Collectors.toList());
		return SearchProductsName;
	}
	
	public void filterProducts()
	{
		//Category Drop Down -------------------
		waitToBeVisible(CategoryDropDown);
		Select categorySelect = new Select(CategoryDropDown);
		categorySelect.selectByValue("Men");
		softAssert.assertEquals("Men (3)",categorySelect.getFirstSelectedOption().getText());
		List<String> FilteredMenProductsName = FilteredProducts.stream().map(WebElement::getText).collect(Collectors.toList());
		
		categorySelect.selectByValue("Women");
		softAssert.assertEquals("Women (3)", categorySelect.getFirstSelectedOption().getText());
		List<String> FilteredWomenProductsName = FilteredProducts.stream().map(WebElement::getText).collect(Collectors.toList());
		
		categorySelect.selectByValue("Footwear");
		softAssert.assertEquals("Footwear (1)", categorySelect.getFirstSelectedOption().getText());
		List<String> FilteredFootwearProductsName = FilteredProducts.stream().map(WebElement::getText).collect(Collectors.toList());
		
		categorySelect.selectByValue("Accessories");
		softAssert.assertEquals("Accessories (1)", categorySelect.getFirstSelectedOption().getText());
		List<String> FilteredAccessoriesProductsName = FilteredProducts.stream().map(WebElement::getText).collect(Collectors.toList());
		
		//Brand Drop Down -------------------
		Select brandSelect = new Select(BrandDropDown);
		brandSelect.selectByValue("Nike");
		softAssert.assertEquals("Nike (1)", brandSelect.getFirstSelectedOption().getText());
		
		brandSelect.selectByValue("Zara");
		softAssert.assertEquals("Zara (1)", brandSelect.getFirstSelectedOption().getText());
		
		brandSelect.selectByValue("Levi's");
		softAssert.assertEquals("Levi's (1)", brandSelect.getFirstSelectedOption().getText());
		
		brandSelect.selectByValue("Coach");
		softAssert.assertEquals("Coach (1)", brandSelect.getFirstSelectedOption().getText());
		
		brandSelect.selectByValue("Adidas");
		softAssert.assertEquals("Adidas (1)", brandSelect.getFirstSelectedOption().getText());
		
		brandSelect.selectByValue("H&M");
		softAssert.assertEquals("H&M (1)", brandSelect.getFirstSelectedOption().getText());
		
		brandSelect.selectByValue("Calvin Klein");
		softAssert.assertEquals("Calvin Klein (1)", brandSelect.getFirstSelectedOption().getText());
		
		brandSelect.selectByValue("Gap");
		softAssert.assertEquals("Gap (1)", brandSelect.getFirstSelectedOption().getText());
	}

}
