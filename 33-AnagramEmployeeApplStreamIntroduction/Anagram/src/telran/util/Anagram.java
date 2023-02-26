package telran.util;

import java.util.*;

public class Anagram {
	public static boolean isAnagram(String str, String anagram) {
		int strLength = str.length();
		if ( strLength != anagram.length()) {
			return false;
		}
		
		HashMap<Character, Integer> mapStr = new HashMap<Character, Integer>();
		int qty;
//		for (char ch : str.toCharArray()) {
//			mapStr.merge(ch, 1, ((old, __) -> old + 1));
//		}
//		for (char ch : anagram.toCharArray()) {
//			qty = mapStr.compute(ch, (__, v) -> v == null ? -1 : --v);
//			if (qty < 0) {
//				return false;
//			}
//		}
		for (int i = 0; i < strLength; i++) {
			mapStr.merge(str.charAt(i), 1, (old,__) -> {
				int res = old + 1;
				return res == 0 ? null : res;
			});
			mapStr.merge(anagram.charAt(i), -1, (old,__) -> {
				int res = old - 1;
				return res == 0 ? null : res;
			});
			
		}
		return mapStr.size() == 0;
	}
}
