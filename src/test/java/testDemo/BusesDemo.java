package testDemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.Driver.DriverFactory;
import com.Pages.BusPage;
import com.Pages.BusSRP;
import com.Utils.Environment;

import setup.Keywords;
import setup.Setup;
import testDataProviders.TestData;

public class BusesDemo extends Setup {
	
	Logger LOG = LogManager.getLogger(BusesDemo.class);

	@Test(dataProvider = "validBusJourneyDetails", dataProviderClass = TestData.class)
	public void verifyValidRouteForBuses(String source, String destination, String date) throws InterruptedException {
        RemoteWebDriver driver = DriverFactory.getDriver();
        
       
		Keywords.openUrl("bus-url");
		
		BusPage busPage = new BusPage(driver);
	
		busPage.enterSourceToFromInputField(source);
		busPage.selectOptionFromAutoSuggestionList(source);
		LOG.info("Selected option: From dropdown");
		
		busPage.enterDestinationToInputField(destination);
		busPage.selectOptionFromAutoSuggestionList(destination);
		LOG.info("Selected option: To dropdown");
		
		busPage.selectDateOnDateInputField(date);
		LOG.info("Selected date from calender");
		
		busPage.clickOnSearchButton();
		LOG.info("Clicked on Search button");
	
        BusSRP busSRP = new BusSRP(DriverFactory.getDriver());
        
        String searchCntString = busSRP.getSearchResultCountText();
        String[] cntArr = searchCntString.split(" ");
		int searchCnt = Integer.parseInt(cntArr[0]);
		Assert.assertTrue(searchCnt > 1, "Search count is expected to be greater than one but found: " + searchCnt);

	}

	@Test(dataProvider = "invalidBusJourneyDetails", dataProviderClass = TestData.class)
	public void verifyInvalidRouteForBuses(String source, String destination, String date) throws InterruptedException {
		RemoteWebDriver driver = DriverFactory.getDriver();
		
		Keywords.openUrl("bus-url");
	
        BusPage busPage = new BusPage(driver);
		
		busPage.enterSourceToFromInputField(source);
		busPage.selectOptionFromAutoSuggestionList(source);
		LOG.info("Selected option: From dropdown");
		
		busPage.enterDestinationToInputField(destination);
		busPage.selectOptionFromAutoSuggestionList(destination);
		LOG.info("Selected option: To dropdown");
		
		busPage.selectDateOnDateInputField(date);
		LOG.info("Selected date from calender");
		
		busPage.clickOnSearchButton();
		LOG.info("Clicked on Search button");
		
		BusSRP busSRP = new BusSRP(driver);
		
		String expectedMsg = "Sorry, no bus found for your searched route.";
		String actaulMsg = busSRP.getErrorMessageOnSearchPageText();
		Assert.assertEquals(actaulMsg, expectedMsg);
	}

	@Test(dataProvider = "samePlaceForSourceAndDestinationBusJourneyDetails", dataProviderClass = TestData.class)
	public void verifySameSourceAndSameDestinationValidation(String source, String destination, String date)
			throws InterruptedException {
		RemoteWebDriver driver = DriverFactory.getDriver();
		
		Keywords.openUrl("bus-url");
		
		BusPage busPage = new BusPage(driver);
		
		busPage.enterSourceToFromInputField(source);
		busPage.selectOptionFromAutoSuggestionList(source);
		LOG.info("Selected option: From dropdown");
		
		busPage.enterDestinationToInputField(destination);
		busPage.selectOptionFromAutoSuggestionList(destination);
		LOG.info("Selected option: To dropdown");
		
		busPage.selectDateOnDateInputField(date);
		LOG.info("Selected date from calender");
		
		busPage.clickOnSearchButton();
		LOG.info("Clicked on Search button");

		String expectedMsg = "Source and Destination can't be same";
		String actaulMsg = busPage.getSameSourceAndDestinationErrorValidationText();
		Assert.assertEquals(actaulMsg, expectedMsg);
		
	}
}
