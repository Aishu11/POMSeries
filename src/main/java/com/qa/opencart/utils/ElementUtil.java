package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	public WebDriver driver;

	// Creating constructor hence we shall pass the drive to this class.
	// Anyways we are going to use these methods - so its like give me the
	// driver, and then use these methods

	public ElementUtil(WebDriver driver) { // Constructor
		this.driver = driver;
	}

	// Wrapper Methods:
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> doGetElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public boolean isElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void verifyElementPresent(By locator) {
		List<WebElement> emaillist = doGetElements(locator);
		System.out.println(emaillist.size());
		try {
			if (emaillist.size() == 0) {
				System.out.println("Element not present");
				throw new Exception("ELEMENTNOTPRESENTEXCEPTION");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// For Imgages and link tag:

	public int doGetLinksCount(By locator) {
		return getLinksTextList(locator).size();
	}

	public List<String> getLinksTextList(By locator) {
		List<WebElement> list = doGetElements(locator);
		List<String> textlist = new ArrayList<String>();
		for (WebElement e : list) {
			if (!e.getText().isEmpty()) {
				String textval = e.getText();
				textlist.add(textval);
			}
		}
		return textlist;
	}

	public List<String> getAttributesTextList(By locator, String attributename) {
		List<WebElement> list = doGetElements(locator);
		List<String> attributetextlist = new ArrayList<String>();
		for (WebElement e : list) {
			String attval = e.getAttribute(attributename);
			attributetextlist.add(attval);
		}
		return attributetextlist;
	}

	// **************************************** Drop Down Utils  // *************************************
	public void doSelectDropDownValueByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownValueByVisibleText(By locator, String visibletext) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibletext);
	}

	public void doSelectDropDownValueByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionslist = select.getOptions();
		System.out.println(optionslist.size());
		for (WebElement e : optionslist) {
			String textval = e.getText();
			if (textval.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	public void doSelectDropDownWithoutSelect(By locator, String value) {
		List<WebElement> list = doGetElements(locator);
		for (WebElement e : list) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	//**************************** Wait Utils ****************************/

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	public WebElement doPresenceOfElementLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element, known to be present on the DOM
	 * of a page, is visible. Visibility means that the element is not only
	 * displayed but also has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	public WebElement isElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	
	public boolean isElementInVisible(By locator, int timeout) { // Very rare method to check the invisiblity of the element
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.invisibilityOf(getElement(locator)));
	}
	
	/**
	 * An expectation for checking that all elements present on the web page that match the locator
     * are visible. Visibility means that the elements are not only displayed but also have a height
     * and width that is greater than 0.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	public List<WebElement> waitforElementsToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void printAllElementsTextUsingStreamsWithWait(By locator, int timeout){
		waitforElementsToBeVisible(locator, timeout).stream().forEach(e -> System.out.println(e.getText()));
	}
	
	
	public void printAllElementsTextUsingForEachWithWait(By locator, int timeout){
		List<WebElement> list = waitforElementsToBeVisible(locator, timeout);
		for(WebElement e: list){
			System.out.println(e.getText());
		}
	}
	
	public List<String> getElementTextListWithWait(By locator, int timeout){
		List<WebElement> list = waitforElementsToBeVisible(locator, timeout);
		List<String> textlist = new ArrayList<String>(); // blank string text
		for(WebElement e : list){
			String text = e.getText().trim(); // To avoid space and to add the list
			textlist.add(text);	// get the text and keep adding it to the string list	
		}
		return textlist;
	}
	
	/**
	   * An expectation for checking an element is visible and enabled such that you can click it.
	   *
	   * @param locator used to find the element
	   * @return the WebElement once it is located and clickable (visible and enabled)
	   */
	
	public void clickWhenReady(By locator, int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public void clickElementWhenReady(By locator, int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();
	}
	
	// **************************************** ALERT Wait  Utils  // *************************************
	private Alert waitForAlert(int timeout){ 
		WebDriverWait wait = new WebDriverWait(driver, timeout); 
		return wait.until(ExpectedConditions.alertIsPresent()); // return type of this statement is Alert 
		
	}
	
	public void acceptAlert(int timeout){
		waitForAlert(timeout).accept(); // just calling the above method for alert.accept
	}
	
	public  void dismissAlert(int timeout){
		waitForAlert(timeout).dismiss(); // just calling the above method for alert.dismiss
	}
	
	public  String alertGetText(int timeout){
		return waitForAlert(timeout).getText(); // just calling the above method for alert.getText
	}
	
	public  void alertSendKeys(int timeout, String value){
		waitForAlert(timeout).sendKeys(value); // just calling the above method for alert.getText
	}
	
	// **************************************** FRAME Wait  Utils  // *************************************
	
	public void waitForFrameAndSwitch(String framenameOrId, int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framenameOrId));
	}
	
	public void waitForFrameAndSwitch(int frameindex, int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameindex));
	}
	
	public void waitForFrameAndSwitch(By frameLocator, int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public void waitForFrameAndSwitch(WebElement frameElement, int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	// **************************************** URL Wait  Utils  // *************************************
	
	public boolean waitForURLFraction(String urlFraction, int timeout){// substring of the url
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}
	
	public boolean waitForURLToBe(String urlname, int timeout){// full name of the url
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlToBe(urlname));
	}
	
	// **************************************** Title Wait  Utils  // *************************************
	
	public String waitForTitleIs(String expectedtitle, int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		boolean flag= wait.until(ExpectedConditions.titleIs(expectedtitle));
		
		if(flag){
			return driver.getTitle();
		}
		return null;
	}
	
	public  String waitForTitleContains(String titlefraction, int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		boolean flag= wait.until(ExpectedConditions.titleContains(titlefraction));
		
		if(flag){
			return driver.getTitle();
		}
		return null;
	}


}
