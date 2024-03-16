package files;
import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;



public class JIRAProject {

	public static void main(String[] args)
		{
		
			RestAssured.baseURI= "http://localhost:8080/";
		
		
		
		//lpogin method
		SessionFilter session= new SessionFilter();  // creating object of session filter which helps in retaining a session then using filter(session) in the method before when()  which helps in retaining same session
		
		String responsevalue = given().header("Content-Type","application/json").body("{ \"username\": \"zhbmatador\", \"password\": \"Jira1234\" }").
		log().all().filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response().asString();
		
		//adding the path parameter now:
		// path param is used to parameterise the path parameter in the following  resource.
		
		String replies = given().pathParam("key","10005").log().all().header("Content-Type","application/json").
				body("{\r\n"
				+ "    \"body\": \"First comments.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "    \"type\": \"role\",\r\n"
				+ "    \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}"). 
		// adding the path parameter which refers to the ID :10005 that is derived from the  path parameter added 
		filter(session).when().post("rest/api/2/issue/{key}/comment").
		then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath ks = new JsonPath(replies);
		String commentID = ks.getString("id");  // . ids that i recieved 
		
		
		
		
	//add attachment testcase"
		
		//curl -D- -u admin:admin -X POST -H "X-Atlassian-Token: no-check" -F "file=@myfile.txt" http://myhost/rest/api/2/issue/TEST-123/attachments

		
		given().header("X-Atlassian-Token"," no-check" ).log().all().
		filter(session).pathParam("key","10005").header("Content-type","multipart/form-data").
		multiPart("file",new File("practise.txt")). 					// using the multipart method which had the file key and new File() to access the File path
		when().post("http://myhost/rest/api/2/issue/{key}/attachments"). // parametrsing the id using the path parameter
		then().log().all().assertThat().statusCode(200);
		
		//get issue 
		
		String response = given().filter(session).pathParam("key", "10005").queryParam("fields","comment").
				log().all().when().get("/rest/api/2/issue/{key}").
				then().log().all().extract().response().asString();
				System.out.println(response);
				
		
				JsonPath jn = new JsonPath(response);  // in order to follow the json path 
				int  commentsCount = jn.getInt("fields.comment.comments.size()");
				for(int i=0; i<commentsCount;i++) 
				{
					
					String retrivedId = jn.get("fields.comment.comments["+i+"].id").toString();         // iterating through the json eresponse block of get issues method
					if(retrivedId.equalsIgnoreCase(commentID)) 
					{
						String body = jn.get("fields.comment.comments["+i+"].body").toString(); 	
					}
					
				}
		   }
		
    	}
