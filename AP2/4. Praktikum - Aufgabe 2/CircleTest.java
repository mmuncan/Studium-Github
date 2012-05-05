import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test für die Klasse Circle.
 */
public class CircleTest extends AbstractShapeTest {

	@Before
	public void setUp() {
		s1 = new Circle( "a", 10.5 );
		s2 = new Circle( "b", 11.0 );
		s3 = new Circle( "c", 10.5 );
	}

	@Test
	public void testArea() {
		double area13 = Math.PI * 10.5 * 10.5;
		double area2 = Math.PI * 11.0 * 11.0;

		assertEquals( area13, s1.getArea(), 1e-7 );
		assertEquals( area2, s2.getArea(), 1e-7 );
		assertEquals( area13, s3.getArea(), 1e-7 );
	}

	protected String prefix() {
		return "Circle.";
	}
}
