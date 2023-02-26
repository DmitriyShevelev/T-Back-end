import java.util.*;
import java.util.stream.Collectors;

public class ProgrammerAppl {

	public static void main(String[] args) {
		String[] tech1 = { "JAVA", "C++", "SQL", "React" };
		String[] tech2 = { "Angular", "JAVA", "C", "SQL" };
		String[] tech3 = { "JAVA", "SQL", "MongoDB" };
		Programmer[] programmers = { new Programmer("Moshe", tech1), new Programmer("Vasya", tech3),
				new Programmer("Sara", tech2) };
		displayMostSkilledNames(programmers);
		displayMostPopularTechnolgies(programmers);

	}

	private static void displayMostPopularTechnolgies(Programmer[] programmers) {
		Map<String, Long> map = Arrays.stream(programmers)
				.flatMap(prog -> Arrays.stream(prog.technologies))
				.collect(Collectors.groupingBy(a -> a,
						Collectors.counting()));
		long maxCount = Collections.max(map.values());
		map.forEach((tech, count) -> {
			if (count == maxCount) {
				System.out.print(tech + " ");
			}
		});

	}

	private static void displayMostSkilledNames(Programmer[] programmers) {
		TreeMap<Integer, List<Programmer>> treeMap = Arrays.stream(programmers)
				.collect(Collectors.groupingBy
						(p -> p.technologies.length, TreeMap::new,
								Collectors.toList()));

		List<Programmer> mostSkilledProgrammers =
				treeMap.lastEntry().getValue();
		mostSkilledProgrammers.forEach(p -> System.out.print(p.name + " "));
		System.out.println();
	}

}
