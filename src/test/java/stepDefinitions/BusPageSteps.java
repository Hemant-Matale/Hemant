package stepDefinitions;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import com.Driver.DriverFactory;
import com.Pages.BusPage;
import com.Pages.BusSRP;
import io.cucumber.java.en.*;
import setup.Keywords;

public class BusPageSteps {

	RemoteWebDriver driver = DriverFactory.getDriver();

	@Given("the user is on the Goibibo Bus booking page")
	public void user_opens_browser_and_launch_application_url_bus() {
		Keywords.openUrl("bus-url");
	}

	@When("the user enters {string} as source and {string} as destination")
	public void user_enters_source_and_destination(String source, String destination) {
		BusPage busPage = new BusPage(driver);
		busPage.enterSourceToFromInputField(source);
		busPage.selectOptionFromAutoSuggestionList(source);

		busPage.enterDestinationToInputField(destination);
		busPage.selectOptionFromAutoSuggestionList(destination);

	}

	@When("the user enters {string} as source and destination kept blank")
	public void user_enters_source_and_destination_kept_Blank(String source) {
		BusPage busPage = new BusPage(driver);
		busPage.enterSourceToFromInputField(source);
		busPage.selectOptionFromAutoSuggestionList(source);

	}

	@When("the user keeps source blank and only enters {string} as destination")
	public void user_keeps_source_blank_and_enters_only_destination(String destination) {
		BusPage busPage = new BusPage(driver);

		busPage.enterDestinationToInputField(destination);
		busPage.selectOptionFromAutoSuggestionList(destination);

	}

	@When("the user enters {string} source and destination")
	public void user_enters_same_city_as_source_and_destination(String city) {
		BusPage busPage = new BusPage(driver);
		busPage.enterSourceToFromInputField(city);
		busPage.selectOptionFromAutoSuggestionList(city);

		busPage.enterDestinationToInputField(city);
		busPage.selectOptionFromAutoSuggestionList(city);

	}

	@And("selects {string} as travel date")
	public void selects_travel_date(String date) {
		BusPage busPage = new BusPage(driver);
		busPage.selectDateOnDateInputField(date);

	}

	@And("clicks on the search button")
	public void click_on_search_button() {
		BusPage busPage = new BusPage(driver);
		busPage.clickOnSearchButton();

	}

	@Then("the search results page should display available buses with the correct count")
	public void search_result_page_should_display_buses_with_search_cnount() {
		BusSRP busSRP = new BusSRP(driver);
		String searchCntString = busSRP.getSearchResultCountText();
		String[] cntArr = searchCntString.split(" ");
		int searchCnt = Integer.parseInt(cntArr[0]);
		Assert.assertTrue(searchCnt > 1, "Search count is expected to be greater than one but found: " + searchCnt);

	}

	@Then("Validation Message should be displayed saying \"Source and Destination cannot be the same\"")
	public void validation_message_should_be_displayed() {
		BusPage busPage = new BusPage(driver);
		String errorMsg = busPage.getSameSourceAndDestinationErrorValidationText();
		Assert.assertEquals(errorMsg, "Source and Destination can't be same",
				"Validation message did not match the expected text.");

	}

	@Then("Validation Message should be displayed saying \"Please enter the TO location\"")
	public void toLocation_validation_message_should_be_displayed() {
		BusPage busPage = new BusPage(driver);
		String errorMsg = busPage.getSameSourceAndDestinationErrorValidationText();
		Assert.assertEquals(errorMsg, "Please enter the TO location",
				"Validation message did not match the expected text.");

	}

	@Then("Validation Message should be displayed saying \"Please enter the FROM location\"")
	public void fromLocation_validation_message_should_be_displayed() {
		BusPage busPage = new BusPage(driver);
		String errorMsg = busPage.getSameSourceAndDestinationErrorValidationText();
		Assert.assertEquals(errorMsg, "Please enter the FROM location",
				"Validation message did not match the expected text.");

	}
}
