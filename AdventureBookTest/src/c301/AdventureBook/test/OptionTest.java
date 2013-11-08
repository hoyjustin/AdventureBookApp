package c301.AdventureBook.test;

import java.util.ArrayList;

import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;
import junit.framework.TestCase;

public class OptionTest extends TestCase {
	private Option option;
	private String description = "this is an option description";
	
	private String goToPageTitle = "goToPage title";
	private String goToPageDescription = "goToPage description";
	private Page goToPage;
	
	protected void setUp() {
		goToPage = new Page(goToPageTitle, goToPageDescription);
		option = new Option(description, goToPage);
	}
	
	/*
     * Test that the getDescription() method gives data matching the constructor.
     */
	public void testGetDescription() {
		assertEquals(description, option.getDescription());
	}
	
	/*
     * Test that the getGoToPage() method gives data matching the constructor.
     */
	public void testGetGoToPage() {
		assertEquals(goToPage, option.getGoToPage());
	}
}
