package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testconfiguration.TestConfiguration;
import ui.pages.BasePage;
import ui.pages.LoginPage;

/**
 * Tests steps: Authentication domain
 * 
 * @author helk
 *
 */
@SpringBootTest(classes = TestConfiguration.class)
public class AuthenticationSteps {

	@Autowired
	WebDriver driver;

	@Given("Open LoginPage")
	public void navigateToLoginPage() {
		driver.navigate().to(BasePage.KEEPER_URL);
	}

	@When("Login with empty credentials")
	public void loginWithEmptyCredentials() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("", "");
	}

	@Then("LoginPage stays open")
	public void loginPageStaysOpen() {
		String pageTitle = driver.getTitle();

		assertThat(pageTitle).isEqualTo("Anmelden - KEEPER");
	}

	@Then("Homepage is opend")
	public void homePageOpens() {

	}

}
