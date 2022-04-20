package sampleRestAssured;



import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import RestAssured.Utils.LoadData;
import io.restassured.assertion.BodyMatcher;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SampleAPITestingExamples 
{
	LoadData data=new LoadData();
	//@Test
	void Test_01() 
	{
		Response response =get(data.readData("GETUrl"));
		System.out.println(response.asString());
	System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("Headercontent"));
		System.out.println(response.getTime());
	int statusCode= response.getStatusCode();
		Assert.assertEquals(statusCode, 200);		
	}
	//@Test
	void Test_02()
	{
		get(data.readData("GETUrl")).then().assertThat().
		body("data.id[0]", Is.is(data.readData("VerifyID1")))
		.body("data.email", hasItem(data.readData("VerifyEmail1")))
		.header(data.readData("Headertype"), data.readData("HeaderValue"))
		.statusCode(200).log().headers();

	}
	//@Test
	public void test_POST()
	{
		//creating the body in json format using java map
		//      Map<String, Object>	map= new HashMap<String, Object>();
		//      map.put("\"name\"", "yugandhar");
		//      map.put("\"job\"", "Software");
		//      System.out.println(map);
		JSONObject request= new JSONObject();
		request.put("name", data.readData("EmployeeName1"));
		request.put("job", data.readData("Job1"));
		//System.out.println(request);	
		System.out.println(request.toJSONString());
		given()
		.header("HeaderContent","HeaderContentType").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.post(data.readData("PostURL"))
		.then()
		.assertThat().header(data.readData("Headertype"), data.readData("HeaderValue"))
		//Verify Status Line
		.statusLine(data.readData("statusLineforPost"))
		//Verify Status code
		.statusCode(201).
		//verify response time lesser than 3000 milliseconds
		time(Matchers.lessThan(3000L))
		.log().all();


	}
	//@Test
	public void test_PUT() 
	{
		JSONObject request= new JSONObject();
		request.put("name", data.readData("EmployeeName1"));
		request.put("job", data.readData("Job1"));
		//System.out.println(request);	
		System.out.println(request.toJSONString());
		given()
		//Get headers
		.header("HeaderContent","HeaderContentType").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.post(data.readData("PUTurl"))
		.then()
		.assertThat().header(data.readData("Headertype"), data.readData("HeaderValue"))
		//Verify Status Line
		.statusLine(data.readData("statusLineforPost"))
		//Verify Status code
		.statusCode(201).
		//verify response time lesser than 3000 milliseconds
		time(Matchers.lessThan(3000L))
		.log().all();


		
	}
	//@Test
	public void test_PATCH() 
	{

		JSONObject request= new JSONObject();
		request.put("name", data.readData("EmployeeName1"));
		request.put("job", data.readData("Job1"));
		//System.out.println(request);	
		System.out.println(request.toJSONString());
		given()
		//Get headers
		.header("HeaderContent","HeaderContentType").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.post(data.readData("PUTurl"))
		.then()
		.assertThat().header(data.readData("Headertype"), data.readData("HeaderValue"))
		//Verify Status Line
		.statusLine(data.readData("statusLineforPost"))
		//Verify Status code
		.statusCode(201).
		//verify response time lesser than 3000 milliseconds
		time(Matchers.lessThan(3000L))
		.log().all();
	
	}
	//@Test
	public void test_DELETE() 
	{
	when()
	.delete(data.readData("DELETEurl"))
	.then()
	.statusCode(204).log().all();



		
	}
	@Test
	public void test_POSTUnsuccessful()
	{
		JSONObject request= new JSONObject();
		request.put("name", data.readData("EmployeeEmail"));
		System.out.println(request.toJSONString());
		given()
		.header("HeaderContent","HeaderContentType").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.post(data.readData("PostUnsuccessful"))
		.then()
		.assertThat().header(data.readData("Headertype"), data.readData("HeaderValue"))
		//Verify Status Line
		.statusLine(data.readData("StatusLineBadRequest"))
		//Verify Status code
		.statusCode(400).
		//verify response time lesser than 3000 milliseconds
		time(Matchers.lessThan(3000L))
		.log().all();
	}
	
	}





