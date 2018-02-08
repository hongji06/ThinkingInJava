package tutorials.jenkov.com.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
	public static void main(String[] args) throws Exception {
		RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\temp\\FileChannelDemo.class", "rw");
		FileChannel channel = aFile.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(48);
		
		ByteBuffer header = ByteBuffer.allocate(128);
		ByteBuffer body   = ByteBuffer.allocate(1024);

		ByteBuffer[] bufferArray = { header, body };

		channel.read(bufferArray);
		System.out.println("================================================================");
		header.flip();
		while(header.hasRemaining()) {
			System.out.print((char)header.get());
		}
		System.out.println("\n================================================================");
		body.flip();
		while(body.hasRemaining()) {
			System.out.print((char)body.get());
		}
		System.out.println("\n================================================================");
		int byteRead = channel.read(buffer);
		while(byteRead!=-1) {
			System.out.println("Read:"+byteRead);
			//The flip() method switches a Buffer from writing mode to reading mode. 
			//Calling flip() sets the position back to 0, and sets the limit to where position just was.
			buffer.flip();
			while(buffer.hasRemaining()) {
				System.out.print((char)buffer.get());
			}
			buffer.clear();
			byteRead = channel.read(buffer);
		}
		
		aFile.close();
	}
}
