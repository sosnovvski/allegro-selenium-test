package org.selenium.testng;

import java.util.Collections;


import org.selenium.pages.HomePage;
import org.selenium.pages.SearchPage;
import org.selenium.testng.listeners.ScreenShotOnFailListener;
import org.testng.annotations.*;


@Listeners({ ScreenShotOnFailListener.class })
public class SimpleTest extends TestBase {
	@Test
	public void allegroTest() throws InterruptedException {
		String toSearch = "Iphone 11";
		String category = "Apple";

		HomePage homePage = new HomePage(seleniumCore);
		homePage.open();
		homePage.closePopupWindowIfExists();
		homePage.fillSearchInput(toSearch);
		SearchPage searchPage = homePage.clickSearchPropositionByCategory(category);

		searchPage.chooseBooleanFilterByCategory("czarny", "Kolor");

		System.out.println(searchPage.countOfItemPrice());

		Long maxPrice = Collections.max(searchPage.priceOfItems());
		System.out.println(maxPrice);
		System.out.println(maxPrice * 1.23);
	}
}
