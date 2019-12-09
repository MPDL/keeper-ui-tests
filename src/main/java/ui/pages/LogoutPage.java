package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object encapsulates the page shown after logout.
 * 
 * @author helk
 *
 */
public class LogoutPage extends BasePage {

	@FindBy(xpath = "*[href='/accounts/login/']")
	private WebElement loginAgain;

	private WebDriver driver;

	public LogoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage loginAgain() {
		this.loginAgain.click();

		return new LoginPage(this.driver);
	}

}
