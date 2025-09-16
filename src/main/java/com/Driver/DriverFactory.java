package com.Driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	private static ThreadLocal<RemoteWebDriver> tlDriver = new ThreadLocal<>();

	public static RemoteWebDriver getDriver() {
		return tlDriver.get();
	}
	public static void setDriver(RemoteWebDriver driverInstance) {
		tlDriver.set(driverInstance);
    }
     
	public static void initDriver(String browser) {
		if (browser== null) {
			tlDriver.set(new FirefoxDriver());
		}
		if (browser.equalsIgnoreCase("chrome")) {
			tlDriver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			tlDriver.set(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("edge")) {
			tlDriver.set(new EdgeDriver());
		} else {
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}
	}

	public static void quitDriver() {
		if (tlDriver.get() != null) {
			tlDriver.get().quit();
			tlDriver.remove();
		}
	}

	

}
