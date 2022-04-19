package RestAssured.Constants;

import java.io.File;

public class FilePathConstants
{
	public static final String USER_HOME = System.getProperty("user.dir") + File.separator;
	public final static String dataProvider = USER_HOME + File.separator + "src/test/resources"
			+ File.separator + "dataProvider" + File.separator + "ApiData";

}
