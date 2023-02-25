package telran.utils;

public interface List<T> {
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
	 * @param index [0, size - 1]
	 * @return true if removed, otherwise false (wrong index)
	 */
	boolean remove(int index);

}
