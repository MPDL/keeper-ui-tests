package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

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

	// TODO: Is there a better way to delete created test libraries @after the tests
	// than saving all in an instant variable
	private List<String> librariesToDelete = new ArrayList<String>();

	@When("Create new Library {string}")
	public void createNewLibrary(String libraryName) {
//		this.newLibraryName = "New UI Test Library " + LocalDateTime.now();
		librariesToDelete.add(libraryName);

		homePage.createNewLibrary(libraryName);
	}

	@Then("My Libraries contains {string}")
	public void myLibrariesContains(String libraryName) {
		boolean containsLibrary = homePage.containsLibrary(libraryName);

		assertThat(containsLibrary).isTrue();
	}

	@Then("Library {string} contains:")
	public void libraryContains(String libraryName, List<String> fileNames) {
		// FIXME: Replace sleep with a appropriate wait
		// Wait implicitly until Cared-Data-Certificate gets added to the new library
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Handle exception
			e.printStackTrace();
		}

		boolean defaultElementsContained = homePage.openLibrary(libraryName).containsElements(fileNames);

		assertThat(defaultElementsContained).isTrue();
	}

	@Given("Open Library {string}")
	public void openNewLibrary(String libraryName) {
		// FIXME: Replace sleep with a appropriate wait
		// Wait implicitly until Cared-Data-Certificate gets added to the new library
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Handle exception
			e.printStackTrace();
		}

		homePage.openLibrary(libraryName);
	}

	@Given("Open Markdown element {word}")
	public void openMarkdownElement(String fileName) {
		libraryPage.openMarkdownElement(fileName);
	}

	@When("Lock {word} file")
	public void lockFile(String fileName) {
		libraryPage.lockElement(fileName);
	}

	@Then("Lock symbole displayed for {word}")
	public void lockSymboleDisplayedForFile(String fileName) {
		assertThat(libraryPage.lockedIconVisible(fileName)).isTrue();
	}

	@When("Unlock {word} file")
	public void unlockFile(String fileName) {
		libraryPage.unlockElement(fileName);
	}

	@Then("Lock symbole not displayed for {word}")
	public void lockSymboleNotDisplayedForFile(String fileName) {
		assertThat(libraryPage.lockedIconVisible(fileName)).isFalse();
	}

	@When("Upload file {word} to Library")
	public void uploadFileToLibrary(String fileName) {
		libraryPage.uploadFile(fileName);
	}

	@Then("Library contains file {word}")
	public void libraryContainsFile(String fileName) {
		// TODO: Return all files of the library and assert whether fileName is
		// contained. Rework this for all assertions which only check true/false!
		boolean elementContained = libraryPage.containsElements(fileName);

		assertThat(elementContained).isTrue();
	}

	@Then("Library {string} contains certificate")
	public void libraryContainsCertificate(String libraryName) {
		// TODO: Rework/Relocate this navigation to the library!?
		homePage.navigateTo();
		homePage.openLibrary(libraryName);

		// TODO: How to handle "cared-data-certificate_" correctly
		boolean elementContained = libraryPage.containsElementsContainingNameSubstring("cared-data-certificate_");

		assertThat(elementContained).isTrue();
	}

	// TODO: Add this @after to a CleanUpHooks class
	@After("@createNewLibrary or @openArchiveMetadata or @FillOutArchiveMetadata or @LockArchiveMetadata or @EditLockedArchiveMetadata or @EditUnlockedArchiveMetadata or @uploadFile or @receiveCertificate")
	public void deleteLibraries() {
		for (String libraryName : librariesToDelete) {
			homePage.navigateTo();
			homePage.openMyLibraries();
			homePage.deleteLibrary(libraryName);
		}
	}

}
