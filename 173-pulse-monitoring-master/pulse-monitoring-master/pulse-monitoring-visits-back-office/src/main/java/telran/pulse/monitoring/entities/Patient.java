package telran.pulse.monitoring.entities;
import javax.persistence.*;
@Entity
@Table(name="patients")
public class Patient {
	@Id
	int id;
	String name;
	public Patient(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public Patient() {
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	
}
