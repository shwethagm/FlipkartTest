package com.flipkart.tescases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;
import com.flipkart.pages.HomePage;
import com.flipkart.pages.ProductPage;
import com.flipkart.pages.SearchResultsPage;

public class SearchResultsPageTest extends TestBase {

	HomePage homePage;
	SearchResultsPage searchPage;

	@BeforeClass
	public void setUp() {
		System.out.println("SearchResultsPage setUp");

		initialization();
		homePage = new HomePage();
		homePage.login();
		searchPage = homePage.searchProduct(HomePage.productSearchName);
	}

	@Test(priority = 1, enabled = true)
	public void pageTitleTest() {
		waitForPageLoad();
		String title = getPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, SearchResultsPage.SearchPageTitle);
	}

	@Test(priority = 2, enabled = true)
	public void openProductPage() {

		ProductPage productPage = searchPage.openProductPage();

		// test if product page opened
		waitForPageLoad();
		switchFocus();// new tab opened switchfocus

		String title = getPageTitle();
		System.out.println("title=" + title);
		System.out.println(ProductPage.ProductPageTitle);
		boolean productTitle = title.contains(ProductPage.ProductPageTitle);
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
