package telran.pulse.monitoring.entities;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.*;
@Entity
@Table(name="visits")
public class Visit {
	@Id
	
	int id;
	LocalDateTime date;
	@ManyToOne
	@JoinColumn(name = "doctor_email")
	Doctor doctor;
	@ManyToOne
	@JoinColumn(name = "patient_id")
	Patient patient;
	public Visit() {
	}
	public Visit(LocalDateTime date, Doctor doctor, Patient patient) {
		this.id = ThreadLocalRandom.current().nextInt(20, 2000000000); //as workaround of existing visits table
		this.date = date;
		this.doctor = doctor;
		this.patient = patient;
	}
	public int getId() {
		return id;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	
	

}
