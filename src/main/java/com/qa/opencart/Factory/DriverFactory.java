package com.qa.opencart.Factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Aishwarya
 *
 */
public class DriverFactory {
	
	/**
	 * This method will initialise driver based on the given browser name
	 */
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(Properties prop){
		
		String browsername = prop.getProperty("browser"); // getting browser name from config file
		System.out.println("browser name is:" + browsername);
		
		if(browsername.equals("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browsername.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browsername.equals("safari")){
			driver = new SafariDriver();
		}
		else{
			System.out.println("Please pass the correct browser" + browsername);
		}
		
		driver.get(prop.getProperty("url")); // getting url from config file.
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		return driver;
	}
	
	/**
	 * This method is used to initialise prop from config.properties file
	 * @return
	 */
	public Properties init_properties(){
		prop = new Properties();
		FileInputStream ip;
		try {
			ip = new FileInputStream("./src/test/Resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		
	return prop;	
		
	}
	

}
