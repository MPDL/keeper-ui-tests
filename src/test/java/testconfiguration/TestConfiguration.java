package testconfiguration;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * The test configuration, responsible for the correct
 * initialization/termination of the WebDriver.
 * 
 * @author helk
 *
 */
@Configuration
@ComponentScan("ui.pages")
public class TestConfiguration {

	@Bean(destroyMethod = "quit")
	@Scope(SCOPE_CUCUMBER_GLUE)
	public WebDriver initializeWebDriver() {
		// TODO: Access the WebDriver in a generic way.
		// Path of the local WebDriver executable:
		System.setProperty("webdriver.gecko.driver", "C:\\WebDriver\\bin\\geckodriver-windows-64bit.exe");

		WebDriver driver = new FirefoxDriver();

		return driver;
	}

}
