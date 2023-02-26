package telran.utils;

public abstract class AbstractList<T> implements List<T> {
	protected int size;
	@Override
	public int size() {
		return size;
	}
	protected boolean isValidIndex(int index) {
		return index >=0 && index < size;
	}
}
