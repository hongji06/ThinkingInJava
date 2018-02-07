/**   
 * @{#} GreenhouseScheduler.java by Hongji.Xu 2018年2月7日 下午4:45:15 
 * @Title:  GreenhouseScheduler.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GreenhouseScheduler {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";

    public synchronized String getThermostat() {
        return thermostat;
    }

    public synchronized void setThermostat(String value) {
        thermostat = value;
    }

    ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);

    public void schedule(Runnable event, long delay) {
        scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
    }

    public void repeat(Runnable event, long initialDelay, long period) {
        scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    class LightOn implements Runnable {
        @Override
        public void run() {
            System.out.println("Turing on lights");
            light = true;
        }
    }
    
    class LightOff implements Runnable {
        @Override
        public void run() {
            System.out.println("Turing off lights");
            light = false;
        }
    }
    
    class WaterOn implements Runnable {
        @Override
        public void run() {
            System.out.println("Turing greenhouse water on");
            water = true;
        }
    }
    
    class WaterOff implements Runnable {
        @Override
        public void run() {
            System.out.println("Turing greenhouse water off");
            water = false;
        }
    }

}
