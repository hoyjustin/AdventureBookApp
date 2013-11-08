package c301.AdventureBook.test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;
import c301.AdventureBook.Models.Annotation;

/**
 * JUnit test case for the Annotation model.
 * 
 * @author ltong2
 *
 */
public class AnnotationTest extends AndroidTestCase
{
	private Annotation annotation;
	private String author = "Some author";
	private String comment = "Some comment";
	private String imagepath1 = "some random imagepath 1";
	
	@Before
	public void setUp() throws Exception{
		annotation = new Annotation(author, comment);
		annotation.addImagePath(imagepath1);
	}
	
	/**
	 * Test the setAuthor and setComment methods to make sure they are able to 
	 * change the author and comment.
	 */
	@Test
	public void testChangeAuthorAndComment()
	{
		String newAuthor = "Author";
		String newComment = "comment";
		annotation.setAuthor(newAuthor);
		annotation.setComment(newComment);
		//assertTrue(res);
		assertEquals(newAuthor,annotation.getAuthor());
		assertEquals(newComment,annotation.getComment());
	}
	
	/**
	 * Test the getAuthor() method to make sure it returns the author name.
	 */
	@Test
	public void testGetAuthor() {
		assertEquals(author, annotation.getAuthor());
	}
	
	/**
	 * Test the getComment() method to make sure it returns the comment.
	 */
	@Test
	public void testGetComment() {
		assertEquals(comment, annotation.getComment());
	}
	
	/**
	 * Test the methods that involve the imagepath.
	 * Checks to see if adding and getting the imagepath from the arraylist of 
	 * imagepaths works.
	 */
	@Test
	public void testImagepath() {
		String imagepath2 = "some imagepath 2";
		
		// add imagepath
		annotation.addImagePath(imagepath2);
		
		// Test get imagepath. Position of imagepath2 should be at 1 because 
		// there are only 2 elements in the arraylist (imagepath1 and imagepath2)
		assertEquals(imagepath2, annotation.getImagePath(1));
	}
	


}
