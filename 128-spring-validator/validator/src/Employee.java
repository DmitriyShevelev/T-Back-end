import java.time.LocalDate;

import telran.validation.constraints.*;

public class Employee {
private static final int MAX_VALUE = 999999;
@Max(value=MAX_VALUE, message="id of Employee can not be greater than " + MAX_VALUE) 
@Min(100000)
	long id;
	@Email
    String email;
	@NotEmpty
	String name;
	@Past
	LocalDate birthDate;
	@Future
	LocalDate jobFinishDate;
	@Valid
	Address address;
	
}
