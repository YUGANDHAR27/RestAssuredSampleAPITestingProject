package RestAssured.Datahelper;

import org.testng.annotations.DataProvider;

import RestAssured.Utils.LoadData;

public class DataForTests
{
	LoadData data=new LoadData();
	@DataProvider(name = "DataforPost")
	public Object[][] dataforPOST()
	{
		return new Object[][] {
			{data.readData("EmployeeName1"),data.readData("Job1")},
			{data.readData("EmployeeName2") ,data.readData("Job2") }
			
		};
				    
	
	}
	@DataProvider(name = "Delete")
	public Object[] dataforDelete()
	{
		return new Object[] {
				data.readData("EmployeeID"),data.readData("EmployeeID2")

		};

	}

}
