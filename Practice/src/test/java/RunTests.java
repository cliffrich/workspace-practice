import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by richard.cliff on 14/04/2015.
 */
@RunWith(Cucumber.class)
@Cucumber.Options(format = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        tags = "@RPSAll",
        features = "classpath:cucumber")
public class RunTests {

}
