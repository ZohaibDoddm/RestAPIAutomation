package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	
	@Test(dataProvider = "BookName")   /// dataProvider   however the method name is DataProvider
	
	public void addBook(String isbn,String aisle)   // using the method parametrisation as we are sending data from the data provider
	{
		
		RestAssured.baseURI = "http://216.10.245.166/";
		String response = given().header("Content-Type","application/json").
		body(payload.AddBook(isbn,aisle)).
		when().
		post("Library/Addbook.php").
		then().assertThat().statusCode(200).
		extract().response().asString();
		
		JsonPath jx = ReusableMethods.rawToJson(response);
		String ID = jx.get("ID");
		System.out.println("ID retrieved is " +ID);
	}
	
	
	@DataProvider(name="BookName")  // naming the dataprovider convention (name = "")
	public Object[][] addData()  //(return type is an object multidimensional array)
	{
	 //new Object[][] {array1, array2, array3}; 
		return new Object[][] {{"abcde","321"},{"fghij","322"},{"klmno","323"}}; //array of array => multidimensional array
	}
}
