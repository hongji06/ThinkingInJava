/**   
 * @{#} ProducerConsumerDemo.java by Hongji.Xu 2018年2月24日 下午3:53:44 
 * @Title:  ProducerConsumerDemo.java   
 * @Package tutorials.jenkov.com.thread   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package tutorials.jenkov.com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Goods {
    private boolean flag = false;

    public synchronized void produce() {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = true;
        System.out.println(Thread.currentThread().getName() + "****Producer ");
        notify();
    }

    public synchronized void consume() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = false;
        System.out.println(Thread.currentThread().getName() + "*******Consumer ");
        notify();
    }
}

class Producer implements Runnable {

    private Goods goods;

    Producer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            goods.produce();
        }
    }

}

class Consumer implements Runnable {
    private Goods goods;

    Consumer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            goods.consume();
        }
    }
}

public class ProducerConsumerDemo {

    public static void main(String[] args) throws Exception {
        Goods goods = new Goods();
        Producer producer = new Producer(goods);
        Consumer consumer = new Consumer(goods);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(consumer);
        exec.execute(producer);
        TimeUnit.MILLISECONDS.sleep(5000);
        exec.shutdown();
    }

}
