package example.cookbook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.Point;

public class InteractionExample4 {
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
	public void shouldMoveByOffsetAndClick() throws InterruptedException {
		driver.get("https://jqueryui.com/selectable/#display-grid");
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

		Thread.sleep(2000);
		WebElement seven = driver.findElement(By.xpath("//li[text()='7']"));
		Actions actions = new Actions(driver);

		actions.moveToElement(seven, 50, 1).click();
		actions.perform();
	}

	@Test
	public void shouldMoveByOffsetAndClickMultiple() throws InterruptedException {
		driver.get("https://jqueryui.com/selectable/#display-grid");
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

		Thread.sleep(2000);
		WebElement item1 = driver.findElement(By.xpath("//li[text()='1']"));

		int border = 1;
		int tileWidth = 100;
		int tileHeight = 80;

		Actions actions = new Actions(driver);

		// Click on One
		actions.moveToElement(item1, border, border).click();
		actions.build().perform();

		// Click on Three
		actions.moveToElement(item1, 2 * tileWidth + 2 * border, 0).click();
		actions.build().perform();

		// Click on Nine
		actions.moveToElement(item1, 0, 2 * tileHeight + 2 * border).click();
		actions.build().perform();
	}

	@Test
	public void shouldClickOnElement() throws InterruptedException {

		driver.get("https://jqueryui.com/selectable/#display-grid");
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

		Thread.sleep(2000);

		WebElement one = driver.findElement(By.xpath("//li[text()='1']"));
		WebElement three = driver.findElement(By.xpath("//li[text()='3']"));
		WebElement five = driver.findElement(By.xpath("//li[text()='5']"));

		Actions actions = new Actions(driver);
		actions.click(one).click(three).click(five).build().perform();
	}

	@Test
	public void shouldClickAndHold() {
		driver.get("https://jqueryui.com/sortable/");
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

		Actions actions = new Actions(driver);

		// Locate the item to move (item1)
		WebElement item1 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 1']"));

		// Move item1 to the position of item3
		actions.moveToElement(item1).clickAndHold().moveByOffset(0, 80).perform();
	}

	@Test
	public void shouldClickAndHoldElement() {
		driver.get("https://jqueryui.com/sortable/");
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

		Actions actions = new Actions(driver);

		// Locate the element to click and hold (item1)
		WebElement item1 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 1']"));

		// Move item1 to the position of item3
		actions.clickAndHold(item1).moveByOffset(0, 80).perform();
	}

	@Test
	public void shouldClickAndHoldAndRelease() {
		driver.get("https://jqueryui.com/sortable/");
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

		// Locate the element to click and hold (Item 1)
		WebElement item1 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 1']"));

		Actions actions = new Actions(driver);

		// Perform the click and hold on Item 1, move by offset, and release at the new location
		actions.clickAndHold(item1).moveByOffset(0, 80).release().perform();
	}

	@Test
	public void shouldClickAndHoldAndReleaseOnElement() {
		driver.get("https://jqueryui.com/sortable/");
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

		// Locate the elements to click and hold, and where to release
		WebElement item1 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 1']"));
		WebElement item2 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 2']"));

		Actions actions = new Actions(driver);

		// Perform click and hold on Item 1, then release on Item 2 (swapping positions)
		actions.clickAndHold(item1).moveToElement(item2).release().perform();
	}

	@Test
	public void shouldDoubleClick() throws InterruptedException {

		driver.get("https://demo.guru99.com/test/simple_context_menu.html");

		WebElement dblClick = driver.findElement(By.tagName("button"));
		Actions actions = new Actions(driver);
		actions.moveToElement(dblClick).doubleClick().perform();

		Thread.sleep(2000);

		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test
	public void shouldDoubleClickElement() throws InterruptedException {

		driver.get("https://demo.guru99.com/test/simple_context_menu.html");

		WebElement dblClick = driver.findElement(By.tagName("button"));
		Actions actions = new Actions(driver);
		actions.doubleClick(dblClick).perform();

		Thread.sleep(2000);

		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test
	public void shouldContextClick() throws InterruptedException {

		driver.get("https://demo.guru99.com/test/simple_context_menu.html");

		WebElement contextMenu = driver.findElement(By.cssSelector("span.context-menu-one"));
		Actions actions = new Actions(driver);
		actions.contextClick(contextMenu).click(driver.findElement(By.xpath("//span[text() = 'Edit']"))).perform();

		// Switch to the alert box and click on OK button
		Alert alert = driver.switchTo().alert();

		System.out.println("Alert Text\n" + alert.getText());

		Thread.sleep(2000);
		alert.accept();
	}

	@Test
	public void shouldContextClickAtCurrentLocation() {

		driver.get("https://demo.guru99.com/test/simple_context_menu.html");

		WebElement contextMenu = driver.findElement(By.cssSelector("span.context-menu-one"));
		Actions actions = new Actions(driver);
		actions.moveToElement(contextMenu).contextClick().click(driver.findElement(By.xpath("//span[text() = 'Edit']"))).perform();

		Alert alert = driver.switchTo().alert();
		alert.accept();
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
