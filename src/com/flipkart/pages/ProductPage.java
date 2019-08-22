package com.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.TestBase;

public class ProductPage extends TestBase {

	@FindBy(xpath = "//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")
	public WebElement addToCartButton;

	@FindBy(xpath = "//span[@class='_35KyD6']")
	WebElement productName;

	@FindBy(xpath = "//div[@class='_1vC4OE _3qQ9m1']")
	WebElement productPrice;

	public static String ProductPageTitle = "Sony CyberShot DSC-W810/BC IN5";

	public ProductPage() {
		System.out.println("ProductPage()");

		PageFactory.initElements(driver, this);
	}

	public ProductDetails getProductDetails() {
		System.out.println("ProductPage::getProductDetails");

		ProductDetails productDetails = new ProductDetails();

		waitForVisibility(productName);

		productDetails.productName = productName.getText();
		productDetails.productPrice = productPrice.getText();

		return productDetails;
	}

	public CartPage addToCart() {
		System.out.println("ProductPage::addToCart");

		waitForVisibility(addToCartButton);

		addToCartButton.click();

		return new CartPage();

	}

	public boolean isProductVisible() {

		return false;
	}
}
