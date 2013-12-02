package c301.AdventureBook.test;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;
import c301.AdventureBook.ElasticSearch.ESClient;
import c301.AdventureBook.Models.Annotation;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;

/**
 * JUnit test case for the ESClient class.
 * 
 * @author ltong2, tyleung
 * 
 */
public class ESClientTest extends AndroidTestCase {

	//private HttpClient httpclient = new DefaultHttpClient();
	private Story story;
	private ESClient esClient;
	public static final String WEBSERVICE_URI = "http://cmput301.softwareprocess.es:8080/cmput301f13t11/";
	public static final String STORIES_FOLDER = "stories/";
	public static final String SEARCH_PRETTY = "_search?pretty=1&q=";
	public static final int MAX_STORIES = 20;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		esClient = new ESClient();
		
		story = new Story("CatStory", "This Story is about a Cat", "lala",
				"2013-23-3234", null);

		Page page = new Page("FirstPage",
				"U find a biscycle. What should the cat do?", null);
		page.addAnnotation(new Annotation("lala ", "This page sucks!!!"));

		Page ridePage = new Page(
				"ridePage",
				"run riding the bicycle, a cat jumps out in the middle of the road. Now what should it do?",
				null);
		ridePage.addAnnotation(new Annotation("Minhal Syed ",
				"I love this Page!!"));

		//Page goAroundPlay = new Page("", "go around it, then u died.", null);

		// goEatPage.addOption(new Option("LiveHapilyEverAfter", goPlay));

		// page.addOption(new Option("Eat", goEatPage));

		Page suvrvivePage = new Page("survivePage",
				"you survived, but police find u so u end up in the jail. :( ",
				null);
		suvrvivePage.addAnnotation(new Annotation("Justin", "Thats Sad!!!"));

		// page.addOption(new Option("Just Die", goDiePage));

		story.addPage(page);
		story.addPage(ridePage);
		// r.addPage(goEatPage);
		story.addPage(suvrvivePage);
	}

	/**
	 * Test that the insertStory() method inserts the story to the online 
	 * database.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testInsertStory() throws IOException {
		esClient.insertStory(story);

		assertEquals(story.getTitle(), esClient.getStory("CatStory-lala").getTitle());
	}

	/**
	 * Test that the getStory() method gets the correct story.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testGetStory() throws IOException {
		esClient.insertStory(story);
		
		assertEquals(story.getTitle(), esClient.getStory("CatStory-lala").getTitle());
		assertEquals(story.getDescription(), esClient.getStory("CatStory-lala").getDescription());
		assertEquals(story.getAuthor(), esClient.getStory("CatStory-lala").getAuthor());
		assertEquals(story.getDate(), esClient.getStory("CatStory-lala").getDate());
		assertEquals(story.getImageByte(), esClient.getStory("CatStory-lala").getImageByte());
	}

	/**
	 * Test that the deleteStory() method deletes the story from the online 
	 * database.
	 */
	@Test
	public void testdeleteStory() {
		esClient.deleteStory(story);
		assertEquals(esClient.getStory("CatStory-lala"), null);
	}

	/**
	 * Test that the searchStories() method filters out the correct stories
	 * from the list given the keyword,
	 */
	@Test
	public void testSearchStories() {
		String keyword = "cat";
		ArrayList<Story> library = esClient.searchStories(keyword);
		Story s1 = library.get(0); // There should only be one story
		assertEquals(s1.getTitle(), story.getTitle());
	}
}
