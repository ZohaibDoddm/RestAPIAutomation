package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",glue= {"stepDefinition"},plugin = "json:target/jsonReports/cucumber-report.json" ,tags= {"@Deleteplace"})

//tags= {"@Addplace"}
public class TestRunner {
	
	//has details of the stepdefintion file and ccumber file linkage

}
//we are sending  the data dynamically using examples also ensure that when you are using example keyword, make sure to use scenario ouytline as the kkeyword