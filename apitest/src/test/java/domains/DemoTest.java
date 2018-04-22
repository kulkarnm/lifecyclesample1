package domains;

import cucumber.api.CucumberOptions;
import org.junit.BeforeClass;


@CucumberOptions(tags = {"~@ignore"})
public class DemoTest extends TestBase {
    @BeforeClass
    public static void beforeClass() {
        // skip 'callSingle' in karate-config.js
        System.setProperty("karate.env", "web");
    }
}
