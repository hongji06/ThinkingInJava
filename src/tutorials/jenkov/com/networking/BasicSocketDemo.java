/**   
 * @{#} BasicSocketDemo.java by Hongji.Xu 2018年2月13日 上午9:53:37 
 * @Title:  BasicSocketDemo.java   
 * @Package tutorials.jenkov.com.networking   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package tutorials.jenkov.com.networking;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

class ConnServer implements Runnable {
    @Override
    public void run() {
        // 鏈接的Server端
        Socket socket = new Socket();
        InputStream inputStream = null;
        try {
            socket.connect(new InetSocketAddress("localhost", 9000), 5000);
            inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int readLen = 0;
            readLen = inputStream.read(buffer);
            while (readLen != -1) {
                System.out.print(new String(buffer));
                readLen = inputStream.read(buffer);
            }
            System.out.println("read finished.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BasicSocketDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(new ConnServer()) {}.start(); 
        }
    }

}
