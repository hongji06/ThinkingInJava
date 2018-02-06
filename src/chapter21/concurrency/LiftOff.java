/**   
 * @{#} LiftOff.java by Hongji.Xu 2018年2月4日 下午3:13:15 
 * @Title:  LiftOff.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

public class LiftOff implements Runnable {

    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countdown) {
        this.countDown = countdown;
    }
    
    public String status() {
        return "#"+id+"("+(countDown>0?countDown:"LiftOff!")+"),";
    }

    @Override
    public void run() {
        while(countDown-->0) {
            System.out.print(status());
            Thread.yield();
        }
    }

}
