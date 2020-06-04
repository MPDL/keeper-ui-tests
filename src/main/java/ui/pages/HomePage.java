package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	@FindBy(xpath = "//*[@id='user-info-popup']//a[text()='Log out']")
	private WebElement logoutElement;

	@FindBy(xpath = "//h3[text()='My Libraries']")
	private WebElement myLibrariesHeading;

	@FindBy(xpath = "//div[@class='cur-view-container']")
	private WebElement myLibrariesDiv;

	@FindBy(xpath = "//button[@title='New Library']")
	private WebElement newLibraryButton;

	@FindBy(className = "side-nav")
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

		WebElement accountName = driver
				.findElement(By.xpath("//div[@id='account']//div[@class='item o-hidden']/div[@class='txt']"));
		return accountName.getText();
	}

	public HomePage createNewLibrary(String newLibraryName) {
		newLibraryButton.click();
		WebElement newLibraryDialog = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));

		WebElement libraryNameInput = newLibraryDialog.findElement(By.id("repoName"));
		libraryNameInput.sendKeys(newLibraryName);

		WebElement submit = newLibraryDialog.findElement(By.xpath(".//button[text()='Submit']"));
		submit.click();

		wait.until(ExpectedConditions.stalenessOf(newLibraryDialog));
		// TODO: Use different waiting strategy to wait for the success of this method
		// (check AJAX request!?)
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath(".//a[contains(@href,'/library/') and text()='" + newLibraryName + "']")));

		return homePage;
	}

	public List<String> readLibraryNames() {
		List<WebElement> libraryLinks = this.myLibrariesDiv.findElements(By.xpath(".//a[contains(@href,'/library/')]"));

		List<String> libraryNames = new ArrayList<>();
		libraryLinks.forEach(libraryLink -> libraryNames.add(libraryLink.getText()));

		return libraryNames;
	}

	public HomePage deleteLibrary(String libraryName) {
		WebElement firstLibraryRow = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='cur-view-container']//a[text()='" + libraryName + "']/ancestor::tr")));

		new Actions(driver).moveToElement(firstLibraryRow).perform();
		WebElement deleteButton = firstLibraryRow.findElement(By.xpath(".//a[@title='Delete']"));
		deleteButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@class='modal-content']//*[contains(text(),'" + libraryName + "')]")));
		WebElement confirmDeleteButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@class='modal-content']//button[text()='Delete']")));
		confirmDeleteButton.click();

		wait.until(ExpectedConditions.visibilityOf(myLibrariesHeading));

		return homePage;
	}

	public LibraryPage openLibrary(String libraryName) {
		// Defensive wait
		WebElement libraryLink = wait.until(driver -> this.myLibrariesDiv.findElement(By.linkText(libraryName)));
		libraryLink.click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Share']")));
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));

		return libraryPage;
	}

	public HomePage openMyLibraries() {
		WebElement myLibrariesButton = sideNavigationDiv.findElement(By.xpath(".//a[@title='My Libraries']"));
		myLibrariesButton.click();

		wait.until(ExpectedConditions.visibilityOf(myLibrariesHeading));
		// TODO: Handle waiting for AJAX differently
//		wait.until(webDriver -> ((Long) (((JavascriptExecutor) webDriver).executeScript("return jQuery.active")) == 0));

		return homePage;
	}

	public boolean aboutDialogIsOpened() {
		WebElement dialog = driver.findElement(By.xpath("//*[@class='modal-dialog']"));
		List<WebElement> keeperLogo = dialog.findElements(By.xpath(".//img[@title='KEEPER']"));

		return !keeperLogo.isEmpty();
	}

	public HomePage navigateTo() {
		// TODO: Rework centralized access to the Keeper URLs
		driver.navigate().to(testDataProperties.getProperty("keeperUrl"));

		// Waiting for the HomePage to load by waiting for My Libraries
		wait.until(ExpectedConditions.visibilityOf(myLibrariesHeading));
		// TODO: Handle waiting for AJAX differently
//		wait.until(webDriver -> ((Long) (((JavascriptExecutor) webDriver).executeScript("return jQuery.active")) == 0));

		return homePage;
	}

}
