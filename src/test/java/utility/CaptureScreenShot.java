package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureScreenShot {
	
	public static void TakeScreenShot(WebDriver driver) {
		
	try {	
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
    Date date = new Date();
	FileUtils.copyFile(scrFile, new File("./ScreenShots/" + "ScreenShot" + "-"+ dateFormat.format(date) + ".png"));
	}
	
	catch(Exception e) {	
	 System.out.println(e.getStackTrace());
		
	}

	}	
	
}
