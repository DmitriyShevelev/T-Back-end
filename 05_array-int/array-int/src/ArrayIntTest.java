import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ArrayIntTest {

	private static final int N_NUMBERS = 1_000_000;

	@Test
	void sortTest() {
		short ar[] = getShortArray(N_NUMBERS);
		Arrays.sort(ar);
		int lim = N_NUMBERS - 1;
		for (int i = 0; i < lim; i++) {
			assertTrue(ar[i] <= ar[i + 1]);
		}
	}

	private short[] getShortArray(int nNumbers) {
		short[] res = new short[nNumbers];
		for(int i = 0; i < nNumbers; i++) {
			res[i] = (short) (Math.random() * Short.MAX_VALUE);
		}
		return res;
	}

}
