package Demo8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {

	@Test
	void testGetUser(ITestContext context)
	{
		int id = (int) context.getAttribute("user_id");
		
		given()
			.headers("Authorization", "Bearer " + context.getAttribute("bearer_token"))
			.pathParam("id", id)
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
	}
}
