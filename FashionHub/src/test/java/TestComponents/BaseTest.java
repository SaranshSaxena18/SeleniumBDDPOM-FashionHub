package TestComponents;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import PageObject.HomePage;
import io.cucumber.java.After;

public class BaseTest {
	private WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	public HomePage homePage;
	public WebDriver initializeDriver()
	{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//java//Resources//BrowserDrivers//chromedriver-win64//chromedriver.exe");
		tlDriver.set(new ChromeDriver());
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public HomePage launchApplication()
	{
		homePage = new HomePage(getDriver());
		homePage.visitApplication();
		return homePage;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);

	    String reportsPath = System.getProperty("user.dir") + File.separator + "reports";
	    File directory = new File(reportsPath);
	    if (!directory.exists()) {
	        directory.mkdirs();
	    }

	    File file = new File(reportsPath + File.separator + testCaseName + ".png");
	    FileUtils.copyFile(source, file);
	    return file.getAbsolutePath();
	}

	
}
