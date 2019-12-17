package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.pages.HomePage;

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

	@After("@createNewLibrary")
	public void deleteLibrary() {
		homePage.openMyLibraries();
		homePage.deleteLibrary(newLibraryName);
	}

}
