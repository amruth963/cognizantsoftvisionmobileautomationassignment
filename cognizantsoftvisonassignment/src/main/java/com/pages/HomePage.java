package com.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import com.common.utils.AppiumUtils;
import com.common.utils.BaseSetUp;
import com.common.utils.Log;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BaseSetUp {

	@AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/rs_search_src_text")
	private MobileElement searchTxtField;

	@AndroidFindBy(xpath ="//android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View")
	private List<WebElement> searchResults;

	public AppiumUtils appiumUtils = new AppiumUtils();
	public boolean flag = true;

	public HomePage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	public boolean enterSearchTxt(String searchTxt) {
		try {
			while(true) { 
				appiumUtils.mobileElementWebDriverWait(searchTxtField);
				searchTxtField.click();
				Thread.sleep(2000);
				searchTxtField.sendKeys(searchTxt);
				Thread.sleep(2000);
				appiumUtils.pressAndroidEnterKey();
				Log.info("Search text field displayed and able to search "+searchTxt+" successfully");
				return flag;
			}
		}catch (Exception e) {
			Log.error("Unable to find element of search text field due to: "+e.getMessage());
			flag = false;
			return flag;
		}
	}

	public String searchAndClickOnTV(String tvName) {
		try {
			String names = null;
			appiumUtils.mobileElementsWebDriverWait(searchResults);
			for(WebElement tvname: searchResults) {
				names = tvname.getText();
				if(names.contains(tvName)) {
					tvname.click();
					System.out.println("The TV name is: "+names);
					break;
				}
			}
			return names;
		}catch (Exception e) {
			Log.error("Unable to get the TV list due to: "+e.getMessage());
			return null;
		}
	}


}
