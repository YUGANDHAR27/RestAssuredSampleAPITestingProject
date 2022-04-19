package sampleRestAssured;
import static io.restassured.RestAssured.when;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RestAssured.Datahelper.DataForTests;
import RestAssured.Utils.LoadData;
public class DeleteDataDrivenTesting extends DataForTests
{
   LoadData data=new LoadData();
	@Test(dataProvider = "Delete")
	public void test_DELETE(String userID) 
	{
		when()
		.delete(data.readData("DeleteURL")+userID)
		.then().assertThat()
		.header(data.readData("Headertype"), data.readData("HeaderValue"))
		.time(Matchers.lessThan(3000L))
		.log().all();
	}

}
