package org.selenium.testng.listeners;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.support.SeleniumCore;
import org.selenium.testng.TestBase;
import org.testng.*;

/**
 * This listener adds screenshot taking on test failure
 */
public class ScreenShotOnFailListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
		Object currentClass = iTestResult.getInstance();
		WebDriver webDriver = ((SeleniumCore) currentClass).getWebDriver();

		if (webDriver != null)
		{

			File f = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

			//etc.
		}
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
