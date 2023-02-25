package telran.util.concurrent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.concurrent.Flow.Subscriber;

public class FlowApp {

	private static final byte MODELING_START = 1;
	private static final byte README = 2;

	private static final String BASE_FOLD_PATH = "C:\\Users\\user\\eclipse-workspace\\62_Sol-DSS-service-provisioning-modeling\\DSS-service-provisioning-modeling-MMK\\";
	private static final String INPUT_FILE_PATH = BASE_FOLD_PATH + "\\input\\configurationProperties.txt";
	private static final String OUTPUT_FOLD_PATH_ROOT = BASE_FOLD_PATH + "output\\";
	private static final char CHAR_START_EQUALLY = '=';
	private static final char SEMICOLON = ';';

	private static final int N_HOURS_PER_WORKING_DAY_ROW = 1;
	private static byte N_HOURS_PER_WORKING_DAY = readAloneByte(N_HOURS_PER_WORKING_DAY_ROW);
/*pv15 EC Num = 14.0*/
	private static final int N_DAYS_WORKING_ROW = 2;
	private static int N_DAYS_WORKING = readAloneInt(N_DAYS_WORKING_ROW);

	private static final int JOB_PROBABILITY_PER_TIME_UNIT_HRS_PERC_ROW = 3;
	private static List<float[]> JobProbFr0To1Val2 = readArrayfloat(JOB_PROBABILITY_PER_TIME_UNIT_HRS_PERC_ROW);

	private static final int JOB_PROCESSING_PROBABILITY_TIME_UNITS_PERC_ROW = 4;
	private static List<float[]> JobProcProbFr0To1Val2 = readArrayfloat(JOB_PROCESSING_PROBABILITY_TIME_UNITS_PERC_ROW);

	private static final int N_MINUTES_PER_HOUR_WORKING_ROW = 5;
	private static byte N_MINUTES_PER_HOUR_WORKING = readAloneByte(N_MINUTES_PER_HOUR_WORKING_ROW);

	private static final int N_SUBSCRIBERS_MIN_ROW = 6;
	private static int N_SUBSCRIBERS_MIN = readAloneInt(N_SUBSCRIBERS_MIN_ROW);

	private static final int N_SUBSCRIBERS_MAX_ROW = 7;
	private static int N_SUBSCRIBERS_MAX = readAloneInt(N_SUBSCRIBERS_MAX_ROW);

	private static final int BUFFER_SIZE_PER_THREAD_ROW = 8;
	private static int BUFFER_SIZE_PER_THREAD = readAloneInt(BUFFER_SIZE_PER_THREAD_ROW);

	private static final int N_RECURSIONS_ROW = 9;
	private static int N_RECURSIONS = readAloneInt(N_RECURSIONS_ROW);

	private static final int AVERAGE_CHECK_CURRENCY_UNIT_ROW = 10;
	private static float AVERAGE_CHECK_CURRENCY_UNIT = readAlonefloat(AVERAGE_CHECK_CURRENCY_UNIT_ROW);

	private static final int SUBSCRIBER_SALARY_CURRENCY_UNIT_HOUR_ROW = 11;
	private static float SUBSCRIBER_SALARY_CURRENCY_UNIT_HOUR = readAlonefloat(
			SUBSCRIBER_SALARY_CURRENCY_UNIT_HOUR_ROW);

	private static final byte README_ROW = 13;
	private static final byte README_NUMBER_OF_ROWS = 8;

	private static final char[] charsPeriod = { '-', '/', ';' };
	private static final byte N_AUXIL_CHARS_ARRAY = (byte) charsPeriod.length; // Number of chars in 1 block in
	// the "configurationProperties.txt" like { '-', '/', ';' }.

	private static int symbolNumber = 0;
	private static int symbolRow;
	private static String charsPeriodAll;
	private static boolean equallyFound = false;

	private static byte readAloneByte(int rowItem) {
		return (byte) Math.round(readValuesInRowAlone(readFileRow(INPUT_FILE_PATH, rowItem), rowItem));
	}

	private static int readAloneInt(int rowItem) {
		return Math.round(readValuesInRowAlone(readFileRow(INPUT_FILE_PATH, rowItem), rowItem));
	}

	private static float readAlonefloat(int rowItem) {
		return readValuesInRowAlone(readFileRow(INPUT_FILE_PATH, rowItem), rowItem);
	}

	private static ArrayList<float[]> readArrayfloat(int rowItem) {
		return readFileValuesInRowArray(readFileRow(INPUT_FILE_PATH, rowItem), rowItem);
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		int choice = choiceDone();
		while (choice == MODELING_START || choice == README) {
			if (choice == README) {
				System.out.println(readFileRows(INPUT_FILE_PATH, README_ROW, README_NUMBER_OF_ROWS));
			} else {
				createOutputFoldCurrent();
				loadConfigProp();
//				multithreadInitialize();
//				ServiceProvider.play();
//				multithreadStop();
			}
			choice = choiceDone();
		}
	}

