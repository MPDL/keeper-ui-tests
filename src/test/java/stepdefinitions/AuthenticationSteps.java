package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testconfiguration.TestConfiguration;
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
	@Qualifier("loadTestDataProperties")
	Properties testDataProperties;

	@Autowired
	WebDriver driver;

	@Lazy
	@Autowired
	LoginPage loginPage;

	@Lazy
	@Autowired
	HomePage homePage;

	@Given("Open LoginPage")
	public void navigateToLoginPage() {
		driver.navigate().to(testDataProperties.getProperty("keeperUrl") + "accounts/login/");
	}

	@Given("Logged in as User")
	public void loggedInAsUser() {
		this.loginAsUser();
	}

	@When("Login as user")
	public void loginAsUser() {
		loginPage.login(testDataProperties.getProperty("testUser1Email"),
				testDataProperties.getProperty("testUser1Password"));
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

		assertThat(userAccountName).isEqualTo(testDataProperties.getProperty("testUser1Name"));
	}

	@Then("Logout Page is opend")
	public void logoutPageOpens() {
		String pageTitle = driver.getTitle();

		assertThat(pageTitle).isEqualTo("Log Out - KEEPER");
	}

}
