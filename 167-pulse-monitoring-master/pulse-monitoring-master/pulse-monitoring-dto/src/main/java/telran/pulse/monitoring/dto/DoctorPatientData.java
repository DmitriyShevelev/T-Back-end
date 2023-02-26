package telran.pulse.monitoring.dto;

public class DoctorPatientData {
public String email;
public String doctorName;
public String patientName;
public DoctorPatientData() {
}
public DoctorPatientData(String email, String doctorName, String patientName) {
	super();
	this.email = email;
	this.doctorName = doctorName;
	this.patientName = patientName;
}
@Override
public String toString() {
	return "DoctorPatientData [email=" + email + ", doctorName=" + doctorName + ", patientName=" + patientName + "]";
}

}
