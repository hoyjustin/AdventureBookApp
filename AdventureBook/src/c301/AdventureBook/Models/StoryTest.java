package c301.AdventureBook.Models;
//author Zhao Zhang.
// this is a raw test file for story
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StoryTest {
	private Story story;
	private String title = "C301";
	private String description = "This is a test";
	private String author = "william";
	private String date = "2013.11.6";
	private String imagePath = "I don't know";
	public List<Page> pages = new ArrayList<Page>();

	@Before
	public void setUp() throws Exception {
		story = new Story(title, description, author, date, imagePath);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetter() {
		assertEquals(imagePath, story.getImagePath());
		assertEquals(date, story.getDate());
		assertEquals(title, story.getTitle());
		assertEquals(description, story.getDescription());
		assertEquals(author, story.getAuthor());

	}


	@Test
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
