package telran.net;
import java.io.IOException;
import java.net.*;

import telran.net.dto.*;
import telran.net.util.UdpUtils;
public class UdpServerJava implements Runnable {
private static final int MAX_PACKET_LENGTH = 100000;

public UdpServerJava(int port, ApplProtocolJava protocol) throws Exception {
	
		this.port = port;
		this.protocol = protocol;
		socket = new DatagramSocket(port);
	}

int port;
ApplProtocolJava protocol;
DatagramSocket socket;

	@Override
	public void run() {
		System.out.println("UDP server is listening datagrams on port " + port);
		while(true) {
			try {
				byte[] bufferSend = null;
				byte[] bufferReceive = new byte [MAX_PACKET_LENGTH];
				DatagramPacket packetReceive = new DatagramPacket(bufferReceive, MAX_PACKET_LENGTH);
				socket.receive(packetReceive);
				RequestJava request =
						(RequestJava) UdpUtils.getSerializableFromByteArray(packetReceive.getData(),
								packetReceive.getLength());
				ResponseJava response = protocol.getResponse(request);
				bufferSend = UdpUtils.getByteArray(response);
				DatagramPacket packetSend = new DatagramPacket(bufferSend, bufferSend.length,
						packetReceive.getAddress(), packetReceive.getPort());
				socket.send(packetSend);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
