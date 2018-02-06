/**   
 * @{#} MainThread.java by Hongji.Xu 2018年2月4日 下午3:26:00 
 * @Title:  MainThread.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */   
package chapter21.concurrency;

  
public class MainThread {

    public static void main(String[] args) {
        LiftOff liftOff = new LiftOff();
        liftOff.run();
    }

}
