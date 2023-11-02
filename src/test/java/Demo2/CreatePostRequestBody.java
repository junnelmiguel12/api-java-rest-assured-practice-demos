package Demo2;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*
Different ways to create POST request Body
1. Using HashMap (prefer for small set of data)
2. using Org.json
3. using POJO class
4. using external json file
*/

public class CreatePostRequestBody {
	String studentId;
	String locationHeader;
	
	// using HashMap
//	 @Test (priority = 1)
	void testPostUsingHashMap()
	{
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "Scott Devin");
		data.put("location", "India");
		data.put("phone", "7570967898");
		
		String courseArr[] = {"C#", "Java"};
		data.put("courses", courseArr);
		
		locationHeader = given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott Devin"))
			.body("location", equalTo("India"))
			.body("phone", equalTo("7570967898"))
			.body("courses[0]", equalTo("C#"))
			.body("courses[1]", equalTo("Java"))
			.log().all()
		.extract()
			.header("Location");
		
		studentId = StringUtils.substringAfter(locationHeader, "/students");
	}
	
	// using org.json library
//	@Test (priority = 1)
	void testPostUsingOrgJson() 
	{
		JSONObject data = new JSONObject();
		
		data.put("name", "Henry");
		data.put("location", "Philippines");
		data.put("phone", "09564675768");
		
		String courseArr[] = {"C++", "C"};
		data.put("courses", courseArr);
		
		locationHeader = given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Henry"))
			.body("location", equalTo("Philippines"))
			.body("phone", equalTo("09564675768"))
			.body("courses[0]", equalTo("C++"))
			.body("courses[1]", equalTo("C"))
			.log().all()
		.extract()
			.header("Location");
		
		studentId = StringUtils.substringAfter(locationHeader, "/students");
	}
	
	// using POJO class
//	@Test (priority = 1)
	void testPostUsingPOJO() 
	{
		POJO_PostRequest data = new POJO_PostRequest();
		
		data.setName("Sam");
		data.setLocation("Korea");
		data.setPhone("7858769");
		
		String courseArr[] = {"C++", "C"};
		data.setCourses(courseArr);
		
		locationHeader = given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Sam"))
			.body("location", equalTo("Korea"))
			.body("phone", equalTo("7858769"))
			.body("courses[0]", equalTo("C++"))
			.body("courses[1]", equalTo("C"))
			.log().all()
		.extract()
			.header("Location");
		
		studentId = StringUtils.substringAfter(locationHeader, "/students");
	}
	
	// using external json file
	@Test (priority = 1)
	void testPostUsingExternalJsonFile() throws FileNotFoundException
	{
		File f = new File(".\\body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		locationHeader = given()
				.contentType("application/json")
				.body(data.toString())
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name", equalTo("John Doe"))
				.body("location", equalTo("Oman"))
				.body("phone", equalTo("75865789"))
				.body("courses[0]", equalTo("Selenium"))
				.body("courses[1]", equalTo("Python"))
				.log().all()
			.extract()
				.header("Location");
			
			studentId = StringUtils.substringAfter(locationHeader, "/students");
	}
	
	@Test (priority = 2)
	void testDelete()
	{
		given()
			//
		.when()
			.delete("http://localhost:3000/students" + studentId)
		.then()
			.statusCode(200);
			
	}
}
