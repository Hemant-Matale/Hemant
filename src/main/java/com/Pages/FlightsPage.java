package com.Pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import setup.Keywords;

public class FlightsPage {
	private RemoteWebDriver driver;
	
	public FlightsPage(RemoteWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class=\"footer-links\"]/descendant::a")
	private List<WebElement> linksInFooter;

	@FindBy(xpath = "//span[@class='logSprite icClose']")
	private WebElement logInPopUpCloseBtn;

	public void closeLogInPopUp() {
		logInPopUpCloseBtn.click();
	}

	public List<String> getFooterLinkUrls() throws IOException {
		List<String> urls = new ArrayList<String>();
		for (WebElement link : linksInFooter) {
			urls.add(link.getAttribute("href"));
		}
		return urls;
	}

	public int getResponseCode(String url) {
		int responseCode = 0;
		try {
			URI uri = URI.create(url);
			URL validUrl = uri.toURL();
			HttpURLConnection connection = (HttpURLConnection) validUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			responseCode = connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseCode;
	}

	public void navigateToUrl(String url) {
		driver.get(url);
	}
	public String getPageTitle() {
		return driver.getTitle();
	}
	public WebElement getLinkByLinkTest(String LinkText) {
		return driver.findElement(By.linkText(LinkText));
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

}
