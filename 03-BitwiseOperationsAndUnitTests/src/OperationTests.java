import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OperationTests {

	@Test
	void test() {
		byte a, b = 10, c = 20;
		a = (byte)((int)b + c);
		
		assertEquals(30, a);
		assertEquals(4, 5 & 6);
		assertEquals(0, 1 & 4);
		assertEquals(7, 5 | 6);
		assertEquals(6, 5 ^ 3);
		assertEquals(5, 6 ^ 3);
		assertEquals(0, 25467 ^ 25467);
		assertEquals(-16, ~15);
		assertEquals (-1, 25467 ^ ~25467);
		assertEquals (128, 1 << 7);
	}

}
