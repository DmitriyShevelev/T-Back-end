import java.io.*;
public class RestorePersonAppl {

	public static void main(String[] args) {
		try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream("person.data"))) {
			Person person = (Person) reader.readObject();
			System.out.println(person.nextPerson.nextPerson.nextPerson.id);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
