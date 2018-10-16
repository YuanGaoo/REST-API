package restAPI.REST_API;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;
public class RestTest{

	@Test
	public void firstTest() {
			// Given rest endpoint http://http://18.212.186.154:1001/ords/hr/employees/
	        // When I send HTTP Ged request to the server
	        // Then I should get 200 OK result as status code
		when()
		.get("http://18.206.125.72:1001/ords/hr/employees")
		.then().statusCode(200);
	}
	
	@Test
	public void secondTest() {
		given().relaxedHTTPSValidation()
		.when().get("https://www.lalalalalalalal.com.dev.cc/wp-json/wp/v2/posts")
		.then().assertThat().statusCode(200);
	}
	
//	@Test
	public void idTest() {
		// Given rest endpoint 
		//https://www.lalalalalalalal.com.dev.cc/wp-json/wp/v2/posts
        // When I send HTTP Ged request to the server
        // Then I should get 200 OK result as status code
		
		given().relaxedHTTPSValidation()
		.when().get("https://www.lalalalalalalal.com.dev.cc/wp-json/wp/v2/posts/20")
		.then().statusCode(200)
		.and().body("id", equalTo(22))
		.body("title.rendered",equalTo( "moti"));
	}

	@Test
	public void idTest2() {
		// Given rest endpoint 
		//https://www.lalalalalalalal.com.dev.cc/wp-json/wp/v2/posts
        // When I send HTTP Ged request to the server
        // Then I should get 200 OK result as status code
		
		given().relaxedHTTPSValidation()
		.when()
		.log().all()
		.get("https://www.lalalalalalalal.com.dev.cc/wp-json/wp/v2/posts/{id}",22)
		.then().statusCode(200)
		.and().body("id", equalTo(22))
		.body("title.rendered",equalTo( "API-DAY3"));
	}
	
	@BeforeClass
	public void setUp(){
		RestAssured.baseURI="https://www.lalalalalalalal.com.dev.cc/wp-json";
		basePath="/wp/v2";
		}
	
	@Test
	public void baseURI_Test() {
		given().relaxedHTTPSValidation()
		.when()
		.get("/posts")
		.then().statusCode(200);
		
				
	}
}
