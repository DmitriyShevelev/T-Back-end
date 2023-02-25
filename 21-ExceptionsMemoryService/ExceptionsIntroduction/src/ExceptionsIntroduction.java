
public class ExceptionsIntroduction {

	public static void main(String[] args) {
		int index = 10;
		
		System.out.println(extracted(index));
		

	}

	@SuppressWarnings("finally")
	private static int extracted(int index) {
		try {
			System.out.println(getElement(index));
			System.out.println("everything ok");
			return 1;
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			return 2;
		} 
		finally {
			System.out.println("finally");
			//return 3;
		}
	}

	private static int getElement(int index) {
		int ar[] = {1 ,2, 3};
		return ar[index];
	}

}
