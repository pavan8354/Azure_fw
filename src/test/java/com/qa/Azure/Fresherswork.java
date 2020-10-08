package com.qa.Azure;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Fresherswork {
	WebDriver driver;
	
	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();			
			
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();		
			
		}
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("https://www.freshworks.com/");
		
	}
	
	@Test(priority=1)
public void Freshwork_logotest() {
	
	boolean flag = false;
	
	flag = driver.findElement(By.xpath("//div[@class='nav-logo-wrapper']//img[contains(@class,'logo logo-fworks1')]")).isDisplayed();
	AssertJUnit.assertTrue(flag);	
	}
	@Test(priority=2)
	public void Freshwork_titletest() {
		
		System.out.println(driver.getTitle());
		AssertJUnit.assertEquals(driver.getTitle(),"A fresh approach to customer engagement");
		}
	@Test(priority=3)
	public void Freshwork_linkstest() {
		
		List<WebElement> linklist = driver.findElements(By.xpath("//div[@class='col-md-4 footer-left-section']"));
		linklist.forEach(ele -> System.out.println(ele.getText()));
		AssertJUnit.assertEquals(linklist.size(),35);
		}
	@AfterMethod
	public void teardown() {
		
		driver.quit();
	}
	
}
