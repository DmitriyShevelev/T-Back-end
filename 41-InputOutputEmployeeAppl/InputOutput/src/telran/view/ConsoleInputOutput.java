package telran.view;
import java.io.*;


public class ConsoleInputOutput implements InputOutput {
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	@Override
	public String readString(String prompt) {
		writeObjectLine(prompt);
		String res = null;
		try {
			res =  reader.readLine();
		} catch (IOException e) {
			
		}
		return res;
	}

	@Override
	public void writeObject(Object obj) {
		System.out.print(obj);

	}

}
