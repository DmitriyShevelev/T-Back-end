package telran.utils;

import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	static private class Node<T> {
		public T obj;
		public Node<T> next;
		public Node<T> prev;
		public Node(T obj) {
			this.obj = obj;
		}

	}
	private Node<T> head;
	private Node<T> tail;
	private int size;

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public void add(T obj) {
		Node<T> node = new Node<T>(obj);
		if (tail != null) {
			tail.next = node;
			node.prev = tail;
			tail = node;
		} else {
			head = tail = node;
		}
		size++;

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
	public boolean remove(T pattern) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addAll(List<T> objects) {
		// TODO Auto-generated method stub

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

	@Override
	public int indexOf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return 0;
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
