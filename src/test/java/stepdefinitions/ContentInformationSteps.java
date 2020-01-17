package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.components.FooterComponent;
import ui.pages.CatalogPage;
import ui.pages.DownloadClientPage;
import ui.pages.FileViewer;
import ui.pages.HelpPage;
import ui.pages.HomePage;

/**
 * Test Steps: Content Information domain
 * 
 * @author helk
 *
 */
public class ContentInformationSteps {

	@Autowired
	WebDriver driver;

	@Lazy
	@Autowired
	HomePage homePage;

	@Lazy
	@Autowired
	CatalogPage catalogPage;

	@Lazy
	@Autowired
	FooterComponent footerComponent;

	@Lazy
	@Autowired
	HelpPage helpPage;

	@Lazy
	@Autowired
	DownloadClientPage downloadClientPage;

	@Lazy
	@Autowired
	FileViewer fileViewer;

	@When("Open Project Catalog")
	public void openProjectCatalog() {
		footerComponent.openProjectCatalog();
	}

	@Then("Project Catalog Page is opened")
	public void projectCatalogPageIsOpened() {
		assertThat(catalogPage.getTitle()).isEqualTo("Catalog - KEEPER");
	}

	@When("Open Help page")
	public void openHelpPage() {
		footerComponent.openHelpPage();
	}

	@Then("Help page is opend")
	public void helpPageIsOpened() {
		// TODO: Move Strings which identify the page e.g. "zendesk" to the page class?
		assertThat(helpPage.getURL()).contains("zendesk", "Keeper");
	}

	@When("Open About dialog")
	public void openAboutDialog() {
		footerComponent.openAboutDialog();
	}

	@Then("About dialog is opend")
	public void aboutDialogIsOpened() {
		homePage.aboutDialogIsOpened();
	}

	@When("Open About Keeper")
	public void openAboutKeeper() {
		footerComponent.openAboutKeeper();
	}

	@When("Open Cared Data Commitment")
	public void openCaredDataCommitment() {
		footerComponent.openCaredDataCommitment();
	}

	@When("Open Terms of Service")
	public void openTermsOfService() {
		footerComponent.openTermsOfService();
	}

	@When("Open Download Client page")
	public void openDownloadClientPage() {
		footerComponent.openDownloadClientPage();
	}

	@Then("Download Client page is opend")
	public void DownloadClientPageIsOpened() {
		assertThat(downloadClientPage.getTitle()).isEqualTo("Download - KEEPER");
	}

	@Then("Seafile link is {word}")
	public void seafileLinkIsEquals(String seafileLink) {
		assertThat(footerComponent.getSeafileLink()).isEqualTo(seafileLink);
	}

	@Then("MPDL link is {word}")
	public void mpdlLinkIsEquals(String mpdlLink) {
		assertThat(footerComponent.getMpdlLink()).isEqualTo(mpdlLink);
	}

	@Then("Contact Keeper Support email is {word}")
	public void contactKeeperSupportEmailIsEquals(String keeperContactEmail) {
		assertThat(footerComponent.getKeeperEmail()).isEqualTo(keeperContactEmail);
	}

	@When("Open Impressum")
	public void openImpressum() {
		footerComponent.openImpressum();
	}

	@When("Open Privacy Policy")
	public void openPrivacyPolicy() {
		footerComponent.openPrivacyPolicy();
	}

	@Then("File {word} is viewed")
	public void fileIsViewed(String fileName) {
		// TODO: Move this method to FileSteps?
		assertThat(fileViewer.getFileTitle()).isEqualTo(fileName);
	}

}