	private static int symbolRowEqually(String row, int rowItem, char[] charsRow, char[] charsNumber) {
		equallyFound = false;
		int res = 0;

		for (int symbolRow = 0; symbolRow < row.length(); symbolRow++) {
			if (charsRow[symbolRow] == CHAR_START_EQUALLY) {
				symbolRow++;
				res = symbolRow;
				equallyFound = true;
				
				
				
				System.out.println("DSS7"); /*pv16    */
				
				
				
				symbolRow = readNumber(row, SEMICOLON, charsRow, charsNumber);
				if (symbolRow == row.length()) {
					configPropIncorrect("Semicolon is not found in the end of the row " + rowItem + ":"
							+ System.lineSeparator() + row);
				}
			}
		}
		if (equallyFound == false) {
			configPropIncorrect("Equally is not found in the row " + rowItem + ":" + System.lineSeparator() + row);
		}
		return res;
	}

	@SuppressWarnings("null")
	private static float readValuesInRowAlone(String row, int rowItem) {
		char[] charsRow = row.toCharArray();
		char[] charsNumber = new char[row.length()];

		String catchMessage = "Data, found between '" + CHAR_START_EQUALLY + "' and '" + SEMICOLON
				+ "', is not a float number in the row " + rowItem + ":" + System.lineSeparator() + row;
		return tryParse(row, rowItem, charsRow, charsNumber, catchMessage); /*pv11 - sm pv10*/
	}

	private static float tryParse(String row, int rowItem, char[] charsRow, char[] charsNumber, String catchMessage) {
		float Num = 0;
		
		System.out.println("DSS5");
		System.out.println(Character.toString(charsNumber[0]));
		System.out.println("DSS6");/*pv13*/
		
		int symbolRowParseFrom = symbolRowEqually(row, rowItem, charsRow, charsNumber); /*pv9 Array - ne equally*/
		
		
		
		
		System.out.println("DSS4");
		System.out.println(Character.toString(charsNumber[1]));
		
		
		
		
		
		try {
			Num = parseFloatNumber(charsNumber, symbolRowParseFrom);
			System.out.println("Num = " + Num);
		} catch (NumberFormatException e) {
			e.printStackTrace();/*pv8*/
			String charsNumberAsString = new String(Arrays.copyOfRange(charsNumber, symbolRowParseFrom, symbolNumber));
			
			
			/*pv10 charsNumber-пустой*/
			
			System.out.println("DSS2" + row);
			char[] charsNumberT = new char[10];
			charsNumberT[2] = 'e';
			System.out.println("charsNumber.length = " + charsNumber.length);
			System.out.println(Character.toString(charsNumber[2]));
			System.out.println("DSS3");
			for (int i = 0; i < charsNumber.length; i++) {
				System.out.println(Character.toString(charsNumber[i]));
			}
			
			System.out.println("DSS" + Character.toString(charsNumberT[5]));
			configPropIncorrect(catchMessage + System.lineSeparator() + /*pv7*/ "Problem is in the:  " + charsNumberAsString);
		}
		return Num;
	}

	private static ArrayList<float[]> readFileValuesInRowArray(String row, int rowItem) {
		// For example:
		// kind = 0 (3-stage values like "7-8/25": FROM (hours), as 7 here),
		// kind = 1 (3-stage values like "7-8/25": TO (hours), as 8 here),
		// kind = 2 (3-stage values like "7-8/25": VALUE (), as 25 here).
		char[] charsRow = row.toCharArray();
		char[] charsNumber = new char[row.length()];
		float[] Fr0To1Val2 = new float[N_AUXIL_CHARS_ARRAY];
		ArrayList<float[]> Fr0To1Val2Arr = new ArrayList<float[]>();
		symbolRow = readNumber(row, CHAR_START_EQUALLY, charsRow, charsNumber);

		if (symbolRow == row.length()) {
			configPropIncorrect("Equally is not found in the row " + rowItem + ":" + System.lineSeparator() + row);
		} else {
			while (symbolRow < row.length()) {
				for (byte kind = 0; kind < N_AUXIL_CHARS_ARRAY; kind++) {
					Fr0To1Val2[kind] = readValuesInRowArray(row, rowItem, charsPeriod[kind], charsRow, charsNumber);
				}
				Fr0To1Val2Arr.add(Fr0To1Val2);
			}
		}

		return Fr0To1Val2Arr;
	}

	private static int readNumber(String row, char symbolNext, char[] charsRow, char[] charsNumber) {
		while ((symbolRow < row.length()) && (charsRow[symbolRow] != symbolNext)) {
			charsNumber[symbolNumber] = charsRow[symbolRow];
			
			System.out.println("symbolRow = " + symbolRow + ", Character.toString(charsNumber[symbolRow]) = " + Character.toString(charsNumber[symbolRow]));
			
			
			symbolRow++;
			symbolNumber++;
		}/*pv12 1-ok, 2я стр конф- не ок: symbolNumber,symbolRow */
		
		
		
		for (int i = 0; i < charsNumber.length; i++) {
			System.out.println(Character.toString(charsNumber[i]));
		}
		
		
		return symbolRow;
	}

