package com.flipkart.base;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static String browser;
	private static FileInputStream configFile;

	public class ProductDetails {
		public String productName;
		public String productPrice;
	}

	public void initialization() {

		System.out.println("initialization()");

		try {
			configFile = new FileInputStream(System.getProperty("user.dir") + "//Properties//config.properties");
			config.load(configFile);

			if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "//Executables//chromedriver.exe");
				driver = new ChromeDriver();

			} else if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "//Executables//geckodriver.exe");
				driver = new FirefoxDriver();
			}

			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForVisibility() {
		try {
			Thread.sleep(10 * 1000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForPageLoad() {
		try {
			Thread.sleep(10 * 1000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPageTitle() {
		System.out.println("TestBase::getPageTitle()");
		return driver.getTitle();
	}

	// change focus to new tab
	public void switchFocus() {
		System.out.println("TestBase::switchFocusNewTab()");
		String originalHandle = driver.getWindowHandle();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(originalHandle);
		driver.switchTo().window(newTab.get(0));
	}

}
