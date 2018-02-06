/**   
 * @{#} SleepingTask.java by Hongji.Xu 2018年2月4日 下午4:36:52 
 * @Title:  SleepingTask.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */   
package chapter21.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepingTask extends LiftOff {

    @Override
    public void run() {
        try {
            while(countDown-->0) {
                System.out.println(status());
                TimeUnit.MILLISECONDS.sleep(10);
            }
        }catch (InterruptedException e) {
            System.err.println(e);
        }
    }
    
    public static void main(String[] args) {
        ExecutorService exec= Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SleepingTask());
        }
        exec.shutdown();
    }
    
}
