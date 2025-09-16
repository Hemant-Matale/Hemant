package testDemo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Driver.DriverFactory;
import com.Pages.FlightsPage;
import com.Utils.Environment;
import com.Utils.LoggingSoftAssert;

import setup.Keywords;
import setup.Setup;
import testDataProviders.JsonTestData;

public class FooterLinksDemo extends Setup {
	Logger LOG = LogManager.getLogger(FooterLinksDemo.class);

	@Test
	public void brokenLinkValidationInFooterSection() throws IOException {
		
		Keywords.openUrl("base-url");
		
		FlightsPage flightsPage = new FlightsPage(DriverFactory.getDriver());
		LoggingSoftAssert softAssert = new LoggingSoftAssert(DriverFactory.getDriver());
		flightsPage.closeLogInPopUp();
		List<String> urls = flightsPage.getFooterLinkUrls();
		for (String url : urls) {
			int responseCode = flightsPage.getResponseCode(url);
			softAssert.assertTrue(responseCode < 400,
					"The link with URL " + url + " is broken with response code " + responseCode);
		}
		softAssert.assertAll();
	}

	@Test(dataProvider = "footerLinksTestData", dataProviderClass = JsonTestData.class)
	public void verifyLinkNavigationUsingPageTitle(Map<String, String> linkData) throws IOException {
		String linkText = linkData.get("linkText");
		String expectedUrl = linkData.get("expectedUrl");
		String expectedTitle = linkData.get("expectedTitle").toLowerCase();
		
		Keywords.openUrl("base-url");
		
		FlightsPage flightsPage = new FlightsPage(DriverFactory.getDriver());
		flightsPage.closeLogInPopUp();
		LoggingSoftAssert softAssert = new LoggingSoftAssert(DriverFactory.getDriver());
		flightsPage.getLinkByLinkTest(linkText).click();
		String actualUrl = flightsPage.getCurrentUrl();
		softAssert.assertEquals(actualUrl, expectedUrl, "The URL for link with text " + linkText + " is not as expected.");
		String actualTitle = flightsPage.getPageTitle().toLowerCase();
//		softAssert.assertEquals(actualTitle, expectedTitle,
//				"The page title for link with text " + linkText + " is not as expected.");
		softAssert.assertTrue(actualTitle.contains(expectedTitle),
				"The page title for link with text " + linkText + " does not contain expected text.");
		softAssert.assertAll();
			
	}
}