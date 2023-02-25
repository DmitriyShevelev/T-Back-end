import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
public class DateTimeAppl {

	public static void main(String[] args) {
		LocalDate current = LocalDate.now();
		LocalDate passed = LocalDate.of(1799, Month.JUNE, 6);
		LocalDate future = LocalDate.parse("3020-08-20");
//		System.out.printf("current date - %s, passed date - %s,"
//				+ " future date - %s\n", current, passed, future);
//		LocalDate bmAS = passed.plusYears(13);
//		String pattern = " eeee d MMMM yyyy ";
//		DateTimeFormatter dt = DateTimeFormatter.ofPattern(pattern,
//				Locale.forLanguageTag("ru"));
//		System.out.printf("formatted date (%s) %s\n ",pattern, passed.format(dt));
//		ChronoUnit cu = ChronoUnit.WEEKS;
//		System.out.printf("amount %s between %s and %s is %d\n", cu,
//				passed, current, cu.between(passed, current));
		
		System.out.println(current.with(TemporalAdjusters.lastDayOfMonth()).getDayOfWeek());
	}

}
