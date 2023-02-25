
public class Connection {
	private int id;
	private String ipAddress;
	private int port;

	public Connection(int id, String ipAddress, int port) {
		super();
		this.id = id;
		this.ipAddress = ipAddress;
		this.port = port;
	}

	public int getId() {
		return id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public int getPort() {
		return port;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
