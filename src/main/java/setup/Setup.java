package setup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.Driver.DriverFactory;
import com.Utils.Environment;
import com.Utils.PropUtils;

public class Setup {
	Logger LOG = LogManager.getLogger(Setup.class);
	@BeforeMethod
	@Parameters("browsername")
	public void setup(@Optional String browser) {
		if(browser==null) {
			browser = PropUtils.readProperty("browser-name");
		}
		String evvString = System.getProperty("env");
		DriverFactory.initDriver(browser);
		DriverFactory.getDriver();
		LOG.info("Environment value: "+evvString); //Information
		LOG.info("Browser value: "+browser); //Information
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
}
}
