/**   
 * @{#} CachedThreadPool.java by Hongji.Xu 2018年2月4日 下午4:05:52 
 * @Title:  CachedThreadPool.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */   
package chapter21.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executor=  Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new LiftOff());
        }
        executor.shutdown();
    }
}
