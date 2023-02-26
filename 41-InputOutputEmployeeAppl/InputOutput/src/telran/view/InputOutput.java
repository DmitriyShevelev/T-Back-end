package telran.view;

import java.util.function.Function;

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
			R res = mapper.apply(string);
			return res;
		} catch (Exception e) {
			writeObjectLine(errorPrompt);
		} 
	}
}

}
