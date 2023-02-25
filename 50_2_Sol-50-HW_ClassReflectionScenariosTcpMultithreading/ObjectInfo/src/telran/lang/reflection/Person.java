

package telran.lang.reflection;

import java.time.LocalDate;

public class Person {
public Person(String name, int id, Address address) {
		super();
		this.name = name;
		this.id = id;
		this.address = address;
	}
private String name;
private Integer id;
private Address address;
private Person person;
private Object personObject = new Address(null, null);
private LocalDate date = LocalDate.of(2000, 10, 19);
public void setPerson(Person person) {
	this.person = person;
}

}
