package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

	
	public static JsonPath rawToJson(String responset) {
		
		JsonPath jz = new JsonPath(responset);
		return jz;
		
	}
}
