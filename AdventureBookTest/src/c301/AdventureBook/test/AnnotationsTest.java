package c301.AdventureBook.test;

import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;
import c301.AdventureBook.Models.Annotations;

/**
 * JUnit test case for the Annotations model
 * 
 * @author ltong2
 *
 */
public class AnnotationsTest extends AndroidTestCase
{
	private Annotations annotations;
	
	@Before
	public void setUp() throws Exception{
		annotations = new Annotations(null,null,0);
	}
	
	@Test
	public void test()
	{
		assertEquals(null,annotations.getAuthor());
		assertEquals(null,annotations.getDescription());
		annotations.setAuthor("jay");
		annotations.setDescription("what's up");
		assertEquals("jay",annotations.getAuthor());
		assertEquals("what's up",annotations.getDescription());
	}

}
