package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.util.SeleniumUtil;
import ui.util.WebDriverCreator;

public class ExampeStepdefinitions {

	@Given("Open Firefox browser")
	public void one() {
		WebDriverCreator.get().navigate().to("https://www.mpdl.mpg.de/");
	}

	@When("Go to Keeper start page")
	public void two() {
		WebDriverCreator.get().navigate().to(SeleniumUtil.KEEPER_URL);
	}

	@Then("MPDL flag tag exists")
	public void three() {
		WebElement mpdlFlag = WebDriverCreator.get().findElement(By.id("mpdl-flag"));

		assertThat(mpdlFlag).isNotNull();
	}

	@Then("Info text heading is visible")
	public void four() {
		WebElement infoPanel = WebDriverCreator.get().findElement(By.id("info_panel"));

		WebElement h3 = infoPanel.findElement(By.tagName("h3"));
		String text = h3.getText();
		assertThat(text).isEqualTo("Archive the way you work");
	}

}
