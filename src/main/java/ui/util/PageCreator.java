package ui.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ui.pages.BasePage;

/**
 * Class responsible for creating one Page Object per WebDriver for each Page
 * Object Type.
 * 
 * @author helk
 *
 */
public class PageCreator {

	// TODO: Replace this class & its functionality with Dependency Injection
	// (Framework).

	private static Map<WebDriver, List<BasePage>> pageObjects = new HashMap<>();

	private PageCreator() {
	}

	public static BasePage get(WebDriver driver, Class<? extends BasePage> pageClass) {
		List<BasePage> pages = pageObjects.get(driver);
		if (pages == null) {
			pages = new ArrayList<>();
			pageObjects.put(driver, pages);
		}
		if (pages.isEmpty()) {
			BasePage newPage = PageFactory.initElements(driver, pageClass);
			pages.add(newPage);
		}

		BasePage page;
		for (BasePage abstractPage : pages) {
			if (abstractPage.getClass() == pageClass) {
				page = abstractPage;
				return page;
			}
		}

		return null;
	}

	public static BasePage get(Class<? extends BasePage> pageClass) {
		Long threadId = Thread.currentThread().getId();
		WebDriver driver = WebDriverCreator.get(threadId);

		List<BasePage> pages = pageObjects.get(driver);
		if (pages == null) {
			pages = new ArrayList<>();
			pageObjects.put(driver, pages);
		}
		if (pages.isEmpty()) {
			BasePage newPage = PageFactory.initElements(driver, pageClass);
			pages.add(newPage);
		}

		BasePage page;
		for (BasePage abstractPage : pages) {
			if (abstractPage.getClass() == pageClass) {
				page = abstractPage;
				return page;
			}
		}

		return null;
	}

}