/**   
 * @{#} ScheduledThreadPoolExecutorDemo.java by Hongji.Xu 2018年2月8日 上午8:38:11 
 * @Title:  ScheduledThreadPoolExecutorDemo.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " executing");
            }
        }, 3000, TimeUnit.MILLISECONDS);
        
        scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " scheduleWithFixedDelay");
            }
        }, 3000,100, TimeUnit.MILLISECONDS);
        
        TimeUnit.MILLISECONDS.sleep(10000);
        scheduler.shutdownNow();
    }
}
