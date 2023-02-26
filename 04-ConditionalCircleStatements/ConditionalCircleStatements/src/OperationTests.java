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
	@Test
	void getBitTest () {
		assertEquals(0, Operations.getBit(5, 1)); //5 - 101 , 1-th bit = 0
		assertEquals(1, Operations.getBit(6, 1)); //6 - 110 , 1-th bit = 1
		assertEquals(1, Operations.getBit(7, 0)); //7 - 111, 0-th bit = 1
	}
	@Test
	void setBitTest() {
		assertEquals(7, Operations.setBit(5, 1));//5 - 101, if 1-th bit is set 1 there will be 7 (111)
		assertEquals(7, Operations.setBit(6, 0));//6 - 110, if 0-th bit is set 1 there will be 7 (111)
		assertEquals(10, Operations.setBit(8, 1));//8 - 1000, if if 1-th bit is set 1 there will be 10 (1010)
	}
	@Test
	void resetBitTest() {
		assertEquals(5, Operations.resetBit(7, 1));//7 - 111, if 1-th bit is reset 0 there will be 5 (101)
		assertEquals(6, Operations.resetBit(7, 0));//7 - 111, if 0-th bit is reset 0 there will be 6 (110)
		assertEquals(8, Operations.resetBit(10, 1));//10 - 1010, if if 1-th bit is reset 0 there will be 8 (1000)
	}
	@Test
	void swapTest() {
		
		int a = 10;
		int b = 20;
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		assertEquals(20, a);
		assertEquals(10, b);
	}
	@Test
	void getNumberSetBitsTest () {
		int a = -1;
		assertEquals(32, Operations.getNumberSetBits(a));
		assertEquals(3, Operations.getNumberSetBits(7));
	}
	@Test
	void getMaxLong() {
		long maxExpected = -1L >>> 1;
		assertEquals(maxExpected, Operations.getMaxLong());
	}

}
