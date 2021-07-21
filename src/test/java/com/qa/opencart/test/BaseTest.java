package com.qa.opencart.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.Pages.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	public DriverFactory driverfactory;
	public Properties prop;
	public LoginPage loginpage;
	
	@BeforeTest
	public void setUp(){
		driverfactory  = new DriverFactory();
		prop= driverfactory.init_properties();
		driver = driverfactory.init_driver(prop);	
		loginpage = new LoginPage(driver); // this driver will now be given to login page class
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
}
