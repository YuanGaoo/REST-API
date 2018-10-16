package restAPI.REST_API;



import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAPITesting {
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="https://www.lalalalalalalal.com.dev.cc/wp-json";
		RestAssured.basePath="/wp/v2";
	}
	@Test(priority=0)
	public void simpleGetrequest() {
		given().relaxedHTTPSValidation()
		.when().log().all()
		.pathParam("per_page", 23)
		
		.get("/posts/{per_page}")
		.then().log().ifValidationFails()// only log if validation is fails 
		.statusCode(200)
		.and().body("slug",equalTo("yyyyyuuuuuaaaaannnnn"))
		.and().body("sticky",is(false));
		
		
	}
	
	@Test(priority=1)
	public void simplePostTest() {
		given().relaxedHTTPSValidation()
		.auth().preemptive().basic("yuangao", "abc123")
		.contentType(ContentType.JSON)
		.when().log().all()
		.body("{\n" + 
				"		\"title\":\"API-DAY3 post time for post asdf\",\n" + 
				"	\"content\":\"Lol 1000k\",\n" + 
				"	\"status\":\"publish\"\n" + 
				"}")
		.post("/posts")
		.then().statusCode(201);
	}
	
	@Test(priority=2)
	public void simplePutTest() {
		
		given().relaxedHTTPSValidation()
		.auth().preemptive().basic("yuangao", "abc123")
		.contentType(ContentType.JSON)
		.when().log().all()
		.body("{\n" + 
				"		\"title\":\"WILL UP DATE post time for post\"\n" + 
				"}").log().all()
		.pathParam("newID", 25)
		.put("/posts/{newID}")
		.then().log().all()
			.statusCode(200)
			.body("title.raw", is("WILL UP DATE post time for post"));	
		
	}
	
	@Test(priority=4)
	public void simpleDeleteTest() {
		
		given()
		.relaxedHTTPSValidation()
		.auth().preemptive().basic("yuangao", "abc123")
		.when()
			.pathParam("deleteID", 27)
			.queryParam("FORCE", true)
			.delete("/posts/{deleteID}")
		.then()
			.statusCode(200);
		
	}
	
	
	
	
	
	
	
	
}
