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
	//Israel code optional +972
    //code operator 050, 051, 052, 053, 054, 055, 056, 057, 058, 059
    //code operator 072 - 077
    //7 digits that may or may not be separated by dash
//   
        var delimiter = "([\\s-]*)";
        var codes = "((5\\d)|7[2-7])";
        
        var shortPrefix = "0" + codes;
        var fullPrefix = "(" + "\\+972" + delimiter + codes + ")";

        var prefix = "(" + fullPrefix + "|" + shortPrefix + ")";
        var digitsWithDelimiter = "([\\d]" + delimiter + "){6}";
        return prefix + delimiter + digitsWithDelimiter + "\\d";
}
public static String arithmeticExpression() {
	//[<delimiter>]<number> [<delimiter><operator>[<delimiter>]<number>]...
	//[<delimiter>]
	String delimiter ="\\s*";
	String number="\\d+";
	String operator="[+/*-]";
	return String.format("%1$s%2$s(%1$s%3$s%1$s%2$s)*%1$s", delimiter, number, operator);
}
}
