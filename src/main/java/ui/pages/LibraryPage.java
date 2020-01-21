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

	@FindBy(id = "dir-view")
	private WebElement directoryViewDiv;

	@FindBy(id = "advanced-upload")
	private WebElement uploadButton;

	@Autowired
	public LibraryPage(WebDriver driver) {
		super(driver);
	}

	public List<String> readFileNames() {
		List<WebElement> fileLinks = this.directoryViewDiv.findElements(By.xpath(".//*[@class='dirent-name']/a"));

		List<String> fileNames = new ArrayList<>();
		fileLinks.forEach(fileLink -> fileNames.add(fileLink.getText()));

		return fileNames;
	}

	public void openMarkdownElement(String elementName) {
		WebElement element = this.directoryViewDiv.findElement(By.linkText(elementName));
		element.click();
		SeleniumUtil.switchToSecondTab(driver, wait);

		wait.until(ExpectedConditions.titleContains(elementName));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("editButton")));
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
	}

	public void lockElement(String elementName) {
		WebElement elementLink = this.directoryViewDiv.findElement(By.linkText(elementName));
		WebElement elementRow = elementLink.findElement(By.xpath(".//ancestor::tr"));

		WebElement moreOptions = elementRow.findElement(By.className("more-op-icon"));
		moreOptions.click();
		WebElement lockFile = elementRow.findElement(By.className("lock-file"));
		lockFile.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("file-locked-icon")));
	}

	public void unlockElement(String elementName) {
		WebElement elementLink = this.directoryViewDiv.findElement(By.linkText(elementName));
		WebElement elementRow = elementLink.findElement(By.xpath(".//ancestor::tr"));

		WebElement moreOptions = elementRow.findElement(By.className("more-op-icon"));
		// Selenium has problems hover/scroll element when clicking => Use JS to click
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", moreOptions);
		WebElement unlockFile = elementRow.findElement(By.className("unlock-file"));
		unlockFile.click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("file-locked-icon")));
	}

	public boolean lockedIconVisible(String elementName) {
		WebElement elementLink = this.directoryViewDiv.findElement(By.linkText(elementName));
		WebElement elementRow = elementLink.findElement(By.xpath(".//ancestor::tr"));

		List<WebElement> lockIcon = elementRow.findElements(By.className("file-locked-icon"));
		return !lockIcon.isEmpty();
	}

	public void uploadFile(String fileName) {
		// Check upload buttons are clickable:
		this.uploadButton.click();
		WebElement uploadFilesButton = driver.findElement(By.className("advanced-upload-file"));
		wait.until(ExpectedConditions.elementToBeClickable(uploadFilesButton));
		// Click on 'Upload' again to hide the field again
		this.uploadButton.click();

		// Upload done using the upload input element:
		WebElement uploadInputElement = driver.findElement(By.id("advanced-upload-file-input"));
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
