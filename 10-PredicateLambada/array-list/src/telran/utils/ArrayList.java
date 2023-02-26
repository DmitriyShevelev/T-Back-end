package telran.utils;

import java.util.Arrays;
import java.util.function.Predicate;

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
		if (size >= array.length) {
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
		if (isValidIndex(index)) {
			res = array[index];
		}
		return res;
	}

	@Override
	public boolean remove(int index) {
		boolean res = false;
		if (isValidIndex(index)) {
			size--;
			System.arraycopy(array, index + 1, array, index, size - index);
			array[size] = null;
			res = true;
		}

		return res;
	}

	private boolean isValidIndex(int index) {
		return index >= 0 && index < size;
	}

	@Override
	public int size() {

		return size;
	}

	

	

	@Override
	public boolean remove(T pattern) {

		return remove(indexOf(pattern));
	}

	@Override
	public void addAll(List<T> objects) {
		int size = objects.size();
		for (int i = 0; i < size; i++) {
			add(objects.get(i));

		}
		


	}

	

	private void clean(int sizeBefore) {
		for (int i = size; i < sizeBefore; i++) {
			array[i] = null;
		}
		
	}

	private boolean removing(List<T> patterns, boolean isRetain) {
		int sizeBeforeRemoving = size;
		int currentIndex = 0;
		for(int i = 0; i < sizeBeforeRemoving; i++) {
			T current = array[i];
			if (conditionRemoving(patterns, current, isRetain)) {
				size--;
			} else {
				array[currentIndex++] = array[i];
			}
		}
		boolean res = sizeBeforeRemoving > size;
		if (res) {
			clean(sizeBeforeRemoving);
		}
		return res;
		
	}

	private boolean conditionRemoving(List<T> patterns, T current,
			boolean isRetain) {
		boolean res = patterns.indexOf(current) >= 0;
		return isRetain ? !res : res;
	}

	@Override
	public boolean retainAll(List<T> patterns) {
		boolean isRetain = true;
		return this == patterns ? false : removing(patterns, isRetain);
	}

	@Override
	public T set(T object, int index) {
		T res = null;
		if (isValidIndex(index)) {
			res = array[index];
			array[index] = object;
		}
		return res;
	}

	@Override
	public boolean swap(int index1, int index2) {
		boolean res = false;
		if (isValidIndex(index1) && isValidIndex(index2) && index1 != index2) {
			T tmp = array[index1];
			array[index1] = array[index2];
			array[index2] = tmp;
			res = true;
		}
		return res;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		int index = 0;
		while(index < size && !predicate.test(array[index])) {
			index++;
		}
		return index < size ? index : -1;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}

	

}
