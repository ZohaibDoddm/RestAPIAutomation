package demo;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderpractise {
	public static void main(String[] args) {
	
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace a =  new AddPlace(); // creating object and then adding the values to create the HJSON object to be added as body to the payload
		a.setAccuracy(50);
		a.setAddress("29, side layout, cohen 09");
		a.setLanguage("French-IN");
		a.setWebsite("http://google.com");
		a.setName("Frontline house");
		a.setPhone_number("(+91) 983 893 3937");
		
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		a.setTypes(mylist);
		
		// adding the location coordinates using the location class object
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		a.setLocation(l);
		
		// REQUEST SPEC BUILDER IS A CLASS THAT ALLOWS US TO ITERATE RESUABLE SNIPPET OF REQUEST OR RESPONSE FOR RESUABILITY .
		//CONSIDER THAT WE CREATE AN OBJECT OF THE REQUEST SPEC UILDER AND USE IT TO STORE THE REQUEST.
		
		
		// VERY VERY UMOORTSNT THE RETURN TYPE OF THE BELOW SPEC BUILDER REQUESTS IS  RequestSpecification as per the documentation.
	//	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();
		
	//	String response = given().queryParam("key","qaclick123").
	//	body(a).  // using the object as body. the object holds all the values to be sent as json load for the body
	//	when().post("/maps/api/place/add/json").
	//	then().assertThat().statusCode(200).extract().response().asString();
		
		//importat that response specification is the return type for responsespec builder. and request specification is the return type for request specification builder 
		ResponseSpecification responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();
		RequestSpecification res = given().spec(req).body(a);  // seperating from when() and using spec(req)
		Response resp = res.when().post("/maps/api/place/add/json"). // seperating the when() 
		then().spec(responsespec).extract().response(); // spec(responsespec) we are adding the object created 
		// no need to add this as we are using respone spec builder above  -> assertThat().statusCode(200).extract().response().asString();
		
		String responsestring = resp.asString();
		System.out.println(responsestring);
    	
	}

}
