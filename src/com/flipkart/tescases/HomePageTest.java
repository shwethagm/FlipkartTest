package com.flipkart.tescases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;
import com.flipkart.pages.HomePage;
import com.flipkart.pages.SearchResultsPage;
import com.flipkart.utilities.Excel.ExcelReader;

public class HomePageTest extends TestBase {

	HomePage homePage;
	SearchResultsPage searchResultsPage;
	ExcelReader reader;

	@BeforeClass
	public void setUp() {

		initialization();
		homePage = new HomePage();
		reader = new ExcelReader();

	}

	@Test(priority = 1)
	public void pageTitleTest() {
		String title = getPageTitle();
		Assert.assertEquals(title, HomePage.homePageTitle);
	}

	@Test(priority = 2)
	public void loginTest() {
		homePage.login();
		Assert.assertEquals(homePage.isLoginSuccessful(), true);
	}

	@Test(priority = 3)
	public void searchProductTest() {
		System.out.println("searchProductTest");

		SearchResultsPage searchPage = homePage.searchProduct(HomePage.productSearchName);

		// assert if new page opened
		waitForPageLoad();
		String title = getPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, SearchResultsPage.SearchPageTitle);

	}

	@Test(priority = 4)
	public void logoutTest() {
		System.out.println("logoutTest");
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