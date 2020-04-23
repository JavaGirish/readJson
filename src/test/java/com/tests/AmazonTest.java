package com.tests;

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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonTest {
	public static WebDriver driver;

	@BeforeMethod
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in");

	}

	@Test(dataProvider = "dp", priority = 1)
	public void test1(String d) {
		String p = (String) d;
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(p);
		driver.findElement(By.xpath("//input[@value='Go']")).click();

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	@DataProvider(name = "dp")
	public String[] readData() throws IOException, ParseException {

		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\com\\data\\AmazonData.json");
		Object obj = jsonParser.parse(reader);

		JSONObject jso = (JSONObject) obj;
		JSONArray arr = (JSONArray) jso.get("AmazonProducts");
		String array[] = new String[arr.size()];

		for (int i = 0; i < arr.size(); i++) {

			JSONObject data = (JSONObject) arr.get(i);
			String prod = (String) data.get("product");
			array[i] = prod;

		}
		return array;

	}

	@Test(priority = 2,enabled=false)
	public void test2() {
		Assert.assertTrue(driver.findElement(By.id("nav-logo")).isDisplayed());
	}

	@Test(priority = 3,enabled=false)
	public void test3() {
		String text = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String title = driver.getTitle();
		Assert.assertEquals(title, text);

	}

}
