package PageObject;


import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import Utilities.Waits;

public class HomePage extends Waits{
	WebDriver driver;
	public AllProductsPage allProductsPage;
	
	@FindBy(xpath="(//div[@class='row'])[2]")
	WebElement FeaturedProductsRow;
	
	@FindBy(xpath="(//div[@class='row'])[2]/descendant::div[@class='card-body d-flex flex-column']/h6")
	List<WebElement> FeaturedProducts;
	
	@FindBy(xpath="//div[@class='text-center mt-4']/a")
	WebElement ViewAllProductsButton;
	
	@FindBy(xpath="(//a[@href='/products'])[2]")
	WebElement ShopNowButton;
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void visitApplication()
	{
		driver.get("https://ecommercepracticeportal.netlify.app/");
		String actualTitle = driver.getTitle();
		String expectedTitle = "FashionHub - E-Commerce Fashion Website";
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	public List<String> returnFeaturedProducts()
	{
		scrollTo(FeaturedProductsRow);
		List<String> Products = FeaturedProducts.stream().map(WebElement::getText).collect(Collectors.toList());
		return Products;
	}
	
	public AllProductsPage visitAllProductsPage()
	{
		waitToBeClickableThenClick(ViewAllProductsButton);
		allProductsPage = new AllProductsPage(driver);
		allProductsPage.verifyAllProductPageLoaded();
		return allProductsPage;
	}
	
	public AllProductsPage goToShopNowPage()
	{
		waitToBeClickableThenClick(ShopNowButton);
		allProductsPage = new AllProductsPage(driver);
		//allProductsPage.verifyAllProductPageLoaded();
		return allProductsPage;
	}
}
