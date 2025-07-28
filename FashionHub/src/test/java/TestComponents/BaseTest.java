package TestComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import PageObject.HomePage;
import io.cucumber.java.After;

public class BaseTest {
	public WebDriver driver;
	public HomePage homePage;
	public WebDriver initializeDriver()
	{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//java//Resources//BrowserDrivers//chromedriver-win64//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	

	public HomePage launchApplication()
	{
		driver = initializeDriver();
		homePage = new HomePage(driver);
		homePage.visitApplication();
		return homePage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
		if (driver != null) {
            driver.quit(); // closes browser after all tests are done
        }
	}
}
