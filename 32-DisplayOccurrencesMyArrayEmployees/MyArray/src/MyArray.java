import java.util.HashMap;

public class MyArray {
	private int allValues;
	private int length;
	private HashMap<Integer, Integer> mapOfSets;
public static MyArray getArray(int length) {
	if (length < 0) {
		throw new IllegalArgumentException("Length can not be less than 0");
	}
	return new MyArray(length);
}
private MyArray(int length) {
	this.length = length;
	mapOfSets = new HashMap<>();
}
public void setAllValues(int value) {
	
	//all elements of this array will be equaled to the given value
	mapOfSets = new HashMap<>();
	allValues = value;
	
}
public void set(int index, int value) {
	if(!validateIndex(index)) {
		throw new IndexOutOfBoundsException();
	}
	mapOfSets.put(index, value);
}
private boolean validateIndex(int index) {
	
	return index >= 0 && index < length;
}
public int get(int index) {
	
	//returns value at specified index
	//throws exception IndexOutOfBoundsException
	if(!validateIndex(index)) {
		throw new IndexOutOfBoundsException();
	}
	
	return mapOfSets.getOrDefault(index, allValues);
}
public int length() {
	
	return length;
}
}
