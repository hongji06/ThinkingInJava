package tutorials.jenkov.com.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLDemo {
	public static void main(String[] args) throws IOException {
		URL url = new URL("https://www.baidu.com");

		URLConnection urlConnection = url.openConnection();
		InputStream input = urlConnection.getInputStream();

		// send an HTTP POST
		// urlConnection.setDoOutput(true);
		// OutputStream output = urlConnection.getOutputStream();

		int data = input.read();
		while (data != -1) {
			System.out.print((char) data);
			data = input.read();
		}
		input.close();
	}
}
