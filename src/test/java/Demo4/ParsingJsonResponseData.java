package Demo4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParsingJsonResponseData { 
	@Test
	void testJsonResponse()
	{
//		given()
//			.contentType("ContentType.JSON")
//		.when()
//			.get("http://localhost:3000/store")
//		.then()
//			.statusCode(200)
//			.header("Content-Type", "application/json; charset=utf-8")
//			.body("books[2].title", equalTo("You and Me"))
//			.log().body();
		
		Response response = given()
			.contentType("ContentType.JSON")
		.when()
			.get("http://localhost:3000/store");
		
//		Assert.assertEquals(response.getStatusCode(), 200); // validation 1
//		Assert.assertEquals(response.header("Content-Type"), "application/json; charset=utf-8");
//		
//		String bookName = response.jsonPath().get("books[2].title").toString();
//		Assert.assertEquals(bookName, "You and Me");
		
		JSONObject jo = new JSONObject(response.asString()); // converting response to json object type
		
		for (int num = 0; num < jo.getJSONArray("books").length(); num++) {
			String bookTitle = jo.getJSONArray("books").getJSONObject(num).get("title").toString();
			System.out.println(bookTitle);
		}
	}
}
