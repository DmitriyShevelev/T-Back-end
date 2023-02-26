package telran.text;

import java.util.Arrays;

public class CodingDecoding {
private String key;
private final int MIN_CODE = 32;
private final int MAX_CODE = 126;
private final int MAX_LENGTH = MAX_CODE - MIN_CODE + 1;
public CodingDecoding (int keyLength) {
  if (keyLength < 2 || keyLength > MAX_LENGTH) {
	  throw new IllegalArgumentException("wrong key length");
  }
  char[] keyAr = new char[keyLength];
  for(int i = 0; i < keyLength; i++) {
	  keyAr[i] = getRandomChar(keyAr);
  }
  key = new String(keyAr);
  
}
@SuppressWarnings("unlikely-arg-type")
private char getRandomChar(char[] keyArr) {
char res;
	
	do {
	    res = (char) ((Math.random() * (MAX_CODE - MIN_CODE + 1)) + MIN_CODE);
	} while (Arrays.asList(keyArr).contains(res));
	
	return res;
    }

/**
 * 
 * @param number
 * @return string presentation of the given number
 */
public String encode(int number) {
	//TODO
	return "";
}
/**
 * 
 * @param str
 * @return number presentation of the given string
 */
public int decode(String str) {
	//TODO
	return 0;
}
}
