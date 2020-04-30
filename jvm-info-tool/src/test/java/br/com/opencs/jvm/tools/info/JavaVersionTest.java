package br.com.opencs.jvm.tools.info;

import static org.junit.Assert.*;

import org.junit.Test;

public class JavaVersionTest {

	@Test
	public void testJavaVersionIntIntIntString() {
		JavaVersion v = new JavaVersion(1, 2, 3, "-4");
		
		assertEquals(1, v.getMajor());
		assertEquals(2, v.getMinor());
		assertEquals(3, v.getRevision());
		assertEquals("-4", v.getIdentifier());
		
		v = new JavaVersion(1, 2, 3, null);		
		assertEquals(1, v.getMajor());
		assertEquals(2, v.getMinor());
		assertEquals(3, v.getRevision());
		assertEquals("", v.getIdentifier());
	}

	@Test
	public void testJavaVersionString() {
		JavaVersion v = new JavaVersion("1.2.3-4");
		
		assertEquals(1, v.getMajor());
		assertEquals(2, v.getMinor());
		assertEquals(3, v.getRevision());
		assertEquals("-4", v.getIdentifier());
		
		v = new JavaVersion("1.2.3");		
		assertEquals(1, v.getMajor());
		assertEquals(2, v.getMinor());
		assertEquals(3, v.getRevision());
		assertEquals("", v.getIdentifier());
	}

	@Test
	public void testToString() {
		JavaVersion v = new JavaVersion(1, 2, 3, "-4");
		assertEquals("1.2.3-4", v.toString());
		
		v = new JavaVersion(1, 2, 3, null);		
		assertEquals("1.2.3", v.toString());
	}

	@Test
	public void testCompareTo() {
		JavaVersion v1 = new JavaVersion(1, 2, 3, "-4");
		JavaVersion v2 = new JavaVersion(1, 2, 3, "-4");
		
		assertEquals(0, v1.compareTo(v1));
		assertEquals(0, v1.compareTo(v2));
		assertEquals(0, v2.compareTo(v1));
		
		JavaVersion v3 = new JavaVersion(2, 2, 3, "-4");
		assertTrue(v1.compareTo(v3) < 0);
		assertTrue(v3.compareTo(v1) > 0);

		v3 = new JavaVersion(1, 3, 3, "-4");
		assertTrue(v1.compareTo(v3) < 0);
		assertTrue(v3.compareTo(v1) > 0);
		
		v3 = new JavaVersion(1, 2, 4, "-4");
		assertTrue(v1.compareTo(v3) < 0);
		assertTrue(v3.compareTo(v1) > 0);
		
		v3 = new JavaVersion(1, 2, 3, "-5");
		assertTrue(v1.compareTo(v3) < 0);
		assertTrue(v3.compareTo(v1) > 0);

		v3 = new JavaVersion(1, 2, 3, null);
		assertTrue(v1.compareTo(v3) > 0);
		assertTrue(v3.compareTo(v1) < 0);
	}

	@Test
	public void testEqualsObject() {
		JavaVersion v1 = new JavaVersion(1, 2, 3, "-4");
		JavaVersion v2 = new JavaVersion(1, 2, 3, "-4");
		
		assertTrue(v1.equals(v1));
		assertTrue(v1.equals(v2));
		assertTrue(v2.equals(v1));
		
		JavaVersion v3 = new JavaVersion(2, 2, 3, "-4");
		assertFalse(v1.equals(v3));
		assertFalse(v3.equals(v1));

		v3 = new JavaVersion(1, 3, 3, "-4");
		assertFalse(v1.equals(v3));
		assertFalse(v3.equals(v1));

		v3 = new JavaVersion(1, 2, 4, "-4");
		assertFalse(v1.equals(v3));
		assertFalse(v3.equals(v1));

		v3 = new JavaVersion(1, 2, 3, "-5");
		assertFalse(v1.equals(v3));
		assertFalse(v3.equals(v1));

		v3 = new JavaVersion(1, 2, 3, null);
		assertFalse(v1.equals(v3));
		assertFalse(v3.equals(v1));
	}

	@Test
	public void testHashCode() {
		JavaVersion v1 = new JavaVersion(1, 2, 3, "-4");
		JavaVersion v2 = new JavaVersion(1, 2, 3, "-4");
		
		assertEquals(v1.hashCode(), v2.hashCode());

		JavaVersion v3 = new JavaVersion(2, 2, 3, "-4");
		assertNotEquals(v1.hashCode(), v3.hashCode());

		v3 = new JavaVersion(1, 3, 3, "-4");
		assertNotEquals(v1.hashCode(), v3.hashCode());

		v3 = new JavaVersion(1, 2, 4, "-4");
		assertNotEquals(v1.hashCode(), v3.hashCode());

		v3 = new JavaVersion(1, 2, 3, "-5");
		assertNotEquals(v1.hashCode(), v3.hashCode());

		v3 = new JavaVersion(1, 2, 3, null);
		assertNotEquals(v1.hashCode(), v3.hashCode());
	}
}
