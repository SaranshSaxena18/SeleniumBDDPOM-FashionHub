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
    	  baseTest = new BaseTest();// Create a new instance of BaseTest for each scenario
    	  driver = baseTest.initializeDriver();// Initialize WebDriver
    	  SCENARIO_NAME.set(scenario.getName());// Store scenario name in ThreadLocal
    	  String scenarioName = scenario.getName();// Get scenario name
          System.out.println("HOOK EXECUTED: " + scenarioName);// Print scenario name
          ExtentTestManager.createTest(scenarioName); // Create a new test in ExtentReports
    }

//    public static String get() {
//        return SCENARIO_NAME.get();//
//    }
    
    @After(order = 1)
   	public void tearDown(Scenario scenario) throws IOException {
   		if (scenario.isFailed()) {
   			String screenshotPath = baseTest.getScreenshot(scenario.getName(),driver);// call the method from BaseTest class
   			ExtentTestManager.getTest().log(Status.FAIL, "<a href='" + screenshotPath + "'>Screenshot</a>");// add screenshot to report
   			String screenshotName = scenario.getName().replaceAll(" ", "_");// to avoid spaces in the name
   			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);// take screenshot as byte array
   			scenario.attach(sourcePath, "image/png", screenshotName);// attach screenshot to scenario
   		}
   	}
    
    @After(order=0)
	public void tearDown()
	{
    	ExtentTestManager.flush();// flush the report
		driver.close();
		if (driver != null) {
            driver.quit(); // closes browser after all tests are done
        }
	}
}

