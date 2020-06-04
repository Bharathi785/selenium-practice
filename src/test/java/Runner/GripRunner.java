package Runner;

import com.qaprosoft.carina.core.foundation.cucumber.CucumberBaseTest;
import cucumber.api.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features/",
        glue = "com.grip.cucumber.steps",
        tags = {"@TC020001"},
        plugin = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json"}
        , dryRun = false
        , monochrome = true
        , strict = false
)

public class GripRunner extends CucumberBaseTest {

}