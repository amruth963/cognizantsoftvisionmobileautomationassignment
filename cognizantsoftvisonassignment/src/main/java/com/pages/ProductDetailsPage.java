package com.pages;



import com.common.utils.AppiumUtils;
import com.common.utils.BaseSetUp;
import com.common.utils.Log;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductDetailsPage extends BaseSetUp {

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='title_feature_div']/android.view.View")
	private MobileElement productDesctiptionDetailsPage;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='atfRedesign_priceblock_priceToPay']/android.view.View")
	private MobileElement productPriceDetailsPage;


	public AppiumUtils appiumUtils = new AppiumUtils();
	public boolean flag = true;

	public ProductDetailsPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}


	public String searchAndGetTVName(String tvName) {
		try {
			String names = null;
			appiumUtils.mobileElementWebDriverWait(productDesctiptionDetailsPage);
			names = productDesctiptionDetailsPage.getText();
			if(names.contains(tvName)) {
				Log.info("TV Description is displayed");
			}
			return names;
		}catch (Exception e) {
			Log.error("Unable to get the TV list due to: "+e.getMessage());
			return null;
		}
	}


}
