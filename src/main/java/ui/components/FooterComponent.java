package ui.components;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ui.pages.BasePage;
import ui.util.SeleniumUtil;

/**
 * Page Object encapsulates the Footer component.
 * 
 * @author helk
 *
 */
@Lazy
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class FooterComponent extends BasePage {

	@FindBy(linkText = "Project Catalog")
	private WebElement projectCatalogLink;

	@FindBy(linkText = "Help / Knowledge Base")
	private WebElement helpLink;

	@FindBy(linkText = "About")
	private WebElement aboutLink;

	@FindBy(xpath = "//*[@id='footer-header']/*[contains(@class,'keeper-icon-triangle-up')]")
	private WebElement footerFoldOutElement;

	@FindBy(linkText = "About Keeper")
	private WebElement aboutKeeperLink;

	@FindBy(linkText = "Cared Data Commitment")
	private WebElement caredDataCommitmentLink;

	@FindBy(linkText = "Terms of Service")
	private WebElement termsOfServiceLink;

	@FindBy(linkText = "Download the Keeper client for Windows, Linux, Mac, Android and iPhone")
	private WebElement downloadClientLink;

	@FindBy(xpath = "//img[@id='seafile-logo']/parent::a")
	private WebElement seafileLink;

	@FindBy(xpath = "//img[@id='MPDL-logo']/parent::a")
	private WebElement mpdlLink;

	@FindBy(linkText = "Contact Keeper Support")
	private WebElement contactKeeperSupportLink;

	@FindBy(linkText = "Impressum")
	private WebElement impressumLink;

	@FindBy(linkText = "Privacy Policy")
	private WebElement privacyPolicyLink;

	@Autowired
	public FooterComponent(WebDriver driver) {
		super(driver);
	}

	public void openProjectCatalog() {
		this.projectCatalogLink.click();
		SeleniumUtil.switchToSecondTab(driver, wait);

		wait.until(ExpectedConditions.titleContains("Catalog"));
	}

	public void openHelpPage() {
		this.helpLink.click();
		SeleniumUtil.switchToSecondTab(driver, wait);

		wait.until(ExpectedConditions.titleIs("Keeper – Max Planck Digital Library"));
	}

	public void openAboutDialog() {
		this.aboutLink.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='modal-dialog']")));
	}

	public void openAboutKeeper() {
		this.footerFoldOutElement.click();
		wait.until(ExpectedConditions.elementToBeClickable(this.aboutKeeperLink));
		this.aboutKeeperLink.click();
		SeleniumUtil.switchToSecondTab(driver, wait);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@title='About.markdown']")));
	}

	public void openCaredDataCommitment() {
		this.footerFoldOutElement.click();
		wait.until(ExpectedConditions.elementToBeClickable(this.caredDataCommitmentLink));
		this.caredDataCommitmentLink.click();
		SeleniumUtil.switchToSecondTab(driver, wait);

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@title='CaredDataPrinciples.markdown']")));
	}

	public void openTermsOfService() {
		this.footerFoldOutElement.click();
		wait.until(ExpectedConditions.elementToBeClickable(this.termsOfServiceLink));
		this.termsOfServiceLink.click();
		SeleniumUtil.switchToSecondTab(driver, wait);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@title='TermsOfService.markdown']")));
	}

	public void openDownloadClientPage() {
		this.footerFoldOutElement.click();
		wait.until(ExpectedConditions.elementToBeClickable(this.downloadClientLink));
		this.downloadClientLink.click();
		SeleniumUtil.switchToSecondTab(driver, wait);

		wait.until(ExpectedConditions.titleIs("Download - KEEPER"));
	}

	public String getSeafileLink() {
		this.footerFoldOutElement.click();
		wait.until(ExpectedConditions.elementToBeClickable(this.seafileLink));

		String seafileLink = this.seafileLink.getAttribute("href");
		return seafileLink;
	}

	public String getMpdlLink() {
		this.footerFoldOutElement.click();
		wait.until(ExpectedConditions.elementToBeClickable(this.mpdlLink));

		String mpdlLink = this.mpdlLink.getAttribute("href");
		return mpdlLink;
	}

	public String getKeeperEmail() {
		this.footerFoldOutElement.click();
		wait.until(ExpectedConditions.elementToBeClickable(this.contactKeeperSupportLink));
		String href = this.contactKeeperSupportLink.getAttribute("href");

		String keeperEmail = href.replace("mailto:", "");
		return keeperEmail;
	}

	public void openImpressum() {
		this.footerFoldOutElement.click();
		wait.until(ExpectedConditions.elementToBeClickable(this.impressumLink));
		this.impressumLink.click();
		SeleniumUtil.switchToSecondTab(driver, wait);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@title='Impressum.markdown']")));
	}

	public void openPrivacyPolicy() {
		this.footerFoldOutElement.click();
		wait.until(ExpectedConditions.elementToBeClickable(this.privacyPolicyLink));
		this.privacyPolicyLink.click();
		SeleniumUtil.switchToSecondTab(driver, wait);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@title='DSGVO_Keeper.md']")));
	}

}
