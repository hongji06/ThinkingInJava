package chapter21.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class WorkerRunnable implements Runnable {
	private final CountDownLatch doneSignal;
	private final int i;

	public WorkerRunnable(CountDownLatch doneSignal, int i) {
		this.doneSignal = doneSignal;
		this.i = i;
	}

	@Override
	public void run() {
		try {
			doWork(i);
			TimeUnit.MILLISECONDS.sleep(500);
			doneSignal.countDown();
		} catch (Exception ex) {
			System.out.println(ex);
		} 

	}

	void doWork(int i) {
		System.out.println(Thread.currentThread().getName() + " do something with " + i);
	}

}
