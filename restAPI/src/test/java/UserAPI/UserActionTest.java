package UserAPI;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class UserActionTest {
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="https://www.lalalalalalalal.com.dev.cc";
		RestAssured.basePath="/wp-json/wp/v2";
	}
	@Test
	public void testPublicUserGetOnlyAdmain() {
		
		RestAssured.given()
			.relaxedHTTPSValidation()
			.when()
			.log().all()
			.get("/users")
			.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=UTF-8")
			.body("id",hasSize(1))
			.body("name", hasItem("yuangao"));
	}
	
	@Test
	public void testPublicUserPostOnlyAdma() {
		RestAssured.given()
						.relaxedHTTPSValidation().auth().preemptive().basic("yuangao", "abc123")
					.when()
						.log().all()
						.body("{\n" + 
								"	\n" + 
								"	\"username\" : \"uaaaate\" ,\n" + 
								"	\"name\" : \"BrandNew\" ,\n" + 
								"	\"first_name\" : \"er yyyyy\", \n" + 
								"	\"last_name\" : \"er ggg \" ,\n" + 
								"	\"email\" : \"yuggaffo@g.com\" ,\n" + 
								"	\"roles\" : \"subscriber\",\n" + 
								"	\"password\" : \"user a\" \n" + 
								"	\n" + 
								"	\n" + 
								"}")
							.contentType(ContentType.JSON)
							.post("/users")
						.then()
							.statusCode(201)
							.log().all();
	
	}
	
	@Test
	public void adminUser_shouldBeAbleto_UpdateExistingUser() {
		RestAssured.given()
				.relaxedHTTPSValidation().auth().preemptive().basic("yuangao", "abc123")
				.when()
				.log().all()
				.body("{\n" + 
						"\n" + 
						"	\"first_name\" : \"UpdateToNew\", \n" + 
						"	\"last_name\" : \"OlllOLLLolllO\" ,\n" + 
						"	\"email\" : \"UPDE@gMAI.com\" ,\n" + 
						"	\"roles\" : \"author\" ,\n" + 
						"	\"password\" : \"ABC123\" \n" + 
						"	\n" + 
						"	\n" + 
						"}")
				.contentType(ContentType.JSON)
				.put("users/19")
				.then().statusCode(200)
				.body("email", is("UPDE@gMAI.com"))
				.log().all();
	}
	
	@Test
	public void adminUser_ShouldBeAbleto_DeleteExistingUser() {

		given()
		.relaxedHTTPSValidation()
		.auth().preemptive().basic("yuangao", "abc123")
		.when()
			.pathParam("deleteID", 19)
			.param("reassign", 1)
			.queryParam("force", true)
			.delete("users/{deleteID}")
		.then()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("deleted", is(true))
			.log().all();
	}
	
	@Test
	public void publicUser_shouldNotBeAbleto_ExistingUser_otherThanAdmin() {
	
		given()
			.relaxedHTTPSValidation()
			.auth().preemptive().basic("yuangao", "abc123")
			.pathParam("id", 14)
		.when()
			.log().all()
			.get("users/{id}")
		.then()
			.statusCode(HttpStatus.SC_FORBIDDEN)
			.contentType(ContentType.JSON)
			.body("message", is("Sorry, you are not allowed to list users."));
		
		
		
		 given()
		    .relaxedHTTPSValidation()
		    .auth().preemptive().basic("user_c", "user")
		    .pathParam("id",1)
		  .when()
		    .log().all()
		    .get("/users/{id}")
		    
		  .then()
		    .statusCode(HttpStatus.SC_OK)
		    //.statusCode(200)
		    .contentType(ContentType.JSON)
		    .body("id", is(1) )
		  ;
		
	}
	
	
	
	
	
	
	
	
}
