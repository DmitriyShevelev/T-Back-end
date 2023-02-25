package telran.text;

public class StringRegExpression {
public static String javaVariable() {
	String first= "a-zA-Z$_";
	String expr =String.format("[%1$s][%1$s0-9]*", first);
	return expr;
}
public static String lessEqual300() {
	return "[1-9]\\d?|[12]\\d{2}|300";
}
public static String less256() {
	return "[0-1]?\\d?\\d|2[0-4]\\d|25[0-5]";
}
public static String ipV4() {
	 return String.format("((%1$s)[.]){3}(%1$s)", less256());
}
public static String email() {
	//email address comprises of two parts
	//first part any symbols except the white ones and comma 
	//second part - domains separated by dot 
	//minimum 2 domains, maximum 4 domains
	//each domain may contain only ascii letters and dashes
	//@ separates that two parts
	return "[^\\s,]+@[a-zA-Z-]+(\\.[a-zA-Z-]+){1,3}";
}
public static String mobileIsraelPhone() {
	//
	return "";
}
}
