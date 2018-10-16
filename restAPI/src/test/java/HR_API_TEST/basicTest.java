package HR_API_TEST;

import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.hamcrest.MatcherAssert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.RestAssured;


public class basicTest {
	Faker faker=new Faker();
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="http://18.206.125.72:1001";
		RestAssured.basePath="/ords/hr";
	}
	
	//@Test
	public void getRequest() {
		given()
			.relaxedHTTPSValidation()
			.pathParam("employee_id", 101)
		.when()
			.get("/employees/{employee_id}")
		.then()
			
			.statusCode(200)
		.and().log().all()
		.and()
			.body("employee_id", equalTo(101))
			.body("first_name", equalTo("Neena"));

	}
	//@Test
	public void postRequest() {
		int id=faker.number().numberBetween(6, 1000);
		String region=faker.address().country();
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			
		.when()
			.body("{\n" + 
				"\"region_id\" : "+id+",\n" + 
				"\"region_name\" : \""+region+"\"\n" + 
				"}")
			.post("regions/").then()
			.statusCode(201).log().all();	
	}
	
	//@Test
	public void deleteRequest() {
		given()
			.relaxedHTTPSValidation()
		.when()
			.pathParam("region_id", 365)
			.queryParam("force", true)
			.delete("regions/{region_id}")
		.then()
			.log().all()
			.statusCode(200);
//		.and()
//			.assertThat()
//			.body("message", is(""))
		
	}
	int id=faker.number().numberBetween(6, 1000);
	String region=faker.address().country();
	
	@Test(priority=0)
	public void postRequeste() {
		
		System.out.println("post----------------------------");
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.body("{\n" + 
					"\"region_id\" : "+id+",\n" + 
					"\"region_name\" : \""+region+"\"\n" + 
					"}")
			.post("regions/")
		.then()
			.statusCode(201)
		.and()
			.log().all()
		.and()
			.assertThat().body("region_id", equalTo(id));
	}
		
		@Test(priority=1)
		public void get() {
				
		System.out.println("get----------------------------");
		given()
			.relaxedHTTPSValidation()
		.when()
			.pathParam("id",id )
			.get("regions/{id}")
		.then()
			.statusCode(200)
		.and()
			.log().all();
		}
		
		@Test(priority=2)
		public void put() {
		System.out.println("put----------------------------");
		
		given()
			.relaxedHTTPSValidation()
		.when()
			.contentType(ContentType.JSON)
			.pathParam("change", 224)
			.body("{\n" + 
					"\n" + 
					"\"region_name\" : \"Chinaa\"\n" + 
					"}")
			.put("regions/{change}")
			.then().statusCode(200)
			.and().log().all();
		}
		
		@Test(priority=3)
		public void get2() {
		System.out.println("get----------------------------");
		
		given()
		.relaxedHTTPSValidation()
	.when()
		.pathParam("id",id )
		.get("regions/{id}")
	.then()
		.statusCode(200)
	.and()
		.log().all();
		}
		
		@Test(priority=4)
		public void deleteTest(){
		System.out.println("delete----------------------------");
		Response rs=given()
			.relaxedHTTPSValidation()
		.when()
			.pathParam("id", id)
			.queryParam("force", true)
			.delete("regions/{id}");
		JsonPath js=rs.jsonPath();
		System.out.println(js.getInt("rowsDeleted"));
			
		
		}
}











