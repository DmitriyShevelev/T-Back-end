import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExpressionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		Operation sum = (v1, v2) -> {
			return  Double.valueOf("" + v1) + Double.valueOf("" + v2);
		};
		Operation and = (v1, v2) -> {
			return (Boolean)v1 && (Boolean)v2;
		};
		assertTrue((Boolean)and.compute(true, true));
		assertEquals(5.0, sum.compute(2.0 , 3.0));
		Object numberValue = sum.compute(2, 3.0);
		Operand numberOperand = () -> (Double)numberValue;
		assertEquals(5.0, numberOperand.getValue());
	}

}
