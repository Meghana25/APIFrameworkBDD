package apiautomation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features"},glue = {"stepDefinations"},dryRun = false,tags = " ",
                        plugin = "json:target/jsonReports/cucumber-report.json")
public class TestRunner {
}
