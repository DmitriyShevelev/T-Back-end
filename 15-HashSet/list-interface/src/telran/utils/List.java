package telran.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public interface List<T> extends Iterable<T> {
	/**
	 * 
	 * @return number of elements
	 */
	int size();

	/**
	 * adding obj at the end
	 * 
	 * @param obj
	 */
	void add(T obj);

	/**
	 * 
	 * @param obj
	 * @param index [0, size]
	 * @return true if added, otherwise false (wrong index)
	 */
	boolean add(T obj, int index);

	/**
	 * 
	 * @param index [0, size -1]
	 * @return reference to T object; if a given index is wrong -> null
	 */
	T get(int index);

	/**
	 * removes object at a given index
	 * 
	 * @param index [0, size - 1]
	 * @return true if removed, otherwise false (wrong index)
	 */
	boolean remove(int index);

	/**
	 * 
	 * @param pattern
	 * @return index of first occurrence for an object equaled to the pattern in the
	 *         case no object equaled to the pattern returns -1
	 */
	default int indexOf(T pattern) {
		return indexOf(e -> e.equals(pattern));
	}

	/**
	 * 
	 * @param pattern
	 * @return index of the last occurrence for an object equaled to the pattern in
	 *         the case no object equaled to the pattern, returns -1
	 */
	default int lastIndexOf(T pattern) {
		
		return lastIndexOf(e -> e.equals(pattern));
	}

	/**
	 * removes first occurred object equaled to a given pattern
	 * 
	 * @param pattern
	 * @return true if removed otherwise false (here there is some challenge, try to
	 *         understand it)
	 */
	boolean remove(T pattern);

	/**
	 * adds all objects
	 * 
	 * @param objects
	 */
	
	public default void addAll(List<T> objects) {
		int size = objects.size();
		Iterator<T> it = objects.iterator();
		for (int i = 0; i < size; i++) {
			add(it.next());

		}

	}

	/**
	 * removes all objects equaled to the given patterns
	 * 
	 * @param patterns
	 * @return true if at least one object has been removed
	 */
	default boolean removeAll(List<T> patterns) {
		if (this == patterns) {
			clean();
			return true;
		}
		return removeIf(e -> patterns.contains(e));
	}

	/**
	 * removes all objects not equaled to the given patterns
	 * 
	 * @param patterns
	 * @return true if at least one object has been removed
	 */
	default boolean retainAll(List<T> patterns) {
		if (this == patterns) {
			return false;
		}
		return removeIf(e -> !patterns.contains(e));
	}

	/**
	 * sets new reference to an object at existing index
	 * 
	 * @param object
	 * @param index
	 * @return reference to an old object at the index or null in the case of wrong
	 *         index
	 */
	T set(T object, int index);

	/**
	 * swaps objects at the given indexes
	 * 
	 * @param index1
	 * @param index2
	 * @return return true if swapped, false in the case of any wrong index
	 */
	boolean swap(int index1, int index2);

	default boolean contains(T pattern) {
		return indexOf(pattern) >= 0;
	}

	static <T> T max(List<T> list, Comparator<T> comp) {
		T max = list.get(0);
		int size = list.size();
		for (int i = 1; i < size; i++) {
			T current = list.get(i);
			if (comp.compare(max, current) < 0) {
				max = current;
			}
		}
		return max;
	}

	@SuppressWarnings("unchecked")
	static <T> T max(List<T> list) {
		return max(list, (Comparator<T>) Comparator.naturalOrder());
	}

	static <T> T min(List<T> list, Comparator<T> comp) {
		return max(list, comp.reversed());
	}

	@SuppressWarnings("unchecked")
	static <T> T min(List<T> list) {
		return min(list, (Comparator<T>) Comparator.naturalOrder());
	}

	@SuppressWarnings("unchecked")
	default void sort() {
		sort((Comparator<T>) Comparator.naturalOrder());
	}

	default void sort(Comparator<T> comp) {
		int size = size();
		boolean isSorted = true;
		do {
			size--;
			isSorted = true;
			for (int i = 0; i < size; i++) {
				if (comp.compare(get(i), get(i + 1)) > 0) {
					isSorted = false;
					swap(i, i + 1);
				}
			}
		} while (!isSorted);
	}

	/**
	 * 
	 * @param predicate
	 * @return either index (first object matching predicate) or -1
	 */
	int indexOf(Predicate<T> predicate);

	/**
	 * 
	 * @param predicate
	 * @return either index (last object matching predicate) or -1
	 */
	int lastIndexOf(Predicate<T> predicate);

	/**
	 * removing all objects matching a given predicate
	 * 
	 * @param predicate
	 * @return true if at least one object has been removed
	 */
	default boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		boolean res = false;
		while(it.hasNext()) {
			T obj = it.next();
			if (predicate.test(obj)) {
				it.remove();
				res = true;
			}
		}
		return res;
	}

	/**
	 * for several equaled objects to leave only one and remove others
	 * 
	 * @return true if at least one object has been removed
	 */
	default boolean removeRepeated() {
		@SuppressWarnings("unchecked")
		T helper[] = (T[]) new Object[size()];
		return removeIf(e -> {
			boolean res = true;
			int ind = 0;
			while (helper[ind] != null && !helper[ind].equals(e)) {
				ind++;
			}
			if (helper[ind] == null) {
				res = false;
				helper[ind] = e;
			}
			return res;
		});
		
	}

	/**
	 * remove all elements from list
	 */
	void clean();

}
