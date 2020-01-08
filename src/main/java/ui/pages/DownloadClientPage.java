package ui.pages;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Page Object encapsulates the Download Client page.
 * 
 * @author helk
 *
 */
@Lazy
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class DownloadClientPage extends BasePage {

	@Autowired
	public DownloadClientPage(WebDriver driver) {
		super(driver);
	}

	public String getTitle() {
		return driver.getTitle();
	}

}
