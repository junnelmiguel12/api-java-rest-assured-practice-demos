package Demo1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


public class HTTPRequests {

	int userId;
	
	 @Test (priority = 1)
	void getUsers() 
	{
		given()
			// 
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	@Test (priority = 2)
	void createUser()
	{
		HashMap<String, String> data = new HashMap<String, String>();
		
		data.put("name", "John");
		data.put("job", "trainer");
		
		userId = given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
	}
	
	@Test (priority = 3, dependsOnMethods = {"createUser"})
	void updateUser()
	{
		HashMap<String, String> data = new HashMap<String, String>();
		
		data.put("name", "Peter");
		data.put("job", "programmer");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users/" + userId)
		.then()
			.statusCode(201)
			.log().all();
	}
	
	@Test (priority = 4)
	void deleteUser()
	{
		given()
			// 
		.when()
			.delete("https://reqres.in/api/users/" + userId)
		.then()
			.statusCode(204)
			.log().all();
	}
}
