package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Page Object encapsulates the Start/Home page.
 * 
 * @author helk
 *
 */
@Lazy
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class HomePage extends BasePage {

	@FindBy(id = "my-info")
	private WebElement userElement;

	@FindBy(id = "logout")
	private WebElement logoutElement;

	@Lazy
	@Autowired
	LogoutPage logoutPage;

	@Autowired
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public LogoutPage logout() {
		this.userElement.click();
		this.logoutElement.click();

		return logoutPage;
	}

}
