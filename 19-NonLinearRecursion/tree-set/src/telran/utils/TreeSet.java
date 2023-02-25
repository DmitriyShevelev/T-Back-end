package telran.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> implements SortedSet<T> {
private static class Node<T> {
	T obj;
	Node<T> left;
	Node<T> right;
	Node<T> parent;
	Node (T obj) {
		this.obj = obj;
	}
}
private class TreeSetIterator implements Iterator<T> {
Node<T> currentNode = root == null ? null : getLeastFrom(root);
Node<T> prevNode;
	@Override
	public boolean hasNext() {
		
		return currentNode != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		T res = currentNode.obj;
		prevNode = currentNode;
		updateCurrentNode();
		return res;
	}
	private void updateCurrentNode() {
		currentNode = getNextIterator(currentNode);
		
	}

	

	

	@Override
	public void remove() {
		if(isJunction(prevNode)) {
			
			currentNode = prevNode;
		}
		removeNode(prevNode);
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
	private Node<T> getNextIterator(Node<T> current) {
		return current.right != null ?
				getLeastFrom(current.right) : getGreaterParent(current);
	}
	private Node<T> getGreaterParent(Node<T> currentNode) {
		while(currentNode.parent != null &&
				currentNode.parent.left != currentNode) {
			currentNode = currentNode.parent;
		}
		return currentNode.parent;
	}
	@Override
	public Iterator<T> iterator() {
		
		return new TreeSetIterator();
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
			
		}
		size++;
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
		Node<T> node = getNode(pattern);
		boolean res = false;
		if (node != null) {
			removeNode(node);
			res = true;
		}
		return res;
	}

	private void removeNode(Node<T> node) {
		size--;
		if (isJunction(node)) {
			removeJunction(node);
		} else {
			removeNonJunction(node);
		}
		
	}
	private void removeNonJunction(Node<T> node) {
		Node<T> child = node.left != null ? node.left : node.right;
		Node<T> parent = node.parent;
		if (child != null) {
			child.parent = parent;
		}
		if (parent == null) {
			root = child;
			
		} else if (parent.left == node) {
			parent.left = child;
		} else parent.right = child;
		
		
	}
	private void removeJunction(Node<T> node) {
		Node<T> substitute = getLeastFrom(node.right);
		node.obj = substitute.obj;
		removeNonJunction(substitute);
		
	}
	private Node<T> getLeastFrom(Node<T> node) {
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	private boolean isJunction(Node<T> node) {
		
		return node.left != null && node.right != null;
	}
	private Node<T> getNode(T pattern) {
		int res = 0;
		Node<T> current = root;
		while(current != null &&
				(res = comp.compare(pattern, current.obj)) != 0) {
			current = res < 0 ? current.left : current.right;
		}
		return current;
	}
	@Override
	public boolean contains(T pattern) {
		
		return root != null && getParent(pattern) == null;
	}

	@Override
	public void clean() {
		root = null;
		size = 0;

	}

	@Override
	public T ceiling(T element) {
		boolean isCeiling = true;
		T res = null;
		Node<T> ceilingNode = ceilingFloorNode(element, isCeiling );
		if (ceilingNode != null) {
			res = ceilingNode.obj;
		}
		return res;
	}

	private Node<T> ceilingFloorNode(T element, boolean isCeiling) {
		Node<T> current = root;
		Node<T> parent = null;
		int cmpRes = 0;
		while (current != null &&
				(cmpRes = comp.compare(element, current.obj)) != 0) {
			if (cmpRes > 0) {
				parent = isCeiling ? parent : current;
				current = current.right;
			} else {
				parent = isCeiling ? current : parent;
				current = current.left;
			}
			
		}
		return current != null ? current : parent;
	}

	@Override
	public T floor(T element) {
		boolean isCeiling = false;
		T res = null;
		Node<T> floorNode = ceilingFloorNode(element, isCeiling );
		if (floorNode != null) {
			res = floorNode.obj;
		}
		return res;
	}

	@Override
	public SortedSet<T> subSet(T fromElement, boolean fromInclusive, T toElement,
			boolean toInclusive) {
		SortedSet<T> res = new TreeSet<>(comp);
		if (isValidArguments(fromElement, fromInclusive, toElement,
				toInclusive)) {
			Node<T> nodeFrom = findClosestGreaterEqual(fromElement, fromInclusive);
			res =  nodeFrom == null ? res : getSetFromNodeToObject(res, nodeFrom, toElement,
					toInclusive);
		}
		return res;
	}
	private Node<T> findClosestGreaterEqual(T fromElement, boolean fromInclusive) {
		Node<T> ceilingNode = ceilingFloorNode(fromElement, fromInclusive);
		Node<T> res = null;
		if (ceilingNode != null) {
			if(ceilingNode.obj.equals(fromElement) && !fromInclusive) {
				res = getNextIterator(ceilingNode);
			} else {
				res = ceilingNode;
			}
			
		}
		return res;
	}
	private SortedSet<T> getSetFromNodeToObject(SortedSet<T> set, Node<T> nodeFrom,
			T to, boolean isIncluded) {
		SortedSet<T> res = set;
		Node<T> node = nodeFrom;
		while(node != null) {
			res.add(node.obj);
			node = getNextSubSet(node, to, isIncluded);
		}
		return res;
	}

	private Node<T> getNextSubSet(Node<T> current, T to, boolean isIncluded) {
		Node<T> res = getNextIterator(current);
		if(res == null) {
			return null;
		}
		int compRes = comp.compare(to, res.obj);
		return compRes < 0 || (compRes == 0 && !isIncluded) ? null : res;
	}

	private boolean isValidArguments(T from, boolean isIncludedFrom, T to, boolean isIncludedTo) {
		if (from == null || to == null)
			return false;
		int compRes = comp.compare(from, to);
		return compRes < 0 || (compRes == 0 && isIncludedFrom && isIncludedTo);
	}

	public Integer height() {
		
		return height(root);
	}

	private Integer height(Node<T> nodeRoot) {
		int res=0;
		if(nodeRoot != null) {
			int heightLeft = height(nodeRoot.left);//height of the left subtree
			int heightRight = height(nodeRoot.right);//height of the right subtree
			res = Math.max(heightLeft, heightRight) + 1;
		}
		return res;
	}

	public Integer width() {
		
		return width(root);
	}

	private Integer width(Node<T> nodeRoot) {
		if (nodeRoot == null) {
			return 0;
		}
		if (nodeRoot.left == null && nodeRoot.right == null) {
			return 1;
		}
		int widthL = width(nodeRoot.left);
		int widthR = width(nodeRoot.right);
		return widthL + widthR;
	}

	public void balance() {
		// TODO Auto-generated method stub
		//1. Create sorted array of Node references using method
		//2. recursion method Node<T> getRoot(Node<T>[] ar, int left,
		//int right, Node<T> parent)
		
	}

}
