import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReusableMethods;

 

public class basics {

	public static void main(String[] args) throws IOException {
		// given when you add the details
		// when you send the api request, and you add the type of request like post or get or put	
		//  then validate the respnse of the request.		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		// converting the API response as a string to extract the 
		// json path is a class to convert the text or string into json body
		String response = given().log().all().queryParam("key","qaclick123").header("Content-type","application/json").
		body(files.payload.AddPlace())
					  .when().post("maps/api/place/add/json")
		              .then().assertThat().statusCode(200)
		              .body("scope",equalTo("APP"))
		              .header("Server","Apache/2.4.52 (Ubuntu)")
		              .extract().response().asString();
		System.out.println(response);
		
	JsonPath js = new JsonPath(response); // parsing JSON path
	String resultid = js.getString("place_id");
	System.out.println(resultid);
	
	
	//update API:
	
	String baseaddress = "dream house bramlea  4321";
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").
/*	body("{\r\n"
			+ "\"place_id\":\""+resultid+"\",\r\n"
			+ "\"address\":\""+baseaddress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}").   */  //added the static payload
	// we can use other way too to add the body
	//content of the file converted to the bytes and then bytes converetd to  String
		// String to convert the bytes into String		//File ;;ibrary to fetch file path readallbytes to covert file to bytes first 							// paths and then path of the file
	body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Hp\\OneDrive\\Desktop\\Test engineering\\rest assured"))));
	when().put("/maps/api/place/update/json").
	then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
	
	//retrieve API
	
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String responset = given().log().all().queryParam("place_id",resultid).queryParam("key", "qaclick123").
		when().get("maps/api/place/get/json").
		then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jz = ReusableMethods.rawToJson(responset);   /// created a class JsonPath which has a method reusable Methods that would return json path for the string passed as a variable to the method.
	
		String jsonaddress = jz.getString("address");   // extracting the string from the jsaon path using getString method. and passing address as the key 
		System.out.println("new address is  "+ jsonaddress);
	    Assert.assertEquals(jsonaddress,baseaddress);   // used the assetion library of testng 7.0 to assert the values
		
	//jsonaddress.equalsIgnoreCase(baseaddress);
	
	}
	
	
}
