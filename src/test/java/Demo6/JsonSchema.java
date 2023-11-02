package Demo6;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class JsonSchema {
	@Test
	void testJsonSchema()
	{
		given()
		.when()
			.get("http://localhost:3000/books")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("StoreJSONSchema.json"));
	}
}
