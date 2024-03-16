package CucumberOptions;

import org.junit.runner.RunWith;
import cucumber.junit.Cucumber;




// to link the cucumber feature file with the step definition, we need to create the test runner which will integrte between them


@RunWith(Cucumber.class)
@Cucumber.Options(
		features= "src/test/java/features",  // write the path of the package
		glue= "StepDefinition")  // glue refers to the path of the stepdefinition- JUST NAME THE FILE NAME
public class TestRunner {

	
	
	
}
