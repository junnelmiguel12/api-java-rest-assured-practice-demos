package Demo7;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentication {
	@Test (priority = 1)
	void testBasicAuthentication()
	{
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test (priority = 2)
	void testDigestAuthentication()
	{
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test (priority = 3)
	void testPreemptiveAuthentication()
	{
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test (priority = 4)
	void testBearerTokenAuthentication()
	{
		String token = "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";
			
		given()
			.header("Authorization", "Bearer " + token)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test (priority = 5)
	void testoAuth2Authentication()
	{
		String token = "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";
			
		given()
			.auth().oauth2(token)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test (priority = 6)
	void testAPIKeyAuthentication()
	{
		given()
			.queryParam("appid", "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")
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		.then()
			.statusCode(200)
			.log().all();
	}
}
