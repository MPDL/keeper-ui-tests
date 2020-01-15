package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Abstract Page Object encapsulates functionalities all pages share.
 * 
 * @author helk
 *
 */
public abstract class BasePage {

	protected WebDriver driver;

	// TODO: Relocate the wait & Rework Waiting-Mechanism for the page loadings
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;

		wait = new WebDriverWait(driver, 10);

		PageFactory.initElements(this.driver, this);
	}

}
