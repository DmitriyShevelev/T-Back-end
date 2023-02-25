
public class Operations {
/**
 * 
 * @param number - given number
 * @param nBit - given sequential number of bit from 0
 * @return value of nBit in the given number (see tests)
 */
	public static int getBit(int number, int nBit) {
	int bitValue = (number >> nBit) & 1;
	
	return bitValue;
}
	/**
	 * 
	 * @param number - given number
	 * @param nBit
	 * @return new number (as a particular case it may be the same number)
	 *  with value of the given bit - 1 (see tests)
	 */
	public static int setBit(int number, int nBit) {
		int numberRes = number | 1 << nBit;
		
		return numberRes ;
	}
	/**
	 * 
	 * @param number - given number
	 * @param nBit
	 * @return new number (as a particular case it may be the same number)
	 *  with value of the given bit - 0 (see tests)
	 */
	public static int resetBit(int number, int nBit) {
		int numberRes = number & ~(1 << nBit);
		
		return numberRes;
	}
	public static int getNumberSetBits(int a) {
		
		int res = 0;
		for (int i = 0; i < 32; i++) {
			if (((1 << i) & a) != 0) {
				res++;
			}
		}
		return res;
	}
	public static long getMaxLong() {
		//only for while example;
		var max = 1L;
		while (max > 0) {
			max <<= 1;
		}
		return max - 1 ;
		
	}
	

}
