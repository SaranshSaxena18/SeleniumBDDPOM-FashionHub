 package StepDefination;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.Status;

import PageObject.HomePage;
import TestComponents.BaseTest;
import Utilities.ExtentTestManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario; 

public class Hooks {
	WebDriver driver;
	public BaseTest baseTest;
    private static final ThreadLocal<String> SCENARIO_NAME = new ThreadLocal<>();//for thread safety in case of parallel execution

    @Before
    public void beforeScenario(Scenario scenario) {
    	  baseTest = new BaseTest();
    	  driver = baseTest.initializeDriver();
    	  String scenarioName = scenario.getName();
          System.out.println("HOOK EXECUTED: " + scenarioName);
          ExtentTestManager.createTest(scenarioName); 
          
    }

    public static String get() {
        return SCENARIO_NAME.get();
    }
    
    @After(order = 1)
   	public void tearDown(Scenario scenario) throws IOException {
   		if (scenario.isFailed()) {
   			
   			String screenshotPath = baseTest.getScreenshot(scenario.getName(),driver);
   			ExtentTestManager.getTest().log(Status.FAIL, "<a href='" + screenshotPath + "'>Screenshot</a>");
   			// take screenshot:
   			String screenshotName = scenario.getName().replaceAll(" ", "_");
   			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
   			scenario.attach(sourcePath, "image/png", screenshotName);
   			//ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: ");
   			
   		}
   	}
    
    @After(order=0)
	public void tearDown()
	{
    	ExtentTestManager.flush();
		driver.close();
		if (driver != null) {
            driver.quit(); // closes browser after all tests are done
        }
	}
}

