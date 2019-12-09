package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.pages.LoginPage;
import ui.util.PageCreator;
import ui.util.SeleniumUtil;
import ui.util.WebDriverCreator;

/**
 * Tests steps: Authentication domain
 * 
 * @author helk
 *
 */
public class AuthenticationSteps {

	@Given("Open LoginPage")
	public void navigateToLoginPage() {
		WebDriverCreator.get().navigate().to(SeleniumUtil.KEEPER_URL);
	}

	@When("Login with empty credentials")
	public void loginWithEmptyCredentials() {
		((LoginPage) PageCreator.get(LoginPage.class)).login("", "");
	}

	@Then("LoginPage stays open")
	public void loginPageStaysOpen() {
		String pageTitle = WebDriverCreator.get().getTitle();

		assertThat(pageTitle).isEqualTo("Anmelden - KEEPER");
	}

	@Then("Homepage is opend")
	public void homePageOpens() {

	}

}
