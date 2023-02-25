package telran.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
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
	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		
		@Override
		public boolean hasNext() {
			
			return current != null;
		}

		@Override
		public T next() {
			T res = current.obj;
			current = current.next;
			return res;
		}
		
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
		boolean res = true;
		if (index == size) {
			add(obj);
			
		} else if (isValidIndex(index)) {
			addIndex(obj, index);
		} else {
			res = false;
		}
		
		return res;
	}

	private void addIndex(T obj, int index) {
		Node<T> newNode = new Node<>(obj);
		if (index == 0) {
			newHead(newNode);
		} else {
			Node<T> nodeAfter = getNodeIndex(index);
			newNode.next = nodeAfter;
			newNode.prev = nodeAfter.prev;
			nodeAfter.prev.next = newNode;
			nodeAfter.prev = newNode;
		}
		size++;
		
		
	}

	private void newHead(Node<T> newNode) {
		newNode.next = head;
		head.prev = newNode;
		head = newNode;
		
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
		Node<T> current = head;
		boolean res = false;
		while(current != null && !current.obj.equals(pattern)) {
			current = current.next;
		}
		if (current != null) {
			removeNode(current);
			res = true;
		}
		return res;
	}

	@Override
	public void addAll(List<T> objects) {
		int size = objects.size();
		
		for (int i = 0; i < size; i++) {
			add(objects.get(i));

		}

	}

	@Override
	public T set(T object, int index) {
		T res = null;
		if (isValidIndex(index)) {
			Node<T> nodeSet = getNodeIndex(index);
			res = nodeSet.obj;
			nodeSet.obj = object;
		}
		return res;
	}

	@Override
	public boolean swap(int index1, int index2) {
		boolean res = false;
		if (isValidIndex(index1) && isValidIndex(index2) && index1 != index2) {
			Node<T> node1 = getNodeIndex(index1);
			Node<T> node2 = getNodeIndex(index2);
			T tmp = node1.obj;
			node1.obj = node2.obj;
			node2.obj = tmp;
			res = true;
		}
		return res;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		Node<T> current = head;
		int index = 0;
		while(current != null && !predicate.test(current.obj)) {
			current = current.next;
			index++;
		}
		return current != null ? index : -1;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		Node<T> current = tail;
		int index = size - 1;
		while(current != null && !predicate.test(current.obj)) {
			current = current.prev;
			index--;
		}
		return current != null ? index : -1;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		var sizeBefore = size;
		var current = head;
		while (current != null) {
			
			if (predicate.test(current.obj)) {				
				removeNode(current);				
			}
			current = current.next;
		}
		return sizeBefore > size;
	}

	@Override
	public void clean() {
		head = tail = null;
		size = 0;

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
	@Override
	public void sort(Comparator<T> comp) {
		T arr[] = toArray();
		Arrays.sort(arr, comp);
		fillFromArray(arr);
	}

	private void fillFromArray(T[] arr) {
		Node<T> current = head;
		for (int i = 0; i < size; i++) {
			current.obj = arr[i];
			current = current.next;
		}
		
	}

	private T[] toArray() {
		
		@SuppressWarnings("unchecked")
		T[] res = (T[]) new Object[size];
		Node<T> current = head;
		for (int i = 0; i < size; i++) {
			res[i] = current.obj;
			current = current.next;
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new LinkedListIterator();
	}

}
