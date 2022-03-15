package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class readproducts {
	@Test
	public void readallproducts() {
		SoftAssert sAssert=new SoftAssert();
		Response response=
		given().log().all()
		.baseUri("https://techfios.com/api-prod/api/product")
		.header("Content-Type","application/json; charset=UTF-8")
		.auth().preemptive().basic("demo@techfios.com", "abc123").
		when().log().all().get("/read.php").
		then().log().all().extract().response();
	int actualresponsecode=response.getStatusCode();
	System.out.println("actualresponsecode:"+actualresponsecode);
Assert.assertEquals(actualresponsecode, 200);

String actualresponsebody=response.getBody().asString();
System.out.println("actualresponsebody:"+actualresponsebody);

		JsonPath jPath=new JsonPath(actualresponsebody);
				String firstproductid=jPath.get("records[0].id");
				System.out.println("firstproductid: "+firstproductid);
				if(firstproductid!=null) {
					System.out.println("Records are not null");
				}
					else {
						System.out.println("Records are null");
					}
				}
	}


