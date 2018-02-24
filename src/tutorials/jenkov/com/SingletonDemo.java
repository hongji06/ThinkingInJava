/**   
 * @{#} SingletonDemo.java by Hongji.Xu 2018年2月13日 下午2:11:17 
 * @Title:  SingletonDemo.java   
 * @Package tutorials.jenkov.com   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package tutorials.jenkov.com;

import java.util.concurrent.TimeUnit;

public class SingletonDemo {

    private volatile static SingletonDemo instance;

    private SingletonDemo() {
    }

    public static SingletonDemo getInstance() {
        if (instance == null) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread() {
                public void run() {
                    System.out.println(SingletonDemo.getInstance().hashCode());
                }
            }.start();
        }
    }

}
