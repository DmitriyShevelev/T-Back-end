package telran.pulse.monitoring.dto;

public class LoginResponse {
public String authToken;
public String role;
public LoginResponse() {
	
}
public LoginResponse(String authToken, String role) {
	this.authToken = authToken;
	this.role = role;
}

}
