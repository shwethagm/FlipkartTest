package com.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.TestBase;

public class CartPage extends TestBase {

	@FindBy(xpath = "//button[@class='_2AkmmA iwYpF9 _7UHT_c']")
	WebElement placeorder;

	@FindBy(xpath = "//span[@class='pMSy0p XU9vZa']")
	WebElement cartPageProductPrice;

	@FindBy(xpath = "//div[@class='_1Ox9a7']")
	WebElement cartPageProductName;

	@FindBy(xpath = "//div[@class='gdUKd9'][contains(text(),'Remove')]")
	WebElement removeCartBtn;

	@FindBy(xpath = "//div[@class='_3hgEev']")
	WebElement removeCartPrompt;

	@FindBy(xpath = "//div[@class='gdUKd9 _3Z4XMp _2nQDKB']")
	WebElement removeCartAcceptBtn;

	@FindBy(xpath = "//div[@class='t-0M7P _27IFdQ']")
	WebElement myCartList;

	@FindBy(xpath = "//div[@class='hJKWmk']")
	WebElement cartEmptyText;

	@FindBy(xpath = "//div[@class='_3dGepu']")
	WebElement homepageBtn;

	public static final String cartPageTitle = "Shopping Cart | Flipkart.com";

	public CartPage() {

		PageFactory.initElements(driver, this);
	}

	public ProductDetails getCartPriceDetails() {

		ProductDetails productDetails = new ProductDetails();
		productDetails.productPrice = cartPageProductPrice.getText();
		productDetails.productName = cartPageProductName.getText();

		return productDetails;
	}

	public CheckOutPage placeOrder() {

		System.out.println("CartPage::placeorder");
		waitForVisibility(placeorder);

		placeorder.click();

		return new CheckOutPage();

		// return checkoutPage()

//		Actions actions = new Actions(driver);
//		actions.moveToElement(myelement);
//		actions.click(myelement);
//		actions.perform();

//		cartDetails.productPrice = orderPPrice;
	}

	public void removeFromCart() {
		System.out.println("CheckOutPage::removeFromCart");

		waitForVisibility(removeCartBtn);
		removeCartBtn.click();

		waitForVisibility(removeCartPrompt);
		waitForVisibility(removeCartAcceptBtn);
		removeCartAcceptBtn.click();
	}

	public boolean isCartEmpty() {

		waitForVisibility(cartEmptyText);
		boolean cartEmpty = false;
		try {
			cartEmpty = cartEmptyText.isDisplayed();
		}
		catch (Exception e) {
			cartEmpty = false;
		}

		return cartEmpty;
	}

	public HomePage navigateToHome() {
		System.out.println("navigateToHome");

		waitForVisibility(homepageBtn);
		homepageBtn.click();
		return new HomePage();
	}

}
