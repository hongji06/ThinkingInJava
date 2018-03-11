package tutorials.jenkov.com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {

	// 通道管理器
	private Selector selector;

	/**
	 * 获得一个Socket通道，并对该通道做一些初始化的工作
	 * 
	 * @param ip
	 * @param port
	 * @throws IOException
	 */
	public void initClient(String ip, int port) throws IOException {
		// 获得一个Socket通道
		SocketChannel sc = SocketChannel.open();
		// 设置通道为非阻塞
		sc.configureBlocking(false);
		// 获得一个通道管理器
		selector = Selector.open();
		// 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调用channel.finishConnect();才能完成连接
		sc.connect(new InetSocketAddress(ip, port));
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
		sc.register(selector, SelectionKey.OP_CONNECT);
	}

	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * 
	 * @throws IOException
	 */
	public void listen() throws IOException {
		while (true) {
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				// 连接事件发生
				if (key.isConnectable()) {
					SocketChannel sc = (SocketChannel) key.channel();
					// 如果正在连接，则完成连接
					if (sc.isConnectionPending()) {
						sc.finishConnect();
					}
					// 设置成非阻塞 
					sc.configureBlocking(false);
					// 在这里可以给服务端发送信息
					sc.write(ByteBuffer.wrap("write something to server".getBytes()));
					// 在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
					sc.register(selector, SelectionKey.OP_READ);
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
        System.out.println("client receive msg from server: " + msg);
		// ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
		// channel.write(outBuffer);
	};

	public static void main(String[] args) throws IOException {
		NIOClient client = new NIOClient();
		client.initClient("localhost", 8000);
		client.listen();
		System.out.println("finished.");

	}

}
