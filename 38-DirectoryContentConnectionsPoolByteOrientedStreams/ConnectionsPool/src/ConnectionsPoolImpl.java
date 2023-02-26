import java.util.*;

public class ConnectionsPoolImpl implements ConnectionsPool {
	public ConnectionsPoolImpl(int limit) {
		this.limit = limit;
	}

	ConnectionsList connectionsList = new ConnectionsList();
	HashMap<Integer, Node> mapNodes = new HashMap<>();
	int limit;
	int size;
	private static class Node {
		public Connection connection;
		public Node next;
		public Node prev;

		public Node(Connection connection) {
			this.connection = connection;
		}
	}
	private class ConnectionsListIterator implements Iterator<Connection> {
		Node current = connectionsList.head;
		@Override
		public boolean hasNext() {
			
			return current != null;
		}

		@Override
		public Connection next() {
			Connection con = current.connection;
			current = current.next;
			return con;
		}
		
	}
	private static class ConnectionsList {
		Node head;
		Node tail;

		Node addNode(Connection connection) {
			Node node = new Node(connection);
			addFirst(node);
			return node;
		}

		private void addFirst(Node node) {
			if (head == null) {
				head = tail = node;
			} else {
				node.next = head;
				node.prev = null;
				
				head.prev = node;
				head = node;
			}

		}
		private void removeNode(Node node) {
			if (node == tail) {
				removeTail();
			} else {
				node.next.prev = node.prev;
				node.prev.next = node.next;
			}
		}
		

		 Node removeTail() {
			 Node node = tail;
			tail.prev.next = null;
			tail = tail.prev;
			return node;
			
		}
		 void moveNode(Node node) {
			 if (node != head) {
				removeNode(node);
				addFirst(node);
			}
		 }
	}

	@Override
	public Iterator<Connection> iterator() {
		
		return new ConnectionsListIterator();
	}

	@Override
	public void add(Connection connection) {
		Node node = mapNodes.get(connection.getId());
		if (node == null) {
			addNewConnection(connection);
		} else {
			addExistingConnection(node);
		}
		
		

	}

	private void addExistingConnection(ConnectionsPoolImpl.Node node) {
			connectionsList.moveNode(node);
		
	}

	private void addNewConnection(Connection connection) {
		;
		if (size == limit) {
			Node removeNode = connectionsList.removeTail();
			mapNodes.remove(removeNode.connection.getId());
			
		} else {
			size++;
		}
		Node addNode = connectionsList.addNode(connection);
		mapNodes.put(connection.getId(), addNode);
		
	}

	@Override
	public Connection get(int id) throws Exception {
		Node node = mapNodes.get(id);
		if (node == null) {
			throw new Exception("Connection doesn't exist");
		}
		connectionsList.moveNode(node);
		return node.connection;
	}

}
