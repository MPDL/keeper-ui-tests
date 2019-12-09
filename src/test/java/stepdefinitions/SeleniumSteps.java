package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ui.util.WebDriverCreator;

/**
 * Tests steps: Opening/Closing the web browser
 * 
 * @author helk
 *
 */
public class SeleniumSteps {

	@Before
	public void openFirefox() {
		WebDriverCreator.createWebDriver();
	}

	@After()
	public void closeBrowser() {
		WebDriverCreator.quitWebDriver();
	}

}
