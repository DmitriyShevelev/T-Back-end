package telran.courses.api.dto;

public class AuthToken {
public String accessToken;
public String role;
public AuthToken(String accessToken, String role) {
	this.accessToken = accessToken;
	this.role = role;
}
public AuthToken() {
}

}
