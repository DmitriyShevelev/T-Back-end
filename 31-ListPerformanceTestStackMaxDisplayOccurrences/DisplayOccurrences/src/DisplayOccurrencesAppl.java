import java.util.*;
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
		
	}

	private static Map<String, Integer> getMap(String[] strings) {
Map<String, Integer> res = new HashMap<String, Integer>();
		
		for (String string : strings) {
			int cnt = res.getOrDefault(string, 0);
			
			res.put(string, cnt + 1);
		}
		
		return res;
	}

}
