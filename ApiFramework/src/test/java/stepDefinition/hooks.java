package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class hooks {
	
	
	// creating a hooks class to ensure the precondition and flow is maintained as per needs
	
	@Before("@Deleteplace")
	
	public void BeforeScenario() throws IOException {
		
		StepDefinition sd = new StepDefinition();
		
		
		// perfor this step nly as a precondition to the deletplace 
		
	 if(StepDefinition.place_id == null) {
		sd.addplace_payload_with("zubbiDoo", "korean", "70 woolridge court");
		sd.user_calls_with_http_request("addPlaceAPI","POST");
		sd.verify_place_Id_created_maps_to_using("zubbiDoo", "getPlaceAPI");
	//	sd.the_API_call_got_success_with_status_code(200);
		
	 }
		
	}
}
 