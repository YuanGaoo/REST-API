package UserAPI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class JSonPath {
	 @BeforeClass
	  public void init() {
	    
	    RestAssured.baseURI = "https://www.lalalalalalalal.com.dev.cc/wp-json/" ; 
	    RestAssured.basePath = "wp/v2" ; 
	    
	  }
	
	 @Test
	 public void testJsonPath() {
		 Response response = given()
		 	.relaxedHTTPSValidation()
		 	.auth().preemptive().basic("yuangao", "abc123")
		 	.contentType(ContentType.JSON)
		 	.pathParam("id", 12)
		 	.get("/users/{id}");
		 JsonPath jsonpath=response.jsonPath();
		 jsonpath.prettyPrint();
		System.out.println(jsonpath.getString("name"));
		System.out.println(jsonpath.getInt("id"));
	 }
	 
	// @Test
	 public void driverinfoTest() {
		 Response response=
				 given()
				 	.relaxedHTTPSValidation()
				 .when()
				 	.log().all()
				 	.get("http://ergast.com/api/f1/drivers.json");
		 response.prettyPrint();
		 JsonPath json=response.jsonPath();
		 
		 System.out.println(json.getString("MRData.DriverTable.Drivers[0].driverId"));
				 	
				 	
	 }
	 
	 
	 
}