	private static float readValuesInRowArray(String row, int rowItem, char auxilCharNext, char[] charsRow,
			char[] charsNumber) {
		symbolNumber = 0;
		float res = 0;
		int symbolRowOld = symbolRow;
		symbolRow = readNumber(row, auxilCharNext, charsRow, charsNumber);

		System.out.println("pdv1: ");

		if (symbolRow < row.length()) {
			String charsNumberErr = new String(Arrays.copyOfRange(charsRow, symbolRowOld, symbolRow));
			String catchMessage = "Data: '" + charsNumberErr + "', found between auxiliary symbols like '" + charsPeriodAll 
					+ "', is not a float number in the row " + rowItem + ":" + System.lineSeparator() + row;/* pv6 */
			res = tryParse(row, rowItem, charsRow, charsNumber, catchMessage);
		} else if ((symbolRow == row.length()) && (auxilCharNext != charsPeriod[0])) {
			configPropIncorrect("Not found full block '" + charsPeriodAll + "' in the end of the row " + rowItem + ":"
					+ System.lineSeparator() + row);
		}
		return res;
	}

	private static float parseFloatNumber(char[] charsNumber, int symbolRowParseFrom) {
		String charsNumberAsString = new String(Arrays.copyOfRange(charsNumber, symbolRowParseFrom, symbolNumber));
		return Float.parseFloat(charsNumberAsString);
	}

	private static String readFileRow(String path, int row) {
		String line = "";
		try (Stream<String> lines = Files.lines(Paths.get(path))) {
			return lines.skip(row - 1).findFirst().get();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			configPropIncorrect("Row № " + row + "couldn't be loaded from the file." + System.lineSeparator());
		}
		return line;
	}

	private static String readFileRows(String path, byte rowStart, byte numberOfRows) {
		String line = "";
		for (int row = rowStart - 1; row < rowStart - 1 + numberOfRows; row++) {
			try (Stream<String> lines = Files.lines(Paths.get(path))) {
				line = line + lines.skip(row).findFirst().get() + System.lineSeparator();
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return line;
	}

	private static void configPropIncorrect(String ErrorLocation) {
		System.out.println(
				"File: " + INPUT_FILE_PATH + " is incorrect. Programm is finished. Please, make proper changements in");
		System.out.println(" this file and relaunch the programm! Problems found:");
		System.out.println(ErrorLocation);
		System.exit(0);
	}

	private static int choiceDone() {
		return enterNumber(
				"Application pourpose and all configuration properties are located in the configuration file in the 'input' folder."
						+ System.lineSeparator() + "Please, enter a number:" + System.lineSeparator()
						+ "-'1' to start modeling;" + System.lineSeparator()
						+ "-'2' to see application observation (readme);" + System.lineSeparator()
						+ "-Any other number is exit (for example, in order to change configuration properties file).",
				"Sorry, that's not an integer number!");
	}

	private static int enterNumber(String question, String prompt) {
		System.out.println(question);
		Scanner sc = new Scanner(System.in);

		while (!sc.hasNextInt()) {
			System.out.println(prompt);
			System.out.println(question);
			sc = new Scanner(System.in);
		}
		return sc.nextInt();
	}

	private static void createOutputFoldCurrent() {
		String outputFoldPathCurrent = OUTPUT_FOLD_PATH_ROOT + System.currentTimeMillis();
		try {
			Files.createDirectory(Paths.get(outputFoldPathCurrent));
		} catch (IOException e) {
			System.out.println("How successfully was current output subfolder created: "
					+ Files.exists(Paths.get(outputFoldPathCurrent)));
			e.printStackTrace();
		}
	}

	private static void loadConfigProp() throws IOException {
		for (int symbol = 0; symbol < charsPeriod.length; symbol++) {
			charsPeriodAll = charsPeriodAll + Character.toString(charsPeriod[symbol]);
		}
		/* pv1 */
		// ******************************
		System.out.println(JobProbFr0To1Val2.get(3)[1]);
		System.out.println(JobProcProbFr0To1Val2.get(3)[1]);

		// ******************************

//		private static final int JOB_PROBABILITY_PER_TIME_UNIT_HRS_PERC_ROW = 3;
//		private static List<float[]> JobProbFr0To1Val2 = readArrayfloat(JOB_PROBABILITY_PER_TIME_UNIT_HRS_PERC_ROW);
//		
//		private static final int JOB_PROCESSING_PROBABILITY_TIME_UNITS_PERC_ROW = 4;
//		private static List<float[]> JobProcProbFr0To1Val2 = readArrayfloat(JOB_PROCESSING_PROBABILITY_TIME_UNITS_PERC_ROW);

	}

	private static void multithreadInitialize() {
		ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>(executor, BUFFER_SIZE_PER_THREAD);
		SimpleSubscriber subscriber = new SimpleSubscriber();
		LoadBalancer<Integer> loadBalancer = new LoadBalancer<>(executor,
				(Class<Subscriber<Integer>>) subscriber.getClass(), N_THREADS, BUFFER_SIZE_PER_THREAD);
		publisher.subscribe(loadBalancer);

	}

	private static void multithreadStop() {
		publisher.close();
		executor.awaitTermination(1, TimeUnit.HOURS);
		System.out.println("Rejects " + loadBalancer.getRejects());

	}
}

