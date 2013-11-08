package c301.AdventureBook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import c301.AdventureBook.Models.Annotation;
import c301.AdventureBook.Models.Annotations;


public class AnnotationTest
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
