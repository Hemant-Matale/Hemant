package com.Utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Driver.DriverFactory;

import setup.Keywords;

public class WaitFor {

	private WebDriverWait wait;
	private WebDriver driver;

	public WaitFor(WebDriver driver) {
		this.driver = driver;
		this.wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(60))
				.pollingEvery(Duration.ofMillis(500)).withMessage("Script Timeout");
	}

	public void visibilityOfAll(List<WebElement> elements) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public WebElement visibilityOfElement(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));

	}

	public WebElement presenceofElement(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public List<WebElement> allElementsToBePresent(By locator) {
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

}
