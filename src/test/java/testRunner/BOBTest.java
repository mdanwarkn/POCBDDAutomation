package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions"},
        tags = "@BOBQuarterlyPayoutTest",
        publish = true,
        dryRun = false,
        monochrome = true,
        plugin = {"html:test-output\\CucumberReports.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

public class BOBTest{
	
	
}
