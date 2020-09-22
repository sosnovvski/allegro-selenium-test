package org.selenium.testng;

import org.selenium.support.SeleniumCore;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
	protected SeleniumCore seleniumCore;

	@BeforeMethod
	public void beforeTest() {
		this.seleniumCore = new SeleniumCore();
	}

	@AfterMethod
	public void afterTest() {
		seleniumCore.getWebDriver().quit();
	}
}
