package Demo3;

import org.testng.annotations.Test;

import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Header {
//	@Test (priority = 1)
	void testHeader()
	{
		given()
			// 
		.when()
			.get("https://www.google.com")
		.then()
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws")
			.log().all();
	}
	
	@Test (priority = 2)
	void getHeader()
	{
		Response response = given()
			// 
		.when()
			.get("https://www.google.com");
		
		String ct = response.getHeader("Content-Type"); // single header
		Headers headers = response.getHeaders(); // multiple header
		
		for (io.restassured.http.Header hd:headers) {
			System.out.println(hd.getName() + "----------" + hd.getValue());
		}
	}
}
