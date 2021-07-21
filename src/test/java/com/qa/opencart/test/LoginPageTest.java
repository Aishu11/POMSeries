package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest{
	
	@Test
	public void loginPageTitleTest(){
		String title = loginpage.dogetTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void forgotpasswordlinkTest(){
		System.out.println(loginpage.isForgotPwdLinkExist());
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	
	@Test
	public void loginTest(){
		loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
}
