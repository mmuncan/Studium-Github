import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test für die Klasse Rectangle.
 */
public class RectangleTest extends AbstractShapeTest {

	@Before
	public void setUp() {
		s1 = new Rectangle( "a", 10.0, 9.0 );
		s2 = new Rectangle( "b", 11.0, 10.5 );
		s3 = new Rectangle( "c", 10, 9.0 );
	}

	@Test
	public void testArea() {
		double area13 = 10.0 * 9.0;
		double area2 = 11.0 * 10.5;

		assertEquals( area13, s1.getArea(), 1e-7) ;
		assertEquals( area2, s2.getArea(), 1e-7 );
		assertEquals( area13, s3.getArea(), 1e-7 );
	}

	protected String prefix() {
		return "Rectangle.";
	}
}
