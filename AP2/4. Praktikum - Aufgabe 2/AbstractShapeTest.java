import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Modellklasse für das Testen von Shape-Klassen.
 * <code>setUp</code>, <code>testArea</code> und <code>prefix</code>
 * müssen in der Unterklasse beschrieben werden.
 *
 * @author Dominik Schilling
 */
public abstract class AbstractShapeTest {
	protected IShape s1;
	protected IShape s2;
	protected IShape s3;

	@Before
	public abstract void setUp();

	@Test
	public abstract void testArea();

	@Test
	public void testName() {
		assertEquals( "a", s1.getName() );
		assertEquals( "b", s2.getName() );
		assertEquals( "c", s3.getName() );
	}

	@Test
	public void testCompare() {
		assertTrue( s1.compareTo(s3) == 0 );
		assertTrue( s3.compareTo(s1) == 0 );
		assertTrue( s1.compareTo(s2) < 0 );
		assertTrue( s2.compareTo(s1) > 0 );
		assertTrue( s3.compareTo(s2) < 0 );
		assertTrue( s2.compareTo(s3) > 0 );
	}

	@Test
	public void testEquals() {
		assertFalse( s1.equals(s2) );
		assertFalse( s1.equals(s3) );
		assertFalse( s3.equals(s2) );
	}

	@Test
	public void testToString() {
		String p = prefix();
		assertEquals( p + "a", s1.toString() );
		assertEquals( p + "b", s2.toString() );
		assertEquals( p + "c", s3.toString() );
	}

	protected abstract String prefix();
}
