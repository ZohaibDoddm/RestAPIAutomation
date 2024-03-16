import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReusableMethods;



public class basics2 {

	public void addbook() {
		// given when you add the details
		// when you send the api request, and you add the type of request like post or get or put	
		//  then validate the respnse of the request.		
		RestAssured.baseURI = "http://216.10.245.166";
		// converting the API response as a string to extract the 
		// json path is a class to convert the text or string into json body

		http://216.10.245.166/
		Response resp = given().
				header("Content-type","application/json").
				body("{\r\\n\"name\": \"Learn Appium Automation with Java\",\r\n\"isbn\": \"bcssddzz\",\r\n\"aisle\": \"229\",\r\n\"author\":\"john froe\"\r\n}")
				.when().post("/Library/Addbook.php")
				.then().assertThat().statusCode(200)
				.extract().response().asString();

		
		JsonPath js = ReusableMethods.rawToJson(resp);
		String id = js.get("id");
		System.out.println(id);
		
	

	}

}
