package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	@Autowired
	public MarkdownEditor(WebDriver driver) {
		super(driver);
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

		WebElement saveButton = driver.findElement(By.id("saveButton"));
		saveButton.click();

		driver.navigate().refresh();
		wait.until(ExpectedConditions.stalenessOf(saveButton));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("editButton")));

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
