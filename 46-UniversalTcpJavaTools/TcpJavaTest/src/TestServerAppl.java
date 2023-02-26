import telran.net.*;
public class TestServerAppl {
	public static void main(String[] args) {
		TcpJavaServer server = new TcpJavaServer(5000, new TestProtocol());
		server.run();
	}


}
