package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {"stepDefinitions", "hooks"},
	    plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"},
	    monochrome = true,
	    //tags = "@SmokeTest",         // Runs only scenarios tagged with @SmokeTest
	    dryRun = false               // If true → checks step definitions without running browser
	)
	public class TestRunner extends AbstractTestNGCucumberTests {
	}
