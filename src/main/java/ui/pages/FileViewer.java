package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Page Object encapsulates the File viewer page.
 * 
 * @author helk
 *
 */
@Lazy
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class FileViewer extends BasePage {

	@FindBy(xpath = "//*[@id='shared-file-view-hd']//h2")
	private WebElement fileTitle;

	@Autowired
	public FileViewer(WebDriver driver) {
		super(driver);
	}

	public String getFileTitle() {
		return this.fileTitle.getAttribute("title");
	}

}
