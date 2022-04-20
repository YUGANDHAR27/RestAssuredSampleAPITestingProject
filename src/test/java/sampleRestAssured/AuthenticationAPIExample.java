package sampleRestAssured;

import static org.hamcrest.Matchers.hasItem;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

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
	//@Test
	public void bearerTokenAuthentication() 
	{
		RequestSpecification request= RestAssured.given();
		String payload="{\r\n"
				+ "            \"name\":\"QA\",\r\n"
				+ "            \"email\":\"ar5@gmail.com\",\r\n"
				+ "            \"password\":123456\r\n"
				+ "\r\n"
				+ "}";

	request.header("Content-Type","application/json");
	Response responseFromGenerateToken=request.body(payload).post(data.readData("BearerTokenPostURL"));
	responseFromGenerateToken.prettyPrint();
	//convert response to string
	String jsonString=responseFromGenerateToken.getBody().asString();
	//extract the token
	String tokenGenerated= io.restassured.path.json.JsonPath.from(jsonString).get("Token");
	//Enter the token
	request.header("Authorization","Bearer"+tokenGenerated).header("Content-Type","application/json");
	
	Response get=request.get(data.readData("BearertokenGetUsers"));
	get.statusCode();
	get.prettyPrint();
	}
	//@Test
	public void invalidPayloadForBearerToken() 
	{
		RequestSpecification request= RestAssured.given();
		String payload="{\r\n"
				+ "            \"email\":\"ar5@gmail.com\",\r\n"
				+ "            \"password\":123456\r\n"
				+ "\r\n"
				+ "}";

	request.header("Content-Type","application/json");
	Response responseFromGenerateToken=request.body(payload).post(data.readData("BearerTokenPostURL"));
	responseFromGenerateToken.prettyPrint();
		
	}
	
	//@Test
	public void oAuth2() 
	{
		String apiToken=data.readData("GithubAPIToken");
		String url=data.readData("GithubGET.URL");
		RestAssured.given().auth().oauth2(apiToken)
		.when().
		get(url)
		.then()
		.log().body();
		
	}
	//@Test
	public void oAuth2WithInvalidToken() 
	{
		String apiToken=data.readData("GithubInvalidToken");
		String url=data.readData("GithubGET.URL");
		RestAssured.given().auth().oauth2(apiToken)
		.when().
		get(url)
		.then()
		.log().all();
		
		
	}
	
	//@Test
	public void queryParam() {
		String apiToken=data.readData("GithubAPIToken");
		String url=data.readData("GithubGET.URL");
		RestAssured.given().auth().oauth2(apiToken)
		.queryParam("sort", "created")
		.queryParam("direction", "desc")
		.when().
		get(url)
		.then()
		.log().body();
	}
	
//	@Test
	public void queryParamWithInvalidToken() {
		String apiToken=data.readData("GithubInvalidToke");
		String url=data.readData("GithubGET.URL");
		RestAssured.given().auth().oauth2(apiToken)
		.queryParam("sort", "created")
		.queryParam("direction", "desc")
		.when().
		get(url)
		.then()
		.log().body();
	}

	//@Test
	public void pathParam()
	{
		RestAssured.given().baseUri(data.readData("PathParameterGET.URI"))
		.basePath(data.readData("PathPArameterBasPath"))
	.pathParam("basePath", data.readData("Parameter1"))
		.pathParam("bookingId", data.readData("Parameter2"))
		.when()
	.get().then()
	.assertThat().header(data.readData("Headertype"), data.readData("HeaderValue"))
	//Verify Status Line
	.statusLine(data.readData("StatusLineforGET"))
	//Verify Status code
	.statusCode(200).
	//verify response time lesser than 3000 milliseconds
	time(Matchers.lessThan(10000L))
	.log().all();
	
	}
	//@Test
	public void pathParamWithInvalidParameter() 
	{
		RestAssured.given().baseUri(data.readData("PathParameterGET.URI"))
		.basePath(data.readData("PathPArameterBasPath"))
	.pathParam("basePath", data.readData("Parameter1"))
		.pathParam("bookingId", data.readData("InvalidPathParameter"))
		.when()
	.get().then()
	.assertThat().header(data.readData("Headertype"), data.readData("HeaderValue"))
	//Verify Status Line
	.statusLine(data.readData("StatusNotFoundLine"))
	//Verify Status code
	.statusCode(404).
	//verify response time lesser than 3000 milliseconds
	time(Matchers.lessThan(10000L))
	.log().all();
	
		
	}
	
}
