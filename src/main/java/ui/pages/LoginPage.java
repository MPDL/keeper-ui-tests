package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object encapsulates the Login page.
 * 
 * @author helk
 *
 */
public class LoginPage extends BasePage {

	@FindBy(name = "login")
	private WebElement emailInput;

	@FindBy(name = "password")
	private WebElement passwordInput;

	@FindBy(className = "submit")
	private WebElement loginButton;

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage login(String email, String password) {
		this.emailInput.sendKeys(email);
		this.passwordInput.sendKeys(password);

		this.loginButton.click();

		return new HomePage(this.driver);
	}

}
