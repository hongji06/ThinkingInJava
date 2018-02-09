/**   
 * @{#} UseConditionWaitNotifyDemo.java by Hongji.Xu 2018年2月9日 下午3:01:13 
 * @Title:  UseConditionWaitNotifyDemo.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean flag = false;// 是否有生產

    public void await(int i) {
        try {
            lock.lock();
            while (!flag) {
                System.out.println(Thread.currentThread().getName()+" "+i+"沒有產品，等待生產...");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+" "+i+"消費產品...");
            flag = false;// 消費後設置為沒有
            TimeUnit.MILLISECONDS.sleep(200);
            condition.signal();
        } catch (InterruptedException e) {
            System.out.println("await() interrupted");
        } finally {
            lock.unlock();
        }
    }

    public void signal(int i) {
        try {
            lock.lock();
            while (!flag) {
                System.out.println(Thread.currentThread().getName()+" "+i+"正在生產產品......");
                TimeUnit.MILLISECONDS.sleep(200);
                flag = true;
                condition.signal();
            }
            condition.await();
        } catch (InterruptedException e) {
            System.out.println("signal() interrupted");
        } finally {
            lock.unlock();
        }
    }
}

class ConsumerA implements Runnable {
    private MyService service;

    public ConsumerA(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            service.await(i);
        }

    }

}

class CustomerA implements Runnable {

    private MyService service;

    public CustomerA(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            service.signal(i);
        }
    }
}

public class UseConditionWaitNotifyDemo {

    public static void main(String[] args) throws Exception {
        MyService myService = new MyService();
        ExecutorService exec = Executors.newCachedThreadPool();

        exec.execute(new CustomerA(myService));
        exec.execute(new ConsumerA(myService));
        exec.shutdown();
    }

}
