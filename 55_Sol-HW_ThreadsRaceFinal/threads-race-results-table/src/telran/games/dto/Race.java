package telran.games.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import telran.games.service.Runner;

public class Race {
	public Lock lock = new ReentrantLock(true);
	private int distance;
	private int minSleep;
	private int maxSleep;
	private AtomicInteger winner = new AtomicInteger(0);
	private List<Runner> resultsTable;
	private Instant startTime;
	
	public List<Runner> getResultsTable() {
		return resultsTable;
	}
	public Instant getStartTime() {
		return startTime;
	}
	public Race(int distance, int minSleep, int maxSleep, List<Runner> resultsTable, Instant startTime) {
		this.distance = distance;
		this.minSleep = minSleep;
		this.maxSleep = maxSleep;
		this.resultsTable = resultsTable;
		this.startTime = startTime;
	}
	public int getWinner() {
		return winner.get();
	}
	public void setWinner(int winner) {
		this.winner.compareAndSet(0, winner);
	}
	public int getDistance() {
		return distance;
	}
	public int getMinSleep() {
		return minSleep;
	}
	public int getMaxSleep() {
		return maxSleep;
	}
	
}