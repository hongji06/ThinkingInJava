/**   
 * @{#} ProducerConsumerBlockingQueueDemo.java by Hongji.Xu 2018年2月9日 下午8:17:23 
 * @Title:  ProducerConsumerBlockingQueueDemo.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//生產者
class ProducerBlockingQueue implements Runnable {
    private BlockingQueue<Integer> queue;
    private volatile boolean flag=false;
    private Random random = new Random();

    public ProducerBlockingQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(!flag) {
            int temp = random.nextInt(100);
            try {
                queue.put(temp);
                System.out.println(Thread.currentThread().getName()+" produce: "+temp);
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void shutdown() {
        flag=true;
    }
}

//消費者
class ConsumerBlockingQueue implements Runnable {
    private BlockingQueue<Integer> queue;
    private volatile boolean flag=false;

    public ConsumerBlockingQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(!flag) {
            try {
                int temp = queue.take();
                System.out.println(Thread.currentThread().getName()+" consume: "+temp);
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void shutdown() {
        flag=true;
    }
}

public class ProducerConsumerBlockingQueueDemo {
    
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        ProducerBlockingQueue producer = new ProducerBlockingQueue(queue);
        ConsumerBlockingQueue consumer = new ConsumerBlockingQueue(queue);
        
        for (int i = 0; i < 3; i++) {
            exec.execute(producer);
        }        
        for (int i = 0; i < 3; i++) {
            exec.execute(consumer);
        }
        
        TimeUnit.MILLISECONDS.sleep(1000);
        producer.shutdown();
        consumer.shutdown();
        exec.shutdown();
    }
}
