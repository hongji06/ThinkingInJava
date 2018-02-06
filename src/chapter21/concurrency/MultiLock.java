/**   
 * @{#} MultiLock.java by Hongji.Xu 2018年2月5日 下午3:05:53 
 * @Title:  MultiLock.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package chapter21.concurrency;

public class MultiLock {
    private String lock = "ok";

    public void f1(int count) {
        synchronized (lock) {
            if (count-- > 0) {
                System.out.println(Thread.currentThread().getName()+" f1() calling f2() with count " + count);
                f2(count);
            }
        }
    }

    public void f2(int count) {
        synchronized (lock) {
            if (count-- > 0) {
                System.out.println(Thread.currentThread().getName()+" f2() calling f1() with count " + count);
                f1(count);
            }
        }
    }

    public static void main(String[] args) {
        MultiLock multiLock = new MultiLock();
        new Thread() {
            @Override
            public void run() {
                multiLock.f1(10);
            }
        }.start();        
    }
}
