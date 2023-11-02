package Demo3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class Cookies {
//	@Test (priority = 1)
	void testCookies()
	{
		given()
			// 
		.when()
			.get("https://www.google.com")
		.then()
			.cookie("AEC", "abcde")
			.log().all();
		
	}
	
	@Test (priority = 2)
	void getCookiesInfo()
	{
		Response response = given()
			// 
		.when()
			.get("https://www.google.com");
		
		Map<String, String> cookieValues = response.getCookies();
		
		for (String key:cookieValues.keySet()) {
			System.out.println(key);
		}
		
	}
}
