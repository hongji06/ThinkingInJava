package tutorials.jenkov.com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO服务端
 * 
 * @author 11H0075
 *
 */
public class NIOServer {
	// 通道管理器
	private Selector selector;

	/**
	 * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
	 * 
	 * @param port
	 * @throws IOException
	 */
	public void initServer(int port) throws IOException {
		// 获得一个ServerSocket通道
		ServerSocketChannel serverChanel = ServerSocketChannel.open();
		// 设置通道为非阻塞
		serverChanel.configureBlocking(false);
		// 将该通道对应的ServerSocket绑定到port端口
		serverChanel.socket().bind(new InetSocketAddress(port));
		// 获得一个通道管理器
		selector = Selector.open();
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
		// 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
		serverChanel.register(selector, SelectionKey.OP_ACCEPT);
	}

	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * 
	 * @throws IOException
	 */
	public void listen() throws IOException {
		System.out.println("服务端启动成功！");
		// 轮询访问selector
		while (true) {
			// 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
			selector.select();
			// 获得selector中选中的项的迭代器，选中的项为注册的事件
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				// 删除已选的key,以防重复处理
				iterator.remove();
				// 客户端请求连接事件
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					// 获得和客户端连接的通道
					SocketChannel channel = server.accept();
					// 设置成非阻塞
					channel.configureBlocking(false);
					// 在这里可以给客户端发送信息哦
					channel.write(ByteBuffer.wrap(new String("send message to client").getBytes()));
					// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
					channel.register(selector, SelectionKey.OP_READ);

				} else if (key.isReadable()) {
					// 获得了可读的事件
					read(key);
				}
			}

		}
	}

	// 处理读取客户端发来的信息 的事件
	private void read(SelectionKey key) throws IOException {
		// 服务器可读取消息:得到事件发生的Socket通道
		SocketChannel channel = (SocketChannel) key.channel();
		// 创建读取的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);  
        byte[] data = buffer.array();  
        String msg = new String(data).trim();  
        System.out.println("server receive from client: " + msg);  
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());  
        channel.write(outBuffer);  
	}

	// 启动服务
	public static void main(String[] args) throws IOException {
		NIOServer nioServer = new NIOServer();
		nioServer.initServer(8000);
		nioServer.listen();
	}

}
