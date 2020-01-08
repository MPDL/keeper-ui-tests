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

	@Then("About Keeper is opend")
	public void aboutKeeperIsOpened() {
		assertThat(fileViewer.getFileTitle()).isEqualTo("About.markdown");
	}

	@When("Open Cared Data Commitment")
	public void openCaredDataCommitment() {
		footerComponent.openCaredDataCommitment();
	}

	@Then("Cared Data Commitment is opend")
	public void caredDataCommitmentIsOpened() {
		assertThat(fileViewer.getFileTitle()).isEqualTo("CaredDataPrinciples.markdown");
	}

	@When("Open Terms of Servicet")
	public void openTermsOfService() {
		footerComponent.openTermsOfService();
	}

	@Then("Terms of Service is opend")
	public void termsOfServiceIsOpened() {
		assertThat(fileViewer.getFileTitle()).isEqualTo("TermsOfService.markdown");
	}

	@When("Open Download Client page")
	public void openDownloadClientPage() {
		footerComponent.openDownloadClientPage();
	}

	@Then("Download Client page is opend")
	public void DownloadClientPageIsOpened() {
		assertThat(downloadClientPage.getTitle()).isEqualTo("Download - KEEPER");
	}

	@Then("Seafile link is corect")
	public void seafileLinkIsCorect() {
		assertThat(footerComponent.getSeafileLink()).isEqualTo("https://www.seafile.com/en/home/");
	}

	@Then("MPDL link is corect")
	public void mpdlLinkIsCorect() {
		assertThat(footerComponent.getMpdlLink()).isEqualTo("https://www.mpdl.mpg.de/");
	}

	@Then("Contact Keeper Support email is corect")
	public void contactKeeperSupportEmailIsCorect() {
		assertThat(footerComponent.getKeeperEmail()).isEqualTo("keeper@mpdl.mpg.de");
	}

	@When("Open Impressum")
	public void openImpressum() {
		footerComponent.openImpressum();
	}

	@Then("Impressum is opend")
	public void impressumIsOpened() {
		assertThat(fileViewer.getFileTitle()).isEqualTo("Impressum.markdown");
	}

	@When("Open Privacy Policy")
	public void openPrivacyPolicy() {
		footerComponent.openPrivacyPolicy();
	}

	@Then("Privacy Policy is opend")
	public void privacyPolicyIsOpened() {
		assertThat(fileViewer.getFileTitle()).isEqualTo("DSGVO_Keeper.md");
	}

}
