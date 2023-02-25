package telran.utils;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
private static final int DEFAULT_CAPACITY = 16;
private T array[];
int size = 0;

public ArrayList(int capacity) {
	array = (T[]) new Object[capacity];
}
public ArrayList() {
	this(DEFAULT_CAPACITY);
}
	@Override
	public void add(T obj) {
		if (size >= array.length) {
			allocate();
		} 
		array[size++] = obj;
		
	}

	private void allocate() {
		array = Arrays.copyOf(array, array.length * 2);
		
	}
	@Override
	public boolean add(T obj, int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int size() {
		
		return size;
	}

}
