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
 * Page Object encapsulates the Markdown viewer page.
 * 
 * @author helk
 *
 */
@Lazy
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class MarkdownViewer extends BasePage {

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

	@FindBy(id = "editButton")
	private WebElement editButton;

	@FindBy(className = "seafile-viewer-outline")
	private WebElement outline;

	@Autowired
	public MarkdownViewer(WebDriver driver) {
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
		return (shareButton.isDisplayed() && this.parentDirectroyButton.isDisplayed() && this.editButton.isDisplayed());
	}

	public List<String> getOutline() {
		List<String> headings = new ArrayList<>();

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

	public void editFile(String title, String author, String description, String year, String institute) {
		this.editButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("seafile-editor")));

		// TODO: Extract to a new Page Class 'MarkdownEditorPage'
		this.addTitle(title);
		this.addAuthor(author);
		this.addDescription(description);
		this.addYear(year);
		this.addInstitute(institute);
		// TODO: Should: Publisher, Resource Type and License be edited?

		WebElement saveButton = driver.findElement(By.id("saveButton"));
		saveButton.click();

		driver.navigate().refresh();
		wait.until(ExpectedConditions.stalenessOf(saveButton));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("editButton")));
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

	public void addDOI(String doi) {
		this.addTextToOutline("DOI", doi);
	}

	private void addTextToOutline(String outline, String outlineText) {
		WebElement outlineElement = driver
				.findElement(By.xpath("//div[@class='outline-h2' and text()='" + outline + "']"));
		outlineElement.click();
		WebElement outlineInput = driver.switchTo().activeElement();
		outlineInput.sendKeys(Keys.END);
		outlineInput.sendKeys(Keys.RETURN);
		WebElement beneathOutlineInput = driver.switchTo().activeElement();
		beneathOutlineInput.sendKeys(outlineText);
	}

}
