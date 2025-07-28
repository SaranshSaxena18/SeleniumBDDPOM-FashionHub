package PageObject;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void visitApplication()
	{
		driver.get("https://ecommercepracticeportal.netlify.app/");
		String actualTitle = driver.getTitle();
		String expectedTitle = "FashionHub - E-Commerce Fashion Website";
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}
	
}
