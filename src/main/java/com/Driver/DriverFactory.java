package com.Driver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Driver Factory to manage WebDriver instances for different browsers.
 */

public class DriverFactory {

    private static ThreadLocal<RemoteWebDriver> tlDriver = new ThreadLocal<>();

    public static RemoteWebDriver getDriver() {
        return tlDriver.get();
    }

    public static void setDriver(RemoteWebDriver driverInstance) {
        tlDriver.set(driverInstance);
    }

    public static void initDriver(String browser) {
        if (browser == null) {
            throw new IllegalArgumentException("Browser name cannot be null");
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized"); 
                chromeOptions.addArguments("--incognito");       
                chromeOptions.addArguments("--disable-notifications");
                tlDriver.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--private"); 
                firefoxOptions.addPreference("dom.webnotifications.enabled", false); 
                tlDriver.set(new FirefoxDriver(firefoxOptions));
                tlDriver.get().manage().window().maximize();
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized"); 
                edgeOptions.addArguments("--inprivate");       
                edgeOptions.addArguments("--disable-notifications");
                tlDriver.set(new EdgeDriver(edgeOptions));
                break;

            default:
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
