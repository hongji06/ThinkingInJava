package itcast.thread;

public class TraditionThread {
	public static void main(String[] args) {
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				for (;;) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}

			}

		};
		thread.start();

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (;;) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			}
		});
		thread2.start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (;;) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("runnable:" + Thread.currentThread().getName());
				}
			}
		}) {
			@Override
			public void run() {
				for (;;) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thread:" + Thread.currentThread().getName());
				}
			}

		}.start();
	}

}
