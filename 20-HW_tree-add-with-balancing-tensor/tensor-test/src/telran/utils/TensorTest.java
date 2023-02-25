package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class TensorTest {
private static final int D1 = 5;
private static final int D2 = 3;
private static final int D3 = 6;
private static final int D4 = 10;
private static final int D5 = 2;
private static final int N_NUMBERS=D1*D2*D3*D4*D5;
private static final Integer VALUE = 10;
Tensor<Integer> tensor;
int dimensions[]= {D1,D2,D3,D4,D5};
	@BeforeEach
	void setUp() throws Exception {
		tensor=new Tensor<Integer>(dimensions, VALUE);
	}

	@Test
	void forEach() {
		Integer values[]=new Integer[N_NUMBERS];
		final int []index = {0};
		
		tensor.forEach(v -> values[index[0]++] = v);
		
		for(Integer num: values) {
			assertEquals(VALUE, num);
		}
	}
	@Test
	void flatMap() {
		int ds1 = 5, ds2 = 100;
		Tensor<String> mdArray = new Tensor<>(new int[] {ds1, ds2}, "");
		for (int i = 0; i < ds1; i++) {
			for (int j = 0; j < ds2; j++) {
				mdArray.setValue(new int[] {i, j}, "Hello" + ((i * ds2) + j));
			}
		}
		String[] actual = new String[ds1 * ds2];
		mdArray.flatMap(actual);
		assertEquals(ds1 * ds2, actual.length);
		for (int i = 0; i < actual.length; i++) {
			assertEquals("Hello" + i, actual[i]);
		}
	}
	@Test
	void setValue() {
		Integer first=1;
		tensor.setValue(new int[] {0,0,0,0,0},first);
		Integer last=2;
		tensor.setValue
		(new int[] {D1-1,D2-1,D3-1,D4-1,D5-1}, last);
		Integer[] array = new Integer[N_NUMBERS];
				tensor.flatMap(array);
		
		//first element of the list should be 1
		assertEquals(first,array[0]);
		//last element of the list should be 2
		assertEquals(last, array[N_NUMBERS - 1]);
		
	}
	@Test
	void getValue() {
		
		int indexes[]=getRandomIndexes();
		assertEquals(VALUE,tensor.getValue(indexes));
	}

	private int[] getRandomIndexes() {
		Random gen= new Random();
		int[]res=new int[dimensions.length];
		for(int i=0;i<res.length;i++) {
			res[i]=gen.nextInt(dimensions[i]);
		}
		return res;
	}
	
}
