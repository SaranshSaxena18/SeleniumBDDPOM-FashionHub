package Runner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;

@CucumberOptions(
		 	features = "src/main/java/Features",
			glue="StepDefination",
			tags = "@CurrentDev"
		)
public class TestRunner extends AbstractTestNGCucumberTests {

//	 @Override
//	    @DataProvider(parallel = false)
//	    public Object[][] scenarios() {
//	        return super.scenarios();
//	    }
//
//	    @Test(dataProvider = "TestData")
//	    public void runScenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
//	        // Your step definitions will execute scenarios one by one
//	        // To pass external DataProvider data into steps, consider using dependency injection or hooks.
//	    }
	}
