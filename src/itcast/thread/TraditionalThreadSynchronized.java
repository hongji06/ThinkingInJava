package itcast.thread;

public class TraditionalThreadSynchronized {
	public static void main(String[] args) {
		new TraditionalThreadSynchronized().init();
	}

	public void init() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					output("xuhongji");
				}				
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					output("eternal");
				}
			}
		}).start();
	}

	public synchronized void output(String name) {
		int len = name.length();
		for (int i = 0; i < len; i++) {
			System.out.print(name.charAt(i));
		}
		System.out.println();
	}

}
