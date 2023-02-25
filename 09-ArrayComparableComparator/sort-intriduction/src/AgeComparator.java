import java.util.Comparator;

public class AgeComparator  implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		int res = o1.getAge() - o2.getAge();
		return res == 0 ? o1.getId() - o2.getId() : res;
	}

	

	

}
