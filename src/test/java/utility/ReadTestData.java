package utility;

import java.util.Map;

import BasePackage.BaseClass;

public class ReadTestData {
	
	public static Map<String, String> TestData = null ;
	
	public static Map<String, String>  ReadData() {
		
		TestData = BaseClass.NewExcelData;
		
		return TestData;
	}

}
