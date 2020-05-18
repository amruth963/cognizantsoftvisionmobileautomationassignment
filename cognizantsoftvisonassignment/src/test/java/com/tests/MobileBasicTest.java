package com.tests;

import org.testng.annotations.Test;

import com.common.utils.AppiumUtils;


public class MobileBasicTest extends AppiumUtils {
	
	@Test
	public void testPushNoti() {
		manageNotifications(true);
	}

}
