package com.flipkart.tescases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;
import com.flipkart.pages.CartPage;
import com.flipkart.pages.HomePage;
import com.flipkart.pages.ProductPage;
import com.flipkart.pages.SearchResultsPage;

public class ProductPageTest extends TestBase {

	HomePage homePage;
	SearchResultsPage searchPage;
	ProductPage productPage;

	@BeforeClass
	public void setUp() {

		initialization();
		homePage = new HomePage();
		homePage.login();
		searchPage = homePage.searchProduct(HomePage.productSearchName);
		productPage = searchPage.openProductPage();
	}

	@Test(priority = 1)
	public void pageTitleTest() {
		waitForPageLoad();
		switchFocus();
		String title = getPageTitle();
		System.out.println(title);
		System.out.println(ProductPage.ProductPageTitle);
		boolean productTitle = title.contains(ProductPage.ProductPageTitle);
		Assert.assertEquals(productTitle, true);
	}

	@Test(priority = 2)
	public void productDetailsTest() {
		ProductDetails productDetails = productPage.getProductDetails();
		String productPagePrice = productDetails.productPrice;
		String amount1 = productPagePrice.substring(1, productPagePrice.length());
		System.out.println(amount1);
		Assert.assertNotEquals(amount1, "");
	}

	@Test(priority = 3)
	public void addToCartTest() {

		CartPage cartPage = productPage.addToCart();
	}

	@AfterClass
	public void tearDown() {
		System.out.println("tearDown");

		if (driver != null) {
			driver.quit();
		}
	}

}
