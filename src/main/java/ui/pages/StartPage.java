package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Page Object encapsulates the Start page (Page of the basic KEEPER URL).
 * 
 * -> Gets redirected to the LoginPage.
 * 
 * @author helk
 *
 */
@Lazy
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class StartPage extends BasePage {

	@Autowired
	@Qualifier("loadTestDataProperties")
	Properties testDataProperties;

	@Lazy
	@Autowired
	StartPage startPage;

	@Autowired
	public StartPage(WebDriver driver) {
		super(driver);
	}

	public StartPage navigateTo() {
		driver.navigate().to(testDataProperties.getProperty("keeperUrl"));

		wait.until(ExpectedConditions.elementToBeClickable(By.name("login")));
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));

		return startPage;
	}

}
