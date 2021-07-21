package com.qa.opencart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementutil;
	
	//By locators:
	By email = By.id("input-email");
	By password = By.id("input-password");
	By loginbtn = By.xpath("//input[@value = 'Login']");
	By forgotpassword = By.linkText("Forgotten Password");
	
	
	//constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(this.driver);
	}
	
	//Page actions
	public String dogetTitle(){
		//return driver.getTitle();
		return elementutil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, 5); //using element util methods
	}
	
	public boolean isForgotPwdLinkExist(){
		//return driver.findElement(forgotpassword).isDisplayed();
		return elementutil.isElementDisplayed(forgotpassword);
		
	}
	
	public void doLogin(String un, String pwd){
//		driver.findElement(email).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginbtn).click();
		elementutil.doSendKeys(email, un);
		elementutil.doSendKeys(password, pwd);
		elementutil.doClick(loginbtn);
	}
}
