package c301.AdventureBook.test;

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
	
	@Before
	public void setUp() throws Exception{
		annotation = new Annotation(null,null);
	}
	
	@Test
	public void test()
	{
		assertEquals(null,annotation.getComment() );
		assertEquals(null,annotation.getAuthor());
		//assertEquals("",annotations.getDescription );
		annotation.setAuthor("Author");
		annotation.setComment("comment");
		//assertTrue(res);
		assertEquals("Author",annotation.getComment() );
		assertEquals("comment",annotation.getAuthor() );
	}

}
