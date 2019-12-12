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
 * Page Object encapsulates the page shown after logout.
 * 
 * @author helk
 *
 */
@Lazy
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class LogoutPage extends BasePage {

	@FindBy(xpath = "*[href='/accounts/login/']")
	private WebElement loginAgain;

	@Lazy
	@Autowired
	LoginPage loginPage;

	@Autowired
	public LogoutPage(WebDriver driver) {
		super(driver);
	}

	public LoginPage loginAgain() {
		this.loginAgain.click();

		return loginPage;
	}

}
