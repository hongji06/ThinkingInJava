package chapter21.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Horse implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private int strides = 0;
	private static Random random = new Random();
	private static CyclicBarrier barrier;

	public Horse(CyclicBarrier barrier) {
		Horse.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					strides += random.nextInt(3);
				}
				barrier.await();
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted.");
		} catch (BrokenBarrierException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return "Horse " + id;
	}

	public synchronized int getStracks() {
		return strides;
	}

	public String tracks() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < getStracks(); i++) {
			sb.append("*");
		}
		sb.append(id);
		return sb.toString();
	}
}

public class HorseRace {
	static final int FINISH_LINE = 75;
	private List<Horse> horses = new ArrayList<Horse>();
	ExecutorService exec = Executors.newCachedThreadPool();
	private static CyclicBarrier barrier;

	public HorseRace(int nHorses, final int pause) {
		barrier = new CyclicBarrier(nHorses, new Runnable() {
			@Override
			public void run() {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < FINISH_LINE; i++) {
					sb.append("=");// the fence on the racetrack
				}
				System.out.println(sb);
				for (Horse horse : horses) {
					System.out.println(horse.tracks());
				}
				for (Horse horse : horses) {
					if (horse.getStracks() >= FINISH_LINE) {
						System.out.print(horse + " won!");
						exec.shutdownNow();
						return;
					}
				}
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					System.out.println("barries-action sleep interrupted");
				}
			}
		});

		for (int i = 0; i < nHorses; i++) {
			Horse horse = new Horse(barrier);
			horses.add(horse);
			exec.execute(horse);
		}
	}

	public static void main(String[] args) {
		int nHorses = 7;
		int pause = 200;
		new HorseRace(nHorses, pause);
	}

}
