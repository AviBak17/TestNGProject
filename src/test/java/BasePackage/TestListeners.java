package BasePackage;



import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utility.CaptureScreenShot;
import utility.LaunchBrowser;

public class TestListeners implements ITestListener {
	
	public WebDriver driver = null;	

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		CaptureScreenShot.TakeScreenShot(LaunchBrowser.driver);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		CaptureScreenShot.TakeScreenShot(LaunchBrowser.driver);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Finished");
	}
	
	
	

}
