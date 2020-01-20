package testconfiguration;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

	@Bean(destroyMethod = "quit")
	@Scope(SCOPE_CUCUMBER_GLUE)
	public WebDriver initializeWebDriver() {
		WebDriverManager.firefoxdriver().setup();

		WebDriver driver = new FirefoxDriver();

		return driver;
	}

	@Bean
	@Scope("singleton")
	public Properties loadTestDataProperties() {
		Properties testDataProperties = new Properties();

		InputStream propertiesInput = TestConfiguration.class.getClassLoader()
				.getResourceAsStream("testData.properties");

		try {
			testDataProperties.load(propertiesInput);
		} catch (IOException e) {
			// TODO Add Logger to class to log this exception
			e.printStackTrace();
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
