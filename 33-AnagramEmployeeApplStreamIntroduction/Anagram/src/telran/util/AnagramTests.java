package telran.util;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import static telran.util.Anagram.*;
class AnagramTests {

	String str = "hello";

	@Test
	void AnagramTrueTests() {
		assertTrue(isAnagram(str, "loleh"));
		assertTrue(isAnagram(str, "elloh"));
		assertTrue(isAnagram(str, "eholl"));
		assertTrue(isAnagram(str, "holle"));
	}
	@Test
	void AnagramFalseTests() {
	
		assertFalse(isAnagram(str, "elloe"));
		assertFalse(isAnagram(str, "ehol"));
		assertFalse(isAnagram(str, "holleo"));
		assertFalse(isAnagram(str, "helod"));
		assertFalse(isAnagram(str, "helli"));
		
	}

}
