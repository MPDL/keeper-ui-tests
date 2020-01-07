package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

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

	@Autowired
	public LibraryPage(WebDriver driver) {
		super(driver);
	}

	public boolean containsElements(String... elements) {
		for (String element : elements) {
			List<WebElement> elementLinks = this.directoryViewDiv.findElements(By.linkText(element));
			if (elementLinks.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public void openMarkdownElement(String elementName) {
		WebElement element = this.directoryViewDiv.findElement(By.linkText(elementName));
		element.click();

		// TODO: Rework window handling
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

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

}
