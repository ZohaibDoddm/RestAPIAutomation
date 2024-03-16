package demo;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;

public class SerialiseTesta {
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
		
		String response = given().queryParam("key","qaclick123").
		body(a).  // using the object as body. the object holds all the values to be sent as json load for the body
		when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
    	
	}

}
