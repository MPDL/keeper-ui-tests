package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.pages.FileViewer;
import ui.pages.MarkdownEditor;

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
	MarkdownEditor markdownEditor;

	@Lazy
	@Autowired
	FileViewer fileViewer;

	@Then("Markdown file title {word} present")
	public void markdownFileTitlePresent(String fileName) {
		String fileTitle = markdownEditor.getFileName();

		assertThat(fileTitle).isEqualTo(fileName);
	}

	@Then("Markdown-Viewer buttons present")
	public void markdownViewerButtonsPresent() {
		boolean buttonsPresent = markdownEditor.buttonsPresent();

		assertThat(buttonsPresent).as("Check presence of the Markdown-Viewer buttons.").isTrue();
	}

	@Then("Archive metadata content consists of:")
	public void archiveMetadataContentConsistsOf(List<String> archiveMetadataContent) {
		List<String> outline = markdownEditor.getOutline();

		// TODO: Handle Check for modifierTime and modifierName correctly
//		String modifierName = markdownViewer.getModifierName();
//		String modifierTime = markdownViewer.getModifierTime();

//		assertThat(modifierName).isEqualTo("dev-keeper");
//		assertThat(modifierTime).isEqualTo("archive-metadata.md");

		assertThat(outline).containsExactlyElementsOf(archiveMetadataContent);
	}

	@Then("Archive metadata contains:")
	public void archiveMetadataContains(DataTable archiveMetadataTable) {
		List<String> content = markdownEditor.getOutlineContent();

		Map<String, String> archiveMetadataMap = archiveMetadataTable.asMap(String.class, String.class);

		assertThat(content).contains(archiveMetadataMap.get("title"), archiveMetadataMap.get("author"),
				archiveMetadataMap.get("description"), archiveMetadataMap.get("year"),
				archiveMetadataMap.get("institute"));
	}

	@When("Edit archive metadata:")
	public void editArchiveMetadata(DataTable archiveMetadataTable) {

		Map<String, String> archiveMetadataMap = archiveMetadataTable.asMap(String.class, String.class);

		markdownEditor.editContent(archiveMetadataMap.get("title"), archiveMetadataMap.get("author"),
				archiveMetadataMap.get("description"), archiveMetadataMap.get("year"),
				archiveMetadataMap.get("institute"));
	}

	@Then("File {word} is viewed")
	public void fileIsViewed(String fileName) {
		assertThat(fileViewer.getFileTitle()).isEqualTo(fileName);
	}

}
