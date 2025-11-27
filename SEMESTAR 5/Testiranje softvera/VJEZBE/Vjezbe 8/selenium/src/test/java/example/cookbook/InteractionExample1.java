package example.cookbook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InteractionExample1 {
	WebDriver driver;

	/**
	 * This method will be executed before the test start.
	 */
	@BeforeClass
	public void initalize() {

		// Create a Selenium WebDriver instance
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();

		// Configure browser if required
		// Maximize browser window
		driver.manage().window().maximize();

		// Wait 5 seconds for loading the page before Exception
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		// Wait 1 second before very action
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		// Navigate to the required web page
		driver.navigate().to("https://www.rentalcars.com/en/");

	}

	@Test
	public void interaction() throws InterruptedException {

		// Text Box interaction
		driver.findElement(By.id("searchbox-toolbox-fts-pickup")).sendKeys("Novi Sad");

		// Drop down item selection - by visible text
		Select dropdownPickupMinutes = new Select(driver.findElement(By.name("pickup-time")));
		Thread.sleep(1000);
		dropdownPickupMinutes.selectByVisibleText("04:00");

		// Drop down item selection - by index of each option, INDEX STARTS FROM 0
		Select dropdownDropOffMinutes = new Select(driver.findElement(By.name("dropoff-time")));
		Thread.sleep(1000);
		dropdownDropOffMinutes.selectByIndex(2);

		driver.findElement(By.xpath("//*[@data-testid='searchbox-toolbox-submit-button']")).click();
	}

	/**
	 * This method will be executed at the end of the test.
	 */
	@AfterClass
	public void quitDriver() {

		// Close browser window
		driver.quit();
	}
}
