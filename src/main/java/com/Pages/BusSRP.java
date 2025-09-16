package com.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Driver.DriverFactory;
import com.Utils.WaitFor;



public class BusSRP {

	public BusSRP(RemoteWebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".dOsBbG")
	private WebElement searchResultCountTxtField;
	
	@FindBy(css = ".dKWUaD")
	private WebElement errorMsgOnSearchPageTxtField;
	
	public String getSearchResultCountText() {
		WaitFor waitFor = new WaitFor(DriverFactory.getDriver());
		String text = waitFor.visibilityOfElement(searchResultCountTxtField).getText();
		return text;
	}
	
	public String getErrorMessageOnSearchPageText() {
		WaitFor waitFor = new WaitFor(DriverFactory.getDriver());
		String text = waitFor.visibilityOfElement(errorMsgOnSearchPageTxtField).getText();
		return text;
	}
}
