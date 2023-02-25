import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.IntStream;

public class KolhozAppl {

	private static final int N_GRUZOVIKOV = 8000; //10000;
	private static final int N_LOADS = 10000;

	public static void main(String[] args) {
		Gruzovik gruzoviki[] = new Gruzovik[N_GRUZOVIKOV];
		Instant start = Instant.now();
		startGrizoviki(gruzoviki);
		joinGruzoviki(gruzoviki);
		System.out.printf("elevator1: %d; elevator2: %d; running time %d\n",
				Gruzovik.getElevator1(), Gruzovik.getElevator2(), 
				ChronoUnit.MILLIS.between(start, Instant.now()));

	}

	private static void joinGruzoviki(Gruzovik[] gruzoviki) {
		Arrays.stream(gruzoviki).forEach(g -> {
			try {
				g.join();
			} catch (InterruptedException e) {
				throw new IllegalStateException("can't be interrupted");
			}
		});
		
	}

	private static void startGrizoviki(Gruzovik[] gruzoviki) {
		IntStream.range(0, gruzoviki.length).forEach(i -> {
			gruzoviki[i] = new Gruzovik(1, N_LOADS); // For example, 1 ton of load for 1 time from 1 truck.
			gruzoviki[i].start();
		});
		
	}

}
