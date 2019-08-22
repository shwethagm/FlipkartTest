package com.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.TestBase;

public class CheckOutPage extends TestBase {

	@FindBy(xpath = "//button[@class='_2AkmmA iwYpF9 _7UHT_c']")
	WebElement placeorder;

	@FindBy(xpath = "//span[@class='pMSy0p XU9vZa']")
	WebElement cartpageprice;

	@FindBy(xpath = "//div[@class='_3dGepu']")
	WebElement homepageBtn;

	public static final String pageTitle = "Place Order";

	public CheckOutPage() {

		PageFactory.initElements(driver, this);
	}

	public HomePage navigateToHome() {
		System.out.println("CheckOutPage::navigateToHome");

		waitForVisibility(homepageBtn);
		homepageBtn.click();
		return new HomePage();
	}

}
