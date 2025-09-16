package setup;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.Driver.DriverFactory;
import com.Utils.PropUtils;
import com.Utils.WaitFor;

import errors.InvalidBrowserNameError;
import excepetions.NoSuchLocatorTypeException;

public class Keywords {

	//public static RemoteWebDriver driver;

//	public static void openBrowser(String browser) {
//		if (browser == null) {
//			System.out.println("Default Browser is: Firefox");
//			browser = "firefox";
//		}
//		if (browser.equalsIgnoreCase("chrome")) {
//			DriverFactory.getDriver() = new ChromeDriver();
//		} else if (browser.equalsIgnoreCase("firefox")) {
//
//			driver = new FirefoxDriver();
//		} else if (browser.equalsIgnoreCase("edge")) {
//
//			driver = new EdgeDriver();
//		} else {
//			throw new InvalidBrowserNameError(browser);
//		}
//	}
	
	public static void maximize() {
		DriverFactory.getDriver().manage().window().maximize();
	}

//	public static void launchAppUrl(String url) {
//		DriverFactory.getDriver().get(url);
//	}
	public static void openUrl(String urlKey) {
		String url = PropUtils.readProperty(urlKey);
		DriverFactory.getDriver().get(url);
    }

	public static void quitBrowser() {
		DriverFactory.getDriver().quit();
	}
	public static void closeBrowser() {
		DriverFactory.getDriver().close();
	}


	public static void enterText(String locatorType, String locator, String text) {
		getElement(locatorType, locator).sendKeys(text);
	}

	public static String getTextMsg(String locatorType, String locator) {
		WaitFor waitFor = new WaitFor(DriverFactory.getDriver());
		String text = waitFor.presenceofElement(getLocator(locatorType, locator)).getText();
		return text;
	}

	public static void elementClick(String locatorType, String locator) {
		getElement(locatorType, locator).click();
	}

	public static void selectDropdownOptions(String locatorType, String locator, String option) {
		WaitFor waitFor = new WaitFor(DriverFactory.getDriver());
		List<WebElement> elements = waitFor.allElementsToBePresent(getLocator(locatorType, locator));
		// List<WebElement> elements = getElements(locatorType, locator);
		for (WebElement webElement : elements) {
			String dropdownOption = webElement.getText();
			if (dropdownOption.contains(option)) {
				webElement.click();
				break;
			}
		}
	}

	private static WebElement getElement(String locatorType, String locator) {
		WebElement element;
		if (locatorType.equalsIgnoreCase("xpath")) {
			element = DriverFactory.getDriver().findElement(By.xpath(locator));
		} else if (locatorType.equalsIgnoreCase("css")) {
			element = DriverFactory.getDriver().findElement(By.cssSelector(locator));
		} else if (locatorType.equalsIgnoreCase("id")) {
			element = DriverFactory.getDriver().findElement(By.id(locator));
		} else {
			throw new NoSuchLocatorTypeException(locatorType);
		}
		return element;
	}

	private static WebElement getElement(By locator) {
		WebElement element = DriverFactory.getDriver().findElement(locator);
		return element;
	}

	private static By getLocator(String locatorType, String locator) {
		By byLocator;
		if (locatorType.equalsIgnoreCase("xpath")) {
			byLocator = By.xpath(locator);
		} else if (locatorType.equalsIgnoreCase("css")) {
			byLocator = By.cssSelector(locator);
		} else if (locatorType.equalsIgnoreCase("id")) {
			byLocator = By.id(locator);
		} else {
			throw new NoSuchLocatorTypeException(locatorType);
		}
		return byLocator;
	}

	private static List<WebElement> getElements(String locatorType, String locator) {
		List<WebElement> elements;
		if (locatorType.equalsIgnoreCase("xpath")) {
			elements = DriverFactory.getDriver().findElements(By.xpath(locator));
		} else if (locatorType.equalsIgnoreCase("css")) {
			elements = DriverFactory.getDriver().findElements(By.cssSelector(locator));
		} else if (locatorType.equalsIgnoreCase("id")) {
			elements = DriverFactory.getDriver().findElements(By.id(locator));
		} else {
			throw new NoSuchLocatorTypeException(locatorType);
		}

		return elements;
	}

	public static void selectDate(String locatorType, String locator, String date) {
		boolean dateSelected = false;
		String[] dateSplit = date.split(" ");// August 14 2025
		String monthYear = dateSplit[0] + " " + dateSplit[2];
		String dateToSelected = dateSplit[1];
		elementClick(locatorType, locator);
		while (!dateSelected) {
			String monthYearDisplayed = DriverFactory.getDriver()
					.findElement(By.xpath(Locators.monthYearDisplayed))
					.getText();

			if (monthYearDisplayed.equalsIgnoreCase(monthYear)) {
				List<WebElement> suggDays = DriverFactory.getDriver()
						.findElements(By.xpath(Locators.suggDays));
				for (WebElement webElement : suggDays) {
					if (webElement.getText().contains(dateToSelected)) {
						webElement.click();
						dateSelected = true;
						break;
					}
				}
			}
			if (!dateSelected)
				DriverFactory.getDriver().findElement(
						By.xpath("//div[@class=\"dcalendarstyles__MonthChangeRightArrowDiv-sc-r2jz2t-16 iJqGSS\"]"))
						.click();
		}
	}
		public static void enterDateToDateInputField(WebElement dateInputField, String date) {
			boolean dateSelected = false;
			String[] dateSplit = date.split(" ");// August 14 2025
			String monthYear = dateSplit[0] + " " + dateSplit[2];
			String dateToSelected = dateSplit[1];
			dateInputField.click();
			while (!dateSelected) {
				String monthYearDisplayed = DriverFactory.getDriver()
						.findElement(By.xpath("//p[@class=\"dcalendarstyles__MonthNamePara-sc-r2jz2t-3 gryMsr\"]"))
						.getText();

				if (monthYearDisplayed.equalsIgnoreCase(monthYear)) {
					List<WebElement> suggDays = DriverFactory.getDriver()
							.findElements(By.xpath("//ul[@class=\"dcalendarstyles__DateWrapDiv-sc-r2jz2t-7 gJsKZe\"]/li"));
					for (WebElement webElement : suggDays) {
						if (webElement.getText().contains(dateToSelected)) {
							webElement.click();
							dateSelected = true;
							break;
						}
					}
				}
				if (!dateSelected)
					DriverFactory.getDriver().findElement(
							By.xpath("//div[@class=\"dcalendarstyles__MonthChangeRightArrowDiv-sc-r2jz2t-16 iJqGSS\"]"))
							.click();
			}

	}

	
}
