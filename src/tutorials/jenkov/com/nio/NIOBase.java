package tutorials.jenkov.com.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class NIOBase {
	// 处理读取客户端发来的信息 的事件
	public void read(SelectionKey key) throws IOException {
		// 服务器可读取消息:得到事件发生的Socket通道
		try (SocketChannel channel = (SocketChannel) key.channel()) {
			// 创建读取的缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int len = 0;
			len = channel.read(buffer);
			while (len != -1) {
				buffer.flip();
				System.out.print(new String(buffer.array()));
				buffer.clear();
				len = channel.read(buffer);
			}
			ByteBuffer message = ByteBuffer.wrap("recived from client".getBytes());
			channel.write(message);
		}
	}
}
