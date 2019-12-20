package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

}
