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

public class IntegrationTest extends TestBase {

	HomePage homePage;
	SearchResultsPage searchPage;
	ProductPage productPage;
	CartPage cartPage;
	CheckOutPage checkOutPage;

	ProductDetails productPagePriceDetails;

	@BeforeClass
	public void setUp() {

		initialization();
	}

	@Test(priority = 1)
	public void integrationTest() {
		homePage = new HomePage();
		homePage.login();
		searchPage = homePage.searchProduct(HomePage.productSearchName);
		productPage = searchPage.openProductPage();
		switchFocus();
		productPagePriceDetails = productPage.getProductDetails();
		cartPage = productPage.addToCart();
		waitForPageLoad();
		checkOutPage = cartPage.placeOrder();
		homePage = checkOutPage.navigateToHome();
		cartPage = homePage.gotoCartPage();
		waitForPageLoad();
		cartPage.removeFromCart();
		Assert.assertEquals(cartPage.isCartEmpty(), true);

		homePage = checkOutPage.navigateToHome();
		homePage.logout();

		Assert.assertEquals(homePage.isLogoutSuccessful(), true);

	}

	@AfterClass
	public void tearDown() {
		System.out.println("tearDown");

		if (driver != null) {
			driver.quit();
		}
	}

}
