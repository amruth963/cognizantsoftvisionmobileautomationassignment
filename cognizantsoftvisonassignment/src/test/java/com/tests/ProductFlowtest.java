package com.tests;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import com.common.utils.AppiumUtils;
import com.pages.HomePage;
import com.pages.ProductDetailsPage;

public class ProductFlowtest extends AppiumUtils {


	@Test
	public void testProductFlow(){
		HomePage homePage = new HomePage(driver);
		ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
		homePage.enterSearchTxt("65-inch TV");
		String tvNameInSearchResultScreen = homePage.searchAndClickOnTV("Sony Bravia 163.9 cm (65 inches)");
		String tvNameInProductDetailsPage = productDetailsPage.searchAndGetTVName("Sony Bravia 163.9 cm (65 inches)");
		assertEquals(tvNameInProductDetailsPage, tvNameInSearchResultScreen);
	}
}
