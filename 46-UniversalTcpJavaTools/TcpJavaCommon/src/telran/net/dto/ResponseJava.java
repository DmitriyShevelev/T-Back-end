package telran.net.dto;

import java.io.Serializable;

public class ResponseJava implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResponseCode code;
	public Serializable data;
	public ResponseJava(ResponseCode code, Serializable data) {
		super();
		this.code = code;
		this.data = data;
	}
	

}
