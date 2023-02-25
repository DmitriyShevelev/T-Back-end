package telran;

import java.util.stream.IntStream;

import telran.performance.annotations.Test;

public class StringsJoinTest {
private static final int N_STRINGS = 1000;
private static final int N_RUNS = 10000;
String[] strings;
String delimiter = ";";
public StringsJoinTest() {
	strings = IntStream.range(0, N_STRINGS).mapToObj(i -> "Hello" + i).toArray(String[]::new);
}
//@Test(nRuns = N_RUNS)
void joinString() {
	String res = strings[0];
	for (int i = 1; i < strings.length; i++) {
		res += delimiter + strings[i];
	}
}
@Test(nRuns = N_RUNS)
void joinStringBuilder() {
	StringBuilder builder = new StringBuilder(strings[0]);
	IntStream.range(1, strings.length).forEach(i -> builder.append(delimiter).append(strings[i]));
}
}
