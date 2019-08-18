package com.selenium.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

class ProductDetails {
	public String productName;
	public String productPrice;
}

public class SeleniumTest {

	public static WebDriver driver;
	public static String browser;
	public static Properties config = new Properties();
	private static FileInputStream configFile;
	private FlipkartPage flipkart;
	private ProductDetails productPageDetails;
	private ExcelReader reader;

	@BeforeSuite
	public void setUp() {
		System.out.println("setUp()");

		try {
			configFile = new FileInputStream(System.getProperty("user.dir") + "//Properties//config.properties");
			config.load(configFile);

			if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Executables//chromedriver.exe");
				driver = new ChromeDriver();

			} else if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "//Executables//geckodriver.exe");
				driver = new FirefoxDriver();
			}
//			
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			flipkart = new FlipkartPage(driver);
			reader = new ExcelReader();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("BeforeTest");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("AfterTest");
	}
	
	@Test(enabled = true, priority=1)
	public void login() {
		System.out.println("login");

		// Read login data from excel
		StringBuilder uname = new StringBuilder();
		StringBuilder pass = new StringBuilder();
		reader.read(uname, pass);

		System.out.println("loginSuccessTest= " + uname + " " + pass);
		boolean loginSuccess = flipkart.login(uname.toString(), pass.toString());

		Assert.assertEquals(loginSuccess, true);
	}

	@Test(enabled = true, priority=2)
	public void searchProduct() {
		System.out.println("searchProduct");

		productPageDetails = new ProductDetails();
		flipkart.searchProduct(productPageDetails);

		System.out.println("productPageDetails =" + productPageDetails.productPrice);
		// valid nonzero price
		Assert.assertNotEquals(0, productPageDetails.productPrice);
	}

	@Test(enabled = true, priority=3)
	public void addToCart() {
		System.out.println("addToCart");

//		flipkart.addToCart();
	boolean retval1 = flipkart.addToCart();
		
		Assert.assertEquals(retval1, true);
		
	}

	@Test(enabled = true, priority=4)
	public void placeOrder() {
		System.out.println("placeOrder");

		ProductDetails cartDetails = new ProductDetails();
		flipkart.placeOrder(cartDetails);

		System.out.println("cartDetails =" + cartDetails.productPrice);

		Assert.assertEquals(productPageDetails.productPrice, cartDetails.productPrice,
				"Prices in both the page are same");
	}

	@Test(enabled = true, priority=5)
	public void removeFromCart() {
		System.out.println("removeFromCart");
		flipkart.gotoHomepage();
		flipkart.gotoCart();
		boolean retval = flipkart.removeFromCart();
		
		Assert.assertEquals(retval, true);
	}
	
	@Test(enabled = true, priority=6)
	public void logout() {
		System.out.println("logout");

		String retval = flipkart.logout();
		
		Assert.assertEquals(retval, "logout successful");

	}

	@AfterSuite
	public void tearDown() {
		System.out.println("tearDown");

	if (driver != null) {
	driver.quit();
	}

	}

}
