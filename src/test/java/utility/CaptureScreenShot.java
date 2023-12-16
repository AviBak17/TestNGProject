package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class CaptureScreenShot {
	
	public static void TakeScreenShot(WebDriver driver , String strScreenShotPath) {
		
	try {	
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrFile, new File(strScreenShotPath));
	}
	
	catch(Exception e) {	
	 System.out.println(e.getStackTrace());
		
	}

	}	
	
}
