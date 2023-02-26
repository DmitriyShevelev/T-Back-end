package telran.timing;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer extends Thread {
	static final String DEFAULT_PATTERN = "HH:mm:ss";
	static final long DEFAULT_TIME_RESOLUTION = 1000;
private DateTimeFormatter dtf;
private long timeResolution ;
public Timer() {
	this(DEFAULT_PATTERN, DEFAULT_TIME_RESOLUTION);
}
public Timer(String pattern, long timeResolution) {
	dtf = DateTimeFormatter.ofPattern(pattern);
	this.timeResolution = timeResolution;
	setDaemon(true);
}
@Override
public void run() {
	while (true) {
		System.out.println(LocalTime.now().format(dtf));
		try {
			sleep(timeResolution);
		} catch (InterruptedException e) {
			break;
		} 
	}
}
}
