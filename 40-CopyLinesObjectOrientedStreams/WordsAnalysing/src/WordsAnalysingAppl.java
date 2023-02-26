import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class WordsAnalysingAppl {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Should be specified two file pathes");
			return;
		}
		try(BufferedReader reader = new BufferedReader(new FileReader(args[0]));
				PrintWriter writer = new PrintWriter(args[1])) {
			Map<String, Long> map = reader.lines().parallel().flatMap(l -> Arrays.stream(l.split("\\W+")))
			.filter(w -> w.length() > 4).map(String::toLowerCase)
			.collect(Collectors.groupingBy(w -> w, Collectors.counting()));
			map.entrySet().parallelStream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
			.limit(10).forEachOrdered(System.out::println);
			
		}catch(Exception e) {
			System.out.println("error " + e.getMessage());
		}

	}

}
