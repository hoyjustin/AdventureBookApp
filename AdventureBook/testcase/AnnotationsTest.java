package c301.AdventureBook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import c301.AdventureBook.Models.Annotation;
import c301.AdventureBook.Models.Annotations;


public class AnnotationsTest
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
