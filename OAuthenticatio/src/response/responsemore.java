package response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class responsemore {


	private List<api> api;  // creating the list of api for api object

	private List<mobile> mobile;

	private List<webAutomation> WebAutomation;   // we are creating this as a list because the webAutomation pojo class has an array of items


	public List<api> getApi() {
		return api;
	}
	public void setApi(List<api> api) {
		this.api = api;
	}

	public List<mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<mobile> mobile) {
		this.mobile = mobile;
	}
	public List<webAutomation> getWebAutomation() {
		return WebAutomation;
	}
	public void setWebAutomation(List<webAutomation> webAutomation) {   // changes the data type to List<webAutomation> for both get ans set method
		WebAutomation = webAutomation;
	}


}
