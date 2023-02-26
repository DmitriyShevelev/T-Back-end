package telran.pulse.monitoring.security;

public class Account {
private final String username;
private  String hashPassword;
private  String role;
public Account(String username, String hashPassword, String role) {
	this.username = username;
	this.hashPassword = hashPassword;
	this.role = role;
}
public void setHashPassword(String hashPassword) {
	this.hashPassword = hashPassword;
}
public void setRole(String role) {
	this.role = role;
}
public String getUsername() {
	return username;
}
public String getHashPassword() {
	return hashPassword;
}
public String getRole() {
	return role;
}


}
