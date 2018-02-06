/**   
 * @{#} ThreadLocalVariableHolder.java by Hongji.Xu 2018年2月5日 上午9:01:58 
 * @Title:  ThreadLocalVariableHolder.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Accessor implements Runnable {
    private final int id;

    public Accessor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + "#" + id + ":" + ThreadLocalVariableHolder.get();
    }
}

/**
 * 
 * @ClassName: ThreadLocalVariableHolder
 * @Description:ThreadLocal Test
 * @author: 11H0075
 * @date: 2018年2月5日 上午9:54:37
 *
 */
public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        private Random random = new Random(47);

        @Override
        protected synchronized Integer initialValue() {
            return random.nextInt(10000);
        }

    };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static Integer get() {
        return value.get();
    }

    private final static int THREAD_SIZE = 5;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = new ThreadPoolExecutor(3, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        System.out.println(this + " creating new Thread");
                        Thread thread = new Thread(r);
                        thread.setName("myThread");
                        return thread;
                    }
                });
        for (int i = 0; i < THREAD_SIZE; i++) {
            exec.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }

}
