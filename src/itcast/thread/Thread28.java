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
class Bussiness {

}

public class Thread28 {
    private boolean flag = true;

    private Bussiness lock = new Bussiness();

    public static void main(String[] args) {
        new Thread28().init();
        // new Init().init();
    }

    public void init() {

        new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 50; i++) {

                        while (flag) {
                            try {
                                wait();
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
                        notify();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 50; i++) {
                        while (!flag) {
                            try {
                                wait();
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
                        notify();
                    }
                }
            }
        }.start();

    }
}

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
