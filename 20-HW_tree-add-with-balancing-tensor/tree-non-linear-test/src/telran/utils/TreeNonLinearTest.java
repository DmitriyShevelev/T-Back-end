package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TreeNonLinearTest {

	
	private static final int N_PORTIONS = 1;
	private static final int N_NUMBERS = 1_000_000;
	private static final int PORTION = N_NUMBERS / N_PORTIONS;
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
	@Disabled
	void heightTest() {
		TreeSet<Integer> tree = setUpNonBalanced();
		assertEquals(7, tree.height());
		tree = setUpBalanced();
		assertEquals(3, tree.height());
		
		
		
		
	}
	@Test
	@Disabled
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
		int ar[] = new int[7];
		int ind = 0;
		for(int num : tree) {
			ar[ind++] = num;
		}
		int expected[] = {0, 1, 2, 3, 4, 5, 6};
		assertArrayEquals(expected, ar);
		
	}
//	@Test
//	void bulkAdding() {
//		TreeSet<Integer> tree = new TreeSet<>();
//		int value = 0;
//		for(int i = 0; i < N_PORTIONS; i++) {
//			for (int j = 0; j < PORTION; j++) {
//				tree.add(++value);
//			}
//			tree.balance();
//		}
//		assertEquals(N_NUMBERS, tree.size());
//		assertEquals(20, tree.height());
//	}
	@Test
	@Disabled
	void addBalancedSorted() {
		TreeSet<Integer> tree = new TreeSet<>();
		for(int i = 1; i <= N_NUMBERS; i++) {
			tree.add(i);
		}
		System.out.println(tree.height());
		//assertTrue(tree.height() <= 25);
	}
	@Test
	void addBalancedRandom() {
		TreeSet<Integer> tree = new TreeSet<>();
		for(int i = 1; i <= N_NUMBERS; i++) {
			tree.add((int)(Math.random() * Integer.MAX_VALUE));
		}
		System.out.println(tree.height());
		assertTrue(tree.height() < 25);
	}
	

}
