/**   
 * @{#} UseConditionWaitNotifyDemo.java by Hongji.Xu 2018年2月9日 下午3:01:13 
 * @Title:  UseConditionWaitNotifyDemo.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean flag = true;

    public void await() {
        try {
            lock.lock();
            while (flag) {
                System.out.println("buy apple");
                condition.await();
            }
            System.out.println("no apple ask for create");
            flag = true;
            TimeUnit.MILLISECONDS.sleep(200);
            condition.signal();
        } catch (InterruptedException e) {
            System.out.println("await() interrupted");
        } finally {
            lock.unlock();
            System.out.println("await() unlock");
        }
    }

    public void signal() {
        try {
            lock.lock();
            while (!flag) {
                System.out.println("creating......apple");
                condition.signal();
            }
            System.out.println("had apple signal for buy");
            flag = false;
            TimeUnit.MILLISECONDS.sleep(200);
            condition.await();
        } catch (InterruptedException e) {
            System.out.println("signal() interrupted");
        } finally {
            lock.unlock();
            System.out.println("signal() unlock");
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
            service.await();
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
            service.signal();
        }
    }
}

public class UseConditionWaitNotifyDemo {

    public static void main(String[] args) throws Exception {
        MyService myService = new MyService();

        new Thread(new CustomerA(myService)) {
        }.start();
        
        new Thread(new ConsumerA(myService)) {
        }.start();
    }

}
