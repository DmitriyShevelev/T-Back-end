import java.util.*;
import java.util.Map.Entry;
public class DisplayOccurrencesAppl {

	public static void main(String[] args) {
		String strings[] = {"a","lmn", "ab", "ab", "a", "ba","lmn", "lmn"};
		//output
		//lmn -> 3
		//a -> 2
		//ab -> 2
		//ba -> 1
		displayOccurrences(strings);

	}

	private static void displayOccurrences(String[] strings) {
		Map<String, Integer> mapOccurrences = getMap(strings);
		var sortedOccurrences = getSortedOccurrences(mapOccurrences);
		displaySortedOccurrences(sortedOccurrences);
		
	}

	private static void displaySortedOccurrences(List<Entry<String, Integer>> sortedOccurrences) {
		sortedOccurrences.forEach(e -> System.out.printf("%s -> %d\n", e.getKey(),e.getValue() ));
		
	}

	private static List<Map.Entry<String, Integer>>
	getSortedOccurrences(Map<String, Integer> mapOccurrences) {
		LinkedList<Map.Entry<String, Integer>> arr = new LinkedList<>(mapOccurrences.entrySet());
		arr.sort((p1, p2) -> {
			int res = p2.getValue().compareTo(p1.getValue());
			return res == 0 ? p1.getKey().compareTo(p2.getKey()) : res;
		});
		return arr;
	}

	private static Map<String, Integer> getMap(String[] strings) {
Map<String, Integer> res = new HashMap<String, Integer>();
		
		for (String string : strings) {
			res.merge(string, 1, (a, b) -> a + b);
		}
		
		return res;
	}

}
