import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyArrayTest {
private static final int LENGTH = 1000;
private static final int VALUE = 10;
private static final int OTHER_VALUE = 5;
MyArray array;
	@BeforeEach
	void setUp() throws Exception {
		array = MyArray.getArray(LENGTH);
		array.setAllValues(VALUE);
	}

	@Test
	void testSetAllValues() {
		for (int i = 0; i < LENGTH; i++) {
			assertEquals(VALUE, array.get(i));
		}
	}

	@Test
	void testSetGet() {
		array.set(0, OTHER_VALUE);
		array.set(LENGTH - 1, OTHER_VALUE);
		int limit = LENGTH - 1;
		for(int i = 1; i < limit; i++) {
			assertEquals(VALUE, array.get(i));
		}
		assertEquals(OTHER_VALUE, array.get(0));
		assertEquals(OTHER_VALUE, array.get(limit));
		array.setAllValues(OTHER_VALUE);
		for(int i = 0; i < LENGTH; i++) {
			assertEquals(OTHER_VALUE, array.get(i));
		}
		
	}

	

	@Test
	void testLength() {
		assertEquals(LENGTH, array.length());
	}
	@Test
	void wrongIndex() {
		try {
			array.set(LENGTH, OTHER_VALUE);
			fail("IndexOutOfBoundsException is required");
		} catch (IndexOutOfBoundsException e) {
			
		}
		try {
			array.get(LENGTH);
			fail("IndexOutOfBoundsException is required");
		} catch (IndexOutOfBoundsException e) {
			
		}
		
	}

}
