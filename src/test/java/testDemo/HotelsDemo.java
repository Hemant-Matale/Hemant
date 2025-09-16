package testDemo;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotelsDemo {
	//@Test(description="Hotel Booking")
      public static void hotelBooking() throws InterruptedException {
    	  RemoteWebDriver driver = new FirefoxDriver();
  		driver.manage().window().maximize();
  		driver.get("https://www.goibibo.com/hotels/");
  		
  		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  		
  		driver.findElement(By.xpath("//span[normalize-space(text())='Where to']")).click();
  		driver.findElement(By.xpath("//input[@placeholder=\"e.g. - Area, Landmark or Property Name\"]")).sendKeys("Pune");
  		 
  		List<WebElement> suggLocations = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class=\"HomePageAutosuggest-styles__AutoSuggestLocationNewItems-sc-bc5f85f4-20 iiJxHf\"]/li"))); 
  		for (WebElement webElement : suggLocations) {
  			if(webElement.getText().contains("Viman Nagar")) {
  				webElement.click();
  				break;
  			}
  		}
  		
       driver.findElement(By.xpath("//span[text()='Check-in']")).click();
  		
  		List<WebElement> suggCheckInDate = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class=\"dcalendar-new-styles__DateWrapDiv-sc-66fe8580-17 dgwawb\"]/li")));
     for (WebElement webElement : suggCheckInDate) {
    	 if (webElement.getAttribute("data-testid") !=null){
    	 if(webElement.getAttribute("data-testid").contains("25_7_2025")) {
    		 webElement.click();
    		 break;
    	 }
    	}
	}
     driver.findElement(By.xpath("//span[text()='Check-out']")).click();
 	List<WebElement> suggCheckOutDate = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class=\"dcalendar-new-styles__DateWrapDiv-sc-66fe8580-17 dgwawb\"]/li")));
    for (WebElement webElement : suggCheckOutDate) {
   	 if (webElement.getAttribute("data-testid") !=null){
   	 if(webElement.getAttribute("data-testid").contains("29_7_2025")) {
   		 webElement.click();
   		 break;
   	 }
   	}
	}
    driver.findElement(By.xpath("//span[text()='Guests & Rooms']")).click();
    int rooms=5;
    while(rooms!=0) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dwebCommon-styles__CenteredSpaceWrap-sc-210f217-0 PaxWidget-styles__ContentActionWrapperDiv-sc-9b67d5e-6 hhioSu hMbwBH']/span[@data-testid=\"button-room-add\"]"))).click();
    rooms--;
    }
    Thread.sleep(5000);
    int adults=15;
    while(adults!=0) {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-testid=\"button-adult-add\"]"))).click();
    adults--;
    }
      }
      public static void main(String[] args) throws InterruptedException {
    	  hotelBooking();
	}
}
