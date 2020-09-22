package org.selenium.support;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class SeleniumCore {
	private static final long WEB_DRIVER_WAIT_TIMEOUT_IN_SECONDS = 80;

	private Wait<WebDriver> webDriverWait;
	private WebDriver webDriver;

	public SeleniumCore() {
		webDriver = initializeDriverSession();
		webDriverWait = new WebDriverWait(webDriver, WEB_DRIVER_WAIT_TIMEOUT_IN_SECONDS, 2000);

	}

	public Wait<WebDriver> getWebDriverWait() {
		return webDriverWait;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public WebElement findWebElementByXpath(String xpath) {
		return getWebDriver().findElement(By.xpath(xpath));
	}

	public Long convertStringPriceToLong(String price) {
		try {
			String clearSuffix = StringUtils.removeEnd(price, " zł").replace(" ", "");
			return NumberFormat.getNumberInstance(Locale.FRANCE).parse(clearSuffix).longValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private WebDriver initializeDriverSession() {
		ChromeDriverManager.getInstance(ChromeDriver.class).setup();
		Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.prompt_for_download", false);

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setExperimentalOption("prefs", chromePrefs);
		browserOptions.addArguments("--disable-extensions");
		browserOptions.addArguments("--no-sandbox");

		return new ChromeDriver(browserOptions);
	}

}
