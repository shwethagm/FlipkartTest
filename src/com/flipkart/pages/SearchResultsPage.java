package com.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.TestBase;

public class SearchResultsPage extends TestBase {

//	public String ProductDetails;

	@FindBy(xpath = "//img[@alt='Sony CyberShot DSC-W810/BC IN5']")
	WebElement product;

	public static final String SearchPageTitle = "Camera - Buy Products Online at Best Price in India - All Categories | Flipkart.com";

	// initializing the Page objects
	public SearchResultsPage() {
		System.out.println("SearchResultsPage()");
		PageFactory.initElements(driver, this);
	}

	public String getPageTitle() {
		System.out.println("SearchResultsPage::getPageTitle()");
		return driver.getTitle();
	}

	public ProductPage openProductPage() {
		System.out.println("SearchResultsPage::openProductPage");

		waitForVisibility(product);
		product.click();
		return new ProductPage();

	}

}
