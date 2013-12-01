package c301.AdventureBook.test;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;
import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;

/**
 * JUnit test case for the Option model
 * 
 * @author tyleung
 *
 */
public class OptionTest extends AndroidTestCase {
	private Option option;
	private String description = "this is an option description";
	
	private String goToPageTitle = "goToPage title";
	private String goToPageDescription = "goToPage description";
	private String goToPageId = UUID.randomUUID().toString();
	private Page goToPage;
	
	@Before
	protected void setUp() {
		goToPage = new Page(goToPageTitle, goToPageDescription, goToPageId);
		option = new Option(description, goToPage.getPageId());
	}
	
	/*
     * Test that the getDescription() method gives data matching the constructor.
     */
	@Test
	public void testGetDescription() {
		assertEquals(description, option.getDescription());
	}
	
	/*
     * Test that the getGoToPage() method gives data matching the constructor.
     */
	@Test
	public void testGetGoToPage() {
		assertEquals(goToPageId, option.getGoToPage());
	}
}
