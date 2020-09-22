package org.selenium.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.selenium.support.SeleniumCore;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	private static final String ITEM_PRICE_XPATH = "//div[@data-box-name='items container']//article[@data-item='true']//span[@class= '_1svub _lf05o']";

	private SeleniumCore seleniumCore;

	@FindBy(xpath = ITEM_PRICE_XPATH)
	private List<WebElement> itemsPrice;

	public SearchPage(SeleniumCore seleniumCore) {
		this.seleniumCore = seleniumCore;
		PageFactory.initElements(this.seleniumCore.getWebDriver(), this);
	}

	public int countOfItemPrice() {
		return itemsPrice.size();
	}

	public List<Long> priceOfItems() {
		return itemsPrice.stream().map(WebElement::getText).map(seleniumCore::convertStringPriceToLong)
				.collect(Collectors.toList());
	}

	public void chooseBooleanFilterByCategory(String filterName, String category) throws InterruptedException {
		String xpath = "//*[@data-slot='" + category + "']/..//span[text()='" + filterName + "']";
		seleniumCore.getWebDriverWait().until(webDriver -> seleniumCore.findWebElementByXpath(xpath).isDisplayed());
		seleniumCore.findWebElementByXpath(xpath).click();
		Thread.sleep(3000);
	}
}
