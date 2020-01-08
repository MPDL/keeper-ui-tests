package ui.util;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class to enhance basic Selenium actions with additional
 * functionality.
 * 
 * @author helk
 *
 */
public class SeleniumUtil {

	private SeleniumUtil() {
		// SeleniumUtil should not be instantiated
	}

	public static void switchToSecondTab(WebDriver driver, WebDriverWait wait) {
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}

}
