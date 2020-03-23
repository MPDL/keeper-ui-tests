package hookdefinitions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import ui.pages.HomePage;

/**
 * Class containing all hooks which should be executed after each scenarios.
 * 
 * @author helk
 *
 */
public class AfterHooks {

	private Logger logger = LoggerFactory.getLogger(AfterHooks.class);

	@Autowired
	WebDriver driver;

	@Lazy
	@Autowired
	HomePage homePage;

	@Lazy
	@Qualifier("createLibrariesToDeleteList")
	@Autowired
	List<String> librariesToDelete;

	@After("@createLibrary")
	public void deleteLibraries() {
		logger.info("Deleting Libraries...");

		// TODO: Maybe use the REST API to delete the Libraries after the scenarios
		for (String libraryName : librariesToDelete) {
			homePage.navigateTo();
			homePage.openMyLibraries();
			homePage.deleteLibrary(libraryName);
		}
	}

	@After
	public void handleTestFailure(Scenario scenario) {
		if (scenario.isFailed()) {
			this.takeScreenshot(scenario.getName() + "_" + scenario.getStatus().name());
		}
	}

	private void takeScreenshot(String screenshotFileName) {
		try {
			String screenshotPath = "./target/" + screenshotFileName + "_Screenshot.jpg";
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
			Files.copy(screenshot.toPath(), new File(screenshotPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException exc) {
			logger.error("Error copying the screenshot file.", exc);
		}
	}

}
