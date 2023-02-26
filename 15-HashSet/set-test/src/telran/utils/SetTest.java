package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetTest {
Set<Integer> setInt;
Set<String> setString;

Person p1 = new Person(1, "Moshe");
Person p2 = new Person(2, "Alex");
	@BeforeEach
	void setUp() throws Exception {
		setInt =  new HashSet<>(1);
		setString = new HashSet<>();

		setInt.add(1);
		setInt.add(2);
		setInt.add(3);
		setInt.add(4);
		setInt.add(5);
		
	}
	@Test
	void containsTest() {
		for(int i = 1; i < 6; i++) {
			assertTrue(setInt.contains(i));
		}
		assertFalse(setInt.contains(100));
		
	}

	@Test
	void addTest() {
		
		assertTrue(setInt.add(6));
		assertTrue(setInt.contains(6));
		assertFalse(setInt.add(6));
		Set<Person> persons = new HashSet<>();
		persons.add(p1);
		persons.add(p2);
	}
	
	
	@Test
	void sizeTest() {
		assertEquals(5, setInt.size());
	}
	
	@Test
	void removePatternTest() {
		assertTrue(setInt.remove(2));
		assertFalse(setInt.contains(2));
		assertFalse(setInt.remove(2));
	}
	@Test
	void addAllTest() {
		int sizeOld = setInt.size();
		setInt.addAll(setInt);
		
		assertEquals(sizeOld , setInt.size());
		
		
		
		List<Integer> list = new LinkedList<>();
		list.add(10);
		list.add(200);
		list.add(100);
		setInt.addAll(list);
		list.forEach(e -> assertTrue(setInt.contains(e)));
		
	}
	@Test
	void removeAll() throws Exception {
		setInt.removeAll(setInt);
		assertEquals(0, setInt.size());
		setUp();
		
		Set<Integer> patterns = new HashSet<>();
		patterns.add(2);
		patterns.add(3);
		setInt.removeAll(patterns);
		assertFalse(setInt.contains(2));
		assertFalse(setInt.contains(3));
		assertEquals(3, setInt.size());
		
	}
	@Test
	void retainAll() {
		setInt.retainAll(setInt);
		assertEquals(5, setInt.size());
		initialSetTest();
		Set<Integer> patterns = new HashSet<>();
		patterns.add(2);
		patterns.add(3);
		setInt.retainAll(patterns);
		assertTrue(setInt.contains(2));
		assertTrue(setInt.contains(3));
		
		assertEquals(2, setInt.size());
		
		
	}
	


	
		 
	
	@Test
	void removeIf() throws Exception {
		setInt.removeIf(n -> true);
		assertEquals(0, setInt.size());
		setUp();
		assertFalse(setInt.removeIf(n -> n > 100));
		initialSetTest();
		assertTrue(setInt.removeIf(n -> n % 2 == 0));
		assertEquals(3, setInt.size());
		assertFalse(setInt.remove((Integer)2));
		assertFalse(setInt.remove((Integer)4));
		
		
	}
	
	
	@Test
	void cleanTest() {
		setInt.clean();
		assertEquals(0, setInt.size());
	}
	 

	private void initialSetTest() {
		int value = 1;
		for (int num: setInt) {
			assertEquals(num, value++);
		}
		assertEquals(6, value);
	}



	

}
