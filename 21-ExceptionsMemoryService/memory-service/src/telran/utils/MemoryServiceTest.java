package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemoryServiceTest {
byte ar[];
	@Test
	void test() {
		
		int maxMemory = MemoryService.getMaxAvailableMemory();
		ar = new byte[maxMemory]; //no exception expected
		ar = null;
		try {
			ar = new byte[maxMemory + 1];
			fail("There should be exception");
		} catch(OutOfMemoryError e) {
			
		}
	}

}
