package telran.net;
import java.io.IOException;
import java.net.*;
public class TcpJavaServer implements Runnable {
private ServerSocket serverSocket;
private int port;
private ApplProtocolJava protocol;

	public TcpJavaServer(int port, ApplProtocolJava protocol)  {
	
	this.port = port;
	this.protocol = protocol;
	try {
		serverSocket = new ServerSocket(port);
	} catch (IOException e) {
		System.out.println("Port in use " + port);
	}
	
}

	@Override
	public void run() {
		System.out.println("Server is listening on port " + port);
		try {
			while(true) {
				Socket socket = serverSocket.accept();
				TcpClientServer clientServer = new TcpClientServer(socket, protocol);
				clientServer.run();
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
