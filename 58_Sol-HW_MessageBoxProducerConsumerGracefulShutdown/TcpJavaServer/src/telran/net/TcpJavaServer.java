package telran.net;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class TcpJavaServer  extends JavaServer {
private static final int IDLE_TIMEOUT = 1000;
private static final int N_THREADS = 5;
private  ServerSocket serverSocket;
private int port;
ExecutorService executor;
private ApplProtocolJava protocol;

	public TcpJavaServer(int port, ApplProtocolJava protocol)  {
	
	this.port = port;
	this.protocol = protocol;
	try {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(IDLE_TIMEOUT);
		executor = Executors.newFixedThreadPool(N_THREADS);
	} catch (IOException e) {
		System.out.println("Port in use " + port);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

	@Override
	public void run() {
		System.out.println("Server is listening on port " + port);
		try {
			while(true) {
				try {
					Socket socket = serverSocket.accept();
					socket.setSoTimeout(IDLE_TIMEOUT);
					TcpClientServer clientServer = new TcpClientServer(socket, protocol);
					
					executor.execute(clientServer);
				} catch (SocketTimeoutException e) {
					if (JavaServer.isShutdown) {
						executor.shutdownNow();
						serverSocket.close();
						break;
					}
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	

}
