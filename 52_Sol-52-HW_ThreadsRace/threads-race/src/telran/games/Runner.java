package telran.games;

import telran.games.dto.Race;

public class Runner extends Thread {
private Race race;
private int runnerId;
public Runner(Race race, int runnerId) {
	this.race = race;
	this.runnerId = runnerId;
}
@Override
public void run() {
	int sleepRange = race.getMaxSleep() - race.getMinSleep() + 1;
	int minSleep = race.getMinSleep();
	int distance = race.getDistance();
	for (int i = 0; i < distance; i++) {
		try {
			sleep((long) (minSleep + Math.random() * sleepRange));
		} catch (InterruptedException e) {
			throw new IllegalStateException();
		}
		System.out.println(runnerId);
	}
	race.setWinner(runnerId);
}
}
