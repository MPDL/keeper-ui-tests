package hookdefinitions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import io.cucumber.java.After;
import ui.pages.HomePage;

/**
 * Class containing all hooks which should be executed after the scenarios.
 * 
 * @author helk
 *
 */
public class CleanUpHooks {

	private Logger logger = LoggerFactory.getLogger(CleanUpHooks.class);

	@Lazy
	@Autowired
	HomePage homePage;

	@Lazy
	@Qualifier("createLibrariesToDeleteList")
	@Autowired
	List<String> librariesToDelete;

	@After("@createLibrary")
	public void deleteLibraries() {
		logger.info("Deleting Libraries...");

		// TODO: Maybe use the REST API to delete the Libraries after the scenarios
		for (String libraryName : librariesToDelete) {
			homePage.navigateTo();
			homePage.openMyLibraries();
			homePage.deleteLibrary(libraryName);
		}
	}

}
