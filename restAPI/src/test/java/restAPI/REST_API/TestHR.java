package restAPI.REST_API;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;


public class TestHR {
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="http://18.206.125.72:1002";
		RestAssured.basePath="/ords/hr/";
	}
	@Test
	public void test1() {
		given()
			.accept(ContentType.JSON)
		.when().log().all()
		.and()
			//.param("limit", 100)
			.queryParam("FIRST_NAME", "Diana")
			//.pathParam("empFirstName", "Diana")
			.get("employees/{FIRST_NAME}")
		.then().log().all()
			.statusCode(200);
		
	}
}
