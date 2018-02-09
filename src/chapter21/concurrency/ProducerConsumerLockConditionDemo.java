/**   
 * @{#} ProducerConsumerLockConditionDemo.java by Hongji.Xu 2018年2月9日 下午7:25:54 
 * @Title:  ProducerConsumerLockConditionDemo.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//模拟生产和消费的对象
class Simulation {
    private int maxSize;
    private List<Date> storage;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public Simulation(int maxSize) {
        this.maxSize = maxSize;
        storage = new LinkedList<Date>();
    }

    // 生產
    public void produce() {
        try {
            lock.lock();
            if (storage.size() == maxSize) {
                System.out.println("storage is full waiting consume");
                notFull.await();
            }
            storage.add(new Date());
            System.out.println("add elemet to storage");
            TimeUnit.MILLISECONDS.sleep(500);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            System.out.println("produce interrupted");
        } finally {
            lock.unlock();
        }
    }

    // 消費
    public void consume() {
        try {
            lock.lock();
            if (storage.size() == 0) {
                System.out.println("storage is empty waiting produce");
                notEmpty.await();
            }
            Date e = ((LinkedList<Date>) storage).poll();
            System.out.println("consume: " + e);
            TimeUnit.MILLISECONDS.sleep(500);
            notFull.signalAll();
        } catch (InterruptedException e) {
            System.out.println("consume interrupted");
        } finally {
            lock.unlock();
        }
    }

}

//生產者
class ProducerSimulation implements Runnable{
    private Simulation simulation;
    
    ProducerSimulation(Simulation simulation){
        this.simulation=simulation;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            simulation.produce();
        }
    }
}

//消費者
class ConsumeSimulation implements Runnable{
    private Simulation simulation;
    
    public ConsumeSimulation(Simulation simulation) {
        this.simulation=simulation;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            simulation.consume();
        }
    }
}

public class ProducerConsumerLockConditionDemo {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(5);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ConsumeSimulation(simulation));
        exec.execute(new ProducerSimulation(simulation));
        
        exec.shutdown();
        
    }
}
