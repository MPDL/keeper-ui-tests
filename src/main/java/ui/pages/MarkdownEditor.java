package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Page Object encapsulates the Markdown editor page.
 * 
 * @author helk
 *
 */
@Lazy
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class MarkdownEditor extends BasePage {

	// TODO: Split into a generic Markdown-Page and a specific
	// ArchiveMetadata-Markdown-Page

	@FindBy(className = "file-name")
	private WebElement fileName;

	@FindBy(className = "file-modifier-name")
	private WebElement modifierName;

	@FindBy(className = "file-modifier-time")
	private WebElement modifierTime;

	@FindBy(id = "shareBtn")
	private WebElement shareButton;

	@FindBy(id = "parentDirectory")
	private WebElement parentDirectroyButton;

	@FindBy(id = "saveButton")
	private WebElement saveButton;

	@FindBy(className = "seafile-editor-outline")
	private WebElement outline;

	@Autowired
	public MarkdownEditor(WebDriver driver) {
		super(driver);
	}

	public String getFileName() {
		return fileName.getText();
	}

	public String getModifierName() {
		return modifierName.getText();
	}

	public String getModifierTime() {
		return modifierTime.getText();
	}

	public boolean buttonsPresent() {
		return (this.shareButton.isDisplayed() && this.parentDirectroyButton.isDisplayed()
				&& this.saveButton.isDisplayed());
	}

	public List<String> getOutline() {
		List<String> headings = new ArrayList<>();

		this.openSidepanel();
		List<WebElement> outlineHeadings = this.outline.findElements(By.tagName("div"));

		for (WebElement heading : outlineHeadings) {
			String headingTitle = heading.getText();
			headings.add(headingTitle);
		}

		return headings;
	}

	public List<String> getOutlineContent() {
		List<String> content = new ArrayList<>();

		List<WebElement> contentElements = driver.findElements(By.xpath("//h2/following-sibling::p"));

		for (WebElement contentElement : contentElements) {
			String contentText = contentElement.getText();
			content.add(contentText);
		}

		return content;
	}

	public MarkdownEditor editContent(String title, String author, String description, String year, String institute) {
		this.addTitle(title);
		this.addAuthor(author);
		// Publisher (has default value)
		this.addDescription(description);
		this.addYear(year);
		this.addInstitute(institute);
		// Resource Type (has default value)
		// License (Optional)

		WebElement staleElement = driver.findElement(By.id("moreButton"));
		this.saveButton.click();

		driver.navigate().refresh();
		wait.until(ExpectedConditions.stalenessOf(staleElement));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("parentDirectory")));

		return this;
	}

	public void addTitle(String title) {
		this.addTextToOutline("Title", title);
	}

	public void addAuthor(String author) {
		this.addTextToOutline("Author", author);
	}

	public void addDescription(String description) {
		this.addTextToOutline("Description", description);
	}

	public void addYear(String year) {
		this.addTextToOutline("Year", year);
	}

	public void addInstitute(String institute) {
		this.addTextToOutline("Institute", institute);
	}

	private void addTextToOutline(String outline, String text) {
		this.openSidepanel();

		// Navigate to the correct input field via Sidepanel
		WebElement outlineElement = driver
				.findElement(By.xpath("//div[contains(@class,'outline-h2') and text()='" + outline + "']"));
		outlineElement.click();
		WebElement outlineInput = driver.switchTo().activeElement();
		outlineInput.sendKeys(Keys.END);
		outlineInput.sendKeys(Keys.RETURN);
		WebElement beneathOutlineInput = driver.switchTo().activeElement();

		// Write text into the input field
		beneathOutlineInput.sendKeys(text);
	}

	public MarkdownEditor openSidepanel() {
		// Open Sidepanel if not open
		boolean sidepanelClosed = driver.findElements(By.className("side-panel")).isEmpty();
		if (sidepanelClosed) {
			WebElement showSidepanelButton = driver.findElement(By.id("showSidepanel"));
			showSidepanelButton.click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("side-panel")));
		}
		// else: Sidepanel already open

		return this;
	}

}
