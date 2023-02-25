package telran.util;
import java.util.*;
public class DictionaryImpl implements Dictionary {
    private TreeSet<String> set;

    public DictionaryImpl() {
	set = new TreeSet<>();
    }

    @Override
    public void addString(String string) {
	set.add(string);
    }

    @Override
    public void addStrings(Iterable<String> strings) {
	for (String string : strings) {
	    set.add(string);
	}
    }
    
    @Override
    public Iterable<String> getAllStrings() {
	return set;
    }

    @Override
    public Iterable<String> findStringsByPrefix(String prefix) {
	char[] symbols = prefix.toCharArray();
	symbols[symbols.length - 1]++;
	
	return set.subSet(prefix, new String(symbols));
    }
}