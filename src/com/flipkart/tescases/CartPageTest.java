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

public class CartPageTest extends TestBase {
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
	}

	@Test(priority = 1)
	public void pageTitleTest() {
		waitForPageLoad();
		String title = getPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, CartPage.cartPageTitle);
	}

	@Test(priority = 2)
	public void comparePriceTest() {
		ProductDetails cartPageProductDetails = cartPage.getCartPriceDetails();
		String productPagePrice = productPagePriceDetails.productPrice;
		String cartPagePrice = cartPageProductDetails.productPrice;
		Assert.assertEquals(productPagePrice, cartPagePrice);

	}

	@Test(priority = 3)
	public void compareProductNameTest() {
		ProductDetails cartPageProductDetails = cartPage.getCartPriceDetails();

		String productPageName = productPagePriceDetails.productName;
		String cartPageProductName = cartPageProductDetails.productName;
		System.out.println("productPageName=" + productPageName);
		System.out.println("cartPageProductName=" + cartPageProductName);
//		productPageName=Sony CyberShot DSC-W810/BC IN5  (20.1 MP, 6 Optical Zoom, 12x Digital Zoom, Black)
//		cartPageProductName=Sony CyberShot DSC-W810/BC IN5

		Assert.assertEquals(productPageName.contains(cartPageProductName), true);

	}

	@Test(priority = 4)
	public void placeOrderTest() {
		System.out.println("CheckOutPage::placeOrderTest");

		CheckOutPage checkoutPage = cartPage.placeOrder();
		waitForPageLoad();

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
