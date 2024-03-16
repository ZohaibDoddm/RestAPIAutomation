package resources;

import java.util.ArrayList;
import java.util.List;
import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address) 
	{
		AddPlace a =  new AddPlace(); // creating object and then adding the values to create the HJSON object to be added as body to the payload
		a.setAccuracy(50);
		a.setAddress(address);
		a.setLanguage(language);
		a.setWebsite("http://google.com");
		a.setName(name);
		a.setPhone_number("(+91) 983 893 3937");
		
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe parkways");
		mylist.add("shop");
		a.setTypes(mylist);
		// adding the location coordinates using the location class object
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		a.setLocation(l);
		return a;   // this method return the object a
	
	}
	
	public String deletepayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	//eturn "{\"place_id\":\""+placeId+"\"}";	
	}
}
