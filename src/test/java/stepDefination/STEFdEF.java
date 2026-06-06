package stepDefination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.cucumber.java.en.Then;
import pojo.Location;
import pojo.pojo_class;
import resources.Apiresources;
import resources.pojo_resource;
import resources.utils;

public class STEFdEF extends utils {
	RequestSpecification rec;
	ResponseSpecification resSpec1;
	Response post_used;
	 static String place_id1;
	JsonPath jd;
	JsonPath jd3;
	  static pojo_resource obj2;
	@Given("Add place PayLoad {string} {string} {string}")
	public void add_place_pay_load(String address, String language, String name) throws IOException {

		obj2 = new pojo_resource();
		pojo_class p = obj2.methd1(address, language, name);

		// creating obj and caling method can be done but here inheretence has been used

		rec = given().spec(reqSpec()).body(p);

	}

	@When("user calls {string} with {string} HTTP request")
	public void user_calls_with_post_http_request(String resource, String method) {
		Apiresources obj = Apiresources.valueOf(resource);
		 
		
		System.out.println(obj.getresource());

		resSpec1 = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST"))

			post_used = rec.when().post(obj.getresource()).then().extract().response();

		else if (method.equalsIgnoreCase("GET"))
			post_used = rec.when().get(obj.getresource()).then().extract().response()

			;

	}

	@Then("response code is {int}")
	public void response_code_is(Integer code) {
		assertEquals(code.intValue(), post_used.getStatusCode());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String getStatus, String expval) {
		String obj = post_used.asString();
		jd = new JsonPath(obj);
		place_id1 = getResponse(obj,"place_id");

		assertEquals(jd.get(getStatus),expval);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using_get_place_api(String expectedName, String getplace)
			throws IOException {
		String url = ConfigReader("baseurl");

		Apiresources obj2 = Apiresources.valueOf(getplace);
		System.out.println(obj2.getresource());
		String obj = given().baseUri(url).queryParam("key", "qaclick123").queryParam("place_id", place_id1).when()
				.get(obj2.getresource()).then().extract().response().asString();

		System.out.println(getResponse(obj, "name"));
		assertEquals(getResponse(obj, "name"), expectedName);
	}
	
	@Given("Delete place payLoad")
	public void delete_place_pay_load() throws IOException {
		obj2 = new pojo_resource();
	   rec=given().spec(reqSpec()).body(obj2.deletebody(place_id1));
	   		
	}
	
	
	
	
	
}