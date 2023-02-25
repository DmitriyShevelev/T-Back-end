package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreeNonLinearTest {

	
	private TreeSet<Integer> setUpNonBalanced() {
		TreeSet<Integer> res = new TreeSet<>();
		for (int i = 0; i <= 6; i++) {
			res.add(i);
		}
		return res;
	}
	private TreeSet<Integer> setUpBalanced() {
		TreeSet<Integer> res = new TreeSet<>();
		res.add(3);
		res.add(5);
		res.add(4);
		res.add(6);
		res.add(1);
		res.add(0);
		res.add(2);
		return res;
	}
	@Test
	void heightTest() {
		TreeSet<Integer> tree = setUpNonBalanced();
		assertEquals(7, tree.height());
		tree = setUpBalanced();
		assertEquals(3, tree.height());
		
	}
	@Test
	void widthTest() {
		TreeSet<Integer> tree = setUpNonBalanced();
		assertEquals(1, tree.width());
		tree = setUpBalanced();
		assertEquals(4, tree.width());
		
	}
	@Test
	void balanceTest() {
		TreeSet<Integer> tree = setUpNonBalanced();
		tree.balance();
		assertEquals(4, tree.width());
		assertEquals(3, tree.height());
		
	}

}
