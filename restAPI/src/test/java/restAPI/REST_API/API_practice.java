package restAPI.REST_API;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;

public class API_practice {
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = "https://www.lalalalalalalal.com.dev.cc/wp-json";
		RestAssured.basePath = "/wp/v2";
	}

//	given().relaxedHTTPSValidation()
//	.when().log().all()
//	.pathParam("per_page", 23)
//	
//	.get("/posts/{per_page}")
//	.then().log().ifValidationFails()// only log if validation is fails 
//	.statusCode(200)
//	.and().body("slug",equalTo("yyyyyuuuuuaaaaannnnn"))
//	.and().body("sticky",is(false));
//	//RestAssured.baseURI="http://18.206.125.72:1001";
	// RestAssured.basePath="/ords/hr/";
	@Test
	public void practiceGET() {
		given().relaxedHTTPSValidation()
		.when()
			.log().all().pathParam("q", 22)
				.get("/posts/{q}").then().log()
				.ifValidationFails().statusCode(200)
			.and().body("id", equalTo(22))
			.and().log().all().and()
				.body("status", is("publish"));

		given()
			.relaxedHTTPSValidation()
			.auth().preemptive().basic("yuangao", "abc123")
			.contentType(ContentType.JSON)
			.when().log().all()
				.body("{\n" + 
						"	\"title\":\"API-DAY3 post time for post asdf\",\n" + 
						"	\"content\":\"Lol 1000k\",\n" + 
						"	\"status\":\"publish\"\n" + 
						"}")
				.post("/posts")
			.then()
				.statusCode(201)
			.and().log().all();
		
		given()
			.relaxedHTTPSValidation()
			.when()
			//.pathParam("id", 41)
			.get("/posts/41")
			.then().log().ifValidationFails()
			.statusCode(200)
			.log().all()
			.and().body("id", equalTo(41));
	}
	
	@Test
	public void postTest() {
		given()
			.relaxedHTTPSValidation().auth().preemptive().basic("yuangao", "abc123")
		.when()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON).log().all()
			.body("{\n" + 
				"		\"title\":\"API-DAY3 post time for post asdf\",\n" + 
				"	\"content\":\"Lol alalasldfknalekf\",\n" + 
				"	\"status\":\"publish\"\n" + 
				"}")
			.post("/posts")
		.then()
				.statusCode(201)
				.log().ifValidationFails();
	}
	

}
