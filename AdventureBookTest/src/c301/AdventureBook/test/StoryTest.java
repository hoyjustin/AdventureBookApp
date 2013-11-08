package c301.AdventureBook.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;

/**
 * JUnit test case for the Story model.
 * 
 * @author William
 *
 */
public class StoryTest extends TestCase{
	private Story story;
	private String title = "C301";
	private String description = "This is a test";
	private String author = "william";
	private String date = "2013.11.6";
	private String imagePath = "I don't know";
	public List<Page> pages = new ArrayList<Page>();

	protected void setUp() throws Exception {
		story = new Story(title, description, author, date, imagePath);
	}

	protected void tearDown() throws Exception {
	}

	public void testGetter() {
		assertEquals(imagePath, story.getImagePath());
		assertEquals(date, story.getDate());
		assertEquals(title, story.getTitle());
		assertEquals(description, story.getDescription());
		assertEquals(author, story.getAuthor());

	}

	public void testAddDeletePages() {
		Page page1 = new Page(title, description);
		String title2 = "this is 2 test";
		String description = "just a test";
		Page page2 = new Page(title2, description);
		this.pages.add(page1);
	    assertEquals(((Story) pages).getPages(), page1);
	    this.pages.remove(page1);
	    this.pages.add(page2);
	    assertEquals(((Story)pages).getPages(), page2);
	}

}
