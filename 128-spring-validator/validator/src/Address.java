import telran.validation.constraints.*;

public class Address {
	@NotEmpty
String city;
	@NotNull
	String street; 
	@Min(1)
	int aprt;
	
}
