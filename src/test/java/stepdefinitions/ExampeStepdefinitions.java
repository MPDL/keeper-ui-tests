package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ExampeStepdefinitions {

	@Autowired
	@Qualifier("loadTestDataProperties")
	Properties testDataProperties;

	@Autowired
	WebDriver driver;

	@Given("Open Firefox browser")
	public void one() {
		driver.navigate().to("https://www.mpdl.mpg.de/");
	}

	@When("Go to Keeper start page")
	public void two() {
		driver.navigate().to(testDataProperties.getProperty("keeperUrl"));
	}

	@Then("MPDL flag tag exists")
	public void three() {
		WebElement mpdlFlag = driver.findElement(By.id("mpdl-flag"));

		assertThat(mpdlFlag).isNotNull();
	}

	@Then("Info text heading is visible")
	public void four() {
		WebElement infoPanel = driver.findElement(By.id("info_panel"));

		WebElement h3 = infoPanel.findElement(By.tagName("h3"));
		String text = h3.getText();

		assertThat(text).isEqualTo("Archive the way you work");
	}

}
