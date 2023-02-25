package telran.utils;

@SuppressWarnings("serial")
public class NoRuleMatchException extends Exception {


private int delta;
public NoRuleMatchException(int delta) {
	this.delta = delta;
}
public int getDelta() {
	return delta;
}
}
