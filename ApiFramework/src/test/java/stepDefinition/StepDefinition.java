package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import pojo.AddPlace;
import pojo.Location;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utlis;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Utlis {

	RequestSpecification res;
	ResponseSpecification responsespec;
	Response resp;
	static String place_id;  // as this variable is not st to NULL. and is some value and can be used for second testcas. IT WILL THROW NULLPOINTER EXCEPTION INCASE WE DONT MAKE THIS STATC


	TestDataBuild tb = new TestDataBuild();  //creating a new object of testdata build in order to fetch the testdata used in the testdata build class 


	
//	@Given("AddPlace payload with{string} {string} {string}")
//	public void addplace_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
	//	res = given().spec(RequestSpecification())
	//			.body(tb.addPlacePayload(name,language,address)); 
	//    throw new cucumber.api.PendingException();
//	}
	

//	@Given("AddPlace payload with {String} {String} {String}")
//	public void addplace_payload_with(String string1,String string2,String string3) throws IOException {



	// REQUEST SPEC BUILDER IS A CLASS THAT ALLOWS US TO ITERATE RESUABLE SNIPPET OF REQUEST OR RESPONSE FOR RESUABILITY .
	//CONSIDER THAT WE CREATE AN OBJECT OF THE REQUEST SPEC UILDER AND USE IT TO STORE THE REQUEST.	
	// VERY VERY UMOORTSNT THE RETURN TYPE OF THE BELOW SPEC BUILDER REQUESTS IS  RequestSpecification as per the documentation.
	//	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();

	//	String response = given().queryParam("key","qaclick123").
	//	body(a).  // using the object as body. the object holds all the values to be sent as json load for the body
	//	when().post("/maps/api/place/add/json").
	//	then().assertThat().statusCode(200).extract().response().asString();

	//importat that response specification is the return type for responsespec builder. and request specification is the return type for request specification builder 
	// responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	//commented from here
//add this 72 line to new code	res = given().spec(RequestSpecification())
	//add this 72 line to new code	      .body(tb.addPlacePayload(string1,string2,string3));  //using the object of the test data build class to fetch the addplaceload method 
	// .body(a);  // seperating from when() and using spec(req)
	//	}
	
	
	
	
	//add this newly
	
	
	@Given("AddPlace payload with{string} {string} {string}")
	public void addplace_payload_with(String string, String string2, String string3) throws IOException {
		res = given().spec(RequestSpecification())
			     .body(tb.addPlacePayload(string,string2,string3));
			
	}

	
	
	
	@When("user calls {string}  with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    
		ApiResources resourceapi = ApiResources.valueOf(resource);
		
		System.out.println(resourceapi.getResource());
		responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		resp = res.when().post(resourceapi.getResource()); // seperating the when() 
		else if(method.equalsIgnoreCase("GET")) 
			resp = res.when().get(resourceapi.getResource());	
	}


	@Then("the API  call got success with status code  {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		System.out.println(int1);
		assertEquals(resp.getStatusCode(),200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedvalue) {
//		String respa = resp.asString(); 
	//	JsonPath jaz = new JsonPath(respa);
	//	String place_id = jaz.get("place_id");
		//String derived = jaz.get(key).toString();
	//	assertEquals(derived,expectedvalue);
		//System.out.println(expectedvalue);
		 System.out.println((getJsonPath(resp,key)));
			
		assertEquals(getJsonPath(resp,key),expectedvalue);
		 
		 
//	assertEquals(getJsonPath(resp,key),expectedvalue);  // getJsonpath accepst2 arguments. it is added in Utilities and fetches the value from response object.
	
	}
	


	@Then("verify  place_Id  created maps  to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedname, String resourcename) throws IOException {
	   
		place_id = getJsonPath(resp,"place_id");   //fetch place id from response  and we make it global varibale measn we can access accross different methods as its used for delete place id method too ~~~~
		res = given().spec(RequestSpecification()).queryParam("place_id", place_id);    // add as a query parameter
		System.out.println(place_id);
		user_calls_with_http_request(resourcename,"GET");
		
		String actualname = getJsonPath(resp,"name");
		System.out.println(actualname);
		System.out.println(expectedname);
		assertEquals(actualname,expectedname);
	}
	

		
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		
		res = given().spec(RequestSpecification()).body(tb.deletepayload(place_id));		
	}

}
