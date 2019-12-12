package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Abstract Page Object encapsulates functionalities all pages share.
 * 
 * @author helk
 *
 */
public abstract class BasePage {

	// TODO Set the URL of the KEEPER instance which should be tested
	public static final String KEEPER_URL = "";

	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(this.driver, this);
	}

}
