package Demo6;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import Demo2.POJO_PostRequest;

public class SerializationDeserialization {
	// Serialization
	@Test 
	void convertPojoToJson() throws JsonProcessingException 
	{
		POJO_PostRequest studPojo = new POJO_PostRequest();
		
		studPojo.setName("Luffy");
		studPojo.setLocation("India");
		studPojo.setPhone("9675678765");
		
		String courseArr[] = {"C++", "C"};
		studPojo.setCourses(courseArr);
		
		ObjectMapper objMapper = new ObjectMapper();
		
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(studPojo);
		System.out.println(jsonData);
	}
	
	// DeSerialization
		@Test 
		void convertJsonToPojo() throws JsonProcessingException 
		{
			String jsonData = "{\r\n"
					+ "      \"name\": \"John Hogwarts\",\r\n"
					+ "      \"location\": \"India\",\r\n"
					+ "      \"phone\": 1234567890,\r\n"
					+ "      \"courses\": [\r\n"
					+ "        \"Java\",\r\n"
					+ "        \"Selenium\"\r\n"
					+ "      ]\r\n"
					+ "    }";
			
			ObjectMapper objMapper = new ObjectMapper();

			POJO_PostRequest student = objMapper.readValue(jsonData, POJO_PostRequest.class);
			
			System.out.println(student.getName());
			System.out.println(student.getLocation());
			System.out.println(student.getPhone());
			System.out.println(student.getCourses()[0]);
			System.out.println(student.getCourses()[1]);
		}
}
