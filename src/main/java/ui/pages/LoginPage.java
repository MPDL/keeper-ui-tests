package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Page Object encapsulates the Login page.
 * 
 * @author helk
 *
 */
@Lazy
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class LoginPage extends BasePage {

	@FindBy(name = "login")
	private WebElement emailInput;

	@FindBy(name = "password")
	private WebElement passwordInput;

	@FindBy(className = "submit")
	private WebElement loginButton;

	@Lazy
	@Autowired
	HomePage homePage;

	@Autowired
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public HomePage login(String email, String password) {
		this.emailInput.sendKeys(email);
		this.passwordInput.sendKeys(password);

		this.loginButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account")));

		return homePage;
	}

	public HomePage loginWithEmptyCredentials() {
		this.emailInput.sendKeys("");
		this.passwordInput.sendKeys("");

		WebElement staleElement = driver.findElement(By.name("login"));

		this.loginButton.click();
		wait.until(ExpectedConditions.stalenessOf(staleElement));
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));

		return homePage;
	}

}
