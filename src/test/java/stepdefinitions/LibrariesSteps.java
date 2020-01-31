package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

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

	private Logger logger = LoggerFactory.getLogger(LibrariesSteps.class);

	@Autowired
	WebDriver driver;

	@Lazy
	@Autowired
	HomePage homePage;

	@Lazy
	@Autowired
	LibraryPage libraryPage;

	@Lazy
	@Qualifier("createLibrariesToDeleteList")
	@Autowired
	List<String> librariesToDelete;

	@When("Create new Library {string}")
	public void createNewLibrary(String libraryName) {
//		this.newLibraryName = "New UI Test Library " + LocalDateTime.now();
		librariesToDelete.add(libraryName);

		homePage.createNewLibrary(libraryName);
	}

	@Then("My Libraries contains {string}")
	public void myLibrariesContains(String libraryName) {
		List<String> containedLibraryNames = homePage.readLibraryNames();

		assertThat(containedLibraryNames).contains(libraryName);
	}

	@Then("Library {string} contains:")
	public void libraryContains(String libraryName, List<String> fileNames) {
		// FIXME: Replace sleep with a appropriate wait
		// Wait implicitly until Cared-Data-Certificate gets added to the new library
		// -> Maybe Use size of the library to check whether to wait before opening it
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			logger.error("Waiting for Library was interrupted.", e);
		}

		List<String> containedFileNames = homePage.openLibrary(libraryName).readFileNames();

		assertThat(containedFileNames).containsAll(fileNames);
	}

	@Given("Open Library {string}")
	public void openNewLibrary(String libraryName) {
		// FIXME: Replace sleep with a appropriate wait
		// Wait implicitly until Cared-Data-Certificate gets added to the new library
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			logger.error("Waiting for Library was interrupted.", e);
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
		boolean lockedIconVisible = libraryPage.lockedIconVisible(fileName);

		assertThat(lockedIconVisible).as("Check visibility of the Locked-Icon.").isTrue();
	}

	@When("Unlock {word} file")
	public void unlockFile(String fileName) {
		libraryPage.unlockElement(fileName);
	}

	@Then("Lock symbole not displayed for {word}")
	public void lockSymboleNotDisplayedForFile(String fileName) {
		boolean lockedIconVisible = libraryPage.lockedIconVisible(fileName);

		assertThat(lockedIconVisible).as("Check non-visibility of the Locked-Icon.").isFalse();
	}

	@When("Upload file {word} to Library")
	public void uploadFileToLibrary(String fileName) {
		libraryPage.uploadFile(fileName);
	}

	@Then("Library contains file {word}")
	public void libraryContainsFile(String fileName) {
		List<String> containedFileNames = libraryPage.readFileNames();

		assertThat(containedFileNames).contains(fileName);
	}

	@Then("Library {string} contains certificate")
	public void libraryContainsCertificate(String libraryName) {
		// TODO: Rework/Relocate this navigation to the library!?
		homePage.navigateTo();
		homePage.openLibrary(libraryName);

		List<String> containedFileNames = libraryPage.readFileNames();

		assertThat(containedFileNames).anyMatch(fileName -> fileName.startsWith("cared-data-certificate_"));
	}

}
