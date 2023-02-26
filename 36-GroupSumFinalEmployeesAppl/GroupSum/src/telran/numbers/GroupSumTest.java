package telran.numbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroupSumTest {
GroupSum groupSum;

int numbers[][] = {
		{1, 2, 3},{1, 2, 3}, {1,2,3}, {10,Integer.MAX_VALUE}
};
	@BeforeEach
	void setUp() throws Exception {
		groupSum = new GroupSumStream(numbers);
	}

	@Test
	void test() {
		assertEquals(18L + Integer.MAX_VALUE + 10 , groupSum.getSum());
		
	}
	@Test
	void bigNumbersTest() {
		
	}

}
