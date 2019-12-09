package testrunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestRunner: Running Cucumber tests using TestNG.
 * 
 * @author helk
 *
 */
@CucumberOptions(plugin = "pretty", glue = "stepdefinitions", features = "src/test/resources/features")
public class TestRunnerTestNG extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}