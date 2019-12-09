package ui.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Utility class for Selenium.
 * 
 * @author helk
 *
 */
public class SeleniumUtil {

	// TODO Set the URL of the KEEPER instance which should be tested
	public static final String KEEPER_URL = "";

	// TODO: Access the WebDriver in a generic way.
	// Path of the local WebDriver executable:
	private static final String PATH_TO_WEBDRIVER = "C:\\WebDriver\\bin\\geckodriver-windows-64bit.exe";

	private SeleniumUtil() {

	}

	public static WebDriver initializeFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", PATH_TO_WEBDRIVER);

		return new FirefoxDriver();
	}

}
