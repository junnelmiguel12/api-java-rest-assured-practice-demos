package Demo5;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class ParsingXMLResponse {
	// @Test
	void testXMLResponse()
	{
//		given()
//			// 
//		.when()
//			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
//		.then()
//			.statusCode(200)
//			.header("Content-Type", "application/xml; charset=utf-8")
//			.body("TravelerinformationResponse.page", equalTo("1"))
//			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
	
		Response res = given()
				.when()
					.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		Assert.assertEquals(res.getStatusCode(), 200);
		
		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");
	}
	
	@Test
	void testXMLResponseBody()
	{
		Response res = given()
			.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlobj = new XmlPath(res.asString());
		
		// verify total number of travellers
		List<String> travellers = xmlobj.getList("travelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(travellers.size(), 10);
		
		List<String> travellerNames = xmlobj.getList("travelerinformationResponse.travelers.Travelerinformation.name");
		boolean isTravellerExist = false;
		
		// verify if traveller name exists
		for (String travellerName : travellerNames) {
			if (travellerName.equals("Developer")) {
				isTravellerExist = true;
				break;
			}
		}
		
		Assert.assertEquals(isTravellerExist, true);
	}
}
