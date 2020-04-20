package com.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonTest {

	public static WebDriver driver;
	public static FileReader fr;
	public static Object obj;
	public static JSONParser jp;

	@BeforeMethod
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@Test()
	public void searchProduct() {

		driver.findElement(By.id("twotabsearchtextbox")).isDisplayed();
	}

	@DataProvider(name = "getData")
	public String[] readJson() {
		jp = new JSONParser();

		try {
			fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\com\\data\\AmazonData.json");
		}

		catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		try {
			obj = jp.parse(fr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject jobj = (JSONObject) obj;
		JSONArray array = (JSONArray) jobj.get("AmazonProducts");
		String arr[] = new String[array.size()];

		for (int i = 0; i < array.size(); i++) {

			JSONObject prod = (JSONObject) array.get(i);
			String searchname = (String) prod.get("product");
			System.out.println(searchname);
			arr[i] = searchname;
		}

		return arr;

	}

	//@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
