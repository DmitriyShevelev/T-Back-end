package telran.utils;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class GeneratorRulesTests {
DividerRule divider10 = new DividerRule(10);
int min = 1, max = 10000, nNumbers = 1000000;
	@Test
	void testGenerate() {
		
		Generator generator = null;
		generator = new Generator();
		generator.setRule(divider10);
		int ar[] = generator.generate(nNumbers, min, max);
		assertEquals(nNumbers, ar.length);
		for(int num: ar) {
			assertTrue(num % 10 == 0 && num >= min && num <= max);
		}
		try {
			generator = new Generator();
			
			generator.setRule(divider10);
			generator.generate(nNumbers, max, min);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			
		}
	}
	@Test
	void testDivider()  {
		try {
			divider10.checkRule(10, min, max);
		} catch(Exception e) {
			fail("Unexpected Exception");
		}
		ruleException(12, min, max, -2);
		ruleException(12, 11, max, 8);
		ruleException(18, min, max, 2);
		
		
		try {
			divider10.checkRule(12, 11, 19);
			fail("Expected RangeException");
		}catch (IllegalArgumentException e) {
			
		} catch (NoRuleMatchException e) {
			fail("Unexpected Exception");
		}
	}
	private void ruleException(int number, int min, int max, int exp) {
		try {
			divider10.checkRule(number, min, max);
			fail("Expected RuleException");
		}catch (NoRuleMatchException e) {
			assertEquals(exp, e.getDelta());
		}
	}

}
