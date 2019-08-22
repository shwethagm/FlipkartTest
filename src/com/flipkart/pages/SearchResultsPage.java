package com.flipkart.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.TestBase;

public class SearchResultsPage extends TestBase {

	@FindBy(xpath = "//div[@class='_3wU53n']")
	List<WebElement> productList;

	public static final String SearchPageTitle = "Camera - Buy Products Online at Best Price in India - All Categories | Flipkart.com";

	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}

	public ProductPage openProductPage() {
		System.out.println("SearchResultsPage::openProductPage");

		waitForPageLoad();
		int val = new Random().nextInt(productList.size());
		System.out.println("product list size=" + productList.size()+" selecting item "+val);
		WebElement selectProduct = productList.get(val);
		selectProduct.click();

		return new ProductPage();

	}

}
