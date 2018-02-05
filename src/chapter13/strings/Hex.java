package chapter13.strings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Hex {
	private static FileInputStream fis;
	private static StringBuilder readSb = new StringBuilder();

	public static String format(byte[] data) {
		StringBuilder sb = new StringBuilder();
		int n = 0;
		for (byte b : data) {
			if (n % 16 == 0)
				sb.append(String.format("%05x: ", n));
			sb.append(String.format("%02x ", b));
			n++;
			if (n % 16 == 0)
				sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	public static void main(String[] args) {		
		writeFile();
		readFile();
	}

	private static void readFile() {
		try {
			fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\1.txt");

			byte[] buffer = new byte[1024];
			int len;
			while ((len = fis.read(buffer)) > 0) {
				readSb.append(new String(buffer, 0, len,"utf8"));
			}
			System.out.println(format(readSb.toString().getBytes()));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void writeFile() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\1.txt");
			int x = 0xE8;
			int y = 0xAE;
			int z = 0xB8;
			fos.write(x);
			fos.write(y);
			fos.write(z);
			System.out.println("ok");
		}catch (Exception e) {
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
