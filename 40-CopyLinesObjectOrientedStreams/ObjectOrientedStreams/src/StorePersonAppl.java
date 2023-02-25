import java.io.*;

public class StorePersonAppl {

	public static void main(String[] args) {
		try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("person.data"))) {
			Person person = new Person();
			person.id = 123;
			person.name = "Vasya";
			person.nextPerson = person;
			writer.writeObject(person);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			
		}

	}

}

