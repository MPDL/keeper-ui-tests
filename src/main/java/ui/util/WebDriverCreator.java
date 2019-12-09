package ui.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

/**
 * Class responsible for creating one WebDriver for each test thread.
 * 
 * @author helk
 *
 */
public class WebDriverCreator {

	// TODO: Replace this class & its functionality with Dependency Injection
	// (Framework).

	private static Map<Long, WebDriver> webDrivers = new HashMap<>();

	private WebDriverCreator() {
	}

	public static WebDriver get(Long threadId) {
		if (!webDrivers.containsKey(threadId)) {
			createWebDriver();
		}

		return webDrivers.get(threadId);
	}

	public static WebDriver get() {
		Long threadId = Thread.currentThread().getId();

		if (!webDrivers.containsKey(threadId)) {
			createWebDriver();
		}

		return webDrivers.get(threadId);
	}

	public static Collection<WebDriver> getAll() {
		return webDrivers.values();
	}

	public static void add(Long threadId, WebDriver webDriver) {
		webDrivers.put(threadId, webDriver);
	}

	public static void add(WebDriver webDriver) {
		Long threadId = Thread.currentThread().getId();
		webDrivers.put(threadId, webDriver);
	}

	public static void createWebDriver() {
		WebDriver driver = SeleniumUtil.initializeFirefoxDriver();

		WebDriverCreator.add(driver);
	}

	public static void quitWebDriver() {
		WebDriver driver = get();
		driver.quit();
	}

}
