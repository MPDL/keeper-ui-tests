package hookdefinitions;

import java.util.Properties;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import io.cucumber.java.Before;
import ui.pages.StartPage;

/**
 * Class containing all hooks which should be executed before the scenarios.
 * 
 * @author helk
 *
 */
public class InitializationHooks {

	private Logger logger = LoggerFactory.getLogger(InitializationHooks.class);

	@Autowired
	@Qualifier("loadTestDataProperties")
	Properties testDataProperties;

	@Autowired
	WebDriver driver;

	@Lazy
	@Autowired
	StartPage startPage;

	// @Before(order = 1)
	// -> Initialization of the WebDriver is done in TestConfiguration

	@Before(order = 2)
	public void openKeeperStartPage() {
		logger.info("Navigate to Keeper Startpage.");

		startPage.navigateTo();
	}

	@Before(order = 3)
	public void setEnglishAsLanguageByCookie() {
		logger.info("Set language to Enlish by cookie.");

		Cookie languageCookie = new Cookie("django_language", "en");
		driver.manage().addCookie(languageCookie);
	}

}
