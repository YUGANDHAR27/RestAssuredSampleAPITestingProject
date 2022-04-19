package RestAssured.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import RestAssured.Constants.FilePathConstants;


public class LoadData
{
	File inputdata = new File(FilePathConstants.dataProvider);
	FileInputStream fis=null;
	Properties property=null;
	public LoadData() 
	{
		try {
			fis=new FileInputStream(inputdata);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		property=new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String readData(String data)
	{
		return property.getProperty(data);
		
	}
	

}
