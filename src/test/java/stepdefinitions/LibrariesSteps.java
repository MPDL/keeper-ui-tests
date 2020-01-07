package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.pages.HomePage;
import ui.pages.LibraryPage;

/**
 * Tests steps: Libraries domain
 * 
 * @author helk
 *
 */
public class LibrariesSteps {

	@Autowired
	WebDriver driver;

	@Lazy
	@Autowired
	HomePage homePage;

	@Lazy
	@Autowired
	LibraryPage libraryPage;

	// TODO: move newLibraryName as variable to the feature file
	private String newLibraryName;

	@When("Create new Library")
	public void createNewLibrary() {
		this.newLibraryName = "New UI Test Library " + LocalDateTime.now();

		homePage.createNewLibrary(newLibraryName);
	}

	@Then("New Library listed in My Libraries")
	public void newLibraryListedInMyLibraries() {
		boolean newLibraryCreated = homePage.containsLibrary(newLibraryName);

		assertThat(newLibraryCreated).isTrue();
	}

	@Then("New Library contains default documents")
	public void newLibraryContainsDefaultDocuments() {
		// FIXME: Replace sleep with a appropriate wait
		// Wait implicitly until Cared-Data-Certificate gets added to the new library
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Handle exception
			e.printStackTrace();
		}

		boolean defaultElementsContained = homePage.openLibrary(newLibraryName).containsElements("archive-metadata.md",
				"Cared-Data-Certificate-HowTo.pdf");

		assertThat(defaultElementsContained).isTrue();
	}

	@Given("Open new Library")
	public void openNewLibrary() {
		// FIXME: Replace sleep with a appropriate wait
		// Wait implicitly until Cared-Data-Certificate gets added to the new library
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Handle exception
			e.printStackTrace();
		}

		homePage.openLibrary(newLibraryName);
	}

	@Given("Open archive metadata")
	public void openArchiveMetadata() {
		libraryPage.openMarkdownElement("archive-metadata.md");
	}

	@When("Lock archive metadata file")
	public void lockArchiveMetadataFile() {
		libraryPage.lockElement("archive-metadata.md");
	}

	@Then("Archive metadata lock symbole is displayed")
	public void archiveMetadataLockSymboleIsDisplayed() {
		assertThat(libraryPage.lockedIconVisible("archive-metadata.md")).isTrue();
	}

	@When("Unlock archive metadata file")
	public void unlockArchiveMetadataFile() {
		libraryPage.unlockElement("archive-metadata.md");
	}

	@Then("Archive metadata lock symbole is not displayed")
	public void archiveMetadataLockSymboleIsNotDisplayed() {
		assertThat(libraryPage.lockedIconVisible("archive-metadata.md")).isFalse();
	}

	@After("@createNewLibrary or @openArchiveMetadata or @FillOutArchiveMetadata or @LockArchiveMetadata")
	public void deleteLibrary() {
		homePage.navigateTo();
		homePage.openMyLibraries();
		homePage.deleteLibrary(newLibraryName);
	}

}
