package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.Driver.DriverFactory;


public class Hooks {

	@Before
    public void launchBrowser() {
        WebDriver driver = new FirefoxDriver();  // or via WebDriverManager
        DriverFactory.setDriver((RemoteWebDriver) driver);
    }

    @After
    public void quitBrowser() {
        DriverFactory.getDriver().quit();
    }
}