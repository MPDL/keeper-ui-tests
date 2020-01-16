package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.pages.MarkdownViewer;

/**
 * Tests steps: Files domain
 * 
 * @author helk
 *
 */
public class FileSteps {

	@Autowired
	WebDriver driver;

	@Lazy
	@Autowired
	MarkdownViewer markdownViewer;

	@Then("Archive metadata is displayed correctly")
	public void archiveMetadataDisplayedCorrectly() {
		String fileName = markdownViewer.getFileName();
		String modifierName = markdownViewer.getModifierName();
		// TODO: Handle Check for modifier time correctly
//		String modifierTime = markdownViewer.getModifierTime();

		boolean buttonsPresent = markdownViewer.buttonsPresent();

		List<String> outline = markdownViewer.getOutline();

		assertThat(fileName).isEqualTo("archive-metadata.md");
		assertThat(modifierName).isEqualTo("keeper");
//		assertThat(modifierTime).isEqualTo("archive-metadata.md");

		assertThat(outline).contains("Title", "Author", "Publisher", "Description", "Year", "Institute",
				"Resource Type", "License");

		assertThat(buttonsPresent).isTrue();
	}

	@Then("Edited archive metadata is displayed correctly")
	public void editedArchiveMetadataDisplayedCorrectly() {
		List<String> content = markdownViewer.getOutlineContent();

		// TODO: Rework (Relocate) the access/initialization of (all) the test data
		String title = "Title for a Test-Project";
		String author = "Author-Lastname, Author-Firstname";
		String description = "This is a Test-Description for a Test-Project.";
		String year = "2020";
		String institute = "Institute-Name; Department-Name; Director, Director-Lastname";

		assertThat(content).contains(title, author, description, year, institute);
	}

	@When("Fill out archive metadata")
	public void fillOutArchiveMetadata() {
		String title = "Title for a Test-Project";
		String author = "Author-Lastname, Author-Firstname";
		String description = "This is a Test-Description for a Test-Project.";
		String year = "2020";
		String institute = "Institute-Name; Department-Name; Director, Director-Lastname";
		markdownViewer.editFile(title, author, description, year, institute);
	}

}
