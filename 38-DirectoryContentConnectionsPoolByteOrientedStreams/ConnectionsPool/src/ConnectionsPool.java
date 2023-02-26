
public interface ConnectionsPool extends Iterable<Connection>{
	/**
	 * adds connection at first one in connections sequence 
	 * If a connection exists, only moves the connection to beginning 
	 * If a connection doesn't exists, adds new one at beginning 
	 * If no room for new connection the latest one is taken out
	 * O[1]
	 * @param connection
	 */
	void add(Connection connection);
	
	/**
	 * 
	 * @param id
	 * @return Connection
	 * if connection exists, returns related connection
	 *  and moves the connection to beginning;
	 *  If connection doesn't exist, throws Exception
	 *  O[1]
	 */
	Connection get(int id) throws Exception;
}
