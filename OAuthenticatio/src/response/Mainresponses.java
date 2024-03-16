package response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mainresponses {
	
	
	// varaibles created and each varaibles is nothing but the response key which we are going to fetch.
	
	//SERIALISATION IS A PROCESS OF COVNERTING THE JAVA OBJECT INTO a request payload
	//deserialisation is the other way.
	

	private String instructor;
	
	private String url;
	
	private String services;
	
	private String expertise;
	
	private responsemore courses;  // it has a seperate class with its objects. so installed of return type as string its going to be a class name

	private String Linkedin;
	
	
	// used SHIFT +ALT+S to generate auto getter an setter methods
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public responsemore getCourses() {
		return courses;
	}
	public void setCourses(responsemore courses) {
		this.courses = courses;
	}
	public String getLinkedin() {
		return Linkedin;
	}
	public void setLinkedin(String linkedin) {
		Linkedin = linkedin;
	}
}
