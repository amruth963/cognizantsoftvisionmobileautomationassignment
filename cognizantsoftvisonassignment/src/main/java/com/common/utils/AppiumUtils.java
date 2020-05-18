package com.common.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AppiumUtils {

	public static AppiumDriver<MobileElement> driver;
	private static final Logger LOGGER = Logger.getLogger(Class.class.getName());
	private boolean flag = true;



	@BeforeTest
	public void setup() throws InterruptedException, MalformedURLException
	{
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("deviceName", "OnePlus 6");
		cap.setCapability("udid", "192.168.1.100:5555");
		cap.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		cap.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "10");
		cap.setCapability("noReset", true);
		driver=new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		LOGGER.info("Appium driver started");
	}


	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	public boolean mobileElementWebDriverWait(MobileElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOf(element));
			return flag;
		}catch (Exception e) {
			Log.error("Unable to wait for element due to: "+e.getMessage());
			flag = false;
			return flag;
		}
	}

	public boolean mobileElementsWebDriverWait(List<WebElement> elements) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			return flag;
		}catch (Exception e) {
			Log.error("Unable to wait for element due to: "+e.getMessage());
			flag = false;
			return flag;
		}
	}

	public boolean pressAndroidEnterKey() {
		try {
			((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			return flag;
		}catch (Exception e) {
			Log.error("Unable to find android keyboard: "+e.getMessage());
			flag = false;
			return flag;
		}
	}

	@SuppressWarnings("rawtypes")
	public void manageNotifications(Boolean show) {
		Dimension screenSize = driver.manage().window().getSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		System.out.println("The mobile screen width is: "+screenWidth);
		System.out.println("The mobile screen height is: "+screenHeight);
		int yMargin = 5;
		int xMid = screenSize.width / 2;
		PointOption top = PointOption.point(xMid, yMargin);
		PointOption bottom = PointOption.point(xMid, screenSize.height - yMargin);

		TouchAction action = new TouchAction(driver);
		if (show) {
			action.press(top);
		} else {
			action.press(bottom);
		}
		action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
		if (show) {
			action.moveTo(bottom);
		} else {
			action.moveTo(top);
		}
		action.perform();
	}



}
