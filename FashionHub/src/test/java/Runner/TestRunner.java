package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		 	features = "src/main/java/Features",
			glue="StepDefination"
		)
public class TestRunner extends AbstractTestNGCucumberTests {

}
