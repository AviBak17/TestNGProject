package BasePackage;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;


import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ReadExcelData;


public class BaseClass {

	public WebDriver driver = null;	
	public static Map<String, String> NewExcelData = null;
	public static WebDriver NewWebdriver = null;

	@Parameters("browser")
	@BeforeTest()
	public void launchBrowser(String browser) {

		if(browser.equals("Chrome")) {

			WebDriverManager.chromedriver().setup();	
			driver = new ChromeDriver();


		}
		else if(browser.equals("Edge")) {

			WebDriverManager.edgedriver().setup();		
			driver = new EdgeDriver();
		}

		else if(browser.equals("FireFox")) {

			WebDriverManager.firefoxdriver().setup();	
			driver = new FirefoxDriver();
		}

		NewWebdriver = driver;
		
	}

	@AfterTest()
	public void quitbrowser() {

		driver.close();
		driver.quit();

	}

	@BeforeTest(dependsOnMethods= {"launchBrowser"})
	@Parameters({"FilePath","SheetName"})
	public void ReadExcelData(String FilePath, String SheetName) {

		Map<String, String> ExcelData = null;

		try {

			ReadExcelData ex = new ReadExcelData(FilePath,SheetName);
			ExcelData =ex.getExcelAsMap();

		}

		catch (Exception e) {

			System.out.println("Excel Reader was not able to read the data " + e.getMessage().toString());		

		}
		NewExcelData = ExcelData;

	}


}
