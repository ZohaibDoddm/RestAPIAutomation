package demo;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import response.Mainresponses;
import response.api;
import response.webAutomation;

public class Oauth {


	public static void main(String[]  args)
	{
		
		
		//adding the list of webatoatio scourestitles ofr assertion
		
		String[] courseTitle =  {"Selenium Webdriver Java","Cypress","Protractor"}; // need to convert the array into arrlalyst in future step77
		

		// adding the form parameters used for OAuth process
		String responsed = given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
				formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W"). 
				formParams("grant_type","client_credentials").
				formParams("scope","trust").
				when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

	//	System.out.println(response);

		JsonPath js = new JsonPath(responsed);
		String accessToken =  js.getString("access_token");
		System.out.println(accessToken);
	
	// sending the accesstoken deviced as a query parameter for the second request
		Mainresponses gc = given().queryParams("access_token",accessToken). 	
		when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails?access_token=H6dHOnCyvLSshZ/YuffFmQ==").as(Mainresponses.class);//we are considering the java object of response class and return type is a java object of class response
//	System.out.println(response2);
		
		
		gc.getLinkedin();	
		gc.getInstructor();
		//gc.getCourses().getApi().get(1).getCourseTitle();
		
		List<api> apicourses = gc.getCourses().getApi();
		int apisize = apicourses.size(); // will fetch the size of api block within courses.
		
		for(int i=0;i< apisize;i++)
			{
			
			if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) 
			{
			System.out.println(apicourses.get(i).getPrice());	
			}
				
			}
		
		
			List<webAutomation> wm = gc.getCourses().getWebAutomation();
			int emzise = wm.size();
			
			// we use arraylist becasue, it is good for runntime chnages of size in array.
			//if we are not sure of the size of array and we feel the size in synamic we use arralylist.
			
			//fetch the name of the courselist in webautomation and compare it.
			
			ArrayList<String> actual =  new ArrayList<String> ();  // created an object of arraylist. now we need to add the tring in to this array of strings
			
			for(int j=0; j<emzise;j++)
			{
				actual.add(wm.get(j).getCourseTitle());  // whatever coursetitle is fetched wil;l be addedt o the als array of string.
			}
			
			// we now need to compare the array fo string course which we recorded in the array against the arralylist
			// converting arrat to arraylist
			
			List<String> expected = Arrays.asList(courseTitle);
			Assert.assertTrue(actual.equals(expected));  // using testNG assertions and adding condition to check if actual equals expected.
		
			
	}
	}


