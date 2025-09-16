package com.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import setup.Keywords;

public class LoggingSoftAssert extends SoftAssert {
    private static final Logger logger = LogManager.getLogger(LoggingSoftAssert.class);
    private final RemoteWebDriver driver;
    
    public LoggingSoftAssert(RemoteWebDriver driver) {
        this.driver = driver; 
    }

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        ITestResult result = Reporter.getCurrentTestResult();
        String testName = (result != null) ? result.getMethod().getMethodName() : "UnknownTest";

        logger.error("❌ [{}] Assertion failed:\n • Message: {}\n • Expected: {}\n • Actual: {}",
                testName,
                assertCommand.getMessage(),
                assertCommand.getExpected(),
                assertCommand.getActual());

        String screenshotPath = ScreenshotUtil.capture(driver,testName);
        if (screenshotPath != null) {
            logger.error("📸 Screenshot: {}", screenshotPath);
        }

        super.onAssertFailure(assertCommand, ex);
    }
}