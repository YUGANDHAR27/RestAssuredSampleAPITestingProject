package sampleRestAssured;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import RestAssured.Datahelper.AutenticationCredentials;
import RestAssured.Utils.LoadData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthenticationAPIExample extends AutenticationCredentials

{
	LoadData data=new LoadData();
	//@Test
	public void test1() {
		
	 RestAssured.given().get(data.readData("AuthenticationURL"))
	 .then()
	 .assertThat()
	 .statusCode(201);
				

	}
	//@Test
	public void testWrongCredentials()
	{

		 RestAssured.given().get(data.readData("AuthenticationURL"))
		 .then()
		 .assertThat()
		 .statusCode(404);
	}
	@Test
	public void bearerTokenAuthentication() 
	{
		RequestSpecification request= RestAssured.given();
		String payload="{\r\n"
				+ "            \"name\":\"QA\",\r\n"
				+ "            \"email\":\"ugandh5@gmail.com\",\r\n"
				+ "            \"password\":123456\r\n"
				+ "\r\n"
				+ "}";

	request.header("Content-Type","application/json");
	Response responseFromGenerateToken=request.body(payload).post(data.readData("BearerTokenPostURL"));
	responseFromGenerateToken.prettyPrint();
	
	}
}
