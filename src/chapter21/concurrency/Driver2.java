package chapter21.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver2 {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch doneSignal = new CountDownLatch(5);
		ExecutorService exec = Executors.newCachedThreadPool();

		for (int i = 0; i < 5; ++i) { // create and start threads
			exec.execute(new WorkerRunnable(doneSignal, i));
		}

		System.out.println("do someting.");
		doneSignal.await(); // wait for all to finish
		System.out.println("do sometingelse.");
	}

}
