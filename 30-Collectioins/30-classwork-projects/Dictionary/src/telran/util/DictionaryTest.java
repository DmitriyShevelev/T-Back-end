package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DictionaryTest {
Dictionary dict;
String [] strings = {
		"abc123456", "123456", "abc123", "abc256"
};
	@BeforeEach
	void setUp() throws Exception {
		dict= new DictionaryImpl();
		dict.addStrings(Arrays.asList(strings));
		
	}

	@Test
	void findByPreficsTest() {
		List<String> expecatble = Arrays.asList("abc123", "abc123456", "abc256");
		assertIterableEquals(expecatble, dict.findStringsByPrefix("abc"));
	}

}
