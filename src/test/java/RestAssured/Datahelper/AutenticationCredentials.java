package RestAssured.Datahelper;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class AutenticationCredentials
{ 
	@BeforeClass
	public void setup() {
		
	RestAssured.authentication=RestAssured.preemptive().basic("ToolsQA", "Testp]Password");

	}
	public void setup1() {
		//enter the wrong password
		RestAssured.authentication=RestAssured.preemptive().basic("ToolsQA", "testp]Password");

		}
}
