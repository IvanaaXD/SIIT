package example.cookbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class InteractionExample3 {
	WebDriver driver;

	/**
	 * This method will be executed before the test start.
	 */
	@BeforeSuite
	public void initalize() {

		// Create a Selenium WebDriver instance
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void shouldPerformCompositeAction() throws InterruptedException {

		// Navigate to jQuery UI Selectable page
		driver.get("https://jqueryui.com/selectable/");

		// Switch to the frame containing the list (since it's in an iframe)
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

		// Locate the checkboxes by their name or ID attributes
		WebElement option1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement option3 = driver.findElement(By.xpath("//li[text()='Item 3']"));
		WebElement option5 = driver.findElement(By.xpath("//li[text()='Item 5']"));

		// Add all the actions into the Actions object
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL) // Hold down CONTROL key
				.click(option1)         // Click the first checkbox
				.click(option3)         // Click the third checkbox
				.click(option5)         // Click the fifth checkbox
				.keyUp(Keys.CONTROL);    // Release the CONTROL key

		// Generate the composite action
		Action compositeAction = actions.build();

		// Perform the composite action
		compositeAction.perform();
	}

	@Test
	public void shouldPerformAction() {
		
		driver.navigate().to("https://www.google.com");

		// Locate element by value of its NAME attribute
		WebElement input = driver.findElement(By.name("q"));

		// Enter text
		Actions builder = new Actions(driver);

		builder.moveToElement(input).click().keyDown(input, Keys.SHIFT).sendKeys(input, "please turn off caps lock")
				.keyUp(input, Keys.SHIFT).perform();

		// Press ENTER key on keyboard
		input.sendKeys(Keys.ENTER);

	}

	/**
	 * This method will be executed at the end of the test.
	 */
	@AfterSuite
	public void quitDriver() {

		// Close browser window
		driver.quit();
	}
}
