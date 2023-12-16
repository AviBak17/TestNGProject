package TestCases;


import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import BasePackage.BaseClass;
import utility.LaunchBrowser;
import utility.ReadTestData;

public class LoginTest extends BaseClass {

	Map<String, String> TestData = null;	
	WebDriver driver ;
	
	@BeforeTest(dependsOnMethods= {"launchBrowser","ReadExcelData"})
	public void ConfigureAllDetails() {
		
		TestData = ReadTestData.ReadData();
		driver = LaunchBrowser.Launch_Browser();
	
	}
	
	@Test(priority=1)
	
	public void Login() {
	
		driver.get("https://www.google.co.in");
			
	}

}
