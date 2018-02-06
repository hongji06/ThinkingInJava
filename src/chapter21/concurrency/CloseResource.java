/**   
 * @{#} CloseResource.java by Hongji.Xu 2018年2月5日 下午2:32:42 
 * @Title:  CloseResource.java   
 * @Package chapter21.concurrency   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */   
package chapter21.concurrency;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CloseResource {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = new Socket("localhost",8080);
        InputStream socketInput = socket.getInputStream();
        exec.execute(new IOBlocked(socketInput));
        exec.execute(new IOBlocked(System.in));
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Shutting down all threads");
        exec.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing "+socketInput.getClass().getSimpleName());
        socketInput.close();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing "+System.in.getClass().getSimpleName());
        socket.close();
        serverSocket.close();
    }
}
