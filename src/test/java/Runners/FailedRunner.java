package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/",
        glue = "Steps",
        dryRun = false,
        tags = "@valid",
        plugin = {"pretty", "html:target/cucumber.html", "json: target/cucumber.json", "rerun: target/failed.txt"}
)

public class FailedRunner {
}
