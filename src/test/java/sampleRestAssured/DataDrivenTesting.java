package sampleRestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RestAssured.Datahelper.DataForTests;
import RestAssured.Utils.LoadData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DataDrivenTesting extends DataForTests
{
	LoadData data=new LoadData();
	@Test(dataProvider = "DataforPost")
	public void test_POST(String name , String Job)
	{
		//creating the body in json format using java map
		//      Map<String, Object>	map= new HashMap<String, Object>();
		//      map.put("\"name\"", "yugandhar");
		//      map.put("\"job\"", "Software");
		//      System.out.println(map);
		JSONObject request= new JSONObject();
		request.put("name", name);
		request.put("job", Job);
		//System.out.println(request);	
		System.out.println(request.toJSONString());
		given()
		//.header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.post(data.readData("PostURL"))
		.then()
		//Verify Headers
		.assertThat().header(data.readData("Headertype"), data.readData("HeaderValue"))
		//Verify Status Line
		.statusLine(data.readData("statusLineforPost"))
		//Verify Status code
		.statusCode(201).
		//verify response time lesser than 3000 milliseconds
		time(Matchers.lessThan(3000L))
		.log().all();


	}
		
		}		    
		
	
	
		
	
 

