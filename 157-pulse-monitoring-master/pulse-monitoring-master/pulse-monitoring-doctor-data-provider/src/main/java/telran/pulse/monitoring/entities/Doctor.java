package telran.pulse.monitoring.entities;
import javax.persistence.*;
@Entity
@Table(name = "doctors")
public class Doctor {
	@Id
	String email;
	String name;
	public Doctor(String email, String name) {
		this.email = email;
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public Doctor() {
		
	}
	
	
}
