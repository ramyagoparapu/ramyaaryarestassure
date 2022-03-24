package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;

public class createproduct {
	SoftAssert saobj; 
	public createproduct() {
		saobj= new SoftAssert();
	}
	
	

	@Test
	
	public void createaproduct() {
	
		Response response = given().log().all()
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8")
				.auth().preemptive().basic("demo@techfios.com", "abc123").queryParam("id","3745")
				.body(new File("src\\main\\java\\data\\createpayload.json")).
				when().log().all().get("/create.php")
				.then()
				.log().all().extract().response();
		int actualresponsecode = response.getStatusCode();
		
		System.out.println("actualresponsecode:" + actualresponsecode);
	//	Assert.assertEquals(actualresponsecode, 200);
		saobj.assertEquals(actualresponsecode,201);
		String actualHeader=response.getHeader("Content-Type");
		System.out.println("actualHeader:" + actualHeader);
	//	Assert.assertEquals(actualHeader, "application/json");
		saobj.assertEquals(actualresponsecode,"application/json; charset=UTF-8");

		String actualresponsebody = response.getBody().asString();
		System.out.println("actualresponsebody:" + actualresponsebody);

		JsonPath jPath = new JsonPath(actualresponsebody);
		String actualproductmsg = jPath.get("message");
		System.out.println("actualproductmsg:" + actualproductmsg);
		//Assert.assertEquals(actualproductid,"3745");
		saobj.assertEquals(actualproductmsg,"Product was created");
		
	}

}
