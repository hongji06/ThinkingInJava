/**   
 * @{#} BasicThread.java by Hongji.Xu 2018年2月4日 下午3:28:34 
 * @Title:  BasicThread.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

public class BasicThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new LiftOff());
        thread.start();
        System.out.println("waiting for liftoff.");
    }
}
