import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LineryRecursionTest {

	@Test
	void pow() {
		assertEquals(1000, LineryRecursion.pow(10, 3));
	}
	@Test
	void sumArray() {
		assertEquals(10, LineryRecursion.sum(new int[] {1,2,3,4}));
	}
	@Test
	void square() {
		assertEquals(25, LineryRecursion.square(5));
		assertEquals(25, LineryRecursion.square(-5));
	}
	@Test
	void isSubstring() {
		assertTrue(LineryRecursion.isSubstring("Hello", "llo"));
		assertTrue(LineryRecursion.isSubstring("Hello", "lo"));
		assertTrue(LineryRecursion.isSubstring("Hello", "el"));
		assertTrue(LineryRecursion.isSubstring("Hello", "Hello"));
		assertFalse(LineryRecursion.isSubstring("Hello", "Helo"));
		assertFalse(LineryRecursion.isSubstring("aba", "aa"));
		
	}

}
