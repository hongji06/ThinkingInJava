package chapter21.concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
	ThreadLocal<String> stringLocal = new ThreadLocal<String>();

	public void set() {
		longLocal.set(1L);
		stringLocal.set(Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		ThreadLocalTest tlt = new ThreadLocalTest();
		tlt.set();
		for (int i = 0; i < 5; i++) {
			System.out.println(tlt.stringLocal.get() + "=>" + (tlt.longLocal.get() + i));
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				tlt.set();
				for (int i = 0; i < 5; i++) {
					try {
						TimeUnit.MILLISECONDS.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(tlt.stringLocal.get() + "=>" + (tlt.longLocal.get() + i));
				}

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				tlt.set();
				for (int i = 0; i < 5; i++) {
					try {
						TimeUnit.MILLISECONDS.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(tlt.stringLocal.get() + "=>" + (tlt.longLocal.get() + i));
				}

			}
		}).start();
	}
}
