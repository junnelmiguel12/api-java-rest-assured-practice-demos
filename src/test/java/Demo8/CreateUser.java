package Demo8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {
	@Test
	void testCreateUser(ITestContext context)
	{
		Faker faker = new Faker();
		context.setAttribute("bearer_token", "c2b550618e5a92a780b47bac851296b740d61efb7e309ba4876121bc21b5e508");
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "Inactive");
		
		int id = given()
			.headers("Authorization", "Bearer " + context.getAttribute("bearer_token"))
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
		context.setAttribute("user_id", id);
	}
}
