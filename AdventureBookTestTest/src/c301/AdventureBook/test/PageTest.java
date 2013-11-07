package c301.AdventureBook.test;

import java.util.ArrayList;
import java.util.List;

import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;
import junit.framework.TestCase;

/**
 * JUnit test case for the Page model
 * 
 * @author tyleung
 *
 */
public class PageTest extends TestCase {
	private Page page;
	private Option option;
	private List<Option> optionsList;
	private String pageTitle = "Page 1";
	private String pageDescription = "This is a page description";
	private String optionDescription = "this is an option description";
	
	private String goToPageTitle = "goToPage title";
	private String goToPageDescription = "goToPage description";
	private Page goToPage;
	
	protected void setUp() {
		page = new Page(pageTitle, pageDescription);
		goToPage = new Page(goToPageTitle, goToPageDescription);
		option = new Option(optionDescription, goToPage);
		optionsList = new ArrayList<Option>();
	}
	
	public void testAddOption() {
		optionsList.add(option);
		page.addOption(option);
		assertEquals(optionsList, page.getOptions());
	}
	
	public void testGetPageTitle() {
		assertEquals(pageTitle, page.getTitle());
	}
	
	public void testGetPageDescription() {
		assertEquals(pageDescription, page.getPageDescription());
	}
	
	public void testGetOptions() {
		assertEquals(optionsList, page.getOptions());
	}

	protected void tearDown() {
		
	}
}
