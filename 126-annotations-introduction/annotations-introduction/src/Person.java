import telran.data.annotations.*;

//@Table(name = "persons")

public class Person {
	public Person(long id, String email) {
		super();
		this.id = id;
		this.email = email;
	}

	@Id
	private long id;

	public long getId() {
		return id;
	};

	@Unique(true)
	@Id
	private String email;

}
