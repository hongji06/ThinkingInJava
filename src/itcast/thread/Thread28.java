package itcast.thread;

public class Thread28 {
	public static void main(String[] args) {
		new Thread28().init();
	}

	public void init() {
		String lock = "xxx";

		for (int i = 0; i < 50; i++) {
			synchronized (lock) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						for (int j = 0; j < 10; j++) {
							System.out.println(Thread.currentThread().getName()+":"+j);
						}
					}
				}).start();

			}

			synchronized (lock) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						for (int j = 0; j < 100; j++) {
							System.out.println(Thread.currentThread().getName()+j);
						}
					}
				}).start();
			}
		}
	}
}
