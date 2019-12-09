package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object encapsulates the Start/Home page.
 * 
 * @author helk
 *
 */
public class HomePage extends BasePage {

	@FindBy(id = "my-info")
	private WebElement userElement;

	@FindBy(id = "logout")
	private WebElement logoutElement;

	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public LogoutPage logout() {
		this.userElement.click();
		this.logoutElement.click();

		return new LogoutPage(this.driver);
	}

}
