package telran.games.service;

import java.time.Instant;

import telran.games.dto.Race;

public class Runner extends Thread {
	private final Race race;
	private int runnerId;
	private volatile Instant finishTime;

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
			// System.out.println(runnerId);
		}

		try {
			race.lock.lock();
			finishTime = Instant.now();
			finishRace();
		} finally {
			race.lock.unlock();
		}

	}

	private void finishRace() {
		race.setWinner(runnerId);

		race.getResultsTable().add(this);

	}

	public int getRunnerId() {
		return runnerId;
	}

	public Instant getFinsishTime() {
		return finishTime;
	}
}
