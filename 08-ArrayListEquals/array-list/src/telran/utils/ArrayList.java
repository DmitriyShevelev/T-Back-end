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
		if (index < 0 || index > size) {
			return false;
		}
		if(size >= array.length) {
			allocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
		return true;
	}

	@Override
	public T get(int index) {
		T res = null;
		if (index >= 0 && index < size) {
			res = array[index];
		}
		return res;
	}

	@Override
	public boolean remove(int index) {
		boolean res = false;
		if(index >= 0 && index < size) {
			size--;
			System.arraycopy(array, index+1, array, index, size - index);
			array[size]=null;
			res = true;
		}
		
		return res;
	}
	@Override
	public int size() {
		
		return size;
	}
	@Override
	public int indexOf(T pattern) {
		int index = 0;
		while(index < size && !array[index].equals(pattern)) {
			index++;
		}
		return index < size ? index : -1;
	}
	@Override
	public int lastIndexOf(T pattern) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean remove(T pattern) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void addAll(List<T> objects) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean removeAll(List<T> patterns) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(List<T> patterns) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public T set(T object, int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean swap(int index1, int index2) {
		// TODO Auto-generated method stub
		return false;
	}

}
