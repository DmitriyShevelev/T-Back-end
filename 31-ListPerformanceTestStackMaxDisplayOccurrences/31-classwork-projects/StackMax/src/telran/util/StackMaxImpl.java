package telran.util;


import java.util.LinkedList;

public class StackMaxImpl implements StackMax {
	
	private LinkedList<Integer> max = new LinkedList<>();
	private LinkedList<Integer> collection = new LinkedList<>();

	@Override
	public boolean isEmpty() {
		return collection.isEmpty();
	}

	@Override
	public void push(int num) {
		collection.add(num);
		if(max.isEmpty() || num>=max.getLast()) {
			max.add(num);
		}
	}

	@Override
	public int pop() {
		
		int tmp = collection.removeLast();
		if(tmp == max.getLast()) {
			max.removeLast();
		}
		return tmp;
	}

	@Override
	public int getMax() {
		return max.getLast();
	}

}