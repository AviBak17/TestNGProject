package TestCases;


import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import BasePackage.BaseClass;
import utility.LaunchBrowser;
import utility.ReadTestData;

public class CreateOrder extends BaseClass {

	Map<String, String> TestData = null;	
	WebDriver driver ;
	
	@BeforeTest(dependsOnMethods= {"launchBrowser","ReadExcelData"})
	public void ConfigureAllDetails() {
		
		TestData = ReadTestData.ReadData();
		driver = LaunchBrowser.Launch_Browser();
	
	}
			
	@Test(priority=1 , groups = "Regression")	
	public void Login() {
		
		String strUrl = TestData.get("Url");
		String strUserName = TestData.get("UserName");
		String strPassword = TestData.get("Password");
		
		driver.get(strUrl);
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(strUserName);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(strPassword);
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
		
	}
	
	@Test(priority=2 , groups = "Regression")	
	public void AddToCart() {
		
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
			
	}

	
	@Test(priority=3 , groups = "Regression")	
	public void AddUserDetails() {
		
		String strFirstName = TestData.get("FirstName");
		String strLastName = TestData.get("LastName");
		String strZipCode = TestData.get("ZipCode");
		
		driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys(strFirstName);
		driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys(strLastName);
		driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys(strZipCode);	
		driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
			
	}
	
	
	@Test(priority=4 , groups = "smoke")	
	public void CheckOutAndLogOut() {
		
		driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();	
		if(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText().equals("Checkout: Complete!")) {	
			driver.findElement(By.xpath("//*[@id=\"back-to-products\"]")).click();
			
		}
			
	}
	
}
