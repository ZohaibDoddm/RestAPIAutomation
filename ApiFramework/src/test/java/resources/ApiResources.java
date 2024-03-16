package resources;

public enum ApiResources {  // enum is special class in jabva which is a collction of constants and  methods
	
	// with enum class we can add several constants embeded in a method each seperated by a comma.
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");  // terminated by a colon to show its a collection
	
	
	private String resourced;
	
	ApiResources(String resource) 
	{
	 this.resourced = resource;		
	}
	
	public String getResource() 
	{	
		return resourced;
	}
}
