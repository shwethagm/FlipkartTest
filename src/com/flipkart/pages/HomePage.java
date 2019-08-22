package com.flipkart.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.TestBase;
import com.flipkart.utilities.Excel.ExcelReader;

public class HomePage extends TestBase {

	@FindBy(xpath = "//input[@class='_2zrpKA _1dBPDZ']")
	WebElement usernameField;

	@FindBy(xpath = "//input[@class='_2zrpKA _3v41xv _1dBPDZ']")
	WebElement passwordField;

	@FindBy(xpath = "//button[@class='_2AkmmA _1LctnI _7UHT_c']")
	WebElement loginButton;

	@FindBy(xpath = "//div[@class='_3Njdz7']")
	WebElement loginPrompt;

	@FindBy(xpath = "//input[@name='q']")
	WebElement searchBar;

	@FindBy(xpath = "//a[@class='_3ko_Ud']")
	WebElement gotoCart;

	ExcelReader reader;
	public static final String homePageTitle = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";

	// Initializing the Page Objects:
	public HomePage() {
		System.out.println("HomePage()");
		PageFactory.initElements(driver, this);
		reader = new ExcelReader();
	}

	public void login() {
		System.out.println("HomePage::login");
		StringBuilder uname = new StringBuilder();
		StringBuilder pass = new StringBuilder();
		reader.read(uname, pass);

		usernameField.sendKeys(uname.toString());
		passwordField.sendKeys(pass.toString());

		waitForVisibility(loginButton);
		loginButton.click();

	}

	public boolean isLoginSuccessful() {
		System.out.println("HomePage::isLoginSuccessful");

//		 login failed if login prompt did not accept credentials
		boolean loginPromptDisplayed = false;

		try {
			Thread.sleep(5000);
			loginPromptDisplayed = loginPrompt.isDisplayed();
		}
		catch (Exception e) {
			// element not found exception
			loginPromptDisplayed = false;
		}

		System.out.println("loginPromptDisplayed =" + loginPromptDisplayed);
		return !loginPromptDisplayed;
	}

	public boolean isLogoutSuccessful() {
		System.out.println("HomePage::isLogoutSuccessful");

//		 logout successful if login prompt is displayed
		boolean loginPromptDisplayed = false;

		try {
			Thread.sleep(5000);
			loginPromptDisplayed = loginPrompt.isDisplayed();
		}
		catch (Exception e) {
			// element not found exception
			loginPromptDisplayed = false;
		}

		System.out.println("loginPromptDisplayed =" + loginPromptDisplayed);
		return loginPromptDisplayed;
	}

	public SearchResultsPage searchProduct(String searchString) {
		System.out.println("HomePage::searchProduct");

		searchBar.sendKeys(searchString);
		searchBar.sendKeys(Keys.ENTER);

		return new SearchResultsPage();
	}

	public CartPage gotoCartPage() {
		waitForVisibility(gotoCart);
		gotoCart.click();
		return new CartPage();
	}
}
