package testcases;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


	
	
	public class Deleteaproduct {
		SoftAssert saobj; 
		public Deleteaproduct() {
			saobj= new SoftAssert();
		}
		
		

		@Test
		
		public void deleteaproduct() {
		
			Response response = given().log().all()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type", "application/json; charset=UTF-8")
					.auth().preemptive().basic("demo@techfios.com", "abc123").body(new File("src\\main\\java\\data\\delete.json")).
					when().log().all().get("/delete.php")
					.then()
					.log().all().extract().response();
			int actualresponsecode = response.getStatusCode();
			System.out.println("actualresponsecode:" + actualresponsecode);
		//	Assert.assertEquals(actualresponsecode, 200);
			saobj.assertEquals(actualresponsecode,200);
			String actualHeader=response.getHeader("Content-Type");
			System.out.println("actualHeader:" + actualHeader);
		//	Assert.assertEquals(actualHeader, "application/json");
			saobj.assertEquals(actualresponsecode,"application/json; charset=UTF-8 ");

			String actualresponsebody = response.getBody().asString();
			System.out.println("actualresponsebody:" + actualresponsebody);

			JsonPath jPath = new JsonPath(actualresponsebody);
			String actualproductid = jPath.get("id");
			System.out.println("actualproductid:" + actualproductid);
			//Assert.assertEquals(actualproductid,"3745");
			saobj.assertEquals(actualproductid,"3908");

		}
}