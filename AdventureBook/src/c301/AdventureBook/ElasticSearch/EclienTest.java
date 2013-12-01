/**
 * 
 */
package c301.AdventureBook.ElasticSearch;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;


import c301.AdventureBook.Models.Annotation;
import c301.AdventureBook.Models.Option;
import c301.AdventureBook.Models.Page;
import c301.AdventureBook.Models.Story;


/**
 * @author ltong2
 *
 */
public class EclienTest extends TestCase
{

	private HttpClient httpclient = new DefaultHttpClient();
	Story r; 
	private ESClient Eclient;
	public static final String WEBSERVICE_URI = "http://cmput301.softwareprocess.es:8080/cmput301f13t11/";
	public static final String STORIES_FOLDER = "stories/";
	public static final String SEARCH_PRETTY = "_search?pretty=1&q=";
	public static final int MAX_STORIES = 20;


	protected void setUp() throws Exception
	{
		super.setUp();
		Eclient =new ESClient();
	}

	public void testInsertStory() throws IOException {
		
		r = new Story("CatStory", "This Story is about a Cat", "lala",
				"2013-23-3234", null);
		
		Page page = new Page("FirstPage", "U find a biscycle. What should the cat do?", null);
		page.addAnnotation(new Annotation("lala ", "This page sucks!!!"));

		Page ridePage = new Page("ridePage","run riding the bicycle, a cat jumps out in the middle of the road. Now what should it do?", null);
		ridePage.addAnnotation(new Annotation("Minhal Syed ", "I love this Page!!"));
		
		Page goAroundPlay = new Page("", "go around it, then u died.", null);
		
	//	goEatPage.addOption(new Option("LiveHapilyEverAfter", goPlay));
		
		//page.addOption(new Option("Eat", goEatPage));
		
		Page suvrvivePage = new Page("survivePage", "you survived, but police find u so u end up in the jail. :( ", null);
		suvrvivePage.addAnnotation(new Annotation("Justin", "Thats Sad!!!"));
		
		//page.addOption(new Option("Just Die", goDiePage));

		r.addPage(page);
		r.addPage(ridePage);
//		r.addPage(goEatPage);
	r.addPage(suvrvivePage);
		
		
		try
		{
			Eclient.insertStory(r);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(r,Eclient.getStory("CatStory-lala"));
	}
	
	public void testGetStory(){
		assertEquals(r,Eclient.getStory("CatStory-lala"));
		
	}
	public void testdeleteStory() {
		Eclient.deleteStory(r);
		assertEquals(Eclient.getStory("CatStory-lala"),null);
		
	}
	public void testSearchStories(){
		String keyword ="cat";
		assertEquals(keyword, Eclient.searchStories(keyword));
	}
}
