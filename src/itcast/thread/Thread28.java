/**
 * 练习notify()与wait()的用法，要使用LockObject.wait()和LockObject.notify()/notifyAll()来控制线程间通信
 */
package itcast.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Thread28
 * @Description:子線程循環10次，接著主線程循環50次，接著又回到子線程執行10次，再接著主線程執行50，如此循環50次
 * @author: 11H0075
 * @date: 2018年2月12日 下午2:18:56
 *
 */
//锁定对象
class Bussiness {
	private boolean flag = false;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}

//子线程
class SubThread implements Runnable {
	private Bussiness bussiness;

	public SubThread(Bussiness bussiness) {
		this.bussiness = bussiness;
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			synchronized (bussiness) {
				while (bussiness.isFlag()) {
					try {
						bussiness.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + " sub thread of " + i);
				try {
					TimeUnit.MILLISECONDS.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				bussiness.setFlag(true);
				bussiness.notify();
			}
		}
	}
}
//主线程
class MainThread implements Runnable {
	private Bussiness bussiness;

	public MainThread(Bussiness bussiness) {
		this.bussiness = bussiness;
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			synchronized (bussiness) {
				while (!bussiness.isFlag()) {
					try {
						bussiness.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println(Thread.currentThread().getName() + " main thread of " + i);
				try {
					TimeUnit.MILLISECONDS.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				bussiness.setFlag(false);
				bussiness.notify();
			}
		}
	}
}

public class Thread28 {
	private static Bussiness lock = new Bussiness();
	private boolean flag = false;

	public static void main(String[] args) {

		new Thread(new SubThread(lock)) {
		}.start();

		new Thread(new MainThread(lock)) {

		}.start();

		// new Thread28().init();
		// new Init().init();
	}

	// solution 1
	public void init() {
		new Thread() {
			@Override
			public void run() {

				for (int i = 0; i < 50; i++) {
					synchronized (lock) {
						while (flag) {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.println(Thread.currentThread().getName() + " sub thread");
						try {
							TimeUnit.MILLISECONDS.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						flag = true;
						lock.notify();
					}
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {

				for (int i = 0; i < 50; i++) {
					synchronized (lock) {
						while (!flag) {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

						System.out.println(Thread.currentThread().getName() + " main thread");
						try {
							TimeUnit.MILLISECONDS.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						flag = false;
						lock.notify();
					}
				}
			}
		}.start();

	}
}

// solution 2
class Init {
	private volatile boolean flag = true;// 是否主線程
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void init() {
		// 子線程
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					lock.lock();
					try {
						while (flag) {
							condition.await();
						}
						System.out.println("sub thread: exec 10");
						TimeUnit.MILLISECONDS.sleep(10);
						flag = true;

						condition.signalAll();

					} catch (InterruptedException e) {
						System.out.println("sub interrupted");
					} finally {
						lock.unlock();
					}
				}
			}
		}.start();

		// 主線程
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					lock.lock();
					try {
						while (!flag) {
							condition.await();
						}
						System.out.println("main thread exec 100");
						TimeUnit.MILLISECONDS.sleep(10);
						flag = false;
						condition.signalAll();

					} catch (InterruptedException e) {
						System.out.println("sub interrupted");
					} finally {
						lock.unlock();
					}
				}
			}
		}.start();

	}
}
