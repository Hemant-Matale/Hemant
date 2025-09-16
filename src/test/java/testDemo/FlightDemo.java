package testDemo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightDemo {

	public static void oneWayFlightBooking(String source, String destination, String date, int travellers, String travelcls)
			throws InterruptedException {
		RemoteWebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.get("https://www.goibibo.com/");
		// close the login pop-up
		driver.findElement(By.xpath("//span[@class='logSprite icClose']")).click();
		Thread.sleep(2000);
		// Select Source
		driver.findElement(By.xpath("//div[@class=\"sc-12foipm-2 eTBlJr fswFld \"][1]")).click();
		driver.findElement(By.xpath("//div[@class=\"sc-12foipm-25 fbAAhv\"]/input")).sendKeys(source);
		List<WebElement> suggSrc = driver
				.findElements(By.xpath("//ul[@id='autoSuggest-list']/descendant::div[@class='sc-12foipm-32 jVTqNw']"));
		for (WebElement element : suggSrc) {
			if (element.getText().contains(source)) {
				element.click();
				break;
			}
		}
		// Select Destination
		driver.findElement(By.xpath("//div[@class='sc-12foipm-25 fbAAhv']/input")).sendKeys(destination);
		List<WebElement> suggDest = driver
				.findElements(By.xpath("//ul[@id='autoSuggest-list']/descendant::div[@class='sc-12foipm-32 jVTqNw']"));
		for (WebElement element : suggDest) {
			if (element.getText().contains(destination)) {
				element.click();
				break;
			}
		}
		boolean datedSelectecd = false;
		driver.findElement(By.xpath("//div[@class=\"sc-12foipm-2 eTBlJr fswFld \"]/child::span[text()='Departure']"))
				.click();
		while (!datedSelectecd) {
			List<WebElement> suggDays = driver.findElements(
					By.xpath("//div[@class=\"DayPicker-wrapper\"]/descendant::div[@class=\"DayPicker-Day\"]"));
			for (WebElement webElement : suggDays) {
				if (webElement.getAttribute("aria-label").contains(date)) {
					webElement.click();
					datedSelectecd = true;
					break;
				}
			}
			if (!datedSelectecd)
				driver.findElement(By.xpath("//span[@aria-label=\"Next Month\"]")).click();
		}

		// Select Travellers & Class
		int cnt = 1;
		driver.findElement(By.xpath("//span[text()='Travellers & Class']")).click();
		Thread.sleep(1000);
		while (!(cnt == travellers)) {
			driver.findElement(By.xpath("(//div[normalize-space(@class) = 'sc-12foipm-50 gaoQkJ'])[1]/span[3]"))
					.click();
			cnt++;
		}
		//Select Class
		List<WebElement> travelclass = driver.findElements(By.xpath("//ul[@class=\"sc-12foipm-45 caZeZT\"]/li"));
		for (WebElement webElement : travelclass) {
			System.out.println(webElement.getText());
			if(webElement.getText().equals(travelcls) ) {
				webElement.click();
		}
		}
		//Click done to confirm
		driver.findElement(By.xpath("//a[text()='Done']")).click();
		//Click Search Flight Button
	    driver.findElement(By.xpath("//span[text()='SEARCH FLIGHTS']")).click();
	}
	
	public static void roundTripFlightBooking(String source, String destination, String date, int travellers, String travelcls, String travellerType)
			throws InterruptedException {
		RemoteWebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.get("https://www.goibibo.com/");
		// close the login pop-up
		driver.findElement(By.xpath("//span[@class='logSprite icClose']")).click();
		Thread.sleep(2000);
		//Select Round Trip
		driver.findElement(By.xpath("//ul[@class=\"sc-12foipm-68 iMiogo\"]/li[2]")).click();
		// Select Source
		driver.findElement(By.xpath("//div[@class=\"sc-12foipm-2 eTBlJr fswFld \"][1]")).click();
		driver.findElement(By.xpath("//div[@class=\"sc-12foipm-25 fbAAhv\"]/input")).sendKeys(source);
		List<WebElement> suggSrc = driver
				.findElements(By.xpath("//ul[@id='autoSuggest-list']/descendant::div[@class='sc-12foipm-32 jVTqNw']"));
		for (WebElement element : suggSrc) {
			if (element.getText().contains(source)) {
				element.click();
				break;
			}
		}
		// Select Destination
		driver.findElement(By.xpath("//div[@class='sc-12foipm-25 fbAAhv']/input")).sendKeys(destination);
		List<WebElement> suggDest = driver
				.findElements(By.xpath("//ul[@id='autoSuggest-list']/descendant::div[@class='sc-12foipm-32 jVTqNw']"));
		for (WebElement element : suggDest) {
			if (element.getText().contains(destination)) {
				element.click();
				break;
			}
		}
		//Select Departure Date
		boolean datedSelectecd = false;
		driver.findElement(By.xpath("//div[@class=\"sc-12foipm-2 eTBlJr fswFld \"]/child::span[text()='Departure']"))
				.click();
		while (!datedSelectecd) {
			List<WebElement> suggDays = driver.findElements(
					By.xpath("//div[@class=\"DayPicker-wrapper\"]/descendant::div[@class=\"DayPicker-Day\"]"));
			for (WebElement webElement : suggDays) {
				if (webElement.getAttribute("aria-label").contains(date)) {
					webElement.click();
					datedSelectecd = true;
					break;
				}
			}
			if (!datedSelectecd)
				driver.findElement(By.xpath("//span[@aria-label=\"Next Month\"]")).click();
		}
		//Select Return Date
		driver.findElement(By.xpath("//span[text()='Return']")).click();
		driver.findElement(By.xpath("//div[contains(@class,'DayPicker-Day--selected')]")).click();//To select Return date==departure date

		// Select Travellers & Class
		int cnt = 1;
		driver.findElement(By.xpath("//span[text()='Travellers & Class']")).click();
		Thread.sleep(1000);
		while (!(cnt == travellers)) {
			driver.findElement(By.xpath("(//div[normalize-space(@class) = 'sc-12foipm-50 gaoQkJ'])[1]/span[3]"))
					.click();
			cnt++;
		}
		//Select Class
		List<WebElement> travelclass = driver.findElements(By.xpath("//ul[@class=\"sc-12foipm-45 caZeZT\"]/li"));
		for (WebElement webElement : travelclass) {
			if(webElement.getText().equals(travelcls) ) {
				webElement.click();
		}
		}
		//Click done to confirm
		driver.findElement(By.xpath("//a[text()='Done']")).click();
		
		//Selection for special fares
		List<WebElement> specialFares = driver.findElements(By.xpath("//div[@class='sc-12foipm-86 iXowoZ']"));
		for (WebElement webElement : specialFares) {
			 if(webElement.getText().contains(travellerType)){
				 webElement.click();
			 }
		}
		//Click Search Flight Button
	    driver.findElement(By.xpath("//span[text()='SEARCH FLIGHTS']")).click();
	   
	    //Cancel Lock Prices pop-up
	  //driver.findElement(By.xpath("//span[@class='bgProperties overlayCrossIcon icon20']")).click();
	}

	public static void multiCityTripFlightBooking(String source, String destination, String date, int travellers, String travelcls, String travellerType)
			throws InterruptedException {
		RemoteWebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.get("https://www.goibibo.com/");
		// close the login pop-up
		driver.findElement(By.xpath("//span[@class='logSprite icClose']")).click();
		Thread.sleep(2000);
		
		// Closing Push Notifications pop-up
		driver.switchTo().frame("webpush-onsite");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Wait until the popup appears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deny")));
		// Click the "I'll do this later" button
		driver.findElement(By.id("deny")).click();
		driver.switchTo().defaultContent();
		//Select Multi City Trip
		driver.findElement(By.xpath("//ul[@class=\"sc-12foipm-68 iMiogo\"]/li[3]")).click();
		// Select Source
		driver.findElement(By.xpath("//div[@class=\"sc-12foipm-2 eTBlJr fswFld \"][1]")).click();
		driver.findElement(By.xpath("//div[@class=\"sc-12foipm-25 fbAAhv\"]/input")).sendKeys(source);
		List<WebElement> suggSrc = driver
				.findElements(By.xpath("//ul[@id='autoSuggest-list']/descendant::div[@class='sc-12foipm-32 jVTqNw']"));
		for (WebElement element : suggSrc) {
			if (element.getText().contains(source)) {
				element.click();
				break;
			}
		}
		// Select Destination
		driver.findElement(By.xpath("//div[@class='sc-12foipm-25 fbAAhv']/input")).sendKeys(destination);
		List<WebElement> suggDest = driver
				.findElements(By.xpath("//ul[@id='autoSuggest-list']/descendant::div[@class='sc-12foipm-32 jVTqNw']"));
		for (WebElement element : suggDest) {
			if (element.getText().contains(destination)) {
				element.click();
				break;
			}
		}
		//Select Departure Date
		boolean datedSelectecd = false;
		driver.findElement(By.xpath("//div[@class=\"sc-12foipm-2 eTBlJr fswFld \"]/child::span[text()='Departure']"))
				.click();
		while (!datedSelectecd) {
			List<WebElement> suggDays = driver.findElements(
					By.xpath("//div[@class=\"DayPicker-wrapper\"]/descendant::div[@class=\"DayPicker-Day\"]"));
			for (WebElement webElement : suggDays) {
				if (webElement.getAttribute("aria-label").contains(date)) {
					webElement.click();
					datedSelectecd = true;
					break;
				}
			}
			if (!datedSelectecd)
				driver.findElement(By.xpath("//span[@aria-label=\"Next Month\"]")).click();
		}
	
		// Select Travellers & Class
		int cnt = 1;
		Thread.sleep(2000);
		while (!(cnt == travellers)) {
			driver.findElement(By.xpath("//div[@class=\"sc-12foipm-61 kiJTGn\"]/descendant::span[3]"))
					.click();
			cnt++;
		}
		//Select Class
		List<WebElement> travelclass = driver.findElements(By.xpath("//ul[@class=\"sc-12foipm-45 caZeZT\"]/li"));
		for (WebElement webElement : travelclass) {
			if(webElement.getText().equals(travelcls) ) {
				webElement.click();
		}
		}
		//Click done to confirm
		driver.findElement(By.xpath("//a[text()='Done']")).click();
		
//		//Selection for special fares
//		List<WebElement> specialFares = driver.findElements(By.xpath("//div[@class='sc-12foipm-86 iXowoZ']"));
//		for (WebElement webElement : specialFares) {
//			 if(webElement.getText().contains(travellerType)){
//				 webElement.click();
//			 }
//		}
		//Select Multicity
		driver.findElement(By.xpath("(//span[text()='To'])[2]")).click();
	    driver.findElement(By.xpath("//div[@class=\"sc-12foipm-25 fbAAhv\"]/input")).sendKeys("Delhi"+Keys.ENTER);
//	    List<WebElement> secondSuggList = driver.findElements(By.xpath("//ul[@id=\"autoSuggest-list\"]/li"));
//	    for (WebElement webElement : secondSuggList) {
//			if(webElement.getText().contains("Delhi")) {
//				webElement.click();
//			}
//		}
	    
	  //Click Search Flight Button
	    driver.findElement(By.xpath("//span[text()='SEARCH FLIGHTS']")).click();
	    
	   
	    //Cancel Lock Prices pop-up
	 // driver.findElement(By.xpath("//span[@class='bgProperties overlayCrossIcon icon20']")).click();
	}


	
//	public static void main(String[] args) throws InterruptedException {
//		//oneWayFlightBooking("Pune", "Mumbai", "Aug 25 2025", 4, "First Class");
//		//roundTripFlightBooking("Pune", "Mumbai", "Aug 25 2025", 3, "Economy", "Student");
//		multiCityTripFlightBooking("Pune", "Mumbai", "Aug 28 2025", 3, "Economy", "Student");
//	}

}
