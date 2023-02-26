package telran.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

public abstract class NetJavaClient implements Closeable{
protected String host;
protected int port;
protected NetJavaClient(String host, int port) {
	this.host = host;
	this.port = port; 
}
public abstract <T> T send(String requestType, Serializable data) throws Exception;
}
