package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class utils {

	public static RequestSpecification req;

	public RequestSpecification reqSpec() throws IOException {
		if(req==null)
		{PrintStream obj = new PrintStream(new FileOutputStream("log.txt"));
		// PrintStream obj= new PrintStream(new FileOutputStream("logging.txt"));

		req = new RequestSpecBuilder().setBaseUri(ConfigReader("baseurl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(obj))
				.addFilter(ResponseLoggingFilter.logResponseTo(obj)).build();
		return req;
		}return req;
		///.addHeader("Content-Type", "application/json")
	}

	public static Properties prop;

	public static String ConfigReader(String key) throws IOException {
		if (prop == null) { // load only once
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/junit-platform.properties");
			prop.load(fis);
		}
		return prop.getProperty(key);
	}
	
	public String getResponse(String response,String key)
	{
		JsonPath jd = new JsonPath(response);
		String value=jd.get(key);
		return value;
		
		
		
	}

}
