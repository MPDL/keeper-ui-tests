package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.pages.CatalogPage;
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

	@When("Open Project Catalog")
	public void openProjectCatalog() {
		homePage.openProjectCatalog();
	}

	@Then("Project Catalog Page is opened")
	public void projectCatalogPageIsOpened() {
		assertThat(catalogPage.getTitle()).isEqualTo("Catalog - KEEPER");
	}

}
