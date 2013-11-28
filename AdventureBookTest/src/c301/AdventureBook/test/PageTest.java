package c301.AdventureBook.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;
import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;

/**
 * JUnit test case for the Page model
 * 
 * @author tyleung
 *
 */
public class PageTest extends AndroidTestCase {
	private Page page;
	private Option option;
	private List<Option> optionsList;
	private String pageTitle = "Page 1";
	private String pageDescription = "This is a page description";
	private String uuid = UUID.randomUUID().toString();
	private String optionDescription = "this is an option description";
	
	private String goToPageTitle = "goToPage title";
	private String goToPageDescription = "goToPage description";
	private String goToPageId = UUID.randomUUID().toString();
	private Page goToPage;
	
	@Before
	protected void setUp() {
		page = new Page(pageTitle, pageDescription, uuid);
		goToPage = new Page(goToPageTitle, goToPageDescription, goToPageId);
		option = new Option(optionDescription, goToPage.getuuid());
		optionsList = new ArrayList<Option>();
	}
	
	/*
     * Test that the addOption() method adds an option to the list of options
     */
	@Test
	public void testAddOption() {
		optionsList.add(option);
		page.addOption(option);
		assertEquals(optionsList, page.getOptions());
	}
	
	/*
     * Test that the getPageTitle() method gives data matching the constructor.
     */
	@Test
	public void testGetPageTitle() {
		assertEquals(pageTitle, page.getTitle());
	}
	
	/*
     * Test that the getPageDescription() method gives data matching the constructor.
     */
	@Test
	public void testGetPageDescription() {
		assertEquals(pageDescription, page.getPageDescription());
	}
	
	/*
     * Test that the getOptions() method gives data matching the constructor.
     */
	@Test
	public void testGetOptions() {
		assertEquals(optionsList, page.getOptions());
	}

}
