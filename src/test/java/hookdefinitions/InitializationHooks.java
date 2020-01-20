package hookdefinitions;

import java.util.Properties;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import io.cucumber.java.Before;

/**
 * Class containing all hooks which should be executed before the scenarios.
 * 
 * @author helk
 *
 */
public class InitializationHooks {

	@Autowired
	@Qualifier("loadTestDataProperties")
	Properties testDataProperties;

	@Autowired
	WebDriver driver;

	// @Before(order = 1)
	// -> Initialization of the WebDriver is done in TestConfiguration

	@Before(order = 2)
	public void openKeeperStartPage() {
		driver.navigate().to(testDataProperties.getProperty("keeperUrl"));
	}

	@Before(order = 3)
	public void setEnglishAsLanguageByCookie() {
		Cookie languageCookie = new Cookie("django_language", "en");
		driver.manage().addCookie(languageCookie);
	}

}
