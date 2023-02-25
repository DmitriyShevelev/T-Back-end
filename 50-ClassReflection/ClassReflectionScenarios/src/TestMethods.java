import java.util.Arrays;

public class TestMethods {
private void test1(int  nRuns) {
	System.out.println("test1 and nRuns: " + nRuns);
}
private void test2(int  nRuns) {
	System.out.println("test2 and nRuns: " + nRuns);
}
private void test3(int  nRuns) {
	System.out.println("test3 and nRuns: " + nRuns);
}
private void test4(int  nRuns) {
	System.out.println("test4 and nRuns: " + nRuns);
}
static public void anyMethod() {
	System.out.println("anyMethod is running");
	
}
public void methodA() {
	System.out.println("methodA is running");
}
@Override
public String toString() {
	return Arrays.deepToString(getClass().getDeclaredMethods());
}
}
