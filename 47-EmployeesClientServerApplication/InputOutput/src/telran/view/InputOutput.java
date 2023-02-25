package telran.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
String readString(String prompt);
void writeObject(Object obj);
default void writeObjectLine(Object obj) {
	writeObject(obj + "\n");
	
}
default <R> R readObject(String prompt, String errorPrompt, Function<String, R> mapper ) {
	
	while (true) {
		try {
			String string = readString(prompt);
			if(string == null) {
				break;
			}
			R res = mapper.apply(string);
			return res;
		} catch (Exception e) {
			writeObjectLine(errorPrompt);
		} 
	}
	throw new EndOfInputException();
}

	public default Integer readInt(String prompt) {
	return readObject(prompt, "No integer number", Integer::parseInt);
    }

    
    public default Integer readInt(String prompt, int min, int max) {
	return readObject(prompt, String.format("no number in [%d - %d]\n", min, max),
			str -> {
				int num = Integer.parseInt(str);
				if (num < min || num > max) {
					throw new IllegalArgumentException();
				}
				return num;
			});
    }

    public default String readStringOption(String prompt, Set<String> options) {
	return readStringPredicate(prompt, "entered string is not among the options",
			str -> options.contains(str));
    }
 
    public default Long readLong(String prompt) {
	return readObject(prompt, "no integer number", Long::parseLong);
    }
    
    public default LocalDate readDate(String prompt) {
	return readObject(prompt, "Wrong date in ISO format. Please, enter date as yyyy-mm-dd", LocalDate::parse);
	// yyyy-mm-dd
    }
    
    public default LocalDate readDate(String prompt, String formatPattern) {
	return readObject(prompt, "Wrong date in format " + formatPattern, str ->
		LocalDate.parse(str, DateTimeFormatter.ofPattern(formatPattern))
	);
    }
    public default String readStringPredicate(String prompt,
    		String errorMessage, Predicate<String> predicate) {
    	return readObject(prompt, errorMessage, str -> {
    		if (!predicate.test(str)) {
    		throw new IllegalArgumentException();
    	}
    		return str;
    	});
    }
}
