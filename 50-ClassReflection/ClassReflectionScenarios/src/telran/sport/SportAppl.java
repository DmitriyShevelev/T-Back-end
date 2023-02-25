package telran.sport;

import java.io.FileInputStream;
import java.util.Properties;

public class SportAppl {

	private static final String BASE_PACKAGE = "telran.sport.";

	public static void main(String[] args) throws Exception{
		Properties props = new Properties();
		props.load(new FileInputStream("application.properties"));
		String className = props.getProperty("sportsman");
		String param = props.getProperty("param");
		@SuppressWarnings("unchecked")
		Class<?> clazz =  Class.forName(className);
		Sportsman sportsman = (Sportsman) clazz.getConstructor(String.class).newInstance(param);
		sportsman.action();
		

	}

}
