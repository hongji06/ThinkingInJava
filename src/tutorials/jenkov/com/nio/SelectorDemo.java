/**   
 * @{#} SelectorDemo.java by Hongji.Xu 2018年2月10日 上午10:48:11 
 * @Title:  SelectorDemo.java   
 * @Package tutorials.jenkov.com.nio   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */
package tutorials.jenkov.com.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

@SuppressWarnings("unused")
public class SelectorDemo {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannell = new ServerSocket(9090).getChannel();

        Socket socket = new Socket("localhost", 9090);
        SocketChannel socketChannel = socket.getChannel();
        socketChannel.configureBlocking(false);//no blocking
        
        // SocketChannel channel2 = SocketChannel.open();
        // channel2.connect(new InetSocketAddress("localhost", 9090));

        socketChannel.configureBlocking(false);
        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);

        // 关闭通道
        socketChannel.close();
        serverSocketChannell.close();

        // 关闭socket
        socket.close();

        final int READ = 1 << 0;
        final int WRITE = 1 << 2;
        final int CONNET = 1 << 3;
        final int ACCEPT = 1 << 4;

        System.out.println(String.format("read=%d;write=%d;connet=%d;accept=%d", READ, WRITE, CONNET, ACCEPT));
        System.out.println(String.format("read|write=%d", READ | WRITE));
    }

}
