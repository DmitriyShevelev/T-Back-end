package telran.utils;

import java.util.function.Predicate;

public class LinkedList<T> extends AbstractList<T> {
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
		
		return isValidIndex(index) ? getNodeIndex(index).obj : null;
	}

	

	@Override
	public boolean remove(int index) {
		if (!isValidIndex(index)) {
			return false;
		}
		removeNode(getNodeIndex(index));
		return true;
	}

	private void removeNode(Node<T> node) {
		if (head == tail) {
			head = tail = null;
			size = 0;
			return;
		}
		if (node == head) {
			removeHead();
			
		} else if (node == tail) {
			removeTail();
		} else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}
		size--;
		
		
	}

	private void removeTail() {
		tail.prev.next = null;
		tail = tail.prev;
		
	}

	private void removeHead() {
		head.next.prev = null;
		head = head.next;
		
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
	private Node<T> getNodeIndex(int index) {
		
		return index < size / 2 ? getNodeLtR(index) : getNodeRtL(index);
	}

	private Node<T> getNodeRtL(int index) {
		Node<T> current = tail;
		for(int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeLtR(int index) {
		Node<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

}
