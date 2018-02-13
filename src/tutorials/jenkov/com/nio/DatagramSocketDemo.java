package tutorials.jenkov.com.nio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class DatagramSocketDemo {

	public static void main(String[] args) {

		// 建立线程用于发送报文
		new Thread() {
			public void run() {
				for (int i = 0; i < 3; i++) {
					try {
						System.out.println(Thread.currentThread().getName() + " datagram sending...");
						byte[] buffer = "0123456789".getBytes();
						InetAddress address = InetAddress.getLocalHost();
						DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 9000);
						DatagramSocket datagram = new DatagramSocket();
						datagram.send(packet);
						datagram.close();
						System.out.println(Thread.currentThread().getName() + " datagram sent over");
					} catch (IOException e) {
						e.printStackTrace();
					}
					// 发送一次完成后间隔一段时间
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		}.start();

		// 建立线程用于发送报文
		new Thread() {
			public void run() {
				for (int i = 0; i < 3; i++) {
					try {
						System.out.println(Thread.currentThread().getName() + " datagram receiving...");
						DatagramSocket datagramSocket = new DatagramSocket(9000);
						byte[] buffer = new byte[10];
						DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
						datagramSocket.receive(packet);
						datagramSocket.close();
						byte[] receiveBuffer = packet.getData();
						System.out.println(
								Thread.currentThread().getName() + " datagram received: " + new String(receiveBuffer));
					} catch (IOException e) {
						e.printStackTrace();
					}
					// 接收一次完成后间隔一段时间
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			};
		}.start();

	}

}
