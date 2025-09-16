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
	
	@When("the user enters a valid source and destination")
	public void user_enters_source_and_destination() {
	     BusPage busPage = new BusPage(driver);
	        busPage.enterSourceToFromInputField("Wakad");
			busPage.selectOptionFromAutoSuggestionList("Wakad");
						
			busPage.enterDestinationToInputField("Thane");
			busPage.selectOptionFromAutoSuggestionList("Thane");
			
	}
	
	@And("selects a travel date")
	public void selects_travel_date() {
         BusPage busPage = new BusPage(driver);
            busPage.selectDateOnDateInputField("September 30 2025");
       
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

}
