package org.selenium.pages;

import org.selenium.support.SeleniumCore;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private static final String LOGO_XPATH = "//a[@href = 'https://allegro.pl']";
	private static final String ACCEPT_POPUP_BUTTON_XPATH = "//button[@data-role='close-and-accept-consent']";
	private static final String HOME_URL = "http://www.allegro.pl/";
	private static final String SEARCH_INPUT_XPATH = "//input[@type='search' and @name='string']";

	private SeleniumCore seleniumCore;

	@FindBy(xpath = LOGO_XPATH)
	private WebElement logo;

	@FindBy(xpath = ACCEPT_POPUP_BUTTON_XPATH)
	private WebElement acceptPopupButton;

	@FindBy(xpath = SEARCH_INPUT_XPATH)
	private WebElement searchInput;

	public HomePage(SeleniumCore webDriver) {
		this.seleniumCore = webDriver;
		PageFactory.initElements(this.seleniumCore.getWebDriver(), this);
	}

	public void open() {
		seleniumCore.getWebDriver().get(HOME_URL);
		seleniumCore.getWebDriverWait().until(driver -> logo.isDisplayed());
	}

	public void closePopupWindowIfExists() {
		if (acceptPopupButton.isDisplayed()) {
			clickAcceptPopupButton();
		}
	}

	private void clickAcceptPopupButton() {
		acceptPopupButton.click();
	}

	public SearchPage clickSearchPropositionByCategory(String category) {
		String xpath = "//span[text()='" + category + "' and @data-type='PRIMARY']";
		seleniumCore.getWebDriverWait().until(webDriver -> seleniumCore.findWebElementByXpath(xpath).isDisplayed());
		seleniumCore.findWebElementByXpath(xpath).click();
		return new SearchPage(seleniumCore);
	}

	public void fillSearchInput(String searchText) {
		searchInput.sendKeys(searchText);
	}

}
