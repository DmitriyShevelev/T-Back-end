package telran.pulse.monitoring.dto;

import javax.validation.constraints.Email;

public class DoctorData {
	@Email
public String email;
public String name;
}
