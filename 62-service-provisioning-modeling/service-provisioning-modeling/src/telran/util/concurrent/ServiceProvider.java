package telran.util.concurrent;

public class ServiceProvider {
private int nDays;
private int nHours;
private int timer;

public void play() throws InterruptedException {
	for (int d = 0; d < nDays; d++) {
		for (int h = 0; h < nHours; h++) {
			for (int m = 0; m < 60; m++) {
				//process
				Thread.sleep(1);
				timer++;
				}
			}
		}	
	}
}
