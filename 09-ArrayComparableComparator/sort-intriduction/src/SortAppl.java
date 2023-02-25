import java.util.Arrays;

public class SortAppl {

	public static void main(String[] args) {
		Person persons[] = {
				new Person(123, 50), new Person(100, 55),
				new Person(500, 55)
		};
		Arrays.sort(persons);
		display(persons);
		System.out.println("__________________________");
		Arrays.sort(persons, new AgeComparator());
		display(persons);
		

	}

	private static void display(Person[] persons) {
		for (int i = 0; i < persons.length; i++) {
			System.out.println(persons[i]);
		}
	}

}
