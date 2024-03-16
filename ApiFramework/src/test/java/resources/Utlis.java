package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utlis {

	public static RequestSpecification req; // making it static means that variable is shared accross the class the previous value of variable will be retained 
	
	public io.restassured.specification.RequestSpecification RequestSpecification() throws IOException {
		
		if(req==null) 
		{
		PrintStream  log = new PrintStream(new FileOutputStream("loggin.txt"));  // Object of printstream class
		RestAssured.baseURI = "https://rahulshettyacademy.com";	
		
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log)) // this step is to create a log and send to the object 
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();	  
		return req;
		}
		return req;
		// this method return the object req, and th above two steps are common accross the delete, add and other API operations
	}
	
	
	public String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties(); // created object
		FileInputStream fis = new FileInputStream("D:\\ApiFramework\\src\\test\\java\\resources\\global.properties"); // object for filestream 
		prop.load(fis);
		return prop.getProperty(key);  //Deriving the baseUrl from the global properties file 
		
	}
	
	
	public String getJsonPath(Response response,String key) 
	{
	
		String respa = response.asString();     // converts the response to string
		JsonPath jaz = new JsonPath(respa);    // object to retrace the json path
		return jaz.get(key).toString();     // fetches the value of specific key in the reponse.
		
	}
	
	
}
