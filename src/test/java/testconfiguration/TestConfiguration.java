package testconfiguration;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * The test configuration, responsible for the correct
 * initialization/termination of the WebDriver.
 * 
 * @author helk
 *
 */
@Configuration
@ComponentScan({ "ui.pages", "ui.components" })
public class TestConfiguration {

	private Logger logger = LoggerFactory.getLogger(TestConfiguration.class);

	@Bean(destroyMethod = "quit")
	@Scope(SCOPE_CUCUMBER_GLUE)
	public WebDriver initializeWebDriver() {
		logger.info("Initializing Firefox WebDriver:");

		WebDriverManager.firefoxdriver().setup();

		WebDriver driver = new FirefoxDriver();

		return driver;
	}

	@Bean
	@Scope("singleton")
	public Properties loadTestDataProperties() {
		logger.info("Loading the testData.properties...");

		Properties testDataProperties = new Properties();

		InputStream propertiesInput = TestConfiguration.class.getClassLoader()
				.getResourceAsStream("testData.properties");

		try {
			testDataProperties.load(propertiesInput);
		} catch (IOException e) {
			logger.error("Failed loading the testData.properties.", e);
		}

		return testDataProperties;
	}

	@Bean
	@Lazy
	@Scope(SCOPE_CUCUMBER_GLUE)
	public List<String> createLibrariesToDeleteList() {
		List<String> librariesToDelete = new ArrayList<String>();

		return librariesToDelete;
	}

}
