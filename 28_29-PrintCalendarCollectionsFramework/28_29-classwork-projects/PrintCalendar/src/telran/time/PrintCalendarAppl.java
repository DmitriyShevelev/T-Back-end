package telran.time;

import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;

public class PrintCalendarAppl {

	private static Locale locale;
	private static int offsetForYear = 8;
	private static int offsetForTitle = 1;
	private static int offsetSize = 1;
	private static int widthColumn;
	private static DayOfWeek[] daysOfWeek;

	public static void main(String[] args) {
		DayOfWeek first = DayOfWeek.valueOf("SUNDAY");
		System.out.println(first.ordinal());
		int[] yearMonth = null;
		try {
			yearMonth = getYearMonth(args);
			setDaysOfWeek(args);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		String languageCode = args.length > 2 ? args[2] : "xx";
		locale = Locale.forLanguageTag(languageCode);
		widthColumn = getWidthColumn() + offsetSize;
		printCalendar(yearMonth[1], yearMonth[0]);

	}

	private static void setDaysOfWeek(String[] args) {
		DayOfWeek[] sourceDays = DayOfWeek.values();
		int daysOnWeek = sourceDays.length;
		DayOfWeek firstDay = args.length > 3 ? DayOfWeek.valueOf(args[3].toUpperCase()) : sourceDays[0];
		if (firstDay == sourceDays[0]) {
			daysOfWeek = sourceDays;
		} else {
			daysOfWeek = new DayOfWeek[daysOnWeek];
			int dayNumber = firstDay.getValue();
			for (int i = 0; i < daysOfWeek.length; i++) {
				int ind = dayNumber <= daysOnWeek ? dayNumber : dayNumber - daysOnWeek;
				daysOfWeek[i] = sourceDays[ind - 1];
				dayNumber++;
			}
		}

	}

	private static int[] getYearMonth(String[] args) throws Exception {
		LocalDate current = LocalDate.now();
		int[] res = { current.getYear(), current.getMonthValue() };
		if (args.length > 0) {
			int year = getYear(args[0]);
			res[0] = year;
		}
		if (args.length > 1) {
			int month = getMonth(args[1]);
			res[1] = month;
		}
		return res;
	}

	private static int getMonth(String monthStr) throws Exception {
		int res = 0;
		try {
			res = Integer.parseInt(monthStr);
		} catch (NumberFormatException e) {
			throw new Exception("Month should be a number");
		}
		if (res < 1 || res > 12) {
			throw new Exception("Wrong month number ");
		}
		return res;
	}

	private static int getYear(String strYear) throws Exception {
		int res = 0;
		try {
			res = Integer.parseInt(strYear);
		} catch (NumberFormatException e) {
			throw new Exception("Year should be a number");
		}
		if (res < 0) {
			throw new Exception("Year can't be negative");
		}
		return res;
	}

	private static int getWidthColumn() {

		return DayOfWeek.MONDAY.getDisplayName(TextStyle.SHORT, locale).length();
	}

	private static void printCalendar(int monthNum, int yearNum) {

		printTitle(monthNum, yearNum);
		printWeekDays();
		printOffset(monthNum, yearNum);

		printDates(monthNum, yearNum);

	}

	private static void printDates(int monthNum, int yearNum) {
		int firstColumn = getFirstColumn(monthNum, yearNum);
		int offset = firstColumn - 1;
		int countDaysOfWeek = DayOfWeek.values().length;
		int lastDate = YearMonth.of(yearNum, monthNum).lengthOfMonth();

		int counter = 1;
		for (int i = 1; i <= lastDate; i++) {
			System.out.printf("%" + widthColumn + "d", i);

			if ((counter + offset) % countDaysOfWeek == 0) {
				System.out.println();
			}

			counter++;
		}

	}

	private static void printOffset(int monthNum, int yearNum) {

		int firstColumn = getFirstColumn(monthNum, yearNum);

		var offset = (firstColumn - 1) * widthColumn;

		System.out.print(" ".repeat(offset));
	}

	private static int getFirstColumn(int monthNum, int yearNum) {
		LocalDate firstDate = LocalDate.of(yearNum, monthNum, 1);
		int res = firstDate.getDayOfWeek().getValue();
		int firstDayNumber = daysOfWeek[0].getValue();
		int delta = res - firstDayNumber;
		res = delta >= 0 ? delta + 1 : delta + daysOfWeek.length + 1;
		return res;
	}

	private static void printWeekDays() {

		String res = " ".repeat(widthColumn / 2);
		String offset = " ".repeat(offsetForTitle);
		for (DayOfWeek dayOfWeek : daysOfWeek) {
			res += dayOfWeek.getDisplayName(TextStyle.SHORT, locale) + offset;
		}
		System.out.println(res);

	}

	private static void printTitle(int monthNum, int yearNum) {
		Month month = Month.of(monthNum);
		String monthName = month.getDisplayName(TextStyle.FULL_STANDALONE, locale);
		String offset = " ".repeat(offsetForYear);
		System.out.printf("%s%d, %s\n", offset, yearNum, monthName);

	}

}
