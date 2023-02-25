package telran.util.concurrent;

public class ServiceProvider {
	private static final int nDaysWorking;
	private static final byte nHoursPerWorkingDay;
	private static final byte nMinutesPerHourWorking;

	private static int timer;

	public static void play() throws InterruptedException {

	for (int nThreadsCurr = ; nThreadsCurr < ; nThreadsCurr++) {
		for (int day = 0; day < nDaysWorking; day++) {
			for (int hour = 0; hour < nHoursPerWorkingDay; hour++) {
				for (int minute = 0; minute < nMinutesPerHourWorking; minute++) {
					// process
					Thread.sleep(1);
					timer++;
					publisher.submit(i);
				}
			}
		}
	}

	private static void getStatistics() {
		// TODO Auto-generated method stub

	}
}
