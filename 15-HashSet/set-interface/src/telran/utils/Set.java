package telran.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Set<T> extends Iterable<T> {
	/**
	 * 
	 * @return number of elements
	 */
	int size();

	/**
	 * adding obj 
	 * 
	 * @param obj
	 * returns true if added. otherwise false (set can't contain two equaled objects
	 * 
	 */
	boolean add(T obj);


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
	
	public default void addAll(Iterable<T> objects) {
		
		if (this != objects) {
			Iterator<T> it = objects.iterator();
			objects.forEach(o -> add(it.next()));
		}

	}

	/**
	 * removes all objects equaled to the given patterns
	 * 
	 * @param patterns
	 * @return true if at least one object has been removed
	 */
	default boolean removeAll(Set<T> patterns) {
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
	default boolean retainAll(Set<T> patterns) {
		if (this == patterns) {
			return false;
		}
		return removeIf(e -> !patterns.contains(e));
	}

	

	

	boolean contains(T pattern) ;
	

	

	

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
	 * remove all elements from list
	 */
	void clean();

}
