package BasePackage;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.CaptureScreenShot;
import utility.LaunchBrowser;
import utility.ReadExcelData;



public class BaseClass {

	public WebDriver driver = null;	
	public static Map<String, String> NewExcelData = null;
	public static WebDriver NewWebdriver = null;
    public static ExtentReports extent = null;
    public static ExtentTest extentTest = null;
    
	@Parameters("browser")
	@BeforeTest()
	public void launchBrowser(ITestContext context , String browser) {

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
		extentTest = extent.createTest(context.getName());
		Capabilities capabilties = ((RemoteWebDriver) driver).getCapabilities();
		String DeviceName = capabilties.getBrowserName() + "  " + capabilties.getBrowserVersion().substring(0,capabilties.getBrowserVersion().indexOf(".")) + "  " + capabilties.getPlatformName();	
		String Author = context.getCurrentXmlTest().getParameter("author");
		extentTest.assignDevice(DeviceName);
		extentTest.assignAuthor(Author);
		
		
	}


	@AfterSuite()
	public void quitbrowser() {

		driver.close();
		driver.quit();
		extent.flush();

	}

	@BeforeTest(dependsOnMethods= {"launchBrowser"})
	@Parameters({"FilePath","SheetName"})
	public void ReadExcelData(String FilePath, String SheetName,ITestContext context ) {

		Map<String, String> ExcelData = null;

		try {

			ReadExcelData ex = new ReadExcelData(FilePath,SheetName,context);
			ExcelData =ex.getExcelAsMap();

		}

		catch (Exception e) {

			System.out.println("Excel Reader was not able to read the data " + e.getMessage().toString());		

		}
		NewExcelData = ExcelData;

	}
	
	@BeforeSuite()
	public void InitializeExtentReport() {
		
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		extent = new ExtentReports();
	    extent.attachReporter(spark);
	    
	}
	
	@AfterMethod
	public void TestStatus(Method m,ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			String strScreenShotPath  = "";
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
		    Date date = new Date();
			strScreenShotPath  = "./ScreenShots/"  +  "ScreenShot"  +  "-" + dateFormat.format(date) +  ".png" ;
			CaptureScreenShot.TakeScreenShot(LaunchBrowser.driver,strScreenShotPath);
			extentTest.addScreenCaptureFromPath(strScreenShotPath);
			extentTest.fail(m.getName() + " is failed");
			extentTest.fail(result.getThrowable());	
		}
		
		else if(result.getStatus() == ITestResult.SUCCESS) {
			
			extentTest.pass(m.getName() + " is Passed");
			
		}
			
		extentTest.assignCategory(m.getAnnotation(Test.class).groups());
		
	}


}
