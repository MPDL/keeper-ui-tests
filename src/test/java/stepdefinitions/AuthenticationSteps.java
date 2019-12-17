package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testconfiguration.TestConfiguration;
import ui.pages.BasePage;
import ui.pages.HomePage;
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

	@Lazy
	@Autowired
	LoginPage loginPage;

	@Lazy
	@Autowired
	HomePage homePage;

	// TODO: Initialize user credentials in a appropriate way
	private String email = "";
	private String password = "";
	private String userName = "";

	@Given("Open LoginPage")
	public void navigateToLoginPage() {
		driver.navigate().to(BasePage.KEEPER_URL);
	}

	@Given("Logged in as User")
	public void loggedInAsUser() {
		driver.navigate().to(BasePage.KEEPER_URL);

		this.loginAsUser();
	}

	@When("Login as user")
	public void loginAsUser() {
		loginPage.login(email, password);
	}

	@When("Login with empty credentials")
	public void loginWithEmptyCredentials() {
		loginPage.loginWithEmptyCredentials();
	}

	@When("Logout as user")
	public void logoutAsUser() {
		homePage.logout();
	}

	@Then("LoginPage stays open")
	public void loginPageStaysOpen() {
		String pageTitle = driver.getTitle();

		assertThat(pageTitle).isEqualTo("Log In - KEEPER");
	}

	@Then("User Homepage is opend")
	public void userHomePageOpens() {
		String userAccountName = homePage.lookUpUserAccountName();

		assertThat(userAccountName).isEqualTo(userName);
	}

	@Then("Logout Page is opend")
	public void logoutPageOpens() {
		String pageTitle = driver.getTitle();

		assertThat(pageTitle).isEqualTo("Log Out - KEEPER");
	}

}
