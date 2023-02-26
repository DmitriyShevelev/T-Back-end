package telran.utils;

import java.util.Comparator;
import java.util.Iterator;

public class TreeSet<T> implements Set<T> {
private static class Node<T> {
	T obj;
	Node<T> left;
	Node<T> right;
	Node<T> parent;
	Node (T obj) {
		this.obj = obj;
	}
}
	private Node<T> root;
	private int size;
	private Comparator<T> comp;
	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}
	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>)Comparator.naturalOrder());
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
		if (root == null) {
			root = new Node<>(obj);
		} else {
			Node<T> parent = getParent(obj);//returning null means the object exists
			if (parent == null) {
				return false;
			}
			Node<T> node = new Node<>(obj);
			if (comp.compare(obj, parent.obj) > 0) {
				parent.right = node;
			} else {
				parent.left = node;
			}
			node.parent = parent;
			size++;
		}
		return true;
	}

	private Node<T> getParent(T obj) {
		Node<T> current = root;
		Node<T> parent = null;
		while(current != null) {
			parent = current;
			int compRes = comp.compare(obj, current.obj);
			if (compRes == 0) {
				return null;
			}
			current = compRes > 0 ? current.right : current.left;
		}
		return parent;
	}
	@Override
	public boolean remove(T pattern) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(T pattern) {
		
		return root != null && getParent(pattern) == null;
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

}
