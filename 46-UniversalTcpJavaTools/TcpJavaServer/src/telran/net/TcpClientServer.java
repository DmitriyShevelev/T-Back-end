package telran.net;
import java.io.*;
import java.net.*;

import telran.net.dto.*;
public class TcpClientServer implements Runnable{
private  final Socket socket;
private ApplProtocolJava protocol;
private ObjectInputStream reader;
private ObjectOutputStream writer;
public TcpClientServer(Socket socket, ApplProtocolJava protocol) throws IOException {
	this.socket = socket;
	this.protocol = protocol;
	reader = new ObjectInputStream(socket.getInputStream());
	writer = new ObjectOutputStream(socket.getOutputStream());
}
@Override
public void run() {
	try(socket) {
		while(true) {
			RequestJava request = (RequestJava) reader.readObject();
			ResponseJava response = protocol.getResponse(request);
			writer.writeObject(response);
		}
	} catch (EOFException e) {
		System.out.println("client closed connection");
		
	} catch (Exception e) {
		System.out.println("abnormal closing connection: " + e.getMessage());
	}
	
}

}
