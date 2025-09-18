package com.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import setup.Keywords;

/**
 * Bus Page of Goibibo Application Application URL:
 * https://www.goibibo.com/bus/
 */

public class BusPage {

	public BusPage(RemoteWebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#autosuggestBusSRPSrcHome")
	private WebElement fromInputField;

	@FindBy(css = "input[placeholder=\"Enter Destination\"]")
	private WebElement toInputField;

	@FindBy(css = "input[placeholder*=\"date\"]")
	private WebElement dateInputField;

	@FindBy(css = "button[data-testid=\"searchBusBtn\"]")
	private WebElement searchBtn;

	@FindBy(css = "div[role=\"option\"]>li")
	private WebElement autoSuggestionList;

	@FindBy(css = ".biXizc .error")
	private WebElement sameSrcAndDestErrValidationTextField;
	

	public void enterSourceToFromInputField(String source) {
		fromInputField.sendKeys(source);
	}

	public void enterDestinationToInputField(String destination) {
		toInputField.sendKeys(destination);
	}

	public void selectDateOnDateInputField(String date) {
		Keywords.selectDate("css", "input[placeholder*=\"date\"]", date);
	}

	public void clickOnSearchButton() {
		searchBtn.click();
	}

	public String getSameSourceAndDestinationErrorValidationText() {
		return sameSrcAndDestErrValidationTextField.getText();
	}

	public void selectOptionFromAutoSuggestionList(String option) {
		Keywords.selectDropdownOptions("css", "div[role=\"option\"]>li", option);
	}

}
