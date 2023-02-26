import java.io.*;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;
public class FileIntroductionAppl {
private static int IDENT_LEVEL = 3;
	public static void main(String[] args) throws IOException {
		try {
			displayDirectoryContent("/",3) ;
		} catch (Exception e) {
			
		}
	}
	public static void displayDirectoryContent(String directoryPath, int maxLevel) throws IOException  {
	       File directory = new File(directoryPath);
	       if (!directory.exists()) {
	    	   throw new RuntimeException(directoryPath + " not exists");
	       }
	       displayDirectoryContentR(directory, 0, maxLevel);
//		Files.walk(Paths.get(directoryPath), maxLevel < 0 ? Integer.BYTES : maxLevel)
//		.forEach(System.out::println);
	       
    }
	private static void displayDirectoryContentR(File node, int currentLevel, int maxLevel) {
		boolean flDir = node.isDirectory();
		printNodeInfo(node, currentLevel, flDir);
		if (flDir && currentLevel != maxLevel) {
			File[] listFiles = node.listFiles();
			if (listFiles != null && listFiles.length > 0) {
				Arrays.stream(listFiles).forEach(n -> displayDirectoryContentR(n, currentLevel + 1, maxLevel));
			}
		} 
		
		
	}
	private static void printNodeInfo(File node, int currentLevel, boolean flDir) {
		System.out.printf("%s%s [%s]\n", " ".repeat(currentLevel * IDENT_LEVEL), node,
				flDir ? "dir" : "file");
		
	}

	    
}
