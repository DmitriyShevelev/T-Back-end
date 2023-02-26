package telran.net.dto;

import java.io.Serializable;

public class RequestJava implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String requestType;
	public Serializable data;
	public RequestJava(String requestType, Serializable data) {
		super();
		this.requestType = requestType;
		this.data = data;
	}
	

}
