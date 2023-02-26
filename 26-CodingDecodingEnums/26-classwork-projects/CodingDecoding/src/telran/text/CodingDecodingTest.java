package telran.text;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CodingDecodingTest {

	@Test
	void decodeTest() {
		var max = 126 - 32;
		for (var i = 2; i <= max; i++) {
			var decoder = new CodingDecoding(i);
			int num1 = 227384723;
			int num2 = 12345;
			int num3 = 0;
			int num4 = Integer.MAX_VALUE;
			String key = getKey(decoder);
			assertEquals(num1, decoder.decode(decoder.encode(num1),key));
			assertEquals(num2, decoder.decode(decoder.encode(num2),key));
			assertEquals(num3, decoder.decode(decoder.encode(num3), key));
			assertEquals(num4, decoder.decode(decoder.encode(num4), key));
		}
	}
	private String getKey(CodingDecoding decoder) {
		String symbol;
		String res = "";
		int i = 0;
		while (true) {
			symbol = decoder.encode(i++);
			if(symbol.length() > 1) {
				break;
			}
			res += symbol;
		}
		return res;
	}
	@Test
	void printCipher() {
		CodingDecoding cd = new CodingDecoding(90);
		int num = 123;
		System.out.printf("code of %d is %s\n", num, cd.encode(num));
		String key = getKey(cd);
		System.out.println("key " + key);
		assertEquals(90, key.length());
	}
}
