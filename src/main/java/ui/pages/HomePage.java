package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Page Object encapsulates the Home page (Page after user login).
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

	@FindBy(id = "my-repos")
	private WebElement myLibrariesDiv;

	@FindBy(xpath = "//*[@id='my-repos-toolbar']/button[contains(@class,'repo-create')]")
	private WebElement newLibraryButton;

	@FindBy(id = "side-nav")
	private WebElement sideNavigationDiv;

	@Autowired
	@Qualifier("loadTestDataProperties")
	Properties testDataProperties;

	@Lazy
	@Autowired
	HomePage homePage;

	@Lazy
	@Autowired
	LogoutPage logoutPage;

	@Lazy
	@Autowired
	LibraryPage libraryPage;

	@Autowired
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public LogoutPage logout() {
		this.userElement.click();
		this.logoutElement.click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log in again")));

		return logoutPage;
	}

	public String lookUpUserAccountName() {
		WebElement userAccountButton = driver.findElement(By.id("my-info"));
		userAccountButton.click();

		WebElement accountName = driver.findElement(By.xpath("//div[@class='item ovhd']/div[@class='txt']"));
		return accountName.getText();
	}

	public HomePage createNewLibrary(String newLibraryName) {
		newLibraryButton.click();
		WebElement newLibraryDialog = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("simplemodal-container")));

		WebElement libraryNameInput = newLibraryDialog.findElement(By.id("repo-name"));
		libraryNameInput.sendKeys(newLibraryName);

		WebElement submit = newLibraryDialog.findElement(By.className("submit"));
		submit.click();

		wait.until(ExpectedConditions.stalenessOf(newLibraryDialog));

		return homePage;
	}

	public List<String> readLibraryNames() {
		List<WebElement> libraryLinks = this.myLibrariesDiv.findElements(By.xpath(".//*[@class='repo-name-span']/a"));

		List<String> libraryNames = new ArrayList<>();
		libraryLinks.forEach(libraryLink -> libraryNames.add(libraryLink.getText()));

		return libraryNames;
	}

	public HomePage deleteLibrary(String libraryName) {
		WebElement firstLibraryRow = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='my-repos']//a[text()='" + libraryName + "']/ancestor::tr")));

		WebElement deleteButton = firstLibraryRow.findElement(By.xpath(".//a[@title='Delete']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style','visibility:visible;');",
				deleteButton);
		deleteButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='confirm-con']//*[contains(text(),'" + libraryName + "')]")));
		WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirm-yes")));
		yesButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("my-libs-more-op")));

		return homePage;
	}

	public LibraryPage openLibrary(String libraryName) {
		// Defensive wait
		WebElement libraryLink = wait.until(driver -> this.myLibrariesDiv.findElement(By.linkText(libraryName)));
		libraryLink.click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("share-cur-dir")));
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));

		return libraryPage;
	}

	public HomePage openMyLibraries() {
		WebElement myLibrariesButton = sideNavigationDiv.findElement(By.xpath(".//a[@title='My Libraries']"));
		myLibrariesButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("my-repos")));
		wait.until(webDriver -> ((Long) (((JavascriptExecutor) webDriver).executeScript("return jQuery.active")) == 0));

		return homePage;
	}

	public boolean aboutDialogIsOpened() {
		WebElement dialog = driver.findElement(By.id("simplemodal-container"));
		List<WebElement> seafileLogo = dialog.findElements(By.xpath(".//img[@title='Seafile']"));

		return !seafileLogo.isEmpty();
	}

	public HomePage navigateTo() {
		// TODO: Rework centralized access to the Keeper URLs
		driver.navigate().to(testDataProperties.getProperty("keeperUrl"));

		// Waiting for the HomePage to load by waiting for My Libraries
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("my-repos")));
		wait.until(webDriver -> ((Long) (((JavascriptExecutor) webDriver).executeScript("return jQuery.active")) == 0));

		return homePage;
	}

}
