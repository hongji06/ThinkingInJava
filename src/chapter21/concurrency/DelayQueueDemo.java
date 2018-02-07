/**   
 * @{#} DelayQueueDemo.java by Hongji.Xu 2018年2月7日 上午11:29:20 
 * @Title:  DelayQueueDemo.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class DelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;

    protected static List<DelayedTask> sequeue = new ArrayList<DelayedTask>();

    public DelayedTask(int delayMilliseconds) {
        delta = delayMilliseconds;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        sequeue.add(this);
    }

    @Override
    public int compareTo(Delayed arg) {
        DelayedTask that = (DelayedTask) arg;
        if (trigger < that.trigger) {
            return -1;
        }
        if (trigger > that.trigger) {
            return 1;
        }
        return 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public void run() {
        System.out.println(this + " ");
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]", delta) + " Task " + id;
    }
    
    public String summary() {
        return "("+id+":"+delta+")";
    }
    
    public static class EndSentinel extends DelayedTask{
        private ExecutorService exec;
        
        public EndSentinel(int delay,ExecutorService exec) {
            super(delay);
            this.exec=exec;
        }
        
        @Override
        public void run() {
            for (DelayedTask pt : sequeue) {
                System.out.println(pt.summary()+" ");
            }
            System.out.println(this+" Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class DelayedTaskConsumer implements Runnable{
    private DelayQueue<DelayedTask> q;
    
    public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q=q;
    }    

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                q.take().run();//run task with the current thread
            }
        }catch (InterruptedException e) {
            System.out.println(this+" interrupted");
        }
        System.out.println("Finished DelayedTaskConsumer");
    }
    
    
}

public class DelayQueueDemo {
    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
        for (int i = 0; i < 20; i++) {
            queue.put(new DelayedTask(random.nextInt(5000)));
        }
        queue.add(new DelayedTask.EndSentinel(5000, exec));
        exec.execute(new DelayedTaskConsumer(queue));
    }
}
