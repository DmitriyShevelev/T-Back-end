package telran.util;

public interface Dictionary {
	Iterable<String> findStringsByPrefix(String prefix);
	Iterable<String> getAllStrings();
	void addString(String string);
	void addStrings(Iterable<String> strings);
}
