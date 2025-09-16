package testDataProviders;

import org.testng.annotations.DataProvider;

public class TestData {
	@DataProvider(name = "validBusJourneyDetails")
	public Object[][] getvalidData() {
		return new Object[][] { { "Wakad", "Thane", "September 30 2025" },
			                    { "Warje", "Borivali", "September 30 2025" },
			                    { "Swargate", "Thane", "September 30 2025" }};
	}
	
	@DataProvider(name = "invalidBusJourneyDetails")
	public Object[][] getInvalidData() {
		return new Object[][] { { "Wakad", "Kamote", "September 30 2025" },
			                    { "Swargate", "Shivane", "September 30 2025" }
			                    };
	}
	
	@DataProvider(name = "samePlaceForSourceAndDestinationBusJourneyDetails")
	public Object[][] getSameSourceAndDestinationData() {
		return new Object[][] { { "Wakad", "Wakad", "September 30 2025" },
			                    { "Swargate", "Swargate", "September 30 2025" }
			                    };
	}


}
