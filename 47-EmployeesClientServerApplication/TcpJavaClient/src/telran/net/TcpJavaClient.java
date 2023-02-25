package telran.net;

import java.io.*;
import java.net.*;

import telran.net.dto.*;
public abstract class TcpJavaClient implements Closeable {
private Socket socket;
private ObjectOutputStream writer;
private ObjectInputStream reader;
protected TcpJavaClient(String host, int port) throws Exception{
	this.socket = new Socket(host, port);
	writer = new ObjectOutputStream(socket.getOutputStream());
	reader = new ObjectInputStream(socket.getInputStream());
}
@SuppressWarnings("unchecked")
protected <T> T send(String requestType, Serializable data) throws Exception {
	RequestJava request = new RequestJava(requestType, data);
	writer.writeObject(request);
	ResponseJava response = (ResponseJava) reader.readObject();
	if(response.code != ResponseCode.OK) {
		throw (Exception)response.data;
	}
	return (T) response.data;
}
	@Override
	public void close() throws IOException {
		socket.close();

	}

}
