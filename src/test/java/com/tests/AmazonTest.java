package com.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonTest {
	
	WebDriver driver;
	FileReader fr;
	
	@BeforeMethod
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	

	
	
	
	@DataProvider(name="getData")
	public void readJson() {
		JSONParser jp= new JSONParser();
		try {
			fr= new FileReader(".\\readJSON\\src\\test\\resources\\com\\data\\AmazonData.json");
		} 
		
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		Object obj = jp.parse(fr);
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		
		driver.quit();
	}

}
