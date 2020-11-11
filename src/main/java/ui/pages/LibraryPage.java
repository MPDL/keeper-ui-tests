package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ui.util.SeleniumUtil;

/**
 * Page Object encapsulates the Library page (showing the content of a single
 * library).
 * 
 * @author helk
 *
 */
@Lazy
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class LibraryPage extends BasePage {

	@FindBy(className = "cur-view-container")
	private WebElement directoryViewDiv;

	@FindBy(xpath = "//button[@title='Upload']")
	private WebElement uploadButton;

	@Autowired
	public LibraryPage(WebDriver driver) {
		super(driver);
	}

	public List<String> readFileNames() {
		List<WebElement> fileLinks = this.directoryViewDiv.findElements(By.xpath(".//a[contains(@href,'/lib/')]"));

		List<String> fileNames = new ArrayList<>();
		fileLinks.forEach(fileLink -> fileNames.add(fileLink.getText()));

		return fileNames;
	}

	public void openMarkdownElement(String elementName) {
		WebElement element = this.directoryViewDiv.findElement(By.linkText(elementName));
		element.click();
		SeleniumUtil.switchToSecondTab(driver, wait);

		wait.until(ExpectedConditions.titleContains(elementName));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("parentDirectory")));
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
	}

	public void lockElement(String elementName) {
		WebElement elementLink = this.directoryViewDiv.findElement(By.linkText(elementName));
		WebElement elementRow = elementLink.findElement(By.xpath(".//ancestor::tr"));

		new Actions(driver).moveToElement(elementRow).perform();
		WebElement moreOptions = elementRow.findElement(By.xpath(".//*[@title='More Operations']"));
		moreOptions.click();
		WebElement lockFile = elementRow.findElement(By.xpath(".//button[text()='Lock']"));
		lockFile.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("locked")));
	}

	public void unlockElement(String elementName) {
		WebElement elementLink = this.directoryViewDiv.findElement(By.linkText(elementName));
		WebElement elementRow = elementLink.findElement(By.xpath(".//ancestor::tr"));

		// Selenium sometimes has problems hovering over elements => Use an additional click
		new Actions(driver).moveToElement(elementRow).click().perform();
		WebElement moreOptions = elementRow.findElement(By.xpath(".//*[@title='More Operations']"));
		// Selenium sometimes has problems hover/scroll element when clicking => Use JS to click
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", moreOptions);
		WebElement unlockFile = elementRow.findElement(By.xpath(".//button[text()='Unlock']"));
		unlockFile.click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("locked")));
	}

	public boolean lockedIconVisible(String elementName) {
		WebElement elementLink = this.directoryViewDiv.findElement(By.linkText(elementName));
		WebElement elementRow = elementLink.findElement(By.xpath(".//ancestor::tr"));

		List<WebElement> lockIcon = elementRow.findElements(By.className("locked"));
		return !lockIcon.isEmpty();
	}

	public void uploadFile(String fileName) {
		// Check upload buttons are clickable (Only to verify the buttons work):
		this.uploadButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Upload Files']")));

		// Upload done using the upload input element:
		WebElement uploadInputElement = driver.findElement(By.className("upload-input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility = 'visible';", uploadInputElement);

		// FIXME: Move the extraction of the filepath to another class/method
		URL fileUrl = this.getClass().getClassLoader().getResource("files/" + fileName);
		String filePath = fileUrl.getPath();
		File systemIndependentFile = new File(filePath);
		String systemIndependentFilePath = systemIndependentFile.getPath();

		uploadInputElement.sendKeys(systemIndependentFilePath);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(fileName)));
	}

}
