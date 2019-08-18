package com.selenium.test;

//import java.awt.RenderingHints.Key;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartPage {

	WebDriver driver;
	public static Properties OR = new Properties();
	public static FileInputStream propertyFile;

	public FlipkartPage(WebDriver drv) {
		driver = drv;

		try {
			propertyFile = new FileInputStream(System.getProperty("user.dir") + "//Properties//OR.properties");
			OR.load(propertyFile);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Boolean login(String uname, String pass) {

		driver.findElement(By.xpath(OR.getProperty("username"))).sendKeys(uname);
		driver.findElement(By.xpath(OR.getProperty("password"))).sendKeys(pass);
		driver.findElement(By.xpath(OR.getProperty("loginbtn"))).click();

//		 login failed if login prompt did not accept credentials
		WebDriverWait wait = new WebDriverWait(driver, 5);

		try {
			Boolean loginSuccess = wait
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(OR.getProperty("loginPrompt"))));
			return loginSuccess;
		}
		catch (Exception e) {
			return false;
		}
//		closeOtherTabs();

	}

	private void closeOtherTabs() {

		// close additional tabs if any
		String originalHandle = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}
		driver.switchTo().window(originalHandle);

	}

	private void switchFocus() {
		// change focus to new tab
		String originalHandle = driver.getWindowHandle();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(originalHandle);
		driver.switchTo().window(newTab.get(0));
	}

	public void searchProduct(ProductDetails productDetails) {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement element = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("searchText"))));
		element.sendKeys("camera");
		element.sendKeys(Keys.ENTER);

		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("product"))));
		element.click();

		switchFocus();

		// Retrieving product price
		WebElement price1elem = driver.findElement(By.xpath(OR.getProperty("productPagePrice")));
		String amount1 = price1elem.getText();
		int length1 = amount1.length();
		System.out.println("amount1= " + amount1 + " len=" + length1);

		String productPagePrice = amount1.substring(1, length1);
		System.out.println("productPagePrice= " + productPagePrice + " len=" + productPagePrice.length());
		productDetails.productPrice = productPagePrice;

	}

	public boolean addToCart() {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			WebElement myelement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("addToCart"))));
			myelement.click();
		}catch (Exception e) {
			return false;
		}

		boolean emptyCart = wait
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(OR.getProperty("cartEmptyText"))));
			if (emptyCart)
				return true;
			else
				return false;
//		}
//		catch (Exception e) {
//			return false;
//		}

	}

	public void placeOrder(ProductDetails cartDetails) {

		WebElement price2elem = driver.findElement(By.xpath(OR.getProperty("cartPagePrice")));

		String amount2 = price2elem.getText();
		int length2 = amount2.length();
		System.out.println("## amount2= " + amount2 + " len=" + length2);

		String orderPPrice = amount2.substring(1, length2);
		System.out.println("orderPPrice= " + orderPPrice + " len=" + orderPPrice.length());

		WebElement myelement = driver.findElement(By.xpath(OR.getProperty("placeOrder")));

		Actions actions = new Actions(driver);
		actions.moveToElement(myelement);
		actions.click(myelement);
		actions.perform();

		cartDetails.productPrice = orderPPrice;
	}

	public void gotoHomepage() {
		WebElement myelement = driver.findElement(By.xpath(OR.getProperty("homepageBtn")));
		Actions actions = new Actions(driver);
		actions.moveToElement(myelement);
		actions.click(myelement);
		actions.perform();

	}

	public void gotoCart() {
		WebElement myelement = driver.findElement(By.xpath(OR.getProperty("gotoCart")));
		Actions actions = new Actions(driver);
		actions.moveToElement(myelement);
		actions.click(myelement);
		actions.perform();

	}

	public boolean removeFromCart() {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement myelement = driver.findElement(By.cssSelector(OR.getProperty("removeCartBtn")));
		Actions actions = new Actions(driver);
		actions.moveToElement(myelement);
		actions.click(myelement);
		actions.perform();

		WebElement removeCartPrompt = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("removeCartPrompt"))));

		if (!removeCartPrompt.isDisplayed())
			return false;

		WebElement removeCartAccept = driver.findElement(By.xpath(OR.getProperty("removeCartAcceptBtn")));
		actions.moveToElement(removeCartAccept);
		actions.click(removeCartAccept);
		actions.perform();

		try {
			myelement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("cartEmptyText"))));
			return myelement.isDisplayed();

		}
		catch (Exception e) {
			return false;
		}
	}

	public String logout() {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Hover menu items
		WebElement menu = driver.findElement(By.xpath(OR.getProperty("myAccount")));
		Actions action = new Actions(driver);
		action.moveToElement(menu).build().perform();

		WebElement myelement;

		String retval = "";
		// select logout from menu list
		try {
			myelement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("logoutButton"))));
			myelement.click();

			// if logout successful login prompt should be visible
			WebElement loginprompt = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("loginPrompt"))));

			if (loginprompt.isDisplayed())
				retval = "logout successful";
		}
		catch (Exception e) {
			retval = "logout failed";
		}

		return retval;

	}

}
