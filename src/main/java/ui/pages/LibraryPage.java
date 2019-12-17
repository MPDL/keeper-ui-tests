package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

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

}
