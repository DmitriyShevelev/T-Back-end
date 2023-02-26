package telran.text.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static telran.text.StringRegExpression.*; 
class StringRegExpressionTest {

	@Test
	void javaVariableTest() {
		//Correct Java Variables
		assertTrue("abc".matches(javaVariable()));
		assertTrue("$abc".matches(javaVariable()));
		assertTrue("__".matches(javaVariable()));
		assertTrue("_1".matches(javaVariable()));
		assertTrue("A$abc".matches(javaVariable()));
		assertTrue("$".matches(javaVariable()));
		//Incorrect Java Variables
		assertFalse("abc*".matches(javaVariable()));
		assertFalse("1abc".matches(javaVariable()));
		assertFalse("1_".matches(javaVariable()));
		assertFalse("a.b".matches(javaVariable()));
		assertFalse("_ _".matches(javaVariable()));
		
	}
	@Test
	void less256Test() {
		//correct strings
		assertTrue("0".matches(less256()));
		assertTrue("00".matches(less256()));
		assertTrue("000".matches(less256()));
		assertTrue("199".matches(less256()));
		assertTrue("99".matches(less256()));
		assertTrue("249".matches(less256()));
		assertTrue("255".matches(less256()));
		assertTrue("1".matches(less256()));
		assertTrue("001".matches(less256()));
		//incorrect strings
		assertFalse("0000".matches(less256()));
		assertFalse("256".matches(less256()));
		assertFalse("1000".matches(less256()));
		assertFalse("abc".matches(less256()));
		assertFalse("a12".matches(less256()));
		assertFalse("".matches(less256()));
		
	}
	@Test
	void ipV4Test() {
		assertTrue("000.000.000.000".matches(ipV4()));
		assertTrue("1.2.3.4".matches(ipV4()));
		assertTrue("255.255.255.255".matches(ipV4()));
		assertFalse("000.000.000.000.".matches(ipV4()));
		assertFalse("000.000.".matches(ipV4()));
		assertFalse("...".matches(ipV4()));
		assertFalse(".100.200.".matches(ipV4()));
		assertFalse("255,255,255,255".matches(ipV4()));
	}
	@Test
	void emailTest() {
		assertTrue("1234@mail.ru".matches(email()));
		assertTrue("#%\\*a_12@tel-ran.co.il".matches(email()));
		assertTrue("#%\\*a_12@telran.co.il".matches(email()));
		
		assertFalse("12,34@mail.ru".matches(email()));
		assertFalse("12.34@mail.ru.ru.ru.ru".matches(email()));
		assertFalse("#%\\*a_12@tel_ran.co.il".matches(email()));
	}
	@Test
	void mobileTest() {
		//Israel code optional +972
		//code operator 050, 051, 052, 053, 054, 055,056,057,058, 059
		//code operator 072 - 077
		//7 digits that may or may not be separated by dash
		assertTrue("+972-541234567".matches(mobileIsraelPhone()));
		assertTrue("0541234567".matches(mobileIsraelPhone()));
		assertTrue("074-1-2345-67".matches(mobileIsraelPhone()));
		assertTrue("+972541234567".matches(mobileIsraelPhone()));
		assertFalse("+972-0541234567".matches(mobileIsraelPhone()));
		assertFalse("+9720541234567".matches(mobileIsraelPhone()));
		assertFalse("972-541234567".matches(mobileIsraelPhone()));
		assertFalse("0641234567".matches(mobileIsraelPhone()));
		assertFalse("+972-54123v567".matches(mobileIsraelPhone()));
		
	}

}
