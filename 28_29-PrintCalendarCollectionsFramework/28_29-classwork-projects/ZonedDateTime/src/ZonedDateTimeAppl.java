import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class ZonedDateTimeAppl {
public static void main(String[] args) {
	ZonedDateTime zdt = ZonedDateTime.now();
	System.out.println(zdt.getOffset());
	displayTimeIn(0, "Canada");
	
	
}
static public void displayTimeIn(long epochSeconds, String country) {
	for (String zona: ZoneId.getAvailableZoneIds()) {
		if (zona.contains(country)) {
			System.out.println(ZonedDateTime.ofInstant(Instant.ofEpochSecond
					(epochSeconds), ZoneId.of(zona)));
		}
	}
}
}
