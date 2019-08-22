package com.flipkart.tescases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;
import com.flipkart.pages.CartPage;
import com.flipkart.pages.CheckOutPage;
import com.flipkart.pages.HomePage;
import com.flipkart.pages.ProductPage;
import com.flipkart.pages.SearchResultsPage;

public class CheckOutPageTest extends TestBase {

	HomePage homePage;
	SearchResultsPage searchPage;
	ProductPage productPage;
	CartPage cartPage;
	CheckOutPage checkOutPage;

	ProductDetails productPagePriceDetails;

	@BeforeClass
	public void setUp() {

		initialization();
		homePage = new HomePage();
		homePage.login();
		searchPage = homePage.searchProduct(HomePage.productSearchName);
		productPage = searchPage.openProductPage();
		switchFocus();
		productPagePriceDetails = productPage.getProductDetails();
		cartPage = productPage.addToCart();
		waitForPageLoad();
		checkOutPage = cartPage.placeOrder();
	}

	@Test(priority = 1)
	public void pageTitleTest() {
		String title = getPageTitle();
		System.out.println("title=" + title);
		System.out.println(CheckOutPage.pageTitle);
		boolean productTitle = title.contains(CheckOutPage.pageTitle);
		Assert.assertEquals(productTitle, true);
	}

	@AfterClass
	public void tearDown() {
		System.out.println("tearDown");

		if (driver != null) {
			driver.quit();
		}
	}

}
