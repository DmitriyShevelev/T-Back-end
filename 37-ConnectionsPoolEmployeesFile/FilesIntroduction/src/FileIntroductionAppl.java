import java.io.*;
public class FileIntroductionAppl {

	public static void main(String[] args) {
	File file = new File("myFile/myFile/myFile/myFile");
	System.out.println(file.exists());
	//file.mkdir();
	//file.mkdirs();
	displayDirectoryContent("..", -1);

	

	}

	private static void displayDirectoryContent(String directoryPath, int maxLevel) {
		//format of printing
		//<directory> name and type (either dir or file)
		//   <file> - 1 level
		//   <directory>
		//       <file> - 2 -level
		//       <directory> -2 level
		//          <....> -3 level
		//   <file> - 1 level
		//   <directory> -1 level
		//       <file> - 2 - level
		
	}

}
