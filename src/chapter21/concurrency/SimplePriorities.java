/**   
 * @{#} SimplePriorities.java by Hongji.Xu 2018年2月4日 下午4:47:50 
 * @Title:  SimplePriorities.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {

    private int countDown = 5;
    @SuppressWarnings("unused")
    private volatile double d;
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ":" + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 1; i < 1000000; i++) {
                d += (Math.PI + Math.E);
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }

}
