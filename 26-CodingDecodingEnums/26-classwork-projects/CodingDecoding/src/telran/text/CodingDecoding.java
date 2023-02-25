package telran.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodingDecoding {
private String key;
private int keyLength;
private final int MIN_CODE = 32;
private final int MAX_CODE = 126;
private final int MAX_LENGTH = MAX_CODE - MIN_CODE + 1;
public CodingDecoding (int keyLength) {
	
  if (keyLength < 2 || keyLength > MAX_LENGTH) {
	  throw new IllegalArgumentException("wrong key length");
  }
  this.keyLength = keyLength;
  char[] keyAr = new char[keyLength];
  for(int i = 0; i < keyLength; i++) {
	  keyAr[i] = getRandomChar(keyAr);
  }
  key = new String(keyAr);
  
}

private char getRandomChar(char[] keyArr) {
char res;

	
	do {
	    res = (char) ((Math.random() * (MAX_CODE - MIN_CODE + 1)) + MIN_CODE);
	} while (contains(res, keyArr));
	
	return res;
    }

private boolean contains(char res, char[] keyArr) {
	for(char ch: keyArr) {
		if (ch == res) {
			return true;
		}
	}
	return false;
}
/**
 * 
 * @param number
 * @return string presentation of the given number
 */
public String encode(int number) {
	StringBuilder res = new StringBuilder();
	do{
		res.append(key.charAt(number % keyLength));
		number /= keyLength;
	} while (number != 0) ;
	return res.reverse().toString();
}
/**
 * 
 * @param str
 * @return number presentation of the given string
 */
public int decode(String str, String decodeKey) {
	int res = 0;
	int decodeLength = decodeKey.length();
	for (char ch: str.toCharArray()) {
		res = res * decodeLength + decodeKey.indexOf(ch);
	}
	return  res;
}
}
