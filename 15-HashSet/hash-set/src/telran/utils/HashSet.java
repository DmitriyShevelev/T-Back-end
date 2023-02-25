package telran.utils;

import java.util.Iterator;

public class HashSet<T> implements Set<T> {
private static final int DEFAULT_TABLE_LENGTH = 16;
private int size;
private LinkedList<T>[] hashTable;
private double factor = 0.75;
@SuppressWarnings("unchecked")
public HashSet(int initialTableLength) {
	hashTable = new LinkedList[initialTableLength];
}
public HashSet() {
	this(DEFAULT_TABLE_LENGTH);
}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if (size >= hashTable.length * factor) {
			recreateTable();
		}
		int index = getIndex(obj);
		if (hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
		}
		if (!hashTable[index].contains(obj)) {
			hashTable[index].add(obj);
			res = true;
			size++;
		}
		return res;

	}

	private void recreateTable() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
		for(LinkedList<T> list: hashTable) {
			if (list != null) {
				for (T obj: list) {
					tmp.add(obj);
				}
			}
		}
		hashTable = tmp.hashTable;
		
	}
	private int getIndex(T obj) {
		int hashCode = obj.hashCode();
		int res = Math.abs(hashCode) % hashTable.length;
		return res;
	}
	@Override
	public boolean remove(T pattern) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getIndex(pattern);
		return hashTable[index] != null && hashTable[index].contains(pattern);
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

}
