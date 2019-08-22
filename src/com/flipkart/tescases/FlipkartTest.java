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

public class FlipkartTest extends TestBase {

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
	}

	@Test(priority = 1)
	public void loginTest() {
		String title = getPageTitle();
		Assert.assertEquals(title, HomePage.homePageTitle);
		homePage.login();
		Assert.assertEquals(homePage.isLoginSuccessful(), true);
	}

	@Test(priority = 2)
	public void searchProductTest() {
		System.out.println("searchProductTest");

		searchPage = homePage.searchProduct("Camera");
		// assert if new page opened
		waitForPageLoad();
		String title = getPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, SearchResultsPage.SearchPageTitle);
	}

	@Test(priority = 3)
	public void openProductPage() {
		productPage = searchPage.openProductPage();

		// new tab opened - switch focus
		switchFocus();

		// store product page price details
		productPagePriceDetails = productPage.getProductDetails();
		Assert.assertNotEquals(productPagePriceDetails.productPrice, "");
		Assert.assertNotEquals(productPagePriceDetails.productName, "");
	}

	@Test(priority = 4)
	public void addToCartTest() {
		cartPage = productPage.addToCart();
		waitForPageLoad();
		String title = getPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, CartPage.cartPageTitle);
	}

	@Test(priority = 5)
	public void compareProductDetails() {
		ProductDetails cartPageProductDetails = cartPage.getCartPriceDetails();
		// compare name from product page
		System.out.println("productPageName=" + productPagePriceDetails.productName);
		System.out.println("cartPageProductName=" + cartPageProductDetails.productName);
		Assert.assertEquals(productPagePriceDetails.productName.contains(cartPageProductDetails.productName), true);

		// compare price from product page
		Assert.assertEquals(productPagePriceDetails.productPrice, cartPageProductDetails.productPrice);
	}

	@Test(priority = 6)
	public void placeOrderTest() {
		System.out.println("placeOrderTest");

		checkOutPage = cartPage.placeOrder();
		waitForPageLoad();
		String title = getPageTitle();
		System.out.println("title=" + title);
		System.out.println(CheckOutPage.pageTitle);
		boolean productTitle = title.contains(CheckOutPage.pageTitle);
		Assert.assertEquals(productTitle, true);

	}

	@Test(priority = 7)
	public void removeProductFromCart() {
		System.out.println("removeProductFromCart");
		homePage = checkOutPage.navigateToHome();
		cartPage = homePage.gotoCartPage();
		waitForPageLoad();
		cartPage.removeFromCart();
		Assert.assertEquals(cartPage.isCartEmpty(), true);
	}

	@Test(priority = 8)
	public void logoutTest() {
		System.out.println("logoutTest");
		cartPage.logout();
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
