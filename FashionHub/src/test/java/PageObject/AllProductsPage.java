package PageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import Utilities.Utility;

public class AllProductsPage extends Utility{
	
	public WebDriver driver;
	
	@FindBy(xpath="//h1[text()='All Products']")
	WebElement AllProductsHeading;
	
	public AllProductsPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void verifyAllProductPageLoaded()
	{
		waitToBeVisile(AllProductsHeading);
		Assert.assertTrue(AllProductsHeading.isDisplayed(),"All Products page is not loaded.");
	}

}
