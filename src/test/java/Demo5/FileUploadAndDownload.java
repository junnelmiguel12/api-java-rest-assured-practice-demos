package Demo5;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;

public class FileUploadAndDownload {
	// @Test
	void testSingleFileUpload()
	{
		File file = new File("D:\\Files\\zoom-background.jpg");
		
		given()
			.multiPart("file", file)
			.contentType("multipart/form-data")
		.when()
			.post("https://v2.convertapi.com/upload")
		.then()
			.statusCode(200)
			.body("FileName", equalTo("zoom-background.jpg"))
			.log().all();		
	}
	
	// @Test
	void testMultipleFileUpload()
	{
		File file1 = new File("D:\\Files\\zoom-background.jpg");
		File file2 = new File("D:\\Files\\zoom-white-fullhd-background.png");
		
//		File fileArr[] = {file1, file2};
		
		given()
			.multiPart("files", file1)
			.multiPart("files", file2)
			.contentType("multipart/form-data")
		.when()
			.post("https://v2.convertapi.com/upload")
		.then()
			.statusCode(200)
//			.body("[0].FileName", equalTo("zoom-background.jpg"))
			.log().all();		
	}
	
	@Test
	void testDownloadFile()
	{
		given()
			// 
		.when()
			.post("https://v2.convertapi.com/downloadFile\filename")
		.then()
			.statusCode(200)
			.log().all();		
	}
}
