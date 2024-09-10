import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"classpath:features"},
        glue = "steps",
        plugin = {"pretty", "json:target/cucumber-report.json"}
)
public class RunCukesTest {
}
