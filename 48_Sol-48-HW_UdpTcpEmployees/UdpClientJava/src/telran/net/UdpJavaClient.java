package telran.net;

import java.io.*;
import java.net.*;

import telran.net.dto.RequestJava;
import telran.net.dto.ResponseCode;
import telran.net.dto.ResponseJava;
import static telran.net.util.UdpUtils.*;
public class UdpJavaClient extends NetJavaClient {
DatagramSocket socket;


	public UdpJavaClient( String hostname, int serverPort) throws Exception {
	super(hostname, serverPort);
	socket = new DatagramSocket();
}

	@Override
	public void close() throws IOException {
		socket.close();

	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> T send (String requestType, Serializable data) throws Exception {
		RequestJava request = new RequestJava(requestType, data);
		byte [] buffer = getByteArray(request);
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), port);
		socket.send(packet);
		byte bufferReceive[] = new byte[100000];
		packet = new DatagramPacket(bufferReceive, bufferReceive.length);
		socket.receive(packet);
		
		ResponseJava response = (ResponseJava) getSerializableFromByteArray(packet.getData(), packet.getLength());
		if (response.code != ResponseCode.OK) {
			throw (Exception)response.data;
		}
		return (T)response.data;
		
	}

	

}
