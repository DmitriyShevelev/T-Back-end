import java.util.Iterator;

public class ConnectionsPoolImpl implements ConnectionsPool {
	private static class Node {
		public Connection connection;
		public Node next;
		public Node prev;

		public Node(Connection connection) {
			this.connection = connection;
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
		boolean isFirst(Node node) {
			return node == head;
		}

		 void removeTail() {
			tail.prev.next = null;
			tail = tail.prev;
			
		}
		 void moveNode(Node node) {
			 removeNode(node);
			 addFirst(node);
		 }
	}

	@Override
	public Iterator<Connection> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Connection connection) {
		// TODO Auto-generated method stub

	}

	@Override
	public Connection get(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
