package utility;

import org.openqa.selenium.WebDriver;

import BasePackage.BaseClass;

public class LaunchBrowser {
	
	public static WebDriver driver = null;
	
	public static WebDriver Launch_Browser() {
		
           driver = BaseClass.NewWebdriver;
           
           return driver;
	}
	

}
