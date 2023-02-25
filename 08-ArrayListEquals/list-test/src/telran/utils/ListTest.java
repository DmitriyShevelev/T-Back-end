package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {
List<Integer> listInt;
List<String> listString;
	@BeforeEach
	void setUp() throws Exception {
		listInt =  new ArrayList<>(1);
		listString = new ArrayList<>();
		listInt.add(1);
		listInt.add(2);
		listInt.add(3);
		listInt.add(4);
		listInt.add(5);
		
	}
	

	@Test
	void addTest() {
		listInt.add(6);
		assertEquals(6, listInt.get(5));
	}
	@Test
	void addIndexTest() {
		assertTrue(listInt.add(100, 0));
		assertEquals(100, listInt.get(0));
		assertEquals(1, listInt.get(1));
		assertTrue(listInt.add(200, listInt.size()));
		assertEquals(200, listInt.get(listInt.size() - 1));
		assertTrue(listInt.add(300, 1));
		assertEquals(300, listInt.get(1));
		assertEquals(1, listInt.get(2));
		assertFalse(listInt.add(400, -1));
		assertFalse(listInt.add(400, 100));
		assertEquals(8,listInt.size());
	}
	@Test
	void removeTest() {
		assertTrue(listInt.remove(0));
		assertEquals(2, listInt.get(0));
		assertTrue(listInt.remove(1));
		assertEquals(4, listInt.get(1));
		assertFalse(listInt.remove(-1));
		assertFalse(listInt.remove(listInt.size()));
		assertEquals(3, listInt.size());
		assertTrue(listInt.remove(listInt.size() - 1));
	}
	@Test
	void getTest() {
		assertEquals(1, listInt.get(0));
		assertNull(listInt.get(-1));
		assertNull(listInt.get(100));
	}
	@Test
	void sizeTest() {
		assertEquals(5, listInt.size());
	}
	@Test
	void indexOfTest() {
		assertEquals(0, listInt.indexOf(1));
		assertEquals(2, listInt.indexOf(3));
		assertEquals(3, listInt.indexOf(4));
		assertEquals(-1, listInt.indexOf(100));
		listInt.add(500,2);
		assertEquals(2, listInt.indexOf(500));
//		Integer a =50;
//		Integer b = 50;
//		String hello = "Hello";
//		String hello1 = new String("Hello");
//		assertTrue(hello.equals(hello1));
		Person prs1 = new Person(0, "Moshe");
		Person pattern = new Person(0, null);
		List<Person> persons = new ArrayList<>();
		persons.add(prs1);
		assertEquals(0, persons.indexOf(pattern));
		
	}

}
