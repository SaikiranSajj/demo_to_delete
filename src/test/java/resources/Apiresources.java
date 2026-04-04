package resources;

public enum Apiresources {
//"maps/api/place/add/json"
	AddPlaceAPI("maps/api/place/add/json"),
	getPlaceAPI("maps/api/place/get/json"),
	deletePlaceAPI("maps/api/place/delete/json");
    private String resource;
	Apiresources(String resource) {
		  this.resource=resource;
	}
	
	public String getresource()
	{
		return resource;
		
	}
	
	
	
}
