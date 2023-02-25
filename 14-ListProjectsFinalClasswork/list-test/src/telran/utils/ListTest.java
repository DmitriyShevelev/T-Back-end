package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {
private static final int N_PERSONS = 1000;
List<Integer> listInt;
List<String> listString;
List<Person> listPersons;
Person p1 = new Person(1, "Moshe");
Person p2 = new Person(2, "Alex");
	@BeforeEach
	void setUp() throws Exception {
//		listInt =  new ArrayList<>(1);
//		listString = new ArrayList<>();
		listInt =  new LinkedList<>();
		listString = new LinkedList<>();
		listInt.add(1);
		listInt.add(2);
		listInt.add(3);
		listInt.add(4);
		listInt.add(5);
		
	}
	

	@Test
	void addTest() {
		initialArrayTest();
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

		Person prs1 = new Person(0, "Moshe");
		Person pattern = new Person(0, null);//equals per only Person Id
		List<Person> persons = new LinkedList<>();
		persons.add(prs1);
		assertEquals(0, persons.indexOf(pattern));
		
	}
	@Test 
	void lastIndexOfTest() {
		listInt.add(2);
		assertEquals(listInt.indexOf(1), listInt.lastIndexOf(1));
		assertEquals(listInt.indexOf(-1), listInt.lastIndexOf(-1));
		assertNotEquals(listInt.indexOf(2), listInt.lastIndexOf(2));
		assertEquals(listInt.size() - 1, listInt.lastIndexOf(2));
		
	}
	@Test
	void removePatternTest() {
		assertTrue(listInt.remove((Integer)2));
		assertEquals(-1, listInt.indexOf(2));
		assertFalse(listInt.remove((Integer)2));
	}
	@Test
	void addAllTest() {
		int sizeOld = listInt.size();
		listInt.addAll(listInt);
		
		assertEquals(sizeOld * 2, listInt.size());
		for (int i = 0; i< sizeOld; i++) {
			assertNotEquals(listInt.indexOf(listInt.get(i)),
					listInt.lastIndexOf(listInt.get(i)));
		}
		int count = 0;
		for(int num: listInt) {
			count++;
		}
		assertEquals(listInt.size(), count);
		
	}
	@Test
	void removeAll() throws Exception {
		listInt.removeAll(listInt);
		assertEquals(0, listInt.size());
		setUp();
		listInt.add(2);
		List<Integer> patterns = new ArrayList<>();
		patterns.add(2);
		listInt.removeAll(patterns);
		assertEquals(-1, listInt.indexOf(2));
		assertEquals(4, listInt.size());
		
	}
	@Test
	void retainAll() {
		listInt.retainAll(listInt);
		assertEquals(5, listInt.size());
		initialArrayTest();
		List<Integer> patterns = new LinkedList<>();
		patterns.add(2);
		listInt.add(2);
		listInt.retainAll(patterns);
		assertEquals(0, listInt.indexOf(2));
		assertEquals(1, listInt.lastIndexOf(2));
		assertEquals(2, listInt.size());
		listInt.add(0);
		
	}
	@Test
	void setTest() {
		assertEquals(2, listInt.set(20, 1));
		assertEquals(20, listInt.get(1));
		assertNull(listInt.set(20, -10));
		assertNull(listInt.set(20, 10));
		assertEquals(-1, listInt.indexOf(2));
	}
	@Test
	void swapTest() {
		assertTrue(listInt.swap(0, 1));
		assertEquals(0, listInt.indexOf(2));
		assertEquals(1, listInt.indexOf(1));
		assertFalse(listInt.swap(-10, 1));
		assertFalse(listInt.swap(1, 10));
	}
	@Test
	void maxTest() {
		
		setUpPersons();
		assertEquals(p2, List.max(listPersons));
		assertEquals(p1, List.max(listPersons, new NamesComparator()));
		assertEquals(5, List.max(listInt));
		
	}
	@Test
	void minTest() {
		
		setUpPersons();
		assertEquals(p1, List.min(listPersons));
		assertEquals(p2, List.min(listPersons, new NamesComparator()));
		assertEquals(1, List.min(listInt));
	}


	private void setUpPersons() {
		//listPersons = new LinkedList<>();
		listPersons = new ArrayList<>();
		listPersons.add(p1);
		listPersons.add(p2);
	}
	@Test
	void sortTest() {
		setUpPersons();
		//listPersons.sort(new NamesComparator());
		listPersons.sort((p1, p2) -> p1.getName().compareTo(p2.getName()) );
		assertEquals(p2, listPersons.get(0));
		assertEquals(p1, listPersons.get(1));
		listPersons.sort();
		assertEquals(p1, listPersons.get(0));
		assertEquals(p2, listPersons.get(1));
		fillRandomPersons(N_PERSONS);
		listPersons.sort();
		sortTestIterator(listPersons);
		
	}


	@SuppressWarnings("unchecked")
	private <T> void sortTestIterator(List<T> list) {

		if (list.size() >= 2) {
			Iterator<T> it1 = list.iterator();
			Iterator<T> it2 = list.iterator();
			it2.next();
			while(it2.hasNext()) {
				assertTrue(((Comparable<T>)it1.next())
						.compareTo(it2.next()) <= 0);
			}
		}
		 
	}


	private  void testArraySorted(Person[] array) {
		for (int i = 1; i < array.length; i++) {
			assertTrue(array[i - 1].compareTo(array[i]) <= 0);
		}
		
	}


	private <T>void fillArrayFromList(List<T> list, T[] array) {
		int index = 0;
		for(T obj: list) {
			array[index++] = obj;
		}
		
	}


	private void fillRandomPersons(int nPersons) {
		for (int i = 0;  i < nPersons; i++) {
			listPersons.add(new Person((int) (Math.random() * Integer.MAX_VALUE),
					"name" + Math.random()));
		}
		
	}
	@Test
	void indexOfPredicateTest() {
		int q =  2;
		Predicate<Integer> pred = n -> n % q == 0;
		assertEquals(1, listInt.indexOf(pred));
		assertEquals(-1, listInt.indexOf(n -> n % 10 == 0));
	}
	@Test
	void lastIndexOfPredicateTest() {
		assertEquals(3, listInt.lastIndexOf(new DividerNumbersPredicate(2)));
		assertEquals(-1, listInt.lastIndexOf(new DividerNumbersPredicate(10)));
	}
	
	@Test
	void removeIf() throws Exception {
		listInt.removeIf(n -> true);
		assertEquals(0, listInt.size());
		setUp();
		assertFalse(listInt.removeIf(n -> n > 100));
		initialArrayTest();
		assertTrue(listInt.removeIf(n -> n % 2 == 0));
		assertEquals(3, listInt.size());
		assertFalse(listInt.remove((Integer)2));
		assertFalse(listInt.remove((Integer)4));
		
		
	}
	
	
	@Test
	void cleanTest() {
		listInt.clean();
		assertEquals(0, listInt.size());
	}
	 @Test
	    void removeRepeated() {
		 for(int i = 0; i < 100; i++) {
			 addSameInReverse();
		 }
	        
	        listInt.removeRepeated();
	        assertEquals(5, listInt.size());
	        initialArrayTest();

	        
	    }


	private void addSameInReverse() {
		listInt.add(5);
		listInt.add(4);
		listInt.add(3);
		listInt.add(2);
		listInt.add(1);
	}


	private void initialArrayTest() {
		int size = listInt.size();
		for (int i = 0; i < size; i++) {
		    assertEquals(i + 1, listInt.get(i));
		}
	}



	

}
